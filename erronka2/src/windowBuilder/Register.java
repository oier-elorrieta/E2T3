package windowBuilder;

import java.awt.Font;
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
    private JTextField txtEmail;
    private JPasswordField txtPasahitza;
    private JPasswordField txtPasahitzaBerretsi;
    private JLabel lblTituloa;
    private JLabel lblErabiltzailea;
    private JLabel lblEmail;
    private JLabel lblPasahitza;
    private JLabel lblPasahitzaBerretsi;
    private JButton btnErregistratu;


    public Register() {
        // Configuración de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 400);
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
        lblErabiltzailea = new JLabel("Erabiltzailea");
        lblErabiltzailea.setBounds(30, 80, 100, 25);
        contentPane.add(lblErabiltzailea);

        txtErabiltzailea = new JTextField();
        txtErabiltzailea.setBounds(150, 80, 200, 25);
        contentPane.add(txtErabiltzailea);

        // Campo Email
        lblEmail = new JLabel("Email:");
        lblEmail.setBounds(30, 120, 100, 25);
        contentPane.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(150, 120, 200, 25);
        contentPane.add(txtEmail);

        // Campo Password
        lblPasahitza = new JLabel("Pasahitza");
        lblPasahitza.setBounds(30, 160, 100, 25);
        contentPane.add(lblPasahitza);

        txtPasahitza = new JPasswordField();
        txtPasahitza.setBounds(150, 160, 200, 25);
        contentPane.add(txtPasahitza);

        // Campo Confirm Password
        lblPasahitzaBerretsi = new JLabel("Pasahitza berretsi");
        lblPasahitzaBerretsi.setBounds(30, 200, 150, 25);
        contentPane.add(lblPasahitzaBerretsi);

        txtPasahitzaBerretsi = new JPasswordField();
        txtPasahitzaBerretsi.setBounds(150, 200, 200, 25);
        contentPane.add(txtPasahitzaBerretsi);

        // Botón Register
        btnErregistratu = new JButton("Erregistratu");
        btnErregistratu.setBounds(150, 260, 100, 30);
        contentPane.add(btnErregistratu);
    }
}
