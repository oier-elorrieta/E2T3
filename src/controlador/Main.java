package controlador;

import java.awt.Color;
import vista.*;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main {
    private JFrame frame;
    private JButton btnOngiEtorri;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main window = new Main();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Main() {
        try {
            // Cambiar a una vista algo mas moderna
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.getContentPane().setBackground(Color.WHITE); // Light background

        // Crear un boton con estilo
        btnOngiEtorri = new JButton("Ongi Etorri");
        btnOngiEtorri.setFont(new Font("Arial", Font.BOLD, 24)); // Cambiar la letra y ponerla ne negrita
        btnOngiEtorri.setForeground(Color.BLACK); // el texto en blanco
        btnOngiEtorri.setBackground(Color.WHITE); // Gradient button background (teal)
        
        btnOngiEtorri.setFocusPainted(false); // quitar la concentración del boton cuando se le hace click
        btnOngiEtorri.setBorderPainted(false); // quitar el borde del botón
        btnOngiEtorri.setBounds(108, 64, 222, 130); 
        btnOngiEtorri.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	//instancio la clase leihoarenFuntzioak para poder acceder al jframe de login
                LeihoarenFuntioak fv = new LeihoarenFuntioak();
                //llamamos al metodo irekiLogin
                fv.irekiLogin();
                //escondemos el frame para que no se vea la pantalla ongietorri
                frame.setVisible(false);
            }
        });
        // añadimos el boton de ongietorri en el frame
        frame.getContentPane().add(btnOngiEtorri);
    }
}