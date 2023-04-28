package Cocina.ControladorCocina;

import Cocina.VistaCocina.VistaCocina;
import Estructuras.APriorityQueue.PriorityQueue;
import Servidor.Controladores.ControllerCocina;
import Servidor.Dominio.Factura;
import Servidor.Dominio.Pedido;
import Servidor.Interfaces.IServices.ICocina;
import Servidor.Servicios.ServiceCocina;


import java.rmi.RemoteException;

public class ControladorCocina {

    public ControladorCocina() throws RemoteException {
        start();
    }
//metodo para la conexion
    public void start() throws RemoteException {
        VistaCocina vistaCocina=new VistaCocina();
        vistaCocina.setVisible(false);
        ServiceCocina serviceCocina=new ServiceCocina(new ControllerCocina());
        while (!serviceCocina.pantallaDePedidos().isEmpty()){
            Pedido pedido= serviceCocina.pantallaDePedidos().extract();
            vistaCocina.editarColaDeDespacho(pedido, serviceCocina.entregarNumeroFogon(pedido));;
        }
    }


}
