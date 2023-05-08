package IServices;

import Dominio.Factura;
import Estructuras.Colas.ColasList;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;

public interface IRepartidor extends Remote, Serializable {

    boolean validarUsuario(String nombre, String contraseña) throws RemoteException;//para el login del modulo
    LinkedList imprimirRuta()throws RemoteException;
    boolean recibirPedido(Factura factura)throws RemoteException;
    boolean estadoPedido(Boolean estado)throws RemoteException;
    boolean disponibilidadRepartidor()throws RemoteException;

}
