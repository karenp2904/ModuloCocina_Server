package Admin.ControladorAdmin;

import Admin.VistaAdmin.VistaAdmin;
import Admin.VistaAdmin.VistaAdminDatos;
import Servidor.Controladores.ControllerAdmin;
import Servidor.Servicios.ServiceAdmin;

import java.rmi.RemoteException;

public class ControladorAdmin {

    public ControladorAdmin() throws RemoteException {
    }

    public void start() throws RemoteException {
        VistaAdmin vistaAdmin=new VistaAdmin();
        vistaAdmin.setVisible(false);
        VistaAdminDatos vistaAdminDatos=new VistaAdminDatos();
        vistaAdminDatos.setVisible(false);
        ServiceAdmin serviceAdmin=new ServiceAdmin(new ControllerAdmin());
        vistaAdmin.validarLogin(serviceAdmin.validarUsuario( vistaAdmin.validarUsuario(),vistaAdmin.validarContraseña()));
        serviceAdmin.resgitrarRepartidor(vistaAdminDatos.ingresarNombreRepartidor(),vistaAdminDatos.ingresarUsuarioRepartidor(),vistaAdminDatos.ingresarContraseñaRepartidor());
        serviceAdmin.registrarOperador(vistaAdminDatos.ingresarNombreOperador(),vistaAdminDatos.ingresarUsuarioOperador(),vistaAdminDatos.ingresarContraseñaOperador());
    }
}
