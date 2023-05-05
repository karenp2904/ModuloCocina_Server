package Cocina.VistaCocina;

import Cocina.ControladorCocina.ControladorCocina;
import Dominio.Pedido;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;


public class VistaCocina extends JFrame{
    JLabel fondo = new JLabel();
    JLayeredPane contenedor=new JLayeredPane();
    JPanel panelCentral=new JPanel();//panel para la pantalla de despacho
    JPanel panelBlanco = new JPanel();//panel para el registro
    JPanel panelDespacho=new JPanel();//panel para la impresion de la cola de pedidos
    JInternalFrame[] internalFrames;//Bancos de trabajos internos del panel
    public JButton botonBanco1=new JButton();
    JButton botonBanco2=new JButton();
    JButton botonBanco3=new JButton();
    JButton botonBanco4=new JButton();

    boolean entregado=false;
    int y;
    public VistaCocina(){
        this.setTitle("Hot Dogs Palace");
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setBackground(Color.white);
        y=30; //coordenadas de la pantalla de despacho
        panelDespachoPedidos();
    }

    public void crearTabla() throws PropertyVetoException {
        // Crear los frames internos de las colas de pedidos
        internalFrames = new JInternalFrame[1];
            int i=0;
        internalFrames[i] = createInternalFrame("Banco de trabajo " + (i + 1));
        internalFrames[i].setSize(new Dimension(600, 400)); // Redimensionar el frame interno
            internalFrames[i].setLocation(i * 420, 0); // Alinear los frames internos horizontalmente
            panelDespacho.add(internalFrames[i]);

    }

