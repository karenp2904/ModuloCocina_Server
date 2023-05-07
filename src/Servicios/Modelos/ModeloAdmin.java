package Servicios.Modelos;

import Dominio.Usuario;
import Servicios.Controladores.IControllerAdmin;
import Servicios.Modelos.GenerarXml.UsuariosXML;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.Serializable;

public class ModeloAdmin implements IControllerAdmin, Serializable {

    UsuariosXML archivoOperador =new UsuariosXML(new File("usuariosOperador.xml"));
    UsuariosXML archivoAdmin = new UsuariosXML(new File("usuariosAdmin.xml"));
    UsuariosXML archivoRepartidor =new UsuariosXML(new File("usuariosRepartidor.xml"));

    public ModeloAdmin() throws ParserConfigurationException {
        archivoAdmin();
    }

    private void archivoAdmin(){
        archivoAdmin.agregarUsuario(new Usuario("karen", "karen","karen"));
        try {
            archivoAdmin.saveToFile(new File("usuariosAdmin.xml"));
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean resgitrarRepartidor(String nombre, String id, String contraseña) {
        archivoRepartidor.agregarUsuario(new Usuario(nombre,id,contraseña));
        try {
            archivoRepartidor.saveToFile(new File("usuariosRepartidor.xml"));
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public boolean registrarOperador(String nombre, String id, String contraseña)  {
        archivoOperador.agregarUsuario(new Usuario(nombre,id,contraseña));
        try {
            archivoOperador.saveToFile(new File("usuariosOperador.xml"));
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public boolean activarCocina(boolean estado) {
        return true;
    }

    @Override
    public boolean validarUsuario(String Usuario, String Contraseña) {
        return archivoAdmin.existeUsuarioPorId(Usuario);
      //  return true;
    }

    public boolean validarUsuarioOperador(String Usuario, String Contraseña) {
       return archivoOperador.existeUsuarioPorId(Usuario);
       // return true;
    }
}
