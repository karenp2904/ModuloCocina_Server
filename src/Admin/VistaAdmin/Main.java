package Admin.VistaAdmin;

public class Main {
    public static void main(String[] args) {
        VistaAdminDatos vistaAdminDatos=new VistaAdminDatos();
        System.out.println(vistaAdminDatos.ingresarUsuarioOperador());
        System.out.println(vistaAdminDatos.ingresarContraseñaOperador());
        System.out.println(vistaAdminDatos.ingresarNombreOperador());
    }
}
