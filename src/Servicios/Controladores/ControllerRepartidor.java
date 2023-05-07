package Servicios.Controladores;

import Dominio.Factura;
import Estructuras.Colas.ColasList;
import Estructuras.DinamicQueue.Queue;
import Servicios.Modelos.ModeloRepartidor;

import javax.xml.parsers.ParserConfigurationException;
import java.io.Serializable;

public class ControllerRepartidor implements IControllerRepartidor , Serializable {

    private ModeloRepartidor modeloRepartidor=new ModeloRepartidor();

    public ControllerRepartidor() throws ParserConfigurationException {
    }

    @Override
    public boolean validarUsuario(String nombre, String contraseña) {
        return modeloRepartidor.validarUsuario(nombre,contraseña);
    }

    @Override
    public Queue imprimirRuta() {
        return modeloRepartidor.imprimirRuta();
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
