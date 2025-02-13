package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import modelo.pojo.Agentzia_Motak;
import modelo.pojo.Lang_kopurua;
import modelo.sql.SqlMetodoak;

import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class Register extends JFrame {

	// creación de todo en private para poder usar todo en metodos y no tener que
	// pasarlo por parametros.

	private JPanel contentPane;
	private JTextField txtErabiltzailea;
	private JPasswordField txtPasahitza, txtPasahitzaBerretsi;
	private JLabel lblTituloa, lblErabiltzailea, lblKolorea, lblPasahitza, lblPasahitzaBerretsi, lblAgentziamota,
			lblempleatuak, lblLogoa;
	private JButton btnErregistratu, btnMostrarContrasenas, btnEzeztatu;
	private JTextField txtKolorea, txtLogoa;
	private JComboBox<String> comboLangile, comboAgentzia;
	private SqlMetodoak sm = new SqlMetodoak();
	private JButton btnKolorea;

	public Register() {

		// Configuración de la ventana para que se pueda ver

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 466, 467);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);

		// creación del label del Título

		lblTituloa = new JLabel("Erregistroa");
		lblTituloa.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloa.setFont(new Font("Arial", Font.BOLD, 20));
		lblTituloa.setBounds(120, 20, 200, 30);
		contentPane.add(lblTituloa);

		// creación del label para agentzia

		lblErabiltzailea = new JLabel("Agentziaren izena:");
		lblErabiltzailea.setBounds(30, 80, 121, 25);
		contentPane.add(lblErabiltzailea);

		// creación del txtField para poner la agentzia

		txtErabiltzailea = new JTextField();
		txtErabiltzailea.setBounds(150, 80, 200, 25);
		contentPane.add(txtErabiltzailea);

		// creación del label de kolorea

		lblKolorea = new JLabel("Markaren kolorea:");
		lblKolorea.setBounds(30, 120, 121, 25);
		contentPane.add(lblKolorea);

		// Añadir un DocumentListener al campo txtKolorea

		/*
		 * Document Listener está todo el rato mirando lo que hace txtKolorea y si hay
		 * algun cambio en el se va cambiando el cuadrado para ver el color que ha
		 * puesto.
		 */

		txtKolorea = new JTextField("#FFFFFF");
		txtKolorea.setBounds(150, 116, 100, 25);
		txtKolorea.getDocument().addDocumentListener(new DocumentListener() {

			// Se llama cuando se inserta texto.

			public void insertUpdate(DocumentEvent e) {
				actualizarColor();
			}

			// Se llama cuando se elimina texto.

			public void removeUpdate(DocumentEvent e) {
				actualizarColor();
			}

			// Se usa en documentos con estilos, pero no en JTextField normal.

			public void changedUpdate(DocumentEvent e) {
				actualizarColor();
			}

			private void actualizarColor() {
				String hexColor = txtKolorea.getText().trim();
				try {

					// Convertir el color hexadecimal a un objeto Color

					Color color = Color.decode(hexColor);

					// Establecer el color de fondo del txtColorPicker

					btnKolorea.setBackground(color);
				} catch (NumberFormatException ex) {

					// En caso de error en el formato, mostrar un color por defecto

					btnKolorea.setBackground(Color.WHITE);
				}
			}
		});
		contentPane.add(txtKolorea);

		// creación del label para pasahitza

		lblPasahitza = new JLabel("Pasahitza:");
		lblPasahitza.setBounds(30, 275, 121, 25);
		contentPane.add(lblPasahitza);

		// creación del textField para pasahitza

		txtPasahitza = new JPasswordField();
		txtPasahitza.setBounds(150, 275, 200, 25);
		contentPane.add(txtPasahitza);

		// creación del label para confirmar la contraseña

		lblPasahitzaBerretsi = new JLabel("Pasahitza berretsi");
		lblPasahitzaBerretsi.setBounds(30, 315, 121, 25);
		contentPane.add(lblPasahitzaBerretsi);

		// creación del textField para pasahitzak

		txtPasahitzaBerretsi = new JPasswordField();
		txtPasahitzaBerretsi.setBounds(150, 315, 200, 25);
		contentPane.add(txtPasahitzaBerretsi);

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

		// creación del label para la media de empleados

		lblempleatuak = new JLabel("Langile-kopuru:");
		lblempleatuak.setBounds(30, 162, 121, 25);
		contentPane.add(lblempleatuak);

		// creación del label de el tipo de agencia

		lblAgentziamota = new JLabel("Agentzia-mota:");
		lblAgentziamota.setBounds(30, 203, 121, 25);
		contentPane.add(lblAgentziamota);

		// creación del boton que saca el desplegable para elegir un color

		btnKolorea = new JButton("");
		btnKolorea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Abrir el selector de color
				Color color = JColorChooser.showDialog(contentPane, "Hautatu kolore bat", Color.WHITE);

				// Si el usuario selecciona un color (no presiona Cancelar)
				if (color != null) {
					// Convertir el color seleccionado a hexadecimal
					String hexColor = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());

					txtKolorea.setText(hexColor);
				} else {
					JOptionPane.showMessageDialog(null, "Ez duzu hautau kolorea", "Errorea", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnKolorea.setBounds(258, 119, 21, 20);
		contentPane.add(btnKolorea);

		// Obtener el ArrayList de Lang_kopurua
		ArrayList<Lang_kopurua> langKopList = sm.langKopEduki();

		// Crear dos ArrayList para almacenar las descripciones (deskribapena) y los
		// codigos (kodeak)
		ArrayList<String> langKopDeskribapena = new ArrayList<>();
		ArrayList<String> langKopKod = new ArrayList<>();

		// Llenar los nuevos Arraylist
		for (Lang_kopurua langKop : langKopList) {
			langKopDeskribapena.add(langKop.getDeskribapena()); // Usar el getter para obtener deskribapena
			langKopKod.add(langKop.getLangKopKodea()); // Usar el getter para obtener kodea
		}

		// Crear el JComboBox con el ArrayList de descripciones
		comboLangile = new JComboBox<>(langKopDeskribapena.toArray(new String[0]));
		comboLangile.setBounds(150, 163, 200, 22);
		contentPane.add(comboLangile);

		// Obtener el ArrayList de Agentzia_Motak
		ArrayList<Agentzia_Motak> agentziaMotaList = sm.agentziaMotaEduki();

		// Crear dos ArrayList para almacenar las descripciones (deskribapena) y los
		// codigos (kodeak)
		ArrayList<String> agentziaMotaDeskribapena = new ArrayList<>(); // Usar el getter para obtener deskribapena
		ArrayList<String> agentziaMotaKod = new ArrayList<>(); // Usar el getter para obtener kodeak

		// Llenar los nuevos Arraylist
		for (Agentzia_Motak agentziaMota : agentziaMotaList) {
			agentziaMotaKod.add(agentziaMota.getAgentziaMKodea()); // Usar el getter para obtener kodea
			agentziaMotaDeskribapena.add(agentziaMota.getDeskribapena()); // Usar el getter para obtener deskribapena
		}

		// Crear el JComboBox con el ArrayList de descripciones
		comboAgentzia = new JComboBox<>(agentziaMotaDeskribapena.toArray(new String[0]));
		comboAgentzia.setBounds(150, 204, 100, 22);
		contentPane.add(comboAgentzia);

		// creación del label para la url

		lblLogoa = new JLabel("Logoa (URL)");
		lblLogoa.setBounds(30, 239, 73, 25);
		contentPane.add(lblLogoa);

		// creación del txt field para la url

		txtLogoa = new JTextField();
		txtLogoa.setBounds(150, 239, 200, 25);
		contentPane.add(txtLogoa);

		// Botón Register
		btnErregistratu = new JButton("Erregistratu");
		btnErregistratu.setBounds(120, 371, 112, 30);
		btnErregistratu.setFocusPainted(false);
		btnErregistratu.setBorderPainted(false);
		btnErregistratu.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// miramos si las dos contraseñas coinciden si no te salta un popUp diciendo que
				// no coinciden

				if (pasahitzaOk()) {
					// esto instancia la clase sqlMetodoak para poder llamar al metodo que hace la
					// consulta de la base de datos
					SqlMetodoak sm = new SqlMetodoak();

					// aqui pillamos el index del combobox para meterlo como indice para el
					// arrayList y sacar el kodigo y poder meterlo en la bd ya que hacemos el select
					// con la ID
					int langileIndex = comboLangile.getSelectedIndex();
					int agentziaIndex = comboAgentzia.getSelectedIndex();

					// pasa todo lo que haya en los txtField a string para poder pasarlo por
					// parametros y hace lo del indice en el arraylist
					String agentziaIzena = txtErabiltzailea.getText().trim();
					String resultLang = langKopKod.get(langileIndex);
					String resultAgen = agentziaMotaKod.get(agentziaIndex);
					String kolorea = txtKolorea.getText().trim();
					String logoa = txtLogoa.getText().trim();
					String pasahitza = new String(txtPasahitzaBerretsi.getPassword());

					// llamamos al metodo de hacer el registro pasando todo por parametro

					sm.erregistroEgin(agentziaIzena, kolorea, resultLang, resultAgen, logoa, pasahitza);

					// llamamos de nuevo al Login para poder entrar con la nueva agencia

					LeihoarenFuntioak fv = new LeihoarenFuntioak();
					fv.irekiLogin();
					dispose();

				} else {
					JOptionPane.showMessageDialog(null, "Pasahitzek berdinak izan behar dute", "Errorea",
							JOptionPane.ERROR_MESSAGE);
					txtPasahitza.setText(null);
					txtPasahitzaBerretsi.setText(null);
				}
			}
		});
		contentPane.add(btnErregistratu);

		// creación del boton ezetatu

		btnEzeztatu = new JButton("Ezeztatu");
		btnEzeztatu.setBounds(242, 371, 100, 30);
		btnEzeztatu.setFocusPainted(false);
		btnEzeztatu.setBorderPainted(false);
		btnEzeztatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LeihoarenFuntioak fv = new LeihoarenFuntioak();
				fv.irekiLogin();
				dispose();
			}
		});
		contentPane.add(btnEzeztatu);

	}

	// este es el metodo que compara los dos JPasswordField para ver si las
	// contraseñas coinciden o no

	private boolean pasahitzaOk() {
		boolean okay = false;

		String pass1 = new String(txtPasahitza.getPassword());
		String pass2 = new String(txtPasahitzaBerretsi.getPassword());

		if (pass1.equals(pass2)) {
			okay = true;
		}

		return okay;
	}

	// esto es un metodo para cargar el icono del botón, también vale para cargar el
	// fondo

	private ImageIcon kargatuImg(String ruta, int ancho, int alto) {
		ImageIcon icon = new ImageIcon(getClass().getResource(ruta));
		Image img = icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
		return new ImageIcon(img);
	}

}