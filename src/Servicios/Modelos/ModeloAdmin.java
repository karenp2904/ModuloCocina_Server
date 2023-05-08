package Servicios.Modelos;

import Dominio.Usuario;
import Servicios.Controladores.IControllerAdmin;
import Servicios.Modelos.GenerarXml.UsuariosXML;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.Serializable;

public class ModeloAdmin implements IControllerAdmin, Serializable {

    UsuariosXML archivoOperador =new UsuariosXML(new File("src/Servicios/Modelos/XML/usuariosOperador.xml"));
    UsuariosXML archivoAdmin = new UsuariosXML(new File("src/Servicios/Modelos/XML/usuariosAdmin.xml"));
    UsuariosXML archivoRepartidor =new UsuariosXML(new File("src/Servicios/Modelos/XML/usuariosRepartidor.xml"));

    public ModeloAdmin() throws ParserConfigurationException {
        archivoAdmin();
    }

    private void archivoAdmin(){
        archivoAdmin.agregarUsuario(new Usuario("karen", "karen","karen"));
        try {
            archivoAdmin.saveToFile(new File("src/Servicios/Modelos/XML/usuariosAdmin.xml"));
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean resgitrarRepartidor(String nombre, String id, String contraseña) {

        try {
            archivoRepartidor.agregarUsuario(new Usuario(nombre,id,contraseña));
            archivoRepartidor.saveToFile(new File("src/Servicios/Modelos/XML/usuariosRepartidor.xml"));
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public boolean registrarOperador(String nombre, String id, String contraseña)  {

        try {
            archivoOperador.agregarUsuario(new Usuario(nombre,id,contraseña));
            archivoOperador.saveToFile(new File("src/Servicios/Modelos/XML/usuariosOperador.xml"));
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
