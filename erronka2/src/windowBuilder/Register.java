package windowBuilder;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Register extends JFrame {

    private JPanel contentPane;
    private JTextField txtErabiltzailea;
    private JPasswordField txtPasahitza;
    private JPasswordField txtPasahitzaBerretsi;
    private JLabel lblTituloa;
    private JLabel lblErabiltzailea;
    private JLabel lblKolorea;
    private JLabel lblPasahitza;
    private JLabel lblPasahitzaBerretsi;
    private JButton btnErregistratu;
    private JTextField txtKolorea;
    private JButton btnColorPicker;

    public Register() {
        // Configuración de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 486, 469);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Título
        lblTituloa = new JLabel("Erregistroa");
        lblTituloa.setHorizontalAlignment(SwingConstants.CENTER);
        lblTituloa.setFont(new Font("Arial", Font.BOLD, 20));
        lblTituloa.setBounds(120, 20, 200, 30);
        contentPane.add(lblTituloa);

        // Campo Username
        lblErabiltzailea = new JLabel("Agentziaren izena:");
        lblErabiltzailea.setBounds(30, 80, 100, 25);
        contentPane.add(lblErabiltzailea);

        txtErabiltzailea = new JTextField();
        txtErabiltzailea.setBounds(150, 80, 200, 25);
        contentPane.add(txtErabiltzailea);

        // Campo Email
        lblKolorea = new JLabel("Markaren kolorea:");
        lblKolorea.setBounds(30, 120, 100, 25);
        contentPane.add(lblKolorea);

        txtKolorea = new JTextField("#FFFFFF");
        txtKolorea.setBounds(150, 116, 100, 25);
        contentPane.add(txtKolorea);

        btnColorPicker = new JButton("...");
        btnColorPicker.setBounds(260, 116, 30, 25);
        contentPane.add(btnColorPicker);

        // Campo Password
        lblPasahitza = new JLabel("Pasahitza");
        lblPasahitza.setBounds(30, 255, 100, 25);
        contentPane.add(lblPasahitza);

        txtPasahitza = new JPasswordField();
        txtPasahitza.setBounds(150, 255, 200, 25);
        contentPane.add(txtPasahitza);

        // Campo Confirm Password
        lblPasahitzaBerretsi = new JLabel("Pasahitza berretsi");
        lblPasahitzaBerretsi.setBounds(30, 295, 150, 25);
        contentPane.add(lblPasahitzaBerretsi);

        txtPasahitzaBerretsi = new JPasswordField();
        txtPasahitzaBerretsi.setBounds(150, 295, 200, 25);
        contentPane.add(txtPasahitzaBerretsi);

        // Botón Register
        btnErregistratu = new JButton("Erregistratu");
        btnErregistratu.setBounds(150, 355, 100, 30);
        contentPane.add(btnErregistratu);
        
        JLabel lblempleatuak = new JLabel("Langile-kopuru:");
        lblempleatuak.setBounds(30, 162, 100, 25);
        contentPane.add(lblempleatuak);
        
        JLabel lblAgentziamota = new JLabel("Agentzia-mota:");
        lblAgentziamota.setBounds(30, 203, 100, 25);
        contentPane.add(lblAgentziamota);

        // Añadir ActionListener al botón de selección de color
        btnColorPicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hexColor = txtKolorea.getText().trim();
                try {
                    // Convertir el color hexadecimal a un objeto Color
                    Color color = Color.decode(hexColor);
                    // Establecer el color de fondo del botón
                    System.out.println(hexColor);
                    btnColorPicker.setBackground(color);
                } catch (NumberFormatException ex) {
                    // En caso de error en el formato, mostrar un color por defecto (rojo)
                    btnColorPicker.setBackground(Color.RED);
                }
            }
        });
        
        
    }
}