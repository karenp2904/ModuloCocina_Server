package Admin.VistaAdmin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaAdminDatos extends JFrame {
    JPanel panelCentral =new JPanel();
    JLabel fondo = new JLabel();
    JLayeredPane contenedor=new JLayeredPane();
    JButton botonIngresar=new JButton(); //boton para ingresar pedido
    JButton botonIngresarOpera=new JButton();
    JTextField txtNombreoperador = new JTextField();
    JTextField txtUseroperador = new JTextField();
    JTextField txtContraOperador = new JTextField();
    JTextField txtnombreRepartidor = new JTextField();
    JTextField txtUsuarioRepartidor = new JTextField();

    JTextField txContraseñaRepartidor = new JTextField();

    boolean estadoOperador,estadoRepartidor;
    String contraseOperador,nombreOperador,usuarioOperador, usuarioRepartidor, nombreRepartidor, contraseñaRepartidor;

    public VistaAdminDatos(){
        this.setTitle("Hot Dogs Palace");
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setBackground(Color.white);
        contenedor();
    }

    public void ingresarRepartidor(){
        panelCentral.setLayout(null);
        panelCentral.setVisible(true);
        panelCentral.setOpaque(true);
        panelCentral.setBounds(200, 70, 900, 600);
        panelCentral.setBackground(Color.white);

        //logo de la salchica para el fondo
        JLabel logo = new JLabel("Logo");
        logo.setBounds(70, 130, 350, 350);
        ImageIcon imgLogo = new ImageIcon("src/Imagenes/FotoPerrito.png");// se le pone icono a boton
        Icon ilogo = new ImageIcon(imgLogo.getImage().getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_DEFAULT));
        logo.setIcon(ilogo);
        panelCentral.add(logo);

        //letrero del registro de clinetes en el panel
        JLabel letreroRegistro = new JLabel(" REGISTRAR REPARTIDOR");
        letreroRegistro.setBackground(Color.black);
        letreroRegistro.setFont(new Font("Arial", Font.BOLD, 40));
        letreroRegistro.setBounds(230, -20, 600, 200);
        panelCentral.add(letreroRegistro);


        //registro con la informacion del cliente
        //Label del nombre
        JLabel nombretext = new JLabel("NOMBRE: ");
        nombretext.setBackground(Color.black);
        nombretext.setFont(new Font("Arial", Font.BOLD, 20));
        nombretext.setBounds(480, 170, 200, 100);
        panelCentral.add(nombretext);
        //Label del numero telefonico
        JLabel telefonoText = new JLabel("USUARIO: ");
        telefonoText.setBackground(Color.black);
        telefonoText.setFont(new Font("Arial", Font.BOLD, 20));
        telefonoText.setBounds(480, 220, 200, 100);
        panelCentral.add(telefonoText);

        JLabel direccionText = new JLabel("CONTRASEÑA: ");
        direccionText.setBackground(Color.black);
        direccionText.setFont(new Font("Arial", Font.BOLD, 20));
        direccionText.setBounds(480, 270, 200, 100);
        panelCentral.add(direccionText);

        String nombre= ingresarNombreRepartidor();
        String usuario= ingresarUsuarioRepartidor();
        String contra= ingresarContraseñaRepartidor();

        botonIngresar.setBounds(630, 400, 200, 80);
        ImageIcon imgR= new ImageIcon("src/Imagenes/btningresarPedido.png");// se le pone icono a boton
        Icon i= new ImageIcon(imgR.getImage().getScaledInstance(botonIngresar.getWidth(), botonIngresar.getHeight(), Image.SCALE_DEFAULT));
        botonIngresar.setIcon(i);
        botonIngresar.setLayout(null);
        botonIngresar.setOpaque(true);
        botonIngresar.setBorderPainted(false);
        ImageIcon imgadmin= new ImageIcon("src/Imagenes/btningresarPedido2.png");// se le pone icono a boton
        Icon iconAdmin= new ImageIcon(imgadmin.getImage().getScaledInstance(botonIngresar.getWidth(), botonIngresar.getHeight(), Image.SCALE_DEFAULT));
        botonIngresar.setRolloverIcon(iconAdmin);
        botonIngresar.setBackground(Color.white);

        botonIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaAdmin admin=new VistaAdmin();
                admin.panelMenu();
                nombreRepartidor=txtnombreRepartidor.getText();
                usuarioRepartidor=txtUsuarioRepartidor.getText();
                contraseñaRepartidor=txContraseñaRepartidor.getText();
                dispose();
            }
        });
        panelCentral.add(botonIngresar);

        contenedor.add(panelCentral,Integer.valueOf(1));

    }

    public boolean botonActivoOperador(){
        return estadoOperador;
    }

    public String ingresarNombreOperador(){
        txtNombreoperador.setBackground(Color.white);
        txtNombreoperador.setFont(new Font("Arial", Font.BOLD, 20));
        txtNombreoperador.setBounds(640, 200, 220, 40);
        panelCentral.add(txtNombreoperador);
        return nombreOperador;
    }

    public String ingresarUsuarioOperador() {
        txtUseroperador.setBackground(Color.white);
        // txTelefono.setBackground(new Color(217,217,217));
        txtUseroperador.setFont(new Font("Arial", Font.BOLD, 20));
        txtUseroperador.setBounds(640, 250, 220, 40);
        panelCentral.add(txtUseroperador);
        return usuarioOperador;
    }

    public String ingresarContraseñaOperador(){
        txtContraOperador.setBackground(Color.white);
        txtContraOperador.setFont(new Font("Arial", Font.BOLD, 20));
        txtContraOperador.setBounds(640, 300, 220, 40);
        panelCentral.add(txtContraOperador);
        return contraseOperador;

    }


    public String ingresarNombreRepartidor(){
        txtnombreRepartidor.setBackground(Color.white);
        txtnombreRepartidor.setFont(new Font("Arial", Font.BOLD, 20));
        txtnombreRepartidor.setBounds(640, 200, 220, 40);
        panelCentral.add(txtnombreRepartidor);
        return nombreRepartidor;
    }

    public String ingresarUsuarioRepartidor(){
        txtUsuarioRepartidor.setBackground(Color.white);
        // txTelefono.setBackground(new Color(217,217,217));
        txtUsuarioRepartidor.setFont(new Font("Arial", Font.BOLD, 20));
        txtUsuarioRepartidor.setBounds(640, 250, 220, 40);
        panelCentral.add(txtUsuarioRepartidor);
        return usuarioRepartidor;
    }

    public String ingresarContraseñaRepartidor(){
        txContraseñaRepartidor.setBackground(Color.white);
        txContraseñaRepartidor.setFont(new Font("Arial", Font.BOLD, 20));
        txContraseñaRepartidor.setBounds(640, 300, 220, 40);
        panelCentral.add(txContraseñaRepartidor);
        return contraseñaRepartidor;
    }



    public void ingresarOperador(){
        panelCentral.setLayout(null);
        panelCentral.setVisible(true);
        panelCentral.setOpaque(true);
        panelCentral.setBounds(200, 70, 900, 600);
        panelCentral.setBackground(Color.white);

        //logo de la salchica para el fondo
        JLabel logo = new JLabel("Logo");
        logo.setBounds(70, 130, 350, 350);
        ImageIcon imgLogo = new ImageIcon("src/Imagenes/FotoPerrito.png");// se le pone icono a boton
        Icon ilogo = new ImageIcon(imgLogo.getImage().getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_DEFAULT));
        logo.setIcon(ilogo);
        panelCentral.add(logo);

        //letrero del registro de clinetes en el panel
        JLabel letreroRegistro = new JLabel(" REGISTARAR OPERADOR");
        letreroRegistro.setBackground(Color.black);
        letreroRegistro.setFont(new Font("Arial", Font.BOLD, 40));
        letreroRegistro.setBounds(230, -20, 600, 200);
        panelCentral.add(letreroRegistro);


        //registro con la informacion del cliente
        //Label del nombre
        JLabel nombretext = new JLabel("NOMBRE: ");
        nombretext.setBackground(Color.black);
        nombretext.setFont(new Font("Arial", Font.BOLD, 20));
        nombretext.setBounds(480, 170, 200, 100);
        panelCentral.add(nombretext);
        //Label del numero telefonico
        JLabel telefonoText = new JLabel("USUARIO: ");
        telefonoText.setBackground(Color.black);
        telefonoText.setFont(new Font("Arial", Font.BOLD, 20));
        telefonoText.setBounds(480, 220, 200, 100);
        panelCentral.add(telefonoText);

        JLabel direccionText = new JLabel("CONTRASEÑA: ");
        direccionText.setBackground(Color.black);
        direccionText.setFont(new Font("Arial", Font.BOLD, 20));
        direccionText.setBounds(480, 270, 200, 100);
        panelCentral.add(direccionText);


        String nombre=ingresarNombreOperador();
        String usuario= ingresarUsuarioOperador();
        String contra= ingresarContraseñaOperador();



        botonIngresarOpera.setBounds(630, 400, 200, 80);
        ImageIcon imgR= new ImageIcon("src/Imagenes/btningresarPedido.png");// se le pone icono a boton
        Icon i= new ImageIcon(imgR.getImage().getScaledInstance(botonIngresarOpera.getWidth(), botonIngresarOpera.getHeight(), Image.SCALE_DEFAULT));
        botonIngresarOpera.setIcon(i);
        botonIngresarOpera.setLayout(null);
        botonIngresarOpera.setOpaque(true);
        botonIngresarOpera.setBorderPainted(false);
        ImageIcon imgadmin= new ImageIcon("src/Imagenes/btningresarPedido2.png");// se le pone icono a boton
        Icon iconAdmin= new ImageIcon(imgadmin.getImage().getScaledInstance(botonIngresarOpera.getWidth(), botonIngresarOpera.getHeight(), Image.SCALE_DEFAULT));
        botonIngresarOpera.setRolloverIcon(iconAdmin);
        botonIngresarOpera.setBackground(Color.white);
        botonIngresarOpera.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                estadoOperador=true;
                nombreOperador=txtNombreoperador.getText();
                usuarioOperador=txtUseroperador.getText();
                contraseOperador=txtContraOperador.getText();
                VistaAdmin admin=new VistaAdmin();
                admin.panelMenu();
                dispose();


            }
        });
        panelCentral.add(botonIngresarOpera);

        contenedor.add(panelCentral,Integer.valueOf(1));

    }
    public void contenedor(){
        ImageIcon imagen =new ImageIcon("src/Imagenes/fondoLetras.png");
        fondo.setIcon(imagen);
        fondo.setSize(imagen.getIconWidth(), imagen.getIconHeight());
        //fondo.setBounds(100,40,1400,600);


        contenedor.add(fondo,Integer.valueOf(0));

        //  contenedor.add(panelInicio,Integer.valueOf(2));

        this.getContentPane().add(contenedor);
        this.setSize(getMaximumSize());
        this.setSize(imagen.getIconWidth(), imagen.getIconHeight());
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
