package Servicios.Modelos;

import Dominio.Factura;
import Estructuras.Colas.ColasList;
import Servicios.Controladores.IControllerRepartidor;
import Servicios.Modelos.XML.UsuariosXML;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.Serializable;

public class ModeloRepartidor implements IControllerRepartidor, Serializable {

    UsuariosXML archivoOperador =new UsuariosXML(new File("src/Servicios/Modelos/XML/usuariosOperador.xml"));

    public ModeloRepartidor() throws ParserConfigurationException {
    }

    @Override
    public boolean validarUsuario( String nombre, String contraseña) {
        archivoOperador.existeUsuarioPorId(nombre);
        return true;
    }

    @Override
    public ColasList imprimirRuta() {
        return null;
    }

    @Override
    public boolean recibirPedido(Factura factura) {
        return false;
    }

    @Override
    public boolean estadoPedido(Boolean estado) {
        return false;
    }

    @Override
    public boolean disponibilidadRepartidor() {
        return false;
    }
}
