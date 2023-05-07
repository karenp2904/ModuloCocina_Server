package Servicios.Modelos.GenerarXml;

import Dominio.Pedido;
import Estructuras.ListasEnlaceDoble.LinkedList;
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
import java.util.ArrayList;
import java.util.List;

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

    public LinkedList<Pedido> buscarPedidosPorNombre(String nombre) {
        List<Pedido> pedidosEncontrados = new ArrayList<>();
        LinkedList<Pedido> pedidosLista=new LinkedList<>();
        NodeList pedidos = doc.getElementsByTagName("Pedido");
        for (int i = 0; i < pedidos.getLength(); i++) {
            Element elementoPedido = (Element) pedidos.item(i);
            String nombrePedido = elementoPedido.getAttribute("ProductoNombre");
            if (nombrePedido.contains(nombre)) {
                String codigo = elementoPedido.getAttribute("Codigo");
                String cantidad = elementoPedido.getAttribute("Cantidad");
                Pedido pedido = new Pedido(nombrePedido, codigo, cantidad);
                pedidosEncontrados.add(pedido);
                pedidosLista.add(pedido);
            }
        }
        return pedidosLista;
    }
    public ArrayList buscarComidasPorNombre(String nombre) {
        ArrayList resultados = new ArrayList<>();
        Element root = doc.getDocumentElement();
        NodeList comidas = root.getElementsByTagName("Comida");
        int umbral = 3;
        for (int i = 0; i < comidas.getLength(); i++) {
            Element comida = (Element) comidas.item(i);
            Element nombreElement = (Element) comida.getElementsByTagName("ProductoNombre").item(0);
            String nombreComida = nombreElement.getTextContent();
            String[] palabras = nombreComida.split("\\s+");
            int distanciaMinima = Integer.MAX_VALUE;
            for (String palabra : palabras) {
                int distancia = levenshtein(nombre.replaceAll("\\d", ""), palabra);
                if (distancia < distanciaMinima) {
                    distanciaMinima = distancia;
                }
            }
            if (distanciaMinima <= umbral) {
               // Pedido.Time tiempo = Food.Time.valueOf(tiempoElement.getTextContent());
                Element precioElement = (Element) comida.getElementsByTagName("Codigo").item(0);
                String codigo =precioElement.getTextContent();
                Element cantidadElement = (Element) comida.getElementsByTagName("Cantidad").item(0);
                String cantidad = cantidadElement.getTextContent();
                Pedido resultado = new Pedido(nombreComida,codigo,cantidad);
                resultados.add(nombreComida);
            }
        }
        return resultados;
    }

    private int levenshtein(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
                }
            }
        }
        return dp[m][n];
    }
}
