package Servicios.Controladores;

import Estructuras.APriorityQueue.PriorityQueue;
import Dominio.Factura;
import Dominio.Pedido;
import Servicios.Modelos.ModeloCocina;

import java.io.Serializable;

public class ControllerCocina implements IControllerCocina, Serializable {

    private ModeloCocina modeloCocina=new ModeloCocina();

    @Override
    public Factura extraerPedido() {//extraer del archivo el pedido pero manejando el archivo de facturas
        return modeloCocina.extraerPedido();
    }

    @Override
    public PriorityQueue<Pedido> pantallaDePedidos() {
        return modeloCocina.pantallaDePedidos();
    }

    @Override
    public int clasificarPedidoPrioridad(Factura factura) {
        return modeloCocina.clasificarPedidoPrioridad(factura) ;
    }

    @Override
    public boolean entregarPedido() {
        return modeloCocina.entregarPedido();
    }

    @Override
    public int entregarNumeroFogon(Pedido pedido) {
        return modeloCocina.entregarNumeroFogon(pedido);
    }
}
