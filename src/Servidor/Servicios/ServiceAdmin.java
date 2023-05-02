package Servidor.Servicios;

import Servidor.Controladores.ControllerAdmin;
import Servidor.Dominio.Usuario;
import Servidor.Interfaces.IServices.IAdmin;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServiceAdmin extends UnicastRemoteObject implements IAdmin, Serializable {

    private ControllerAdmin controllerAdmin;
    private static final long serialVersionUID = 1L;
    public ServiceAdmin(ControllerAdmin controllerAdmin) throws RemoteException {
        this.controllerAdmin=controllerAdmin;
    }
    public boolean registrarOperador(String nombre, String id, String contraseña) {
        Usuario usuario = new Usuario(nombre, id, contraseña);
        try {
            if(usuario!=null) {
                BufferedWriter writer = new BufferedWriter(new FileWriter("src/Servidor/Modelos/JSON/operadores.json", true));
                writer.write(new Gson().toJson(usuario) + "\n");
                writer.close();
                System.out.println("El operador ha sido agregado con éxito");
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error agregar operador" + e.getMessage());
            return false;
        }
    }

    public boolean validarUsuario(String id, String contraseña) throws RemoteException {
        boolean valido=false;
        Gson gson = new Gson();
        Usuario user = gson.fromJson(id, Usuario.class);
        try {
            if(user!=null){
                FileReader reader = new FileReader("src/Servidor/Modelos/JSON/admin.json");
                JsonParser jsonParser = new JsonParser();
                JsonObject jsonObject = (JsonObject) jsonParser.parse(reader);
                String idAdmin = jsonObject.get("id").getAsString();
                String contraseñaAdmin = jsonObject.get("contraseña").getAsString();
                if ((idAdmin.equals(id)) && (contraseñaAdmin.equals(contraseña))) {
                    System.out.println("Bienvenido Administrador, recuerde... UN GRAN PODER REQUIERE UNA GRAN RESPONSABILIDAD");
                    valido=true;
                    return true;
                } else {
                    System.out.println("Credenciales no válidas");
                    return false;
                }
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo JSON");
            return false;
        }
        finally {
            return valido;
        }
    }


    @Override
    public boolean resgitrarRepartidor(String nombre, String id, String contraseña) {
        Usuario usuario = new Usuario(nombre, id, contraseña);
        try {
            if(usuario!=null) {
                BufferedWriter writer = new BufferedWriter(new FileWriter("src/Servidor/Modelos/JSON/repartidores.json", true));
                writer.write(new Gson().toJson(usuario) + "\n");
                writer.close();
                System.out.println("El operador ha sido agregado con éxito");
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error agregar operador" + e.getMessage());
            return false;
        }
    }


    @Override
    public boolean activarCocina(boolean estado) {
        return controllerAdmin.activarCocina(estado);
    }
}
