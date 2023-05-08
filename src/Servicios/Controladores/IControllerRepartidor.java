package Servicios.Controladores;

import Dominio.Factura;
import Estructuras.Colas.ColasList;
import Estructuras.ListasEnlaceDoble.LinkedList;

import java.io.Serializable;


public interface IControllerRepartidor extends Serializable {

    boolean validarUsuario(String nombre, String contraseña);//para el login del modulo
    LinkedList imprimirRuta();
    boolean recibirPedido(Factura factura);
    boolean estadoPedido(Boolean estado);
    boolean disponibilidadRepartidor();
}
