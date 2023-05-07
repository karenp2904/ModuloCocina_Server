package Servicios.Modelos;

import Dominio.Factura;
import Estructuras.APriorityQueue.PriorityQueue;
import Dominio.Cliente;
import Estructuras.ListasEnlaceDoble.LinkedList;
import Servicios.Controladores.IControllerCocina;

import javax.xml.parsers.ParserConfigurationException;
import java.io.Serializable;

public class ModeloCocina implements IControllerCocina, Serializable {

    // el archivo se guarda- guardar cola de despacho de los pedidos- en el metodo de guardarPedido();
    PriorityQueue<Factura> colaDespacho=new PriorityQueue(5);


    public ModeloCocina() throws ParserConfigurationException {

    }

    @Override
    public boolean obtenerFacturas() {
        try {
          //  ObtenerFacturasTiempo facturalTiempo=new ObtenerFacturasTiempo();
           // facturalTiempo.start();
           // Tiempo tiempo=new Tiempo();
            //tiempo.start();
            ModeloOperador operador = new ModeloOperador();
            LinkedList<Factura> listaFactura=operador.obtenerFacturas();
            System.out.println("lista en cocina");
          //  System.out.println(listaFactura.toString());
            while (!listaFactura.isEmpty()){
                Factura factura=listaFactura.popHead();
                System.out.println("Factura de xml" +factura);
                guardarPedidos(factura,clasificarPedidoPrioridad(factura));
            }

            return true;
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PriorityQueue<Factura> pantallaDePedidos() {
       if(colaDespacho.isEmpty()){
           obtenerFacturas();
       }else{
           obtenerFacturas();
       }
        System.out.println("\nLa pantalla de pedidos es:");
        System.out.println(colaDespacho.toString());
        return colaDespacho; // aqui se muestra la pantalla en la vista y se extrae
    }

    private PriorityQueue<Factura> guardarPedidos(Factura factura, int prioridad) {
        //System.out.println("factura"+factura +""+ prioridad);
       // System.out.println("Cola en modelo");
       // System.out.println(colaDespacho.toString());
       // colaDespacho.insert(new Factura(new Pedido("perro","1","22"),new Cliente("sksks","skjdks","skkskd","skkss"),"2"),3);
        colaDespacho.insertFirst(factura,1);
        return colaDespacho;//aqui se guarda en el archivo
    }


    public int entregarNumeroFogon(Factura factura) {
        return colaDespacho.searchPriority(factura);
    }

    @Override
    public int clasificarPedidoPrioridad(Factura factura) {
        int prioridadDefinida=0;
        Integer tiempoCoccion=0;
        try {
            tiempoCoccion=Integer.parseInt(factura.getPedido().getCodigo());// se convierte para comparar
            // Perform comparison using tiempoCoccion
        } catch (NumberFormatException e) {
          e.printStackTrace();
        }


        boolean clientePremium=false;
        int prioridadDireccion=definirPrioridadPorDireccion(factura.getCliente());

        if(factura.getCliente().getTipoCuenta().toLowerCase().contains("premium")){ // se evalua el tipo de cliente
            clientePremium=true;
        }

        // Si el tiempo de cocción es menor a 10 minutos o la prioridad por dirección es 3 o 4 y el cliente no es premium
        if((tiempoCoccion<10 || prioridadDireccion==3 || prioridadDireccion==4) && (clientePremium==false) ){ // comida rapida
            //opciones puesto 3 o 4
            //verificar la cantidad de elementos que tiene el puesto 3 y 4 y encolar en el menor
            if (colaDespacho.sizePrioridad(3)>colaDespacho.sizePrioridad(4)){
                colaDespacho.insert(factura,3);
               // System.out.println("Insertado en la cola");
                prioridadDefinida=3;
            }else{
                //System.out.println("Insertado en la cola");
                colaDespacho.insert(factura,4);
                prioridadDefinida=4;
            }
        // Si el tiempo de cocción es menor a 10 minutos y la prioridad por dirección es 2 y el cliente es premium
        }else if( (tiempoCoccion<10 || prioridadDireccion==2) && ( clientePremium==true)) {
            //opcion 2
            colaDespacho.insert(factura,2);
            prioridadDefinida=2;
        // Si el tiempo de cocción es mayor o igual a 10 minutos o la prioridad por dirección es 1
        }else if(tiempoCoccion>=10 || prioridadDireccion==1){// coccion lenta
            //opcion 1 - Mayor prioridad
            if(clientePremium){
                // Si el cliente es premium, se encola el pedido al principio de la cola de prioridad 1
                colaDespacho.insertFirst(factura,1);
            }else{
                // Si el cliente no es premium, se encola el pedido al final de la cola de prioridad 1
                colaDespacho.insert(factura,1);
            }
            prioridadDefinida=1;
        }
        // Se muestra la prioridad definida y se devuelve
        System.out.println("prioridad definidda  "+ prioridadDefinida);
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
        System.out.println("direccion" + direccionLejana);
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
