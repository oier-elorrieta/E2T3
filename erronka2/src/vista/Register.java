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
		// Configuración de la ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 466, 467);
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
		lblErabiltzailea.setBounds(30, 80, 121, 25);
		contentPane.add(lblErabiltzailea);

		txtErabiltzailea = new JTextField();
		txtErabiltzailea.setBounds(150, 80, 200, 25);
		contentPane.add(txtErabiltzailea);

		// Campo Email
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

			public void insertUpdate(DocumentEvent e) {
				actualizarColor();
			}

			public void removeUpdate(DocumentEvent e) {
				actualizarColor();
			}

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
					// En caso de error en el formato, mostrar un color por defecto (rojo)
					btnKolorea.setBackground(Color.WHITE);
				}
			}
		});
		contentPane.add(txtKolorea);

		// Campo Password
		lblPasahitza = new JLabel("Pasahitza:");
		lblPasahitza.setBounds(30, 275, 121, 25);
		contentPane.add(lblPasahitza);

		txtPasahitza = new JPasswordField();
		txtPasahitza.setBounds(150, 275, 200, 25);
		contentPane.add(txtPasahitza);

		// Campo Confirm Password
		lblPasahitzaBerretsi = new JLabel("Pasahitza berretsi");
		lblPasahitzaBerretsi.setBounds(30, 315, 121, 25);
		contentPane.add(lblPasahitzaBerretsi);

		// Botón para mostrar/ocultar contraseñas
		btnMostrarContrasenas = new JButton("");
		btnMostrarContrasenas.setBounds(361, 288, 31, 30);
		btnMostrarContrasenas.setHorizontalAlignment(SwingConstants.CENTER);
		btnMostrarContrasenas.setVerticalAlignment(SwingConstants.CENTER);
		btnMostrarContrasenas.setIcon(cargarIconoEscalado("/begiaItxita.png", 31, 30));

		btnMostrarContrasenas.addActionListener(new ActionListener() {
			private boolean isPasswordVisible = false;

			public void actionPerformed(ActionEvent e) {
				if (isPasswordVisible) {
					txtPasahitza.setEchoChar('*');
					txtPasahitzaBerretsi.setEchoChar('*');
					btnMostrarContrasenas.setIcon(cargarIconoEscalado("/begiaItxita.png", 31, 30));
				} else {
					txtPasahitza.setEchoChar((char) 0);
					txtPasahitzaBerretsi.setEchoChar((char) 0);
					btnMostrarContrasenas.setIcon(cargarIconoEscalado("/begia.png", 31, 30));
				}
				isPasswordVisible = !isPasswordVisible;
			}
		});
		contentPane.add(btnMostrarContrasenas);
		// Acción para alternar visibilidad de las contraseñas

		txtPasahitzaBerretsi = new JPasswordField();
		txtPasahitzaBerretsi.setBounds(150, 315, 200, 25);
		contentPane.add(txtPasahitzaBerretsi);

		lblempleatuak = new JLabel("Langile-kopuru:");
		lblempleatuak.setBounds(30, 162, 121, 25);
		contentPane.add(lblempleatuak);

		lblAgentziamota = new JLabel("Agentzia-mota:");
		lblAgentziamota.setBounds(30, 203, 121, 25);
		contentPane.add(lblAgentziamota);

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
                }
                else {
                	JOptionPane.showMessageDialog(null, "Ez duzu hautau kolorea", "Errorea",
							JOptionPane.ERROR_MESSAGE);
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

		// Llenar los nuevos ArrayLists con las descripciones
		for (Lang_kopurua langKop : langKopList) {
			langKopDeskribapena.add(langKop.getDeskribapena()); // Usar el getter para obtener deskribapena
			langKopKod.add(langKop.getLangKopKodea());
		}

		// Crear el JComboBox con el ArrayList de descripciones
		comboLangile = new JComboBox<>(langKopDeskribapena.toArray(new String[0]));
		comboLangile.setBounds(150, 163, 200, 22);
		contentPane.add(comboLangile);

		// Obtener el ArrayList de Agentzia_Motak
		ArrayList<Agentzia_Motak> agentziaMotaList = sm.agentziaMotaEduki();

		// Crear dos ArrayList para almacenar las descripciones (deskribapena) y los
		// codigos (kodeak)
		ArrayList<String> agentziaMotaDeskribapena = new ArrayList<>();
		ArrayList<String> agentziaMotaKod = new ArrayList<>();

		// Llenar el nuevo ArrayList con las descripciones
		for (Agentzia_Motak agentziaMota : agentziaMotaList) {
			agentziaMotaKod.add(agentziaMota.getAgentziaMKodea()); // Usar el getter para obtener deskribapena
			agentziaMotaDeskribapena.add(agentziaMota.getDeskribapena());
		}

		// Crear el JComboBox con el ArrayList de descripciones
		comboAgentzia = new JComboBox<>(agentziaMotaDeskribapena.toArray(new String[0]));
		comboAgentzia.setBounds(150, 204, 100, 22);
		contentPane.add(comboAgentzia);

		lblLogoa = new JLabel("Logoa (URL)");
		lblLogoa.setBounds(30, 239, 73, 25);
		contentPane.add(lblLogoa);

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
				if (pasahitzaOk()) {
					SqlMetodoak sm = new SqlMetodoak();

					int langileIndex = comboLangile.getSelectedIndex();
					int agentziaIndex = comboAgentzia.getSelectedIndex();

					String agentziaIzena = txtErabiltzailea.getText().trim();
					String resultLang = langKopKod.get(langileIndex);
					String resultAgen = agentziaMotaKod.get(agentziaIndex);
					String kolorea = txtKolorea.getText().trim();
					String logoa = txtLogoa.getText().trim();
					String pasahitza = new String(txtPasahitzaBerretsi.getPassword());
					sm.erregistroEgin(agentziaIzena, kolorea, resultLang, resultAgen, logoa, pasahitza);

					LehioarenFuntioak fv = new LehioarenFuntioak();
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

		btnEzeztatu = new JButton("Ezeztatu");
		btnEzeztatu.setBounds(242, 371, 100, 30);
		btnEzeztatu.setFocusPainted(false);
		btnEzeztatu.setBorderPainted(false);
		btnEzeztatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LehioarenFuntioak fv = new LehioarenFuntioak();
				fv.irekiLogin();
				dispose();
			}
		});
		contentPane.add(btnEzeztatu);
		

	}

	private boolean pasahitzaOk() {
		boolean okay = false;

		String pass1 = new String(txtPasahitza.getPassword());
		String pass2 = new String(txtPasahitzaBerretsi.getPassword());

		if (pass1.equals(pass2)) {
			okay = true;
		}

		return okay;
	}

	private ImageIcon cargarIconoEscalado(String ruta, int ancho, int alto) {
		ImageIcon icon = new ImageIcon(getClass().getResource(ruta));
		Image img = icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
		return new ImageIcon(img);
	}
}