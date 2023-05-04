package Servicios.Modelos.XML;

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

public class PedidosXML {
    private Document doc;

    public PedidosXML(File file) throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        try {
            doc = builder.parse(file);
        } catch (Exception e) {
            doc = builder.newDocument();
            Element root = doc.createElement("Pedidos");
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

    public void agregarPedido(Pedido pedido) {
        Element elementoPedido = doc.createElement("Pedido");
        elementoPedido.setAttribute("ProductoNombre", pedido.getProductoNombre());
        elementoPedido.setAttribute("Codigo", pedido.getCodigo());
        elementoPedido.setAttribute("Cantidad", pedido.getCantidad());

        Node root = doc.getDocumentElement();
        root.appendChild(elementoPedido);
    }

    public void eliminarPedidoPorNombre(String nombre) {
        NodeList pedidos = doc.getElementsByTagName("Pedido");
        for (int i = 0; i < pedidos.getLength(); i++) {
            Element elementoPedido = (Element) pedidos.item(i);
            String nombrePedido = elementoPedido.getAttribute("ProductoNombre");
            if (nombrePedido.equals(nombre)) {
                Node root = doc.getDocumentElement();
                root.removeChild(elementoPedido);
                break;
            }
        }
    }

    public boolean existePedidoPorNombre(String nombre) {
        NodeList pedidos = doc.getElementsByTagName("Pedido");
        for (int i = 0; i < pedidos.getLength(); i++) {
            Element elementoPedido = (Element) pedidos.item(i);
            String nombrePedido = elementoPedido.getAttribute("ProductoNombre");
            if (nombrePedido.equals(nombre)) {
                return true;
            }
        }
        return false;
    }

    public Pedido buscarPedidoPorNombre(String nombre) {
        NodeList pedidos = doc.getElementsByTagName("Pedido");
        for (int i = 0; i < pedidos.getLength(); i++) {
            Element elementoPedido = (Element) pedidos.item(i);
            String nombrePedido = elementoPedido.getAttribute("ProductoNombre");
            if (nombrePedido.equals(nombre)) {
                String codigo = elementoPedido.getAttribute("Codigo");
                String cantidad = elementoPedido.getAttribute("Cantidad");
                return new Pedido(nombre, codigo, cantidad);
            }
        }
        return null;
    }
}
