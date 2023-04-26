import Admin.VistaAdmin.VistaAdmin;
import Admin.VistaAdmin.VistaPrincipal;
import Servidor.Controladores.*;
import Servidor.Servicios.*;
import Shared.Environment;
//
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        try {
                Properties properties= new Properties();
                try {
                    ServiceAdmin serviceAdmin = new ServiceAdmin(new ControllerAdmin());
                    Server modAdmin = new Server((String) properties.get("IP"), (String) properties.get("PORT0"), (String) properties.get("SERVICE0"), serviceAdmin);

                    properties.load(new FileInputStream(new File("src/server.properties")));
                    Server server = new Server(
                            (String) properties.get("IP"),
                            (String) properties.get("PORT"),
                            (String) properties.get("SERVICENAME"), serviceAdmin
                    );
                    server.deployService();
                    //System.out.print("Service on");
                    VistaPrincipal viewAdmin = new VistaPrincipal();
                    /*

                properties.load(new FileInputStream(new File("src/server.properties")));
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
