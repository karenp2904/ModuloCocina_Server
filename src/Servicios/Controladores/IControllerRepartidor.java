package Servicios.Controladores;

import Dominio.Factura;
import Estructuras.Colas.ColasList;
import Estructuras.DinamicQueue.Queue;

import java.io.Serializable;

public interface IControllerRepartidor extends Serializable {

    boolean validarUsuario(String nombre, String contraseña);//para el login del modulo
    Queue imprimirRuta();
    boolean estadoPedido(Boolean estado);
    boolean disponibilidadRepartidor();
}
