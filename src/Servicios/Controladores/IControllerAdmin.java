package Servicios.Controladores;

import javax.xml.transform.TransformerException;

public interface IControllerAdmin {
    boolean resgitrarRepartidor(String nombre,String id, String contraseña) throws TransformerException;
    boolean registrarOperador(String nombre,String id, String contraseña) throws TransformerException;
    boolean activarCocina(boolean estado);
    boolean validarUsuario(String Usuario,String Contraseña);


}
