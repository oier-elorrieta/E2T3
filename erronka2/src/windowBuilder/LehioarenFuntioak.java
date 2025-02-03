package windowBuilder;

import java.awt.EventQueue;

public class LehioarenFuntioak {
	
	
	public LehioarenFuntioak() {	
	}

	//esto me vale para cambiar de ventanas usando el frame con esto Login extends JFrame
	public void irekiLogin() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void irekiRegister() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void irekiHasiera() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Hasiera frame = new Hasiera();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void irekiBidaiak() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bidaiak frame = new Bidaiak();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void irekiZerbitzuak() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Zerbitzuak frame = new Zerbitzuak();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
