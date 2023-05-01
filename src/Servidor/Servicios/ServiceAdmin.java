package Servidor.Servicios;

import Servidor.Controladores.ControllerAdmin;
import Servidor.Interfaces.IServices.IAdmin;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServiceAdmin extends UnicastRemoteObject implements IAdmin, Serializable {

    private ControllerAdmin controllerAdmin;

    private static final long serialVersionUID = 1L;


    public ServiceAdmin(ControllerAdmin controllerAdmin) throws RemoteException {
        this.controllerAdmin=controllerAdmin;
    }

    @Override
    public boolean validarUsuario( String nombre, String contraseña) throws RemoteException {
        return false;
    }

    @Override
    public boolean resgitrarRepartidor(String nombre, String usuario, String contraseña) {
        return controllerAdmin.resgitrarRepartidor(nombre,usuario,contraseña);
    }

    @Override
    public boolean registrarOperador(String nombre, String usuario, String contraseña) {
        return controllerAdmin.registrarOperador(nombre,usuario,contraseña);
    }

    @Override
    public boolean activarCocina(boolean estado) {
        return controllerAdmin.activarCocina(estado);
    }
}
