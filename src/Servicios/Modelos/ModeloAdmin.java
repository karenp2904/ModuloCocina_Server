package Servicios.Modelos;

import Servicios.Controladores.IControllerAdmin;

import java.io.Serializable;

public class ModeloAdmin implements IControllerAdmin, Serializable {

    @Override
    public boolean resgitrarRepartidor(String nombre, String id, String contraseña) {
        System.out.println("Re"+nombre+id+contraseña);
        return false;
    }

    @Override
    public boolean registrarOperador(String nombre, String id, String contraseña) {
        System.out.println("Opera"+nombre+id+contraseña);
        return false;
    }

    @Override
    public boolean activarCocina(boolean estado) {
        return false;
    }
}
