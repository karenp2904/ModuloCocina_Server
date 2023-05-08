package IServices;

import Dominio.Factura;
import Estructuras.ListasEnlaceDoble.LinkedList;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRepartidor extends Remote, Serializable {

    boolean validarUsuario(String nombre, String contraseña) throws RemoteException;//para el login del modulo
    LinkedList imprimirRuta()throws RemoteException;

    boolean estadoPedido(Boolean estado)throws RemoteException;
    boolean disponibilidadRepartidor()throws RemoteException;

}
