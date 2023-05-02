import Admin.ControladorAdmin.ControladorAdmin;
import Admin.VistaAdmin.VistaPrincipal;
import Servicios.Controladores.*;
import Servicios.Server;
import Servicios.ServiceOperador;
import Servicios.ServiceRepartidor;
import Servicios.ServiceOperador;
//
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        try {
                try {
                    Properties properties= new Properties();
                    properties.load(new FileInputStream(new File("src/serverOperador.properties")));

                    ServiceOperador serviceOperador = new ServiceOperador(new ControllerOperador());
                    Server modOperador = new Server((String) properties.get("IP"),
                            (String) properties.get("PORT"),
                            (String) properties.get("SERVICE"),
                            serviceOperador);

                    /*
                    Properties propertiess= new Properties();
                    propertiess.load(new FileInputStream(new File("src/serverRepartidor.properties")));
                    ServiceRepartidor serviceRepartidor = new ServiceRepartidor(new ControllerRepartidor());
                    Server modRepartidor = new Server((String) propertiess.get("IP"),
                            (String) propertiess.get("PORT"),
                            (String) propertiess.get("SERVICE"),
                            serviceRepartidor);

                     */

                    /*
                    properties.load(new FileInputStream(new File("src/serverAdmin.properties")));
                    ServiceAdmin serviceAdmin = new ServiceAdmin(new ControllerAdmin());
                    Server modAdmin = new Server((String) properties.get("IP"),
                            (String) properties.get("PORT"),
                            (String) properties.get("SERVICE"),
                            serviceAdmin);

                    properties.load(new FileInputStream(new File("src/serverCocina.properties")));
                    ServiceCocina serviceCocina = new ServiceCocina(new ControllerCocina());
                    Server modCocina = new Server((String) properties.get("IP"),
                            (String) properties.get("PORT"),
                            (String) properties.get("SERVICE"),
                            serviceCocina);


                     */

                   // Server server = new Server((String) properties.get("IP"), (String) properties.get("PORT"), (String) properties.get("SERVICENAME"), serviceOperador);

                    Thread[] threadList = { new Thread(modOperador)
                            //, new Thread(modRepartidor),
                          //  new Thread(modAdmin), new Thread(modCocina)
                    };

                    for (Thread thread : threadList) { //hilos
                        thread.start();
                    }

                    //metodos para acceder a la cocina y al admin que se encuentran servidor
                    VistaPrincipal viewAdmin = new VistaPrincipal();
                    ControladorAdmin controladorAdmin = new ControladorAdmin();
                    controladorAdmin.start();

                    /*
                    ControladorCocina controladorCocina=new ControladorCocina();

                    controladorCocina.start();

                     */

                    /*

                properties.load(new FileInputStream(new File("src/serverOperador.properties")));
                ServiceAdmin serviceAdmin = new ServiceAdmin(new ControllerAdmin());
                Server modAdmin = new Server((String) properties.get("IP"), (String) properties.get("PORT0"), (String) properties.get("SERVICE0"), serviceAdmin);

                ServiceCocina serviceCocina = new ServiceCocina(new ControllerCocina());
                Server modCocina = new Server((String)properties.get("IP"),(String) properties.get("PORT1"),(String) properties.get("SERVICE1"), serviceCocina);

                ServiceOperador serviceOperador = new ServiceOperador(new ControllerOperador());
                Server modOperador = new Server((String)properties.get("IP"),(String) properties.get("PORT2"),(String) properties.get("SERVICE2"), serviceOperador);

                ServiceRepartidor serviceRepartidor = new ServiceRepartidor(new ControllerRepartidor());
                Server modRepartidor = new Server((String)properties.get("IP"), (String)properties.get("PORT4"), (String)properties.get("SERVICE4"), serviceRepartidor);

                System.out.print("Service on");

                VistaAdmin viewAdmin = new VistaAdmin();

                Thread[] threadList = {new Thread(modAdmin), new Thread(modOperador), new Thread(modCocina), new Thread(modRepartidor)};

                for (Thread thread : threadList) { //hilos
                    thread.start();
                }

                     */
                Logger.getLogger("Server").log(Level.INFO, "Server is running...");

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //Map<String, String> properties = Environment.getInstance().getVariables();

        } catch (Exception e) {
            Logger.getLogger("Server").log(Level.WARNING, e.getMessage(), e);
        }
    }

}
