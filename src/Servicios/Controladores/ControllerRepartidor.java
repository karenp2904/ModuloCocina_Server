package Servicios.Controladores;

import Estructuras.Colas.ColasList;
import Dominio.Factura;
import Servicios.Modelos.ModeloRepartidor;

import java.io.Serializable;

public class ControllerRepartidor implements IControllerRepartidor , Serializable {

    private ModeloRepartidor modeloRepartidor=new ModeloRepartidor();

    @Override
    public boolean validarUsuario(String nombre, String contraseña) {
        return modeloRepartidor.validarUsuario(nombre,contraseña);
    }

    @Override
    public ColasList imprimirRuta() {
        return modeloRepartidor.imprimirRuta();
    }

    @Override
    public boolean recibirPedido(Factura factura) {
        return modeloRepartidor.recibirPedido(factura);
    }

    @Override
    public boolean estadoPedido(Boolean estado) {
        return modeloRepartidor.estadoPedido(estado);
    }

    @Override
    public boolean disponibilidadRepartidor() {
        return modeloRepartidor.disponibilidadRepartidor();
    }
}
