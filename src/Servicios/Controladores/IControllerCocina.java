package Servicios.Controladores;

import Estructuras.APriorityQueue.PriorityQueue;
import Dominio.Factura;
import Dominio.Pedido;

public interface IControllerCocina {

    Factura extraerPedido(); //devuelve la informacion del pedido
    PriorityQueue<Pedido> pantallaDePedidos();
    int clasificarPedidoPrioridad(Factura factura);
    boolean entregarPedido();
    int entregarNumeroFogon(Pedido pedido);
}
