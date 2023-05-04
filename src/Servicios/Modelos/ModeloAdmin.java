package Servicios.Modelos;

import Dominio.Usuario;
import Servicios.Controladores.IControllerAdmin;
import Servicios.Modelos.XML.UsuariosXML;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.Serializable;

public class ModeloAdmin implements IControllerAdmin, Serializable {

    UsuariosXML archivoOperador =new UsuariosXML(new File("src/Servicios/Modelos/XML/usuariosOperador.xml"));
    UsuariosXML archivoAdmin = new UsuariosXML(new File("src/Servicios/Modelos/XML/usuariosAdmin.xml"));
    UsuariosXML archivoRepartidor =new UsuariosXML(new File("src/Servicios/Modelos/XML/usuariosRepartidor.xml"));

    public ModeloAdmin() throws ParserConfigurationException {
    }

    @Override
    public boolean resgitrarRepartidor(String nombre, String id, String contraseña) throws TransformerException {
        archivoRepartidor.agregarUsuario(new Usuario(nombre,id,contraseña));
        archivoRepartidor.saveToFile(new File("src/Servicios/Modelos/XML/usuariosRepartidor.xml"));
        return true;
    }

    @Override
    public boolean registrarOperador(String nombre, String id, String contraseña) throws TransformerException {
        archivoOperador.agregarUsuario(new Usuario(nombre,id,contraseña));
        archivoOperador.saveToFile(new File("src/Servicios/Modelos/XML/usuariosOperador.xml"));
        return true;
    }

    @Override
    public boolean activarCocina(boolean estado) {
        return true;
    }

    @Override
    public boolean validarUsuario(String Usuario, String Contraseña) {
        archivoAdmin.existeUsuarioPorId(Usuario);
        return true;
    }
}
