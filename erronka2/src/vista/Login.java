package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import modelo.pojo.Cache;
import modelo.sql.SqlMetodoak;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

@SuppressWarnings("serial")
public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtErabiltzailea;
	private JPasswordField txtPasahitza;
	private JLabel lblErabiltzailea, lblPasahitza, lblLogIn, lblFondo;
	private JButton btnSartu, btnRegister, btnMostrarContrasenas;
	private Cache cache = new Cache();

	public Login() {
		// para quitar el valor que se queda en el cache
		cache.setAgentzia(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		lblLogIn = new JLabel("Log In");
		lblLogIn.setFont(new Font("ROG Fonts", Font.PLAIN, 24));
		lblLogIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogIn.setBounds(0, 0, 434, 47);
		getContentPane().add(lblLogIn);

		txtErabiltzailea = new JTextField();
		txtErabiltzailea.setBounds(197, 79, 121, 30);
		getContentPane().add(txtErabiltzailea);
		txtErabiltzailea.setColumns(10);

		lblErabiltzailea = new JLabel("Agentziaren izena:");
		lblErabiltzailea.setForeground(new Color(255, 255, 255));
		lblErabiltzailea.setBounds(85, 87, 112, 14);
		getContentPane().add(lblErabiltzailea);

		// Campo Password
		lblPasahitza = new JLabel("Pasahitza:");
		lblPasahitza.setForeground(new Color(255, 255, 255));
		lblPasahitza.setBounds(128, 129, 64, 17);
		getContentPane().add(lblPasahitza);

		txtPasahitza = new JPasswordField();
		txtPasahitza.setBounds(197, 122, 121, 30);
		getContentPane().add(txtPasahitza);

		btnMostrarContrasenas = new JButton("");
		btnMostrarContrasenas.setBounds(328, 122, 30, 30);
		btnMostrarContrasenas.setHorizontalAlignment(SwingConstants.CENTER);
		btnMostrarContrasenas.setVerticalAlignment(SwingConstants.CENTER);
		btnMostrarContrasenas.setIcon(cargarIconoEscalado("/begiaItxita.png", 31, 30));
		btnMostrarContrasenas.addActionListener(new ActionListener() {
			private boolean isPasswordVisible = false;

			public void actionPerformed(ActionEvent e) {
				if (isPasswordVisible) {
					txtPasahitza.setEchoChar('*');
					btnMostrarContrasenas.setIcon(cargarIconoEscalado("/begiaItxita.png", 31, 30));
				} else {
					txtPasahitza.setEchoChar((char) 0);
					btnMostrarContrasenas.setIcon(cargarIconoEscalado("/begia.png", 31, 30));
				}
				isPasswordVisible = !isPasswordVisible;
			}
		});
		getContentPane().add(btnMostrarContrasenas);

		btnSartu = new JButton("Sartu");
		btnSartu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SqlMetodoak sm = new SqlMetodoak();
				
				String erabiltzailea = txtErabiltzailea.getText();
				String pasahitza = new String(txtPasahitza.getPassword());
				
				sm.loginKomparatu(erabiltzailea, pasahitza);
				if (loginOk()) {
					LehioarenFuntioak fv = new LehioarenFuntioak();
					fv.irekiHasiera();
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Erabiltzailea edo pasahitza okerrak", "Errorea",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSartu.setBounds(171, 177, 89, 23);
		getContentPane().add(btnSartu);

		btnRegister = new JButton("Erregistratu");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LehioarenFuntioak fv = new LehioarenFuntioak();
				fv.irekiRegister();
				dispose();
			}
		});
		btnRegister.setBounds(298, 219, 108, 23);
		getContentPane().add(btnRegister);

		lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 434, 261);
		lblFondo.setIcon(cargarIconoEscalado("/fondo.jpg", 434, 261));
		getContentPane().add(lblFondo);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	}

	private ImageIcon cargarIconoEscalado(String ruta, int ancho, int alto) {
		ImageIcon icon = new ImageIcon(getClass().getResource(ruta));
		Image img = icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
		return new ImageIcon(img);
	}

	private boolean loginOk() {
		boolean sartu = false;
		Cache cache = new Cache();
		if (cache.getAgentzia() != null) {
			sartu = true;
		}
		return sartu;
	}
}
