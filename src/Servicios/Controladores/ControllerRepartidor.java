package Servicios.Controladores;

import Dominio.Factura;
import Estructuras.Colas.ColasList;
import Servicios.Modelos.ModeloRepartidor;

import javax.xml.parsers.ParserConfigurationException;
import java.io.Serializable;
import java.util.LinkedList;

public class ControllerRepartidor implements IControllerRepartidor , Serializable {

    private ModeloRepartidor modeloRepartidor=new ModeloRepartidor();

    public ControllerRepartidor() throws ParserConfigurationException {
    }

    @Override
    public boolean validarUsuario(String nombre, String contraseña) {
        return modeloRepartidor.validarUsuario(nombre,contraseña);
    }

    @Override
    public LinkedList imprimirRuta() {
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
