package Servidor.Interfaces.IServices;

import Estructuras.Colas.ColasList;
import Servidor.Dominio.Factura;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRepartidor extends Remote {

    boolean validarUsuario(String modulo,String nombre, String contraseña) throws RemoteException;//para el login del modulo
    ColasList imprimirRuta();
    boolean recibirPedido(Factura factura);
    boolean estadoPedido(Boolean estado);
    boolean disponibilidadRepartidor();

}
