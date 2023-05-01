package Servidor.Modelos;

import Estructuras.Colas.ColasArray;
import Servidor.Dominio.Cliente;
import Servidor.Dominio.Factura;
import Servidor.Dominio.Pedido;
import Servidor.Interfaces.IController.IControllerOperador;

import java.io.Serializable;
import java.rmi.RemoteException;

public class ModeloOperador implements IControllerOperador, Serializable {


    @Override
    public boolean validarUsuario( String nombre, String contraseña) {
        return false;
    }

    @Override
    public boolean registrarCliente(String nombre, String direccion, String telefono, String tipoDeCuenta) {
        return false;
    }

    @Override
    public boolean actualizarCliente(String nombre, String direccion, String telefono, String tipoDeCuenta) {
        return false;
    }

    @Override
    public boolean ingresarPedido(String producto, String codigo, String cantidad) {
        return false;
    }

    @Override
    public boolean actualizarPedido(String producto, String codigo, String cantidad) {
        return false;
    }

    @Override
    public ColasArray pedidosFrecuentesCliente(String telefono) {
        return null;
    }

    @Override
    public ColasArray busquedaPedido(String pedidoABuscar) {
        return null;
    }

    @Override
    public ColasArray busquedaCliente(String clienteTelefonoABuscar) {
        return null;
    }

    @Override
    public Factura generarFactura(Pedido pedido, Cliente cliente) {
        return null;
    }

    @Override
    public boolean clienteExistente(String telefono) {
        return false;
    }

    public ModeloOperador obtenerDatos() {
        return null;
    }
    


}
