package Servicios.Controladores;

import Dominio.Factura;
import Estructuras.APriorityQueue.PriorityQueue;
import Dominio.Pedido;
import Servicios.Modelos.ModeloCocina;

import javax.xml.parsers.ParserConfigurationException;
import java.io.Serializable;

public class ControllerCocina implements IControllerCocina, Serializable {

    private ModeloCocina modeloCocina=new ModeloCocina();

    public ControllerCocina() throws ParserConfigurationException {
    }

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
    public boolean entregarPedido(Boolean estado) {
        return modeloCocina.entregarPedido(estado);
    }

    @Override
    public int entregarNumeroFogon(Pedido pedido) {
        return modeloCocina.entregarNumeroFogon(pedido);
    }
}
