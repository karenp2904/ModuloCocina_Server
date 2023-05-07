package IServices;

import Dominio.Factura;
import Dominio.Pedido;
import Estructuras.APriorityQueue.PriorityQueue;
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICocina extends Remote, Serializable{
    boolean obtenerFacturas()throws RemoteException; //devuelve la informacion del pedido
    PriorityQueue<Factura> pantallaDePedidos() throws RemoteException;
    int clasificarPedidoPrioridad(Factura factura) throws RemoteException;
    boolean entregarPedido(Boolean estado) throws RemoteException;
    int entregarNumeroFogon(Factura pedido)throws RemoteException;

}
