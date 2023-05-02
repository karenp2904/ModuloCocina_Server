package Cocina.ControladorCocina;

import Cocina.VistaCocina.VistaCocina;
import Servicios.Controladores.ControllerCocina;

import Servicios.ServiceCocina;
import Dominio.Pedido;

import java.rmi.RemoteException;

public class ControladorCocina {
    Pedido pedido;
    public ControladorCocina() throws RemoteException {
        start();
    }
//metodo para la conexion
    public void start() throws RemoteException {
        VistaCocina vistaCocina=new VistaCocina();
        vistaCocina.setVisible(false);
        ServiceCocina serviceCocina=new ServiceCocina(new ControllerCocina());
        while (!serviceCocina.pantallaDePedidos().isEmpty()){
            pedido= serviceCocina.pantallaDePedidos().extract();
            vistaCocina.editarColaDeDespacho(pedido, serviceCocina.entregarNumeroFogon(pedido));;
        }
    }


}
