package windowBuilder;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import modelo.*;
import sqlKonexioa.SqlMetodoak;

import javax.swing.JComboBox;

public class Register extends JFrame {

	private JPanel contentPane;
	private ImageIcon imageIcon1;
	private JTextField txtErabiltzailea;
	private JPasswordField txtPasahitza;
	private JPasswordField txtPasahitzaBerretsi;
	private JLabel lblTituloa;
	private JLabel lblErabiltzailea;
	private JLabel lblKolorea;
	private JLabel lblPasahitza;
	private JLabel lblPasahitzaBerretsi;
	private JLabel lblAgentziamota;
	private JLabel lblempleatuak; 
	private JButton btnErregistratu;
	private JTextField txtKolorea;
	private JTextField txtColorPicker;
	private JComboBox<String> comboLangile;
	private JComboBox<String> comboAgentzia;
	private JButton btnMostrarContrasenas;
	private SqlMetodoak sm = new SqlMetodoak();

	public Register() {
		// Configuración de la ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 466, 410);
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

		txtKolorea = new JTextField("#FFFFFF");
		txtKolorea.setBounds(150, 116, 100, 25);
		contentPane.add(txtKolorea);

		// Campo Password
		lblPasahitza = new JLabel("Pasahitza:");
		lblPasahitza.setBounds(30, 239, 121, 25);
		contentPane.add(lblPasahitza);

		txtPasahitza = new JPasswordField();
		txtPasahitza.setBounds(150, 239, 200, 25);
		contentPane.add(txtPasahitza);

		// Campo Confirm Password
		lblPasahitzaBerretsi = new JLabel("Pasahitza berretsi");
		lblPasahitzaBerretsi.setBounds(30, 279, 121, 25);
		contentPane.add(lblPasahitzaBerretsi);
		

        // Botón para mostrar/ocultar contraseñas
        btnMostrarContrasenas = new JButton("");
        btnMostrarContrasenas.setBounds(360, 259, 31, 30);
        contentPane.add(btnMostrarContrasenas);

        // Acción para alternar visibilidad de las contraseñas


		txtPasahitzaBerretsi = new JPasswordField();
		txtPasahitzaBerretsi.setBounds(150, 279, 200, 25);
		contentPane.add(txtPasahitzaBerretsi);

		// Botón Register
		btnErregistratu = new JButton("Erregistratu");
		btnErregistratu.setBounds(165, 326, 100, 30);
		contentPane.add(btnErregistratu);

		lblempleatuak = new JLabel("Langile-kopuru:");
		lblempleatuak.setBounds(30, 162, 121, 25);
		contentPane.add(lblempleatuak);

		lblAgentziamota = new JLabel("Agentzia-mota:");
		lblAgentziamota.setBounds(30, 203, 121, 25);
		contentPane.add(lblAgentziamota);

		txtColorPicker = new JTextField();
		txtColorPicker.setEditable(false);
		txtColorPicker.setBounds(258, 119, 21, 20);
		contentPane.add(txtColorPicker);
		txtColorPicker.setColumns(10);
		
		// Obtener el ArrayList de Lang_kopurua
		ArrayList<Lang_kopurua> langKopList = sm.langKopEduki();

		// Crear un ArrayList para almacenar solo las descripciones (deskribapena)
		ArrayList<String> langKopDeskribapenaList = new ArrayList<>();

		// Llenar el nuevo ArrayList con las descripciones
		for (Lang_kopurua langKop : langKopList) {
		    langKopDeskribapenaList.add(langKop.getDeskribapena());  // Usar el getter para obtener deskribapena
		}

		// Crear el JComboBox con el ArrayList de descripciones
		comboLangile = new JComboBox<>(langKopDeskribapenaList.toArray(new String[0]));
		comboLangile.setBounds(150, 163, 200, 22);
		contentPane.add(comboLangile);
		
		
		// Obtener el ArrayList de Agentzia_Motak
		ArrayList<Agentzia_Motak> agentziaMotaList = sm.agentziaMotaEduki();

		// Crear un ArrayList para almacenar solo las descripciones (deskribapena)
		ArrayList<String> agentziaMotaDeskribapenaList = new ArrayList<>();

		// Llenar el nuevo ArrayList con las descripciones
		for (Agentzia_Motak agentziaMota : agentziaMotaList) {
		    agentziaMotaDeskribapenaList.add(agentziaMota.getDeskribapena());  // Usar el getter para obtener deskribapena
		}

		// Crear el JComboBox con el ArrayList de descripciones
		comboAgentzia = new JComboBox<>(agentziaMotaDeskribapenaList.toArray(new String[0]));
		comboAgentzia.setBounds(150, 204, 100, 22);
		contentPane.add(comboAgentzia);
		
		
		imageIcon1 = new ImageIcon(this.getClass().getResource("/begia.png"));
		Image image1 = imageIcon1.getImage().getScaledInstance(btnMostrarContrasenas.getWidth(), btnMostrarContrasenas.getHeight(),
				Image.SCALE_SMOOTH);
		imageIcon1.setImage(image1);
		btnMostrarContrasenas.setIcon(imageIcon1);

        btnMostrarContrasenas.addActionListener(new ActionListener() {
            private boolean isPasswordVisible = false;
            
            
            public void actionPerformed(ActionEvent e) {
                if (isPasswordVisible) {
                	txtPasahitza.setEchoChar('•'); // Punto de lista más grande
                	txtPasahitzaBerretsi.setEchoChar('•'); // Punto de lista más grande
                    btnMostrarContrasenas.setText("Mostrar");
                } else {
                    txtPasahitza.setEchoChar((char) 0); // Mostrar texto
                    txtPasahitzaBerretsi.setEchoChar((char) 0); // Mostrar texto
                    btnMostrarContrasenas.setText("Ocultar");
                }
                isPasswordVisible = !isPasswordVisible;
            }
        });
        
		// Añadir un DocumentListener al campo txtKolorea
		
		/*
		 * Document Listener está todo el rato mirando lo que hace txtKolorea y si hay algun cambio en el se va cambiando el cuadrado para ver el color que ha puesto.
		 */
		
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
					txtColorPicker.setBackground(color);
				} catch (NumberFormatException ex) {
					// En caso de error en el formato, mostrar un color por defecto (rojo)
					txtColorPicker.setBackground(Color.WHITE);
				}
			}
		});
		
		btnErregistratu.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				final int langileIndex = comboLangile.getSelectedIndex();
				final int agentziaIndex = comboAgentzia.getSelectedIndex();
				
				String resultLang = langKopDeskribapenaList.get(langileIndex);
				String resultAgen = agentziaMotaDeskribapenaList.get(agentziaIndex);
				
				LehioarenFuntioak fv = new LehioarenFuntioak();
				fv.irekiLogin();
				dispose();
			}
		});
		
		

	}
	

	
}