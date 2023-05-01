package Admin.ControladorAdmin;

import Admin.VistaAdmin.VistaAdmin;
import Admin.VistaAdmin.VistaAdminDatos;
import Servidor.Controladores.ControllerAdmin;
import Servidor.Dominio.Cliente;
import Servidor.Dominio.Usuario;
import Servidor.Servicios.ServiceAdmin;

import java.rmi.RemoteException;

public class ControladorAdmin {



    Usuario user1=new Usuario();
    Usuario user2=new Usuario();
    ServiceAdmin serviceAdmin;

    {
        try {
            serviceAdmin = new ServiceAdmin(new ControllerAdmin());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public ControladorAdmin()  {
    }

    public void start() throws RemoteException {
        VistaAdmin vistaAdmin = new VistaAdmin();
        vistaAdmin.setVisible(false);
        VistaAdminDatos vistaAdminDatos = new VistaAdminDatos();
        vistaAdminDatos.setVisible(false);

    }

    public  void validarLogin(String usuario, String contraseña) throws RemoteException {
        VistaAdmin vistaAdmin = new VistaAdmin();
        vistaAdmin.setVisible(false);
        vistaAdmin.validarLogin(serviceAdmin.validarUsuario(vistaAdmin.validarUsuario(), vistaAdmin.validarContraseña()));
    }

    public void datosOperador(String nombre,String user, String contraseña){
        user1.setContraseña(contraseña);
        user1.setId(user);
        user1.setNombre(nombre);
        serviceAdmin.registrarOperador(user1.getNombre(), user1.getId(),user1.getContraseña());
        System.out.println("usuarioOpera " + user1.getNombre());
        System.out.println("contra00 " + user1.getId());
        System.out.println("nombre00  " + user1.getContraseña());
    }

    public void datosRepartidor(String nombre,String user, String contraseña){
        user2.setContraseña(contraseña);
        user2.setId(user);
        user2.setNombre(nombre);
        user2=new Usuario(nombre,user, contraseña);

        serviceAdmin.resgitrarRepartidor(user2.getNombre(), user2.getId(),user2.getContraseña());
        System.out.println("usuario00 " +  user2.getNombre());
        System.out.println("contra00 " +  user2.getId());
        System.out.println("nombre00  " +  user2.getContraseña());
    }

}
