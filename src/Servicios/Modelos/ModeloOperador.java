package Servicios.Modelos;

import Dominio.Factura;
import Estructuras.Colas.ColasArray;
import Dominio.Cliente;
import Dominio.Pedido;
import Estructuras.ListasEnlaceDoble.LinkedList;
import Servicios.Controladores.IControllerOperador;
import Servicios.Modelos.GenerarXml.CustomersXML;
import Servicios.Modelos.GenerarXml.FacturasXML;
import Servicios.Modelos.GenerarXml.PedidosXML;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ModeloOperador implements IControllerOperador, Serializable {

    CustomersXML archivoCliente=new CustomersXML(new File("src/Servicios/Modelos/XML/clientes.xml"));
    FacturasXML archivoFacturasXML =new FacturasXML(new File("src/Servicios/Modelos/XML/factura.xml"));

    PedidosXML archivoPedido=new PedidosXML(new File("src/Servicios/Modelos/XML/pedidos.xml"));
   Cliente cliente;
   Pedido pedido;


   // UsuariosXML archivoOperador =new UsuariosXML(new File("src/Servicios/Modelos/XML/usuariosOperador.xml"));

    public ModeloOperador() throws ParserConfigurationException {
    }

    @Override
    public boolean validarUsuario(String nombre, String contraseña) {
        if(nombre!=null){
            return true;
        }else{
            System.out.println("Incorrecto");
            return false;
        }
    }

    @Override
    public boolean registrarCliente(String nombre, String direccion, String telefono, String tipoDeCuenta) {
        try {
            cliente=new Cliente(nombre,direccion,telefono,tipoDeCuenta);
            if(nombre!=null){
                archivoCliente.agregarCliente(nombre,direccion,telefono,tipoDeCuenta);
                archivoCliente.saveToFile(new File("src/Servicios/Modelos/XML/clientes.xml"));
                return true;
            }else{
                return false;
            }
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean actualizarCliente(String nombre, String direccion, String telefono, String tipoDeCuenta) {
        try {
            if(nombre!=null){
                return true;
            }
            cliente=new Cliente(nombre,direccion,telefono,tipoDeCuenta);
            archivoCliente.agregarCliente(nombre,direccion,telefono,tipoDeCuenta);
            archivoCliente.saveToFile(new File("src/Servicios/Modelos/XML/clientes.xml"));
            return true;
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean ingresarPedido(String producto, String codigo, String cantidad) {
        try {
            if(producto!=null || codigo!=null){
                pedido=new Pedido(producto,codigo,cantidad);
                archivoPedido.agregarPedido(new Pedido(producto,codigo,cantidad));
                archivoPedido.saveToFile(new File("src/Servicios/Modelos/XML/pedidos.xml"));
                generarFactura(pedido,cliente);
                return true;
            }else{
                return false;
            }
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean actualizarPedido(String producto, String codigo, String cantidad) {
        try {
            if(producto==null){
                return true;
            }else{
                if(producto!=null || codigo!=null) {
                    pedido = new Pedido(producto, codigo, cantidad);
                    archivoPedido.agregarPedido(new Pedido(producto, codigo, cantidad));
                    archivoPedido.saveToFile(new File("src/Servicios/Modelos/XML/pedidos.xml"));
                    generarFactura(pedido, cliente);
                }
            }
            return true;
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public ColasArray pedidosFrecuentesCliente(String telefono) {
        Map<String, Integer> pedidos = archivoFacturasXML.getCantidadPedidosPorProductoOrdenado(telefono);
        System.out.println("Pedidos frecuentes");
        System.out.println(pedidos.toString());
        ColasArray colasArray = new ColasArray();
        for (Map.Entry<String, Integer> entry : pedidos.entrySet()) {
            String producto = entry.getKey();
            int cantidad = entry.getValue();
            String result = producto + "     --------------------      " + cantidad + "\n";
            colasArray.enqueue(result);
        }
        System.out.println("cola" + colasArray.toString());
        return colasArray;
           /*
            Map<String, Integer> pedidos = archivoFacturasXML.getCantidadPedidosPorProductoOrdenado(telefono);

            System.out.println("Pedidos frecuentes");
            System.out.println(pedidos.toString());
            String result = pedidos.entrySet().stream()
                    .map(entry -> entry.getKey() + "     --------------------      " + entry.getValue() + "\n")
                    .collect(Collectors.joining());
            ColasArray colasArray = new ColasArray();
            colasArray.enqueue(result);
            System.out.println("cola" + colasArray.toString());
            return colasArray;

            */

    }

    @Override
    public ColasArray busquedaPedido(String pedidoABuscar) {
       ArrayList lista=archivoPedido.buscarComidasPorNombre(pedidoABuscar);
       ColasArray colasArray=new ColasArray();

        for (int i = 0; i < lista.size(); i++) {
            System.out.println("palabra error "+lista.get(i));
            colasArray.enqueue(lista.get(i));
        }

        if(colasArray==null) {
            colasArray.enqueue("No hay coincidencias");
            System.out.println("La cola de array");
            return colasArray;
        }
        return colasArray;
    }

    @Override
    public ColasArray busquedaCliente(String clienteTelefonoABuscar) {

        if(archivoCliente.existeCliente(clienteTelefonoABuscar)){
            ColasArray colasArray=new ColasArray();
            colasArray.enqueue(cliente.getNombreCliente());
            colasArray.enqueue(cliente.getDireccionCliente());
            colasArray.enqueue(cliente.getTelefono());
            colasArray.enqueue(cliente.getTipoCuenta());
            return colasArray;
        }
        return null;
    }

    int numFact=0;
    @Override
    public boolean generarFactura(Pedido pedido, Cliente cliente) {
        try {
          //  System.out.println("el pedido es " + pedido.getProductoNombre()+ "el cliente"+ cliente.getNombreCliente());
            if(pedido!=null&&cliente!=null){
                numFact++;
                archivoFacturasXML.agregarFactura(new Factura(pedido,cliente,String.valueOf(numFact)));
                archivoFacturasXML.saveToFile(new File("src/Servicios/Modelos/XML/factura.xml"));
                return true;
            }else{
                return false;
            }

        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean clienteExistente(String telefono) {
        cliente=archivoCliente.buscarClientePorTelefono(telefono);
        return archivoCliente.existeCliente(telefono);
    }

    public LinkedList<Factura> obtenerFacturas(){
        try {
           // System.out.println(archivoFacturasXML.leerArchivoXML("src/Servicios/Modelos/XML/factura.xml").toString());
            return archivoFacturasXML.leerArchivoXML("src/Servicios/Modelos/XML/factura.xml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    


}
