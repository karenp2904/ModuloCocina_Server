package Servicios.Modelos;

import Estructuras.Colas.ColasArray;
import Dominio.Cliente;
import Dominio.Factura;
import Dominio.Pedido;
import Servicios.Controladores.IControllerOperador;
import Servicios.Modelos.XML.CustomersXML;
import Servicios.Modelos.XML.PedidosXML;
import Servicios.Modelos.XML.UsuariosXML;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.Serializable;

public class ModeloOperador implements IControllerOperador, Serializable {

    CustomersXML archivoCliente=new CustomersXML(new File("src/Servicios/Modelos/XML/clientes.xml"));
    UsuariosXML archivoUserOperador =new UsuariosXML(new File("src/Servicios/Modelos/XML/usuariosOperador.xml"));
    PedidosXML archivoPedido=new PedidosXML(new File("src/Servicios/Modelos/XML/pedidos.xml"));

    public ModeloOperador() throws ParserConfigurationException {
    }

    @Override
    public boolean validarUsuario( String nombre, String contraseña) {
        return  archivoUserOperador.existeUsuarioPorId(nombre);
    }

    @Override
    public boolean registrarCliente(String nombre, String direccion, String telefono, String tipoDeCuenta) {
        archivoCliente.agregarCliente(nombre,direccion,telefono,tipoDeCuenta);
        try {
            archivoCliente.saveToFile(new File("src/Servicios/Modelos/XML/clientes.xml"));
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public boolean actualizarCliente(String nombre, String direccion, String telefono, String tipoDeCuenta) {
        archivoCliente.agregarCliente(nombre,direccion,telefono,tipoDeCuenta);
        try {
            archivoCliente.saveToFile(new File("src/Servicios/Modelos/XML/clientes.xml"));
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public boolean ingresarPedido(String producto, String codigo, String cantidad) throws TransformerException {
        archivoPedido.agregarPedido(new Pedido(producto,codigo,cantidad));
        archivoPedido.saveToFile(new File("src/Servicios/Modelos/XML/pedidos.xml"));
        return true;
    }

    @Override
    public boolean actualizarPedido(String producto, String codigo, String cantidad) throws TransformerException {
        archivoPedido.agregarPedido(new Pedido(producto,codigo,cantidad));
        archivoPedido.saveToFile(new File("src/Servicios/Modelos/XML/pedidos.xml"));
        return true;
    }

    @Override
    public ColasArray pedidosFrecuentesCliente(String telefono) {
        ColasArray colasArray=new ColasArray();
        colasArray.enqueue("Hamburguesa");
        colasArray.enqueue("10");
        colasArray.enqueue("1");
        colasArray.enqueue("Perro S");
        colasArray.enqueue("5");
        colasArray.enqueue("12");
        colasArray.enqueue("Perro G");
        colasArray.enqueue("2");
        colasArray.enqueue("4");
        System.out.println(colasArray.print());
        return colasArray;
    }

    @Override
    public ColasArray busquedaPedido(String pedidoABuscar) {
        Pedido pedido= archivoPedido.buscarPedidoPorNombre(pedidoABuscar);
        ColasArray colasArray=new ColasArray();
        colasArray.enqueue(pedido.getProductoNombre());
        colasArray.enqueue(pedido.getCodigo());
        colasArray.enqueue(pedido.getCantidad());
        System.out.println(colasArray.print());
        return colasArray;
    }

    @Override
    public ColasArray busquedaCliente(String clienteTelefonoABuscar) {
        if(archivoCliente.existeCliente(clienteTelefonoABuscar)){
            Cliente cliente=archivoCliente.buscarClientePorTelefono(clienteTelefonoABuscar);
            ColasArray colasArray=new ColasArray();
            colasArray.enqueue(cliente.getNombreCliente());
            colasArray.enqueue(cliente.getDireccionCliente());
            colasArray.enqueue(cliente.getTelefono());
            colasArray.enqueue(cliente.getTipoCuenta());
            return colasArray;
        }
        return null;

        /*
        Cliente cliente=new Cliente("Karen","Giron", "3157660279", "premium");
        ColasArray colasArray=new ColasArray();
        colasArray.enqueue("karen");
        colasArray.enqueue("Giron");
        colasArray.enqueue("3157660279");
        colasArray.enqueue("premium");
        System.out.println(colasArray.print());
        return colasArray;
         */
    }

    @Override
    public Factura generarFactura(Pedido pedido, Cliente cliente) {
        return null;
    }

    @Override
    public boolean clienteExistente(String telefono) {
        return archivoCliente.existeCliente(telefono);
    }

    public ModeloOperador obtenerDatos() {
        return null;
    }
    


}
