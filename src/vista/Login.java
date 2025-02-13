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

//esto es para quitar el warning del extends, si no sale en amarillo

@SuppressWarnings("serial")
public class Login extends JFrame {

	// creación de todo en private para poder usar todo en metodos y no tener que
	// pasarlo por parametros.
	
	private JPanel contentPane;
	private JTextField txtErabiltzailea;
	private JPasswordField txtPasahitza;
	private JLabel lblErabiltzailea, lblPasahitza, lblLogIn, lblFondo;
	private JButton btnSartu, btnRegister, btnMostrarContrasenas;
	private Cache cache = new Cache();

	public Login() {
		// para quitar el valor que se queda en el cache y poder meter una nueva
		// agentzia
		cache.setAgentzia(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		// creación del label para el logIn

		lblLogIn = new JLabel("Log In");
		lblLogIn.setFont(new Font("ROG Fonts", Font.PLAIN, 24));
		lblLogIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogIn.setBounds(0, 0, 434, 47);
		getContentPane().add(lblLogIn);

		// creación el textField de Erabiltzailea

		txtErabiltzailea = new JTextField();
		txtErabiltzailea.setBounds(197, 79, 121, 30);
		getContentPane().add(txtErabiltzailea);
		txtErabiltzailea.setColumns(10);

		// creación del label para erabiltzailea

		lblErabiltzailea = new JLabel("Agentziaren izena:");
		lblErabiltzailea.setForeground(new Color(255, 255, 255));
		lblErabiltzailea.setBounds(85, 87, 112, 14);
		getContentPane().add(lblErabiltzailea);

		// creación del label para la contraseña

		lblPasahitza = new JLabel("Pasahitza:");
		lblPasahitza.setForeground(new Color(255, 255, 255));
		lblPasahitza.setBounds(128, 129, 64, 17);
		getContentPane().add(lblPasahitza);

		// creación del JPasswordField esto hace que no se vea lo que escribes en el
		// txtfield

		txtPasahitza = new JPasswordField();
		txtPasahitza.setBounds(197, 122, 121, 30);
		getContentPane().add(txtPasahitza);

		// Creación del boton para mostrar contraseñas

		btnMostrarContrasenas = new JButton("");
		btnMostrarContrasenas.setBounds(328, 122, 30, 30);
		btnMostrarContrasenas.setHorizontalAlignment(SwingConstants.CENTER);
		btnMostrarContrasenas.setVerticalAlignment(SwingConstants.CENTER);
		// le ponemos por defecto la foto del ojo cerrado
		btnMostrarContrasenas.setIcon(kargatuImg("/begiaItxita.png", 31, 30));
		btnMostrarContrasenas.addActionListener(new ActionListener() {
			private boolean isPasswordVisible = false;

			public void actionPerformed(ActionEvent e) {
				// esto pone el formato de los caracteres en asteriscos al darle al boton
				if (isPasswordVisible) {
					txtPasahitza.setEchoChar('*');
					btnMostrarContrasenas.setIcon(kargatuImg("/begiaItxita.png", 31, 30));
				} else {
					// esto pone el formate de los caracteres en las letras que están puestas
					txtPasahitza.setEchoChar((char) 0);
					btnMostrarContrasenas.setIcon(kargatuImg("/begia.png", 31, 30));
				}

				// Si isPasswordVisible es true, se convierte en false. Si isPasswordVisible es
				// false, se convierte en true.

				isPasswordVisible = !isPasswordVisible;

			}
		});
		getContentPane().add(btnMostrarContrasenas);

		// creación del boton para ir a la siguiente pantalla
		btnSartu = new JButton("Sartu");
		btnSartu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// esto instancia la clase sqlMetodoak para poder llamar al metodo que hace la
				// consulta de la base de datos
				SqlMetodoak sm = new SqlMetodoak();

				// pasa todo lo que haya en los txtField a string para poder pasarlo por
				// parametros
				String erabiltzailea = txtErabiltzailea.getText();
				String pasahitza = new String(txtPasahitza.getPassword());

				// llamo al metodo loginKonparatu para ver si tiene la base de datos ese usuario
				// y contraseña
				sm.loginKonparatu(erabiltzailea, pasahitza);
				// si caché está vacio el login está mal escrito o no existe en la base de
				// datos.
				if (loginOk()) {
					LeihoarenFuntioak fv = new LeihoarenFuntioak();
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

		// creación del botón para ir a la pantalla del register

		btnRegister = new JButton("Erregistratu");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LeihoarenFuntioak fv = new LeihoarenFuntioak();
				fv.irekiRegister();
				dispose();
			}
		});
		btnRegister.setBounds(298, 219, 108, 23);
		getContentPane().add(btnRegister);

		// creación del fondo para poder poner la foto del login

		lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 434, 261);
		lblFondo.setIcon(kargatuImg("/fondo.jpg", 434, 261));
		getContentPane().add(lblFondo);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	}

	// esto es un metodo para cargar el icono del botón, también vale para cargar el
	// fondo

	private ImageIcon kargatuImg(String ruta, int ancho, int alto) {
		ImageIcon icon = new ImageIcon(getClass().getResource(ruta));
		Image img = icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
		return new ImageIcon(img);
	}

	// esto mira si se ha llenado en caché la agencia metida en el login, si se ha
	// llenado es que el usuario y contraseña están bien si no es que están mal o no
	// existe

	private boolean loginOk() {
		boolean sartu = false;
		Cache cache = new Cache();
		if (cache.getAgentzia() != null) {
			sartu = true;
		}
		return sartu;
	}
}
