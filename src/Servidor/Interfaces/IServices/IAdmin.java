package Servidor.Interfaces.IServices;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAdmin extends Serializable, Remote { //INTERFACES DEL RMI

    boolean validarUsuario(String modulo,String id, String contraseña) throws RemoteException;//para el login del modulo
    boolean resgitrarRepartidor(String nombre,String id, String contraseña) throws RemoteException;
    boolean registrarOperador(String nombre,String id, String contraseña) throws RemoteException;
    boolean activarCocina(boolean estado) throws RemoteException;

}
