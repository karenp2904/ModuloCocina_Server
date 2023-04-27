package Servidor.Interfaces.IServices;

import Estructuras.Colas.ColasList;
import Servidor.Dominio.Factura;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRepartidor extends Remote, Serializable {

    boolean validarUsuario(String modulo,String nombre, String contraseña) throws RemoteException;//para el login del modulo
    ColasList imprimirRuta()throws RemoteException;
    boolean recibirPedido(Factura factura)throws RemoteException;
    boolean estadoPedido(Boolean estado)throws RemoteException;
    boolean disponibilidadRepartidor()throws RemoteException;

}
