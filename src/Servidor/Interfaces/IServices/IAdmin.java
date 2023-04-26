package Servidor.Interfaces.IServices;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAdmin extends Remote { //INTERFACES DEL RMI

    boolean validarUsuario(String modulo,String nombre, String contraseña) throws RemoteException;//para el login del modulo
    boolean resgitrarRepartidor(String nombre,String usuario, String contraseña) throws RemoteException;
    boolean registrarOperador(String nombre,String usuario, String contraseña) throws RemoteException;
    boolean activarCocina(boolean estado) throws RemoteException;

}
