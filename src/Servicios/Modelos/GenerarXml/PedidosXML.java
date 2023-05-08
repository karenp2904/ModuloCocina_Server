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
import java.util.Arrays;
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

    public  ArrayList<String> buscarComidasPorNombre(String nombre) {
        ArrayList<String> resultados = new ArrayList<>();
        String[] menu = {"Papas Locas", "Perro XL", "Perro S", "Perrito", "Salchipapa", "Choripapa"};
        double umbral = 0.5;
        for (String plato : menu) {
            String[] palabras = plato.split("\\s+");
            double similitudMaxima = 0;
            for (String palabra : palabras) {
                double similitud = jaroWinkler(nombre.replaceAll("\\d", ""), palabra);
                if (similitud > similitudMaxima) {
                    similitudMaxima = similitud;
                }
            }
            if (similitudMaxima >= umbral) {
                resultados.add(plato);
            }
        }
        return resultados;
    }

    private double jaroWinkler(String s1, String s2) {
        int s1_len = s1.length();
        int s2_len = s2.length();

        if (s1_len == 0 && s2_len == 0) return 1; // Caso de cadenas vacías
        if (s1_len == 0 || s2_len == 0) return 0; // Si alguna cadena está vacía, no hay similitud

        int match_distance = Math.max(s1_len, s2_len) / 2 - 1;

        boolean[] s1_matches = new boolean[s1_len];
        Arrays.fill(s1_matches, false);

        boolean[] s2_matches = new boolean[s2_len];
        Arrays.fill(s2_matches, false);

        int matches = 0;
        int transpositions = 0;

        // Busca caracteres coincidentes
        for (int i = 0; i < s1_len; i++) {
            int start = Math.max(0, i - match_distance);
            int end = Math.min(i + match_distance + 1, s2_len);

            for (int j = start; j < end; j++) {
                if (s2_matches[j]) continue;
                if (s1.charAt(i) != s2.charAt(j)) continue;
                s1_matches[i] = true;
                s2_matches[j] = true;
                matches++;
                break;
            }
        }

        if (matches == 0) return 0;

        // Busca transposiciones
        int k = 0;
        for (int i = 0; i < s1_len; i++) {
            if (!s1_matches[i]) continue;
            while (!s2_matches[k]) k++;
            if (s1.charAt(i) != s2.charAt(k)) transpositions++;
            k++;
        }

        double similitud = ((double) matches / s1_len + (double) matches / s2_len + (double) (matches - transpositions / 2) / matches) / 3;
        int prefijo_comun = 0;
        while (prefijo_comun < 4 && prefijo_comun < Math.min(s1_len, s2_len) && s1.charAt(prefijo_comun) == s2.charAt(prefijo_comun)) {
            prefijo_comun++;
        }
        return similitud + prefijo_comun * 0.1 * (1 - similitud);
    }

/*
    public ArrayList<Pedido> buscarComidasPorNombre(String nombre) {
        ArrayList<Pedido> resultados = new ArrayList<>();
        Element root = doc.getDocumentElement();
        NodeList comidas = root.getElementsByTagName("Comida");
        int umbral = 3;
        for (int i = 0; i < comidas.getLength(); i++) {
            Element comida = (Element) comidas.item(i);
            Element nombreElement = (Element) comida.getElementsByTagName("Nombre").item(0);
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
                Element tiempoElement = (Element) comida.getElementsByTagName("Tiempo").item(0);
                Element precioElement = (Element) comida.getElementsByTagName("Codigo").item(0);
                String codigo =precioElement.getTextContent();
                Element cantidadElement = (Element) comida.getElementsByTagName("Cantidad").item(0);
                int cantidad = Integer.parseInt(cantidadElement.getTextContent());
                Pedido resultado = new Pedido(nombreComida,codigo, String.valueOf(cantidad));
                resultados.add(resultado);
            }
        }
        return resultados;
    }


 */
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
