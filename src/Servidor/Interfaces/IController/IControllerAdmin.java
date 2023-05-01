package Servidor.Interfaces.IController;

public interface IControllerAdmin {
    boolean resgitrarRepartidor(String nombre,String id, String contraseña);
    boolean registrarOperador(String nombre,String usuario, String contraseña);
    boolean activarCocina(boolean estado);
}
