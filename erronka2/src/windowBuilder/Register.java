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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JComboBox;

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
	private JLabel lblAgentziamota;
	private JLabel lblempleatuak; 
	private JButton btnErregistratu;
	private JTextField txtKolorea;
	private JTextField txtColorPicker;
	private JComboBox comboLangile ;
	private JComboBox comboAgentzia;

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
		
		comboLangile = new JComboBox();
		comboLangile.setBounds(150, 163, 100, 22);
		contentPane.add(comboLangile);
		
		comboAgentzia = new JComboBox();
		comboAgentzia.setBounds(150, 204, 100, 22);
		contentPane.add(comboAgentzia);

		// Añadir un DocumentListener al campo txtKolorea
		
		/*
		 * Document Listener está todo el rato mirando lo que hace txtKolorea y si hay algun cambio en el se va cambiando el cuadrado para ver el color que ha puesto.
		 */
		txtKolorea.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				actualizarColor();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				actualizarColor();
			}

			@Override
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
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				LehioarenFuntioak fv = new LehioarenFuntioak();
				fv.irekiLogin();
				dispose();
			}
		});

	}
}