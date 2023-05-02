package Dominio;

public class Usuario {
    private String nombre;
    private String id;
    private String contraseña;

    public Usuario(String id, String contraseña){
        this.id = id;
        this.contraseña = contraseña;
    }

    public Usuario(String nombre, String id, String contraseña) {
        this.nombre = nombre;
        this.id = id;
        this.contraseña = contraseña;
    }

    public Usuario() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
/*
    //Metodo para validar los datos ingresados
    //Devolverá true si es el usuario, de lo contrario devolverá false
    public boolean validarUsuario(String nombre, String contrasena){
        return this.nombre.equals(nombre) && this.password.equals(contrasena);
    }

 */

}
