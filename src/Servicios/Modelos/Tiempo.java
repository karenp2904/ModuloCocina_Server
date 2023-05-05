package Servicios.Modelos;

public class Tiempo extends Thread{

    public Tiempo() {
    }
    public void run() {
            try {
                while (true) {
                    int tiempoRestante = 10;
                    while (tiempoRestante > 0) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        tiempoRestante--;
                        if (tiempoRestante == 0) {
                           ModeloCocina modeloCocina=new ModeloCocina();
                           modeloCocina.obtenerFacturas();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}
