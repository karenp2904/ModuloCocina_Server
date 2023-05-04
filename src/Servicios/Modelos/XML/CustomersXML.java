package Servicios.Modelos.XML;

import java.io.File;
import java.io.Serializable;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import Dominio.Cliente;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CustomersXML implements Serializable {
    private Document doc;

    public CustomersXML(File file) throws ParserConfigurationException{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        try {
            doc = builder.parse(file);
        } catch (Exception e) {
            doc = builder.newDocument();
            Element root = doc.createElement("Clientes");
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
    public void agregarCliente(Cliente cliente) {
        Element elementoCliente = doc.createElement("Cliente");
        elementoCliente.setAttribute("Nombre", cliente.getNombreCliente());
        elementoCliente.setAttribute("Direccion", cliente.getDireccionCliente());
        elementoCliente.setAttribute("Telefono", cliente.getTelefono());
        elementoCliente.setAttribute("TipoCuenta", cliente.getTipoCuenta());

        Node root = doc.getDocumentElement();
        root.appendChild(elementoCliente);
    }
    public void agregarCliente(String nombreCliente, String direccionCliente, String telefono, String tipoCuenta) {
        Element cliente = doc.createElement("Cliente");
        cliente.setAttribute("Nombre", nombreCliente);
        cliente.setAttribute("Direccion", direccionCliente);
        cliente.setAttribute("Telefono", telefono);
        cliente.setAttribute("TipoCuenta", tipoCuenta);

        Node root = doc.getDocumentElement();
        root.appendChild(cliente);
    }

    public boolean existeCliente(String telefono) {
        NodeList clientes = doc.getElementsByTagName("Cliente");
        for (int i = 0; i < clientes.getLength(); i++) {
            Node cliente = clientes.item(i);
            if (cliente.getNodeType() == Node.ELEMENT_NODE) {
                Element elementoCliente = (Element) cliente;
                String telefonoCliente = elementoCliente.getAttribute("Telefono");
                if (telefonoCliente.equals(telefono)) {
                    return true;
                }
            }
        }
        return false;
    }
    public Cliente buscarClientePorTelefono(String telefono) {
        NodeList clientes = doc.getElementsByTagName("Cliente");
        for (int i = 0; i < clientes.getLength(); i++) {
            Node cliente = clientes.item(i);
            if (cliente.getNodeType() == Node.ELEMENT_NODE) {
                Element elementoCliente = (Element) cliente;
                String telefonoCliente = elementoCliente.getAttribute("Telefono");
                if (telefonoCliente.equals(telefono)) {
                    String nombreCliente = elementoCliente.getAttribute("Nombre");
                    String direccionCliente = elementoCliente.getAttribute("Direccion");
                    String tipoCuenta = elementoCliente.getAttribute("TipoCuenta");
                    return new Cliente(nombreCliente, direccionCliente, telefono, tipoCuenta);
                }
            }
        }
        return null;
    }
}
