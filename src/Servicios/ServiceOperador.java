package Servicios;

import Estructuras.Colas.ColasArray;
import Servicios.Controladores.ControllerOperador;
import IServices.IOperador;

import java.io.*;
/*
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

 */
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServiceOperador extends UnicastRemoteObject implements IOperador, Serializable {

    private static final long serialVersionUID = 1L;

    private ControllerOperador controllerOperador;

    public ServiceOperador(ControllerOperador controllerOperador) throws RemoteException {
        this.controllerOperador=controllerOperador;
    }


    @Override
    public boolean validarUsuario( String nombre, String contraseña) throws RemoteException {
        System.out.println("Validar"+ nombre + contraseña);
        return controllerOperador.validarUsuario(nombre,contraseña);
    }

    @Override
    public boolean registrarCliente(String nombre, String direccion, String telefono, String tipoDeCuenta) {
        System.out.println("registro!!!!" + nombre+ direccion+telefono+tipoDeCuenta);
        return controllerOperador.registrarCliente(nombre,direccion,telefono,tipoDeCuenta);
    }

    @Override
    public boolean actualizarCliente(String nombre, String direccion, String telefono, String tipoDeCuenta) {
        System.out.println("actualizar" + nombre+ direccion+telefono+tipoDeCuenta);
        return controllerOperador.actualizarCliente(nombre,direccion,telefono,tipoDeCuenta);
    }

    @Override
    public boolean ingresarPedido(String producto, String codigo, String cantidad) {
        System.out.println("pedido" + producto+ codigo+ cantidad);
        return controllerOperador.ingresarPedido(producto,codigo,cantidad);
    }

    @Override
    public boolean actualizarPedido(String producto, String codigo, String cantidad) {
        System.out.println("actualizar" + producto+ codigo+ cantidad);
        return controllerOperador.actualizarPedido(producto,codigo,cantidad);
    }

    @Override
    public ColasArray pedidosFrecuentesCliente(String telefono) {
        System.out.println("frecuente tel"+ telefono);
        return controllerOperador.pedidosFrecuentesCliente(telefono);
    }

    @Override
    public ColasArray busquedaPedido(String pedidoABuscar) {
        System.out.println("pedido" + pedidoABuscar);
        return controllerOperador.busquedaPedido(pedidoABuscar);
    }

    @Override
    public ColasArray busquedaCliente(String clienteABuscar) {
        System.out.println("busqueda cliente"+ clienteABuscar);
        return controllerOperador.busquedaCliente(clienteABuscar);
    }

    @Override
    public boolean clienteExistente(String telefono) throws RemoteException {
        System.out.println("clinte existe" + telefono);
        return controllerOperador.clienteExistente(telefono);
    }

    //Json part 
    public class FileJsonAdapter<T> {
/*
        public T getObject(String path, Class<T> classOfT) {
            T object = null;
            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                Gson gson = new GsonBuilder().create();
                object = gson.fromJson(br, classOfT);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return object;
        }
    
        public boolean writeObject(String path, T object) {
            try (FileWriter writer = new FileWriter(path)) {
                Gson gson = new GsonBuilder().create();
                gson.toJson(object, writer);
                return true;
            } catch (IOException ex) {
                ex.printStackTrace();
                return false;
            }
        }

 */
    }


}
