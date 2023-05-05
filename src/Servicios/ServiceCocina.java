package Servicios;

import Dominio.Factura;
import Estructuras.APriorityQueue.PriorityQueue;
import IServices.ICocina;
import Servicios.Controladores.ControllerCocina;
import Dominio.Pedido;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class ServiceCocina extends UnicastRemoteObject implements ICocina, Serializable {

    private ControllerCocina controllerCocina; //instancia
    private static final long serialVersionUID = 1L;

    public ServiceCocina(ControllerCocina controllerCocina) throws RemoteException {
        this.controllerCocina=controllerCocina; //controlador
    }


    @Override
    public PriorityQueue<Factura> pantallaDePedidos() throws RemoteException {
        return controllerCocina.pantallaDePedidos() ;
    }


    @Override
    public int clasificarPedidoPrioridad(Factura factura) throws RemoteException {
        return controllerCocina.clasificarPedidoPrioridad(factura);
    }

    @Override
    public boolean entregarPedido(Boolean estado) throws RemoteException {
        return controllerCocina.entregarPedido(estado);
    }

    @Override
    public int entregarNumeroFogon(Factura pedido) throws RemoteException {
        return controllerCocina.entregarNumeroFogon(pedido);
    }
}
