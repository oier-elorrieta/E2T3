package windowBuilder;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {
	
	private JPanel contentPane;
	private JTextField txtErabiltzailea;
	private JTextField txtPasahitza;
	private JLabel lblLogIn;
	private JLabel lblErabiltzailea;
	private JLabel lblPasahitza;
	private JButton btnSartu;
	private JButton btnRegister;

	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		lblLogIn = new JLabel("Log In");
		lblLogIn.setFont(new Font("ROG Fonts", Font.PLAIN, 24));
		lblLogIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogIn.setBounds(0, 0, 434, 47);
		getContentPane().add(lblLogIn);
		
		txtErabiltzailea = new JTextField();
		txtErabiltzailea.setBounds(186, 95, 121, 20);
		getContentPane().add(txtErabiltzailea);
		txtErabiltzailea.setColumns(10);
		
		lblErabiltzailea = new JLabel("Erabiltzailea");
		lblErabiltzailea.setHorizontalAlignment(SwingConstants.CENTER);
		lblErabiltzailea.setBounds(85, 98, 97, 14);
		getContentPane().add(lblErabiltzailea);
		
		lblPasahitza = new JLabel("Pasahitza");
		lblPasahitza.setHorizontalAlignment(SwingConstants.CENTER);
		lblPasahitza.setBounds(85, 134, 97, 14);
		getContentPane().add(lblPasahitza);
		
		txtPasahitza = new JTextField();
		txtPasahitza.setColumns(10);
		txtPasahitza.setBounds(186, 131, 121, 20);
		getContentPane().add(txtPasahitza);
		
		btnSartu = new JButton("Sartu");
		btnSartu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	}

}
