package IServices;

import Dominio.Factura;
import Dominio.Pedido;
import Estructuras.APriorityQueue.PriorityQueue;
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICocina extends Remote, Serializable{

    PriorityQueue<Pedido> pantallaDePedidos() throws RemoteException;
    int clasificarPedidoPrioridad(Factura factura) throws RemoteException;
    boolean entregarPedido() throws RemoteException;
    int entregarNumeroFogon(Pedido pedido)throws RemoteException;

}
