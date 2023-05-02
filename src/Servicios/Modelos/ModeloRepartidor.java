package Servicios.Modelos;

import Estructuras.Colas.ColasList;
import Dominio.Factura;
import Servicios.Controladores.IControllerRepartidor;

import java.io.Serializable;

public class ModeloRepartidor implements IControllerRepartidor, Serializable {
    @Override
    public boolean validarUsuario( String nombre, String contraseña) {
        return false;
    }

    @Override
    public ColasList imprimirRuta() {
        return null;
    }

    @Override
    public boolean recibirPedido(Factura factura) {
        return false;
    }

    @Override
    public boolean estadoPedido(Boolean estado) {
        return false;
    }

    @Override
    public boolean disponibilidadRepartidor() {
        return false;
    }
}
