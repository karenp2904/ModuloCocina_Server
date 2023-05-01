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


    private final ControllerAdmin controllerAdmin;

    @Serial
    private static final long serialVersionUID = 1L;


    public ServiceAdmin(ControllerAdmin controllerAdmin) throws RemoteException {
        this.controllerAdmin = controllerAdmin;
    }

    @Override
    public boolean registrarOperador(String nombre, String id, String contraseña) {
        Usuario usuario = new Usuario(nombre,id, contraseña);
        try {
            FileWriter file = new FileWriter("C:\\Users\\AngiePC\\OneDrive - UPB\\UPB\\Universidad UPB\\3. Tercer Semestre\\ESTRUCTURA DE DATOS\\ModuloCocina_Server\\src\\Servidor\\Modelos\\JSON\\operadores.json",true);
            file.write(new Gson().toJson(usuario));
            file.close();
            System.out.println("El operador ha sido agregado con éxito");
            return true;
        } catch (IOException e) {
            System.out.println("Error agregar operador" + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean validarUsuario(String modulo, String id, String contraseña) throws RemoteException {
        Gson gson = new Gson();
        Usuario usuario = gson.fromJson(modulo, Usuario.class);
        JsonParser jsonParser = new JsonParser();
        try {
            FileReader reader = new FileReader(
                    "\"C:\\Users\\AngiePC\\OneDrive - UPB\\UPB\\Universidad UPB\\3. Tercer Semestre\\ESTRUCTURA DE DATOS\\ModuloCocina_Server\\src\\Servidor\\Modelos\\JSON\\operadores.json"
            );
            Object obj = jsonParser.parse(reader);
            JsonObject objectJSON = (JsonObject) obj;
            id = objectJSON.get("id").toString();
            contraseña = objectJSON.get("contraseña").toString();
            modulo = objectJSON.get("modulo").toString();
            return true;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean resgitrarRepartidor(String nombre, String usuario, String contraseña) {
        return controllerAdmin.resgitrarRepartidor(nombre, usuario, contraseña);
    }




    @Override
    public boolean activarCocina(boolean estado) {
        return controllerAdmin.activarCocina(estado);
    }
}
