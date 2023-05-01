package Admin.VistaAdmin;

public class Main {
    public static void main(String[] args) {

        VistaPrincipal vistaPrincipal=new VistaPrincipal();
        vistaPrincipal.setVisible(true);
        VistaAdminDatos vistaAdminDatos=new VistaAdminDatos();
        vistaAdminDatos.setVisible(false);


        if(vistaAdminDatos.botonActivoOperador()){
            System.out.println("usuario00 " + vistaAdminDatos.ingresarUsuarioOperador());
            System.out.println("contra00 " + vistaAdminDatos.ingresarContraseñaOperador());
            System.out.println("nombre00  " + vistaAdminDatos.ingresarNombreOperador());
        }

       if(vistaAdminDatos.botonIngresar.isSelected()) {
           System.out.println("usuario00 " + vistaAdminDatos.ingresarUsuarioRepartidor());
           System.out.println("contra00 " + vistaAdminDatos.ingresarContraseñaRepartidor());
           System.out.println("nombre00  " + vistaAdminDatos.ingresarNombreRepartidor());
        }
       if(vistaAdminDatos.botonIngresar.isSelected()) {
           System.out.println("usuario00 " + vistaAdminDatos.ingresarUsuarioRepartidor());
           System.out.println("contra00 " + vistaAdminDatos.ingresarContraseñaRepartidor());
           System.out.println("nombre00  " + vistaAdminDatos.ingresarNombreRepartidor());
       }

        if(vistaAdminDatos.botonIngresar.isSelected()) {
            System.out.println("usuario00 " + vistaAdminDatos.ingresarUsuarioOperador());
            System.out.println("contra00 " + vistaAdminDatos.ingresarContraseñaOperador());
            System.out.println("nombre00  " + vistaAdminDatos.ingresarNombreOperador());
        }







        }
    }



