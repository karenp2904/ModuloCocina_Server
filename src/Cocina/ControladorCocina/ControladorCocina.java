package Cocina.ControladorCocina;

import Cocina.VistaCocina.VistaCocina;
import Servicios.Controladores.ControllerCocina;

import Servicios.ServiceCocina;
import Dominio.Pedido;

import java.beans.PropertyVetoException;
import java.io.Serializable;
import java.rmi.RemoteException;

public class ControladorCocina implements Serializable {
    VistaCocina vistaCocina=new VistaCocina();

    public ControladorCocina() throws RemoteException{
        start();
    }
//metodo para la conexion
    public void start() throws RemoteException {
        vistaCocina.panelDespachoPedidos();
        ServiceCocina serviceCocina=new ServiceCocina(new ControllerCocina());

        vistaCocina.agregarPedido(1,"perro","3",2);
        vistaCocina.agregarPedido(1,"hamburgesa","3",3);
        vistaCocina.agregarPedido(1,"salchicha","3",4);
        vistaCocina.agregarPedido(1,"perro","3",5);

        System.out.println(serviceCocina.pantallaDePedidos().toString());
        extraerPedido();

       /*
        while (!serviceCocina.pantallaDePedidos().isEmpty()){

            try{
                Pedido pedido= serviceCocina.pantallaDePedidos().extract();
                System.out.println("pedido"+pedido.getProductoNombre());
                vistaCocina.agregarPedido(1,pedido.getProductoNombre(), pedido.getCantidad(),serviceCocina.entregarNumeroFogon(pedido));;
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        */


    }

    public void extraerPedido() throws RemoteException {
        ServiceCocina serviceCocina=new ServiceCocina(new ControllerCocina());
        if(vistaCocina.pedidoEntregado()){
           // vistaCocina.eliminarPedido(2);
        }
        /*
        vistaCocina.eliminarPedido(2);
        if(serviceCocina.entregarPedido(true)){
            /*
            while (!serviceCocina.pantallaDePedidos().isEmpty()){
                Pedido pedido= new Pedido();
                pedido=serviceCocina.pantallaDePedidos().extract();
                System.out.println("pedido"+pedido.getProductoNombre());
                vistaCocina.eliminarPedido(serviceCocina.entregarNumeroFogon(pedido));;
            }

             */

    }





}
