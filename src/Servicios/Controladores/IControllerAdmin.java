package Servicios.Controladores;

public interface IControllerAdmin {
    boolean resgitrarRepartidor(String nombre,String id, String contraseña);
    boolean registrarOperador(String nombre,String id, String contraseña);
    boolean activarCocina(boolean estado);
}
