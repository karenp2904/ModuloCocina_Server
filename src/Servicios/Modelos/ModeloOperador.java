package Servicios.Modelos;

import Estructuras.Colas.ColasArray;
import Dominio.Cliente;
import Dominio.Factura;
import Dominio.Pedido;
import Servicios.Controladores.IControllerOperador;

import java.io.Serializable;

public class ModeloOperador implements IControllerOperador, Serializable {


    @Override
    public boolean validarUsuario( String nombre, String contraseña) {
        return true;
    }

    @Override
    public boolean registrarCliente(String nombre, String direccion, String telefono, String tipoDeCuenta) {
        return true;
    }

    @Override
    public boolean actualizarCliente(String nombre, String direccion, String telefono, String tipoDeCuenta) {
        return true;
    }

    @Override
    public boolean ingresarPedido(String producto, String codigo, String cantidad) {
        return true;
    }

    @Override
    public boolean actualizarPedido(String producto, String codigo, String cantidad) {
        return true;
    }

    @Override
    public ColasArray pedidosFrecuentesCliente(String telefono) {
        return new ColasArray();
    }

    @Override
    public ColasArray busquedaPedido(String pedidoABuscar) {
        return new ColasArray();
    }

    @Override
    public ColasArray busquedaCliente(String clienteTelefonoABuscar) {
        Cliente cliente=new Cliente("Karen","Giron", "3157660279", "premium");
        ColasArray colasArray=new ColasArray();
        colasArray.enqueue("karen");
        colasArray.enqueue("Giron");
        colasArray.enqueue("3157660279");
        colasArray.enqueue("premium");
        System.out.println(colasArray.print());
        return colasArray;
    }

    @Override
    public Factura generarFactura(Pedido pedido, Cliente cliente) {
        return null;
    }

    @Override
    public boolean clienteExistente(String telefono) {
        return true;
    }

    public ModeloOperador obtenerDatos() {
        return null;
    }
    


}
