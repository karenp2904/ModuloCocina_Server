package Servidor.Interfaces.IController;

import Estructuras.Colas.ColasArray;
import Servidor.Dominio.Cliente;
import Servidor.Dominio.Factura;
import Servidor.Dominio.Pedido;

public interface IControllerOperador {

    boolean validarUsuario(String modulo,String nombre, String contraseña);//para el login del modulo
    boolean registrarCliente(String nombre, String direccion, String telefono,String tipoDeCuenta);//registro de clientes
    boolean actualizarCliente(String nombre, String direccion, String telefono,String tipoDeCuenta );//actualizar el cliente
    boolean ingresarPedido(String producto, String codigo, String cantidad);//ingresar un pedido
    boolean actualizarPedido(String producto, String codigo, String cantidad);//actualizar un pedido
    ColasArray pedidosFrecuentesCliente(String telefono);//pedidos mas solicitados por el cliente
    ColasArray busquedaPedido(String pedidoABuscar); //busqueda de pedido
    ColasArray busquedaCliente(String clienteTelefonoABuscar); //busqueda de cliente
    Factura generarFactura(Pedido pedido, Cliente cliente);
}
