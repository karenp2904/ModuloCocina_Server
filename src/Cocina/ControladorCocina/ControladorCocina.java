package Cocina.ControladorCocina;

import Cocina.VistaCocina.VistaCocina;
import Dominio.Factura;
import Estructuras.APriorityQueue.PriorityQueue;
import Servicios.Controladores.ControllerCocina;

import Servicios.ServiceCocina;
import Dominio.Pedido;

import javax.xml.parsers.ParserConfigurationException;
import java.beans.PropertyVetoException;
import java.io.Serializable;
import java.rmi.RemoteException;

public class ControladorCocina implements Serializable {
    VistaCocina vistaCocina=new VistaCocina();
    ServiceCocina serviceCocina=new ServiceCocina(new ControllerCocina());

    public ControladorCocina() throws RemoteException, ParserConfigurationException {
        start();
    }
//metodo para la conexion
    public void start() throws RemoteException, ParserConfigurationException {

    }

    public void añadirPantalla() throws RemoteException {
        vistaCocina.panelDespachoPedidos();
        System.out.println("pantalla");
        System.out.println(serviceCocina.pantallaDePedidos().toString());
        PriorityQueue<Factura> colaDespacho=serviceCocina.pantallaDePedidos();
        System.out.println(colaDespacho.toString());
        while (!colaDespacho.isEmpty()){
            try{
                System.out.println(colaDespacho.extract());
                Factura factura= colaDespacho.extract();
                System.out.println(factura);
                System.out.println("pedido "+factura.getPedido().getProductoNombre());
                Pedido pedido=factura.getPedido();
               System.out.println(pedido);
               vistaCocina.agregarPedido(1,pedido.getProductoNombre(), pedido.getCantidad(),serviceCocina.entregarNumeroFogon(factura));;
            }catch (Exception e){
                e.printStackTrace();
            }



        }
    }

    public void extraerPedido(boolean estado) throws RemoteException, ParserConfigurationException {
        if (estado) {
            vistaCocina.eliminarPedido(1);
            /*
            if (serviceCocina.entregarPedido(true)) {
                while (!serviceCocina.pantallaDePedidos().isEmpty()) {
                    Factura factura= serviceCocina.pantallaDePedidos().extract();
                    System.out.println("pedido" + factura.getPedido().getProductoNombre());
                    vistaCocina.eliminarPedido(serviceCocina.entregarNumeroFogon(factura.getPedido()));
                    ;

                }
            }

             */
        }
    }





}
