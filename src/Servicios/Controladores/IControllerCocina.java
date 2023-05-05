package Servicios.Controladores;

import Dominio.Factura;
import Estructuras.APriorityQueue.PriorityQueue;
import Dominio.Pedido;

public interface IControllerCocina {

    boolean obtenerFacturas(); //devuelve la informacion del pedido
    PriorityQueue<Factura> pantallaDePedidos();
    int clasificarPedidoPrioridad(Factura factura);
    boolean entregarPedido(Boolean estado);
    int entregarNumeroFogon(Factura pedido);
}
