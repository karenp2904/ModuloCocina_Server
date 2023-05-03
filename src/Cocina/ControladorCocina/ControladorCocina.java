package Cocina.ControladorCocina;

import Cocina.VistaCocina.VistaCocina;
import Servicios.Controladores.ControllerCocina;

import Servicios.ServiceCocina;
import Dominio.Pedido;

import java.rmi.RemoteException;

public class ControladorCocina {
    VistaCocina vistaCocina=new VistaCocina();
    public ControladorCocina() throws RemoteException {
        start();
    }
//metodo para la conexion
    public void start() throws RemoteException {
        vistaCocina.setVisible(false);
        ServiceCocina serviceCocina=new ServiceCocina(new ControllerCocina());
        vistaCocina.agregarPedido("perro","3",2);
        vistaCocina.agregarPedido("hamburgesa","3",3);
        vistaCocina.agregarPedido("salchicha","3",4);
        vistaCocina.agregarPedido("perro","3",5);
        Pedido pedido= serviceCocina.pantallaDePedidos().extract();
        while (!serviceCocina.pantallaDePedidos().isEmpty()){
            vistaCocina.agregarPedido(pedido.getProductoNombre(), pedido.getCantidad(),serviceCocina.entregarNumeroFogon(pedido));;
        }
    }

    public void extraerPedido() throws RemoteException {
        ServiceCocina serviceCocina=new ServiceCocina(new ControllerCocina());
        vistaCocina.eliminarPedido(2);
        if(serviceCocina.entregarPedido(true)){
            while (!serviceCocina.pantallaDePedidos().isEmpty()){
                Pedido pedido= serviceCocina.pantallaDePedidos().extract();
                vistaCocina.eliminarPedido(serviceCocina.entregarNumeroFogon(pedido));;
            }
        }
    }





}
