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

    }
//metodo para la conexion
    public void start() throws RemoteException, ParserConfigurationException {

    }

    public void añadirPantalla() throws RemoteException, PropertyVetoException {
        vistaCocina.setVisible(true);
        vistaCocina.panelDespachoPedidos();
        PriorityQueue<Factura> cola=serviceCocina.pantallaDePedidos();
        while (!cola.isEmpty()){//hasta que la cola se vacie
            Factura factura=cola.extract();
            System.out.println(factura.getPedido().getProductoNombre());
            int prioridad=serviceCocina.clasificarPedidoPrioridad(factura);
            if(factura!=null){
                vistaCocina.agregarPedido(1,factura.getPedido().getProductoNombre(),factura.getPedido().getCantidad(),prioridad);
            }
        }
        /*
        vistaCocina.setVisible(true);
        vistaCocina.panelDespachoPedidos();
        vistaCocina.agregarPedido(1,"Perro","2",2);
        vistaCocina.agregarPedido(1,"Perro S","2",2);
        vistaCocina.agregarPedido(1,"Hamburguesa","2",2);
        vistaCocina.agregarPedido(1,"Perro Caliente","2",3);
        vistaCocina.agregarPedido(1,"Perro","2",2);
        System.out.println("Pantalla en añadir pantalla");
        PriorityQueue<Factura> colaDespacho=serviceCocina.pantallaDePedidos();
        System.out.println(colaDespacho.toString());
        while (!colaDespacho.isEmpty()){
                Factura factura= colaDespacho.extract();
                System.out.println("factura en cocina"+factura);
                System.out.println("pedido en cocina "+factura.getPedido().getProductoNombre());
                Pedido pedido=factura.getPedido();
                int numeroFogon= serviceCocina.entregarNumeroFogon(factura);
               System.out.println(pedido);
              vistaCocina.agregarPedido(1, pedido.getProductoNombre(), pedido.getCantidad(), numeroFogon);
        }

         */
    }

    public void extraerPedido(boolean estado) throws RemoteException, ParserConfigurationException {
        if (estado) {
            if (serviceCocina.entregarPedido(true)) {
                PriorityQueue<Factura> cola = serviceCocina.pantallaDePedidos();
                while (!cola.isEmpty()) {//hasta que la cola se vacie
                    Factura factura = cola.extract();
                    System.out.println(factura.getPedido().getProductoNombre());
                    int prioridad=serviceCocina.clasificarPedidoPrioridad(factura);
                    if (factura != null) {
                        vistaCocina.agregarPedido(1, factura.getPedido().getProductoNombre(), factura.getPedido().getCantidad(), 3);
                    }
                }

            }
        }
    }





}
