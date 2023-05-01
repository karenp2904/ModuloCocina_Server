package Admin.VistaAdmin;

public class Main {
    public static void main(String[] args) {

        VistaPrincipal vistaPrincipal=new VistaPrincipal();
        vistaPrincipal.setVisible(true);
        VistaAdminDatos vistaAdminDatos=new VistaAdminDatos();
        vistaAdminDatos.setVisible(false);

        vistaAdminDatos.ingresarOperador();


        System.out.println("usuario " + vistaAdminDatos.ingresarUsuarioOperador());
        System.out.println("contra " + vistaAdminDatos.ingresarContraseñaOperador());
        System.out.println("nombre  " + vistaAdminDatos.ingresarNombreOperador());

        System.out.println("usuario " + vistaAdminDatos.ingresarUsuarioRepartidor());
        System.out.println("contra " + vistaAdminDatos.ingresarContraseñaRepartidor());
        System.out.println("nombre  " + vistaAdminDatos.ingresarNombreRepartidor());

        }
    }



