package Servicios.Modelos;

public class ObtenerFacturasTiempo extends Thread{
    public ObtenerFacturasTiempo() {
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

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
