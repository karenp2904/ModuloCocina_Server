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
    @Override
    public boolean registrarOperador(String nombre, String id, String contraseña) {
        Usuario usuario = new Usuario(nombre, id, contraseña);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\AngiePC\\OneDrive - UPB\\UPB\\Universidad UPB\\3. Tercer Semestre\\ESTRUCTURA DE DATOS\\ModuloCocina_Server\\src\\Servidor\\Modelos\\JSON\\operadores.json", true));
            writer.write(new Gson().toJson(usuario) + "\n");
            writer.close();
            System.out.println("El operador ha sido agregado con éxito");
            return true;
        } catch (IOException e) {
            System.out.println("Error agregar operador" + e.getMessage());
            return false;
        }
    }

    public boolean validarUsuario(String usuario) throws RemoteException {
        Gson gson = new Gson();
        Usuario user = gson.fromJson(usuario, Usuario.class);

        try {
            FileReader reader = new FileReader("C:\\Users\\AngiePC\\OneDrive - UPB\\UPB\\Universidad UPB\\3. Tercer Semestre\\ESTRUCTURA DE DATOS\\ModuloCocina_Server\\src\\Servidor\\Modelos\\JSON\\admin.json");
            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = (JsonObject) jsonParser.parse(reader);
            String idAdmin = jsonObject.get("id").getAsString();
            String contraseñaAdmin = jsonObject.get("contraseña").getAsString();

            if (user.getId().equals(idAdmin) && user.getContraseña().equals(contraseñaAdmin)) {
                System.out.println("Bienvenido Administrador, recuerde... UN GRAN PODER REQUIERE UNA GRAN RESPONSABILIDAD");
                return true;
            } else {
                System.out.println("Credenciales no válidas");
                return false;
            }

        } catch (FileNotFoundException e) {
            System.out.println("No se pudo abrir el archivo admin.json");
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public boolean resgitrarRepartidor(String nombre, String id, String contraseña) {
        Usuario usuario = new Usuario(nombre, id, contraseña);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("JSON\\repartidores.json", true));
            writer.write(new Gson().toJson(usuario) + "\n");
            writer.close();
            System.out.println("El operador ha sido agregado con éxito");
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
