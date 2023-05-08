package Servicios;

import Dominio.Factura;
import Estructuras.Colas.ColasList;
import Servicios.Controladores.ControllerRepartidor;
import IServices.IRepartidor;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;

public class ServiceRepartidor extends UnicastRemoteObject implements IRepartidor, Serializable {

    private static final long serialVersionUID = 1L;

    private ControllerRepartidor controllerRepartidor;

    public ServiceRepartidor(ControllerRepartidor controllerRepartidor) throws RemoteException {
        this.controllerRepartidor=controllerRepartidor;
    }

    @Override
    public boolean validarUsuario(String nombre, String contraseña) throws RemoteException {
        return false;
    }

    @Override
    public LinkedList imprimirRuta() {
        return controllerRepartidor.imprimirRuta();
    }

    @Override
    public boolean recibirPedido(Factura factura) {
        return controllerRepartidor.recibirPedido(factura);
    }

    @Override
    public boolean estadoPedido(Boolean estado) {
        return controllerRepartidor.estadoPedido(estado);
    }

    @Override
    public boolean disponibilidadRepartidor() {
        return controllerRepartidor.disponibilidadRepartidor();
    }
}
