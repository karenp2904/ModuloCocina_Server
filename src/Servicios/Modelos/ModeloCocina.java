package Servicios.Modelos;

import Estructuras.APriorityQueue.PriorityQueue;
import Estructuras.Colas.ColasArray;
import Dominio.Cliente;
import Dominio.Factura;
import Dominio.Pedido;
import Servicios.Controladores.IControllerCocina;

import java.io.Serializable;

public class ModeloCocina implements IControllerCocina, Serializable {

    // el archivo se guarda- guardar cola de despacho de los pedidos- en el metodo de guardarPedido();
    PriorityQueue<Pedido> colaDespacho=new PriorityQueue(5);
    ColasArray numFogon=new ColasArray();
    @Override
    public Factura extraerPedido() {
        //este objeto es temporal mientras se devuelven los archivos, llenar esos espacios cuando se tenga la info
        Factura fact= new Factura(new Pedido("Papas", "8","6"), new Cliente("berta", "giron", "73773737", "premium"));
        Factura fact3= new Factura(new Pedido("Perro", "8","6"), new Cliente("berta", "Pidecuesta", "73773737", "premium"));
        Factura fact2= new Factura(new Pedido("Perrito", "10","6"), new Cliente("berta", "provenza", "73773737", "normal"));
       // guardarPedidos(fact,clasificarPedidoPrioridad(fact));
        guardarPedidos(fact,clasificarPedidoPrioridad(fact));
        guardarPedidos(fact3,clasificarPedidoPrioridad(fact3));
        guardarPedidos(fact2,clasificarPedidoPrioridad(fact2));
        return null;
    }

    @Override
    public PriorityQueue<Pedido> pantallaDePedidos() {
        return colaDespacho; // aqui se muestra la pantalla en la vista y se extrae
    }

    private PriorityQueue guardarPedidos(Factura factura, int prioridad) {
        System.out.println(colaDespacho.toString());
        return colaDespacho;//aqui se guarda en el archivo
    }


    public int entregarNumeroFogon(Pedido pedido) {
        return colaDespacho.searchPriority(pedido);
    }

    @Override
    public int clasificarPedidoPrioridad(Factura factura) {
        int prioridadDefinida=0;

        int tiempoCoccion=Integer.parseInt(factura.getPedido().getCodigo());// se convierte para comparar
        boolean clientePremium=false;
        int prioridadDireccion=definirPrioridadPorDireccion(factura.getCliente());

        if(factura.getCliente().getTipoCuenta().toLowerCase().equals("premium")){ // se evalua el tipo de cliente
            clientePremium=true;
        }

        if((tiempoCoccion<10 || prioridadDireccion==3 || prioridadDireccion==4) && (clientePremium==false) ){ // comida rapida
            //opciones puesto 3 o 4
            //verificar la cantidad de elementos que tiene el puesto 3 y 4 y encolar en el menor
            if (colaDespacho.sizePrioridad(3)>colaDespacho.sizePrioridad(4)){
                colaDespacho.insert(factura.getPedido(),3);
                prioridadDefinida=3;
            }else{
                colaDespacho.insert(factura.getPedido(),4);
                prioridadDefinida=4;
            }

        }else if( (tiempoCoccion<10 || prioridadDireccion==2) && ( clientePremium==true)) {
            //opcion 2
            colaDespacho.insert(factura.getPedido(),2);
            prioridadDefinida=2;

        }else if(tiempoCoccion>=10 || prioridadDireccion==1){// coccion lenta
            //opcion 1 - Mayor prioridad
            if(clientePremium){
                colaDespacho.insertFirst(factura.getPedido(),1);
            }else{
                colaDespacho.insert(factura.getPedido(),1);
            }
            prioridadDefinida=1;
        }
        return prioridadDefinida;
    }

    private int definirPrioridadPorDireccion(Cliente cliente){

        int direccionLejana=0; //entre mas lejos sera un numero menor
        if(cliente.getDireccionCliente().contains(String.valueOf("Giron"))){
            direccionLejana=1;
        }
        if(cliente.getDireccionCliente().contains(String.valueOf("Pidecuesta"))){
            direccionLejana=1;
        }
        if(cliente.getDireccionCliente().contains(String.valueOf("Buscaramanga"))){
            direccionLejana=2;
        }
        if(cliente.getDireccionCliente().contains(String.valueOf("Florida"))){
            direccionLejana=3;
        }
        if(cliente.getDireccionCliente().contains(String.valueOf("Cañaveral"))){
            direccionLejana=4;
        }
        if(cliente.getDireccionCliente().contains(String.valueOf("Provenza"))){
            direccionLejana=4;
        }
        return direccionLejana;
    }

    @Override
    public boolean entregarPedido(Boolean estado) {
        if(estado){
            return quitarPedido();
        }else{
            return false;
        }
    }

    private boolean quitarPedido(){
        if(!colaDespacho.isEmpty()){
            colaDespacho.extract();
            return true;
        }else{
            return false;
        }
    }


}
