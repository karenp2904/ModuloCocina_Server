package Servicios.Modelos.GenerarXml;

import Dominio.Cliente;
import Dominio.Factura;
import Dominio.Pedido;
import Estructuras.ListasEnlaceDoble.LinkedList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FacturasXML {
    private Document doc;

    public FacturasXML(File file) throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        try {
            doc = builder.parse(file);
        } catch (Exception e) {
            doc = builder.newDocument();
            Element root = doc.createElement("Facturas");
            doc.appendChild(root);
        }
    }

    public void saveToFile(File file) throws TransformerException {
        // Crear la fuente de datos y el resultado de la transformación
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(file);

        // Configurar la transformación para que el resultado tenga formato
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        // Realizar la transformación y guardar el resultado en el archivo
        transformer.transform(source, result);
    }

    public Factura buscarFacturaPorId(String id) {
        Element factura = (Element) doc.getElementsByTagName("Factura").item(0);
        String idFactura = factura.getAttribute("ID");
        if (idFactura.equals(id)) {
            String nombreCliente = factura.getAttribute("NombreCliente");
            String direccionCliente = factura.getAttribute("DireccionCliente");
            String telefono = factura.getAttribute("Telefono");
            String tipoCuenta = factura.getAttribute("TipoCuenta");

            Element pedido = (Element) factura.getElementsByTagName("Pedido").item(0);
            String productoNombre = pedido.getAttribute("ProductoNombre");
            String codigo = pedido.getAttribute("Codigo");
            String cantidad = pedido.getAttribute("Cantidad");

            return new Factura(new Pedido(productoNombre, codigo, cantidad), new Cliente(nombreCliente, direccionCliente, telefono, tipoCuenta), id);
        }
        return null;
    }

    public boolean existeFacturaPorId(String id) {
        Element factura = (Element) doc.getElementsByTagName("Factura").item(0);
        String idFactura = factura.getAttribute("ID");
        if (idFactura.equals(id)) {
            return true;
        }
        return false;
    }

    public void agregarFactura(Factura factura) {
        Element elementoFactura = doc.createElement("Factura");
        elementoFactura.setAttribute("ID", factura.getId());
        elementoFactura.setAttribute("NombreCliente", factura.getCliente().getNombreCliente());
        elementoFactura.setAttribute("DireccionCliente", factura.getCliente().getDireccionCliente());
        elementoFactura.setAttribute("Telefono", factura.getCliente().getTelefono());
        elementoFactura.setAttribute("TipoCuenta", factura.getCliente().getTipoCuenta());

        Pedido pedido = factura.getPedido();
        Element elementoPedido = doc.createElement("Pedido");
        elementoPedido.setAttribute("ProductoNombre", pedido.getProductoNombre());
        elementoPedido.setAttribute("Codigo", pedido.getCodigo());
        elementoPedido.setAttribute("Cantidad", pedido.getCantidad());

        elementoFactura.appendChild(elementoPedido);

        Node root = doc.getDocumentElement();
        root.appendChild(elementoFactura);
    }

    public LinkedList<Factura> leerArchivoXML(String nombreArchivo) throws IOException {
        LinkedList<Factura> listaContenido = new LinkedList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            doc = builder.parse(new File(nombreArchivo));
            NodeList facturaNodes = doc.getElementsByTagName("Factura");
            for (int i = 0; i < facturaNodes.getLength(); i++) {
                Element factura = (Element) facturaNodes.item(i);
                String idFactura = factura.getAttribute("ID");
                String nombreCliente = factura.getAttribute("NombreCliente");
                String direccionCliente = factura.getAttribute("DireccionCliente");
                String telefono = factura.getAttribute("Telefono");
                String tipoCuenta = factura.getAttribute("TipoCuenta");

                Element pedido = (Element) factura.getElementsByTagName("Pedido").item(0);
                String productoNombre = pedido.getAttribute("ProductoNombre");
                String codigo = pedido.getAttribute("Codigo");
                String cantidad = pedido.getAttribute("Cantidad");

                listaContenido.add(new Factura(new Pedido(productoNombre, codigo, cantidad), new Cliente(nombreCliente, direccionCliente, telefono, tipoCuenta), idFactura));
            }
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
        return listaContenido;
    }

    public LinkedList getCantidadPedidosPorProductoOrdenado(String telefonoo) {
        Map<String, Integer> cantidadPedidosPorProducto = new HashMap<>();
        LinkedList lista=new LinkedList<>();
        NodeList facturaNodes = doc.getElementsByTagName("Factura");
        for (int i = 0; i < facturaNodes.getLength(); i++) {
            Element factura = (Element) facturaNodes.item(i);
            String telefonoCliente = factura.getAttribute("Telefono");
            if (telefonoCliente.equals(telefonoo)) {
                Element pedido = (Element) factura.getElementsByTagName("Pedido").item(0);
                String productoNombre = pedido.getAttribute("ProductoNombre");
                String cantidadString = pedido.getAttribute("Cantidad");
                int cantidad = Integer.parseInt(cantidadString);
                if (cantidadPedidosPorProducto.containsKey(productoNombre)) {
                    cantidad += cantidadPedidosPorProducto.get(productoNombre);
                }
                cantidadPedidosPorProducto.put(productoNombre, cantidad);
                lista.add(productoNombre);

            }
        }
        Map<String, Integer> cantidadPedidosPorProductoOrdenado = new LinkedHashMap<>();
        cantidadPedidosPorProducto.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEachOrdered(entry -> cantidadPedidosPorProductoOrdenado.put(entry.getKey(), entry.getValue()));

        return lista;

    }
}