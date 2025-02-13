package vista;

import java.awt.EventQueue;

public class LeihoarenFuntioak {
	
	
	public LeihoarenFuntioak() {	
	}

	//esto me vale para cambiar de ventanas usando el frame con esto Login extends JFrame
	public void irekiLogin() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//creamos una instancia nueva para mostrar el frame
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//Son todos iguales con que entiendas el primero entiendes todos.
	
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
	
	public void irekiZerbitzuak(int idBidaia) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Zerbitzuak frame = new Zerbitzuak(idBidaia);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