    //Metodos de la tablita
    public void agregarPedido(int bancoDeTrabajo,String nombrePedido, String cantidad,int bancoAsignado) {
        System.out.println(nombrePedido+cantidad+bancoAsignado);
        // Obtener la tabla de la cola de pedidos del banco de trabajo especificado
        JTable queueTable = (JTable) ((JScrollPane) internalFrames[bancoDeTrabajo - 1].getContentPane().getComponent(0)).getViewport().getView();
        DefaultTableModel tableModel = (DefaultTableModel) queueTable.getModel();

        // Obtener la información del nuevo pedido

        String banco = "Banco de trabajo " + bancoAsignado;

        // Agregar el nuevo pedido a la tabla de la cola de pedidos
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        tableModel.addRow(new Object[]{nombrePedido, cantidad, banco});
    }
    public void eliminarPedido(int bancoDeTrabajo) {
        try{
            // Obtener la tabla de la cola de pedidos del banco de trabajo especificado
            JTable queueTable = (JTable) ((JScrollPane) internalFrames[bancoDeTrabajo-1].getContentPane().getComponent(bancoDeTrabajo)).getViewport().getView();
            DefaultTableModel tableModel = (DefaultTableModel) queueTable.getModel();

            // Eliminar la primera fila de la tabla si existe
            if (tableModel.getRowCount() > 0) {
                tableModel.removeRow(0);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void ingresarIntento(int bancoDeTrabajo,String nombrePedido, String cantidad,int bancoAsignado){
        agregarPedido( bancoDeTrabajo, nombrePedido,  cantidad, bancoAsignado);
    }


    public void panelDespachoPedidos(){
        //Nuevo
        //panel de color blanco
        panelCentral.setLayout(null);
        panelCentral.setVisible(true);
        panelCentral.setOpaque(true);
        panelCentral.setBounds(0,0,1400,600);
        panelCentral.setBackground(Color.white);

        panelDespacho.setLayout(null);
        panelDespacho.setVisible(true);
        panelDespacho.setOpaque(true);
        panelDespacho.setBounds(200,150,800,700);
        panelDespacho.setBackground(new Color(217, 217, 217));

        JLabel titulo=new JLabel("PANTALLA DE PEDIDOS");
        titulo.setBackground(new Color(0,0,0));
        titulo.setFont(new Font("Arial", Font.BOLD, 40));
        titulo.setBounds(420,50,600,100);
        panelCentral.add(titulo);
/*
        JLabel pedido=new JLabel();
        pedido.setBackground(Color.black);
        pedido.setIcon(new ImageIcon("src/Imagenes/pedido.png"));
        pedido.setFont(new Font("Arial", Font.BOLD, 30));
        pedido.setBounds(400,200,200,75);


        JLabel puesto=new JLabel();
        puesto.setBackground(Color.black);
        puesto.setIcon(new ImageIcon("src/Imagenes/puesto.png"));
        puesto.setFont(new Font("Arial", Font.BOLD, 30));
        puesto.setBounds(600,200,200,75);

 */
        //Probandoooo
        String prueba;
        prueba="perro caliente";
        prueba="perro caliente";
        prueba="perro caliente";


        botonBanco1.setBounds(1000, 350, 200, 90);
        ImageIcon img= new ImageIcon("src/Imagenes/entregado1.png");// se le pone icono a boton
        // Icon i= new ImageIcon(img.getImage().getScaledInstance(botonPedidoListo.getWidth(), botonPedidoListo.getHeight(), Image.SCALE_DEFAULT));
        botonBanco1.setIcon(img);
        botonBanco1.setLayout(null);
        botonBanco1.setBackground(new Color(217, 217, 217));
        botonBanco1.setOpaque(false);
        botonBanco1.setBorderPainted(false);
        panelCentral.add(botonBanco1);

        botonBanco1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon imgadmin= new ImageIcon("Imagenes/entregado11.png");// se le pone icono a boton
                //  Icon iconAdmin= new ImageIcon(imgadmin.getImage().getScaledInstance(botonPedidoListo.getWidth(), botonPedidoListo.getHeight(), Image.SCALE_DEFAULT));
                botonBanco1.setBackground(new Color(217, 217, 217));
                entregado=true;
                try {
                    ControladorCocina controladorCocina = new ControladorCocina();
                    //controladorCocina.extraerPedido(true);
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                } catch (ParserConfigurationException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });

        entregado=false;

        JLabel fondoLetras=new JLabel();
        ImageIcon imagen =new ImageIcon("Imagenes/fondoLetras.png");
        fondoLetras.setIcon(imagen);
        fondoLetras.setSize(imagen.getIconWidth(), imagen.getIconHeight());
        this.setSize(imagen.getIconWidth(), imagen.getIconHeight());

        contenedor.add(fondoLetras,Integer.valueOf(6));
        contenedor.add(titulo,Integer.valueOf(7));
       // contenedor.add(puesto,Integer.valueOf(8));
        //contenedor.add(pedido,Integer.valueOf(8));
        contenedor.add(panelDespacho,Integer.valueOf(8));
        contenedor.add(botonBanco1,Integer.valueOf(9));


        contenedor();
      createWorkbenches(1,800,600);
      createInternalFrame("Pedidos");
    }

    public void createWorkbenches(int numWorkbenches, int width, int height) {
        // Crear los frames internos de los bancos de trabajo
        internalFrames = new JInternalFrame[numWorkbenches];
        for (int i = 0; i < numWorkbenches; i++) {
            internalFrames[i] = createInternalFrame("Cocina " );
            internalFrames[i].setSize(new Dimension(width, height)); // Redimensionar el frame interno
            internalFrames[i].setLocation(i * (width + 20), 0); // Alinear los frames internos horizontalmente
            panelDespacho.add(internalFrames[i]);
        }

        // Configurar la ventana principal
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int preferredWidth = numWorkbenches * (width + 20); // Calcular el ancho preferido de la ventana principal
        int preferredHeight = height + 50; // Calcular la altura preferida de la ventana principal
        //this.setPreferredSize(new Dimension(preferredWidth, preferredHeight)); // Establecer el tamaño preferido de la ventana principal
        this.pack();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        this.setVisible(true);
    }

    private JInternalFrame createInternalFrame(String title) {
        // Crear el panel de la cola de pedidos
        JPanel queuePanel = new JPanel(new GridLayout(1, 1));
        queuePanel.setPreferredSize(new Dimension(600, 500)); // Establecer el tamaño preferido del panel
        DefaultTableModel tableModel = new DefaultTableModel(new Object[][]{}, new String[]{"Pedido", "Cantidad", "Banco de trabajo"});
        JTable queueTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(queueTable);
        queuePanel.add(scrollPane);

        // Configurar el frame interno
        JInternalFrame internalFrame = new JInternalFrame(title, true, false, true, true);
        internalFrame.setContentPane(queuePanel);
        Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        internalFrame.getRootPane().setBorder(border);
        internalFrame.setVisible(true);

        return internalFrame;
    }

    public void editarColaDeDespacho(Pedido pedido, int puestoTrabajo){


        JLabel titulo=new JLabel(pedido.getProductoNombre()+ "   "+ pedido.getCantidad());
        titulo.setBackground(Color.black);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBounds(50,y-5,600,100);
        panelDespacho.add(titulo);

        JLabel puesto=new JLabel(String.valueOf(puestoTrabajo));
        puesto.setBackground(Color.black);
        puesto.setFont(new Font("Arial", Font.BOLD, 20));
        puesto.setBounds(300,y-5,600,100);
        panelDespacho.add(puesto);

        y+=50;//se le agrega distancia a y para la ubicacion del texto
    }

    public boolean pedidoEntregado(){
        return entregado;
    }


    public void contenedor(){
        this.getContentPane().add(contenedor);
        this.setSize(getMaximumSize());
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

      /*
        public void panelRegistro(){
            //Panel que tendrá las etiquetas y botones
            panelBlanco.setLayout(null);
            panelBlanco.setVisible(true);
            panelBlanco.setOpaque(true);
            panelBlanco.setBounds(160,80,400,500);
            panelBlanco.setBackground(Color.white);


            JLabel logo=new JLabel("Logo");
            logo.setBounds(110,10,150,150);
            ImageIcon imgLogo= new ImageIcon("ModuloCocina/src/Imagenes/logoPerrito.png");// se le pone icono a boton
            Icon ilogo= new ImageIcon(imgLogo.getImage().getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_DEFAULT));
            logo.setIcon(ilogo);
            panelBlanco.add(logo);

            JLabel cocinaText=new JLabel("COCINA");
            cocinaText.setBackground(Color.black);
            cocinaText.setFont(new Font("Arial", Font.BOLD, 20));
            cocinaText.setBounds(120,140,200,100);
            panelBlanco.add(cocinaText);


            JLabel nombreUsuario=new JLabel("Usuario");
            nombreUsuario.setBounds(40,180,200,100);
            panelBlanco.add(nombreUsuario);

           String usuario=registroNombreUsuario();

            JLabel contraseña=new JLabel("Contraseña");
            contraseña.setBounds(40,290,200,100);
            panelBlanco.add(contraseña);

            String contraseñaa=registroContraseñaUsuario();

            JButton botonRegistrar=new JButton();
            botonRegistrar.setBounds(140, 420, 100, 50);
            ImageIcon img= new ImageIcon("ModuloCocina/src/Imagenes/INGRESAR.png");// se le pone icono a boton
            Icon i= new ImageIcon(img.getImage().getScaledInstance(botonRegistrar.getWidth(), botonRegistrar.getHeight(), Image.SCALE_DEFAULT));
            botonRegistrar.setIcon(i);
            botonRegistrar.setLayout(null);
            botonRegistrar.setOpaque(true);
            botonRegistrar.setBorderPainted(false);
            ImageIcon imgadmin= new ImageIcon("ModuloCocina/src/Imagenes/INGRESAR2.png");// se le pone icono a boton
            Icon iconAdmin= new ImageIcon(imgadmin.getImage().getScaledInstance(botonRegistrar.getWidth(), botonRegistrar.getHeight(), Image.SCALE_DEFAULT));
            botonRegistrar.setRolloverIcon(iconAdmin);
            botonRegistrar.setBackground(Color.white);

            botonRegistrar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panelBlanco.setVisible(false);
                    fondo.setVisible(false);
                    panelCentral.setVisible(true);
                    panelDespachoPedidos();

                    contenedor.add(panelCentral,Integer.valueOf(6));


                }
            });
            panelBlanco.add(botonRegistrar);

            //se llama al contenedor
            contenedor();

            ImageIcon imagen =new ImageIcon("ModuloCocina/src/Imagenes/loginCocina.png");
            fondo.setIcon(imagen);
            fondo.setSize(imagen.getIconWidth(), imagen.getIconHeight());
            //fondo.setBounds(100,40,1400,600);

            contenedor.add(panelBlanco,Integer.valueOf(5));
            contenedor.add(fondo,Integer.valueOf(4));
            //  contenedor.add(panelInicio,Integer.valueOf(2));
            this.setSize(imagen.getIconWidth(), imagen.getIconHeight());
        }

         public String registroNombreUsuario(){
        JTextField txusuario=new JTextField();
        txusuario.setBounds(30,250,300,50);
        panelBlanco.add(txusuario);
        String usuario=txusuario.getText();
        return usuario;
    }

    public String registroContraseñaUsuario(){
        JTextField txcontraseña=new JTextField();
        txcontraseña.setBounds(30,360,300,50);
        panelBlanco.add(txcontraseña);
        String contraseña=txcontraseña.getText();
        return contraseña;
    }
    */

}
