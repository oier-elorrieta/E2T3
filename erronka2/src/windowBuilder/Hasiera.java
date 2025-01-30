package windowBuilder;

import java.awt.EventQueue;
import funtzioak.*;
import modelo.Bidaia;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Hasiera extends JFrame {

	private JPanel contentPane;
	private JButton btnAtzera;

	public Hasiera() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		btnAtzera = new JButton("<--");
		btnAtzera.setBounds(335, 227, 89, 23);
		getContentPane().add(btnAtzera);

		
		
		btnAtzera.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LehioarenFuntioak fv = new LehioarenFuntioak();
				fv.irekiLogin();
				dispose();
			}
		});

	}
}
