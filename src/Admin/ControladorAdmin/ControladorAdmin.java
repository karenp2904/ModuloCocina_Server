package Admin.ControladorAdmin;

import Admin.VistaAdmin.VistaAdmin;
import Admin.VistaAdmin.VistaAdminDatos;
import Admin.VistaAdmin.VistaPrincipal;
import Servicios.Controladores.ControllerAdmin;
import Dominio.Usuario;
import Servicios.ServiceAdmin;


import java.io.Serializable;
import java.rmi.RemoteException;

public class ControladorAdmin implements Serializable {



    Usuario user1=new Usuario();
    Usuario user2=new Usuario();
    Usuario userAdmin=new Usuario();
    ServiceAdmin serviceAdmin;
    VistaAdmin vistaAdmin = new VistaAdmin();

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
        VistaPrincipal vistaPrincipal=new VistaPrincipal();
        vistaAdmin.setVisible(false);

        System.out.println("admin " + userAdmin.getNombre());
        System.out.println("contra00 " + userAdmin.getId());

        System.out.println("usuarioOpera " + user1.getNombre());
        System.out.println("contra00 " + user1.getId());
        System.out.println("nombre00  " + user1.getContraseña());

        System.out.println("usuarioRepar " +  user2.getNombre());
        System.out.println("contra00 " +  user2.getId());
        System.out.println("nombre00  " +  user2.getContraseña());
/*
        vistaAdmin.validarLogin(serviceAdmin.validarUsuario(userAdmin.getId(), userAdmin.getContraseña()));
        serviceAdmin.registrarOperador(user1.getNombre(), user1.getId(),user1.getContraseña());
        serviceAdmin.resgitrarRepartidor(user2.getNombre(), user2.getId(),user2.getContraseña());



 */

    }

    public  boolean validarLogin(String usuario, String contraseña) throws RemoteException {
       userAdmin.setId(usuario);
       userAdmin.setContraseña(contraseña);
        return serviceAdmin.validarUsuario(userAdmin.getId(), userAdmin.getContraseña());

    }

    public void datosOperador(String nombre,String user, String contraseña){
        user1.setContraseña(contraseña);
        user1.setId(user);
        user1.setNombre(nombre);

        System.out.println("usuarioOpera " + user1.getNombre());
        System.out.println("contra00 " + user1.getId());
        System.out.println("nombre00  " + user1.getContraseña());
        serviceAdmin.registrarOperador(user1.getNombre(), user1.getId(),user1.getContraseña());
    }

    public void datosRepartidor(String nombre,String user, String contraseña){
        user2.setContraseña(contraseña);
        user2.setId(user);
        user2.setNombre(nombre);
        user2=new Usuario(nombre,user, contraseña);

        System.out.println("usuario00 " +  user2.getNombre());
        System.out.println("contra00 " +  user2.getId());
        System.out.println("nombre00  " +  user2.getContraseña());
        serviceAdmin.resgitrarRepartidor(user2.getNombre(), user2.getId(),user2.getContraseña());
    }

}
