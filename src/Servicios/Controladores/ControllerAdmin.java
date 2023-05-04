package Servicios.Controladores;

import Servicios.Modelos.ModeloAdmin;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.Serializable;

public class ControllerAdmin implements IControllerAdmin, Serializable {

    private ModeloAdmin modeloAdmin=new ModeloAdmin();

    public ControllerAdmin() throws ParserConfigurationException {
    }

    @Override
    public boolean resgitrarRepartidor(String nombre, String usuario, String contraseña) {
        return modeloAdmin.registrarOperador(nombre,usuario,contraseña);
    }

    @Override
    public boolean registrarOperador(String nombre, String usuario, String contraseña) {
        return modeloAdmin.resgitrarRepartidor(nombre,usuario,contraseña);
    }

    @Override
    public boolean activarCocina(boolean estado) {
        return modeloAdmin.activarCocina(estado);
    }

    @Override
    public boolean validarUsuario(String Usuario, String Contraseña) {
        return modeloAdmin.validarUsuario(Usuario,Contraseña);
    }
}
