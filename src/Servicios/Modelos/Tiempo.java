package Servicios.Modelos;

import Cocina.ControladorCocina.ControladorCocina;
import Dominio.Factura;
import Estructuras.ListasEnlaceDoble.LinkedList;

import javax.xml.parsers.ParserConfigurationException;

public class Tiempo extends Thread{

    public Tiempo() {
    }
    public void run() {
        try {
                while (true) {
                    int tiempoRestante = 500;
                    while (tiempoRestante > 0) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        tiempoRestante--;
                        if (tiempoRestante == 0) {
                            facturas();
                           ModeloCocina modeloCocina=new ModeloCocina();
                           modeloCocina.obtenerFacturas();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public LinkedList<Factura> facturas() throws ParserConfigurationException {
            ModeloOperador modeloOperador=new ModeloOperador();
            return modeloOperador.obtenerFacturas();
        }
}
