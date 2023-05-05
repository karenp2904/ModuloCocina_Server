package Servicios.Modelos;

import Dominio.Factura;
import Estructuras.Colas.ColasArray;
import Dominio.Cliente;
import Dominio.Pedido;
import Servicios.Controladores.IControllerOperador;
import Servicios.Modelos.XML.CustomersXML;
import Servicios.Modelos.XML.FacturasXML;
import Servicios.Modelos.XML.PedidosXML;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.Serializable;

public class ModeloOperador implements IControllerOperador, Serializable {

    CustomersXML archivoCliente=new CustomersXML(new File("src/Servicios/Modelos/XML/clientes.xml"));
    FacturasXML facturasXML=new FacturasXML(new File("src/Servicios/Modelos/XML/factura.xml"));

   Cliente cliente;
   Pedido pedido;

    PedidosXML archivoPedido=new PedidosXML(new File("src/Servicios/Modelos/XML/pedidos.xml"));
   // UsuariosXML archivoOperador =new UsuariosXML(new File("src/Servicios/Modelos/XML/usuariosOperador.xml"));

    public ModeloOperador() throws ParserConfigurationException {
    }

    @Override
    public boolean validarUsuario(String nombre, String contraseña) {
          //  return archivoOperador.existeUsuarioPorId(nombre);
        ModeloAdmin modeloAdmin= null;
        try {
            modeloAdmin = new ModeloAdmin();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
       // return modeloAdmin.validarUsuarioOperador(nombre,contraseña);
        return  true;

    }

    @Override
    public boolean registrarCliente(String nombre, String direccion, String telefono, String tipoDeCuenta) {
        try {
            cliente=new Cliente(nombre,direccion,telefono,tipoDeCuenta);
            archivoCliente.agregarCliente(nombre,direccion,telefono,tipoDeCuenta);
            archivoCliente.saveToFile(new File("src/Servicios/Modelos/XML/clientes.xml"));
            return true;
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean actualizarCliente(String nombre, String direccion, String telefono, String tipoDeCuenta) {
        try {
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
            pedido=new Pedido(producto,codigo,cantidad);
            archivoPedido.agregarPedido(new Pedido(producto,codigo,cantidad));
            archivoPedido.saveToFile(new File("src/Servicios/Modelos/XML/pedidos.xml"));
            generarFactura(pedido,cliente);
            return true;
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean actualizarPedido(String producto, String codigo, String cantidad) {
        try {
            pedido=new Pedido(producto,codigo,cantidad);
            archivoPedido.agregarPedido(new Pedido(producto, codigo, cantidad));
            archivoPedido.saveToFile(new File("src/Servicios/Modelos/XML/pedidos.xml"));
            generarFactura(pedido,cliente);
            return true;
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
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
    }

    int numFact=0;
    @Override
    public boolean generarFactura(Pedido pedido, Cliente cliente) {
        System.out.println("el pedido es " + pedido.getProductoNombre()+ "el cliente"+ cliente.getNombreCliente());
        numFact++;
        facturasXML.agregarFactura(new Factura(pedido,cliente,String.valueOf(numFact)));
        try {
            facturasXML.saveToFile(new File("src/Servicios/Modelos/XML/factura.xml"));
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
        return true;
    }


    @Override
    public boolean clienteExistente(String telefono) {
        return archivoCliente.existeCliente(telefono);
    }

    


}
