package Servicios.Modelos.XML;

import Dominio.Cliente;
import Dominio.Factura;
import Dominio.Pedido;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

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
}