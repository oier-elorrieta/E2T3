package windowBuilder;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OngiEtorria {

	private JFrame frame;
	private JButton btnOngiEtorri;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OngiEtorria window = new OngiEtorria();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OngiEtorria() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btnOngiEtorri = new JButton("Ongi Etorri");
		btnOngiEtorri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LehioarenFuntioak fv = new LehioarenFuntioak();
				fv.irekiLogin();
				frame.setVisible(false);
			}
		});
		btnOngiEtorri.setBounds(108, 64, 222, 130);
		frame.getContentPane().add(btnOngiEtorri);
	}
}
