package Servicios;

import Estructuras.APriorityQueue.PriorityQueue;
import IServices.ICocina;
import Servicios.Controladores.ControllerCocina;
import Dominio.Factura;
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
    public PriorityQueue<Pedido> pantallaDePedidos() throws RemoteException {
        return controllerCocina.pantallaDePedidos() ;
    }



    @Override
    public int clasificarPedidoPrioridad(Factura factura) throws RemoteException {
        return controllerCocina.clasificarPedidoPrioridad(factura);
    }

    @Override
    public boolean entregarPedido() throws RemoteException {
        return controllerCocina.entregarPedido();
    }

    @Override
    public int entregarNumeroFogon(Pedido pedido) throws RemoteException {
        return controllerCocina.entregarNumeroFogon(pedido);
    }
}
