package Servicios.Controladores;

import Estructuras.Colas.ColasList;
import Dominio.Factura;

public interface IControllerRepartidor {

    boolean validarUsuario(String nombre, String contraseña);//para el login del modulo
    ColasList imprimirRuta();
    boolean recibirPedido(Factura factura);
    boolean estadoPedido(Boolean estado);
    boolean disponibilidadRepartidor();
}
