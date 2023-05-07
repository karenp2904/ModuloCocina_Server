package Servicios.Modelos.GenerarXml;

import Dominio.Usuario;
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

public class UsuariosXML {
    private Document doc;

    public UsuariosXML(File file) throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        try {
            doc = builder.parse(file);
        } catch (Exception e) {
            doc = builder.newDocument();
            Element root = doc.createElement("Usuarios");
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
    public void agregarUsuario(Usuario usuario) {
        Element elementoUsuario = doc.createElement("Usuario");
        elementoUsuario.setAttribute("Nombre", usuario.getNombre());
        elementoUsuario.setAttribute("ID", usuario.getId());
        elementoUsuario.setAttribute("Contraseña", usuario.getContraseña());

        Node root = doc.getDocumentElement();
        root.appendChild(elementoUsuario);
    }

    public Usuario buscarUsuarioPorId(String id) {
        NodeList usuarios = doc.getElementsByTagName("Usuario");
        for (int i = 0; i < usuarios.getLength(); i++) {
            Element elementoUsuario = (Element) usuarios.item(i);
            String idUsuario = elementoUsuario.getAttribute("ID");
            if (idUsuario.equals(id)) {
                String nombre = elementoUsuario.getAttribute("Nombre");
                String contraseña = elementoUsuario.getAttribute("Contraseña");
                return new Usuario(nombre, id, contraseña);
            }
        }
        return null;
    }

    public boolean existeUsuarioPorId(String id) {
        NodeList usuarios = doc.getElementsByTagName("Usuario");
        for (int i = 0; i < usuarios.getLength(); i++) {
            Element elementoUsuario = (Element) usuarios.item(i);
            String idUsuario = elementoUsuario.getAttribute("ID");
            if (idUsuario.equals(id)) {
                return true;
            }
        }
        return false;
    }
    public void eliminarUsuario(String id) {
        NodeList usuarios = doc.getElementsByTagName("Usuario");
        for (int i = 0; i < usuarios.getLength(); i++) {
            Element elementoUsuario = (Element) usuarios.item(i);
            String idUsuario = elementoUsuario.getAttribute("ID");
            if (idUsuario.equals(id)) {
                Node root = doc.getDocumentElement();
                root.removeChild(elementoUsuario);
                break;
            }
        }
    }
}
