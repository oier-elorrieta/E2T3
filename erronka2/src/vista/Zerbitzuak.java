package vista;

import javax.swing.JFrame;

import com.toedter.calendar.JDateChooser;

import modelo.pojo.Airelinea;
import modelo.pojo.Aireportu;
import modelo.pojo.Cache;
import modelo.pojo.Logela_motak;
import modelo.sql.SqlMetodoak;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Font;

@SuppressWarnings("serial")
public class Zerbitzuak extends JFrame {

	private SqlMetodoak sm = new SqlMetodoak();
	private JTextArea txtJardueraDeskribapena;
	private JLabel lblLogoa, lblTituloa, lblIzena, lblMota, lblJoanekoData, lblHelmugakoAireportua, lblIbilbide,
			lblJardueraData, lblJatorrizkoaireportua, lblBidaiarenKodea, lblAerolinea, lblPrezioa, lblIrteeraOrdutegia,
			lblIraupena, lblEtorriData, lblBidaiarenKodeaEtorria, lblHiria, lblPrezioaOstatu, lblAerolineaEtorria,
			lblPrezioaEtorria, lblEtorriOrdutegia, lblIraupenaEtorria, lblLogelaMota, lblJardueraPrezioa,
			lblOstatuSarrera, lblOstatuIrteera, lblJardueraDeskribapena;
	private Cache cache = new Cache();
	private JDateChooser dataAukeratuJoaneko, dataJarduera, dataAukeratuEtorria, dataOstatuSarrera, dataOstatuIrteera;
	private JTextField txtizena, txtBidaiarenKodea, txtPrezioa, txtOrdutegia, txtIraupena, txtOstatuHiria,
			txtOstatuPrezio, txtBidaiarenKodeaEtorria, txtPrezioEtorria, txtEtorriOrdutegia, txtIraupenaEtorria,
			txtJardueraPrezioa;
	private JComboBox<String> comboBoxHelmuga, comboBoxMotak, comboBoxJatorria, comboBoxIbilbidea, comboBoxAerolinea,
			comboBoxAerolineaEtorria, comboBoxLogela;
	ArrayList<Logela_motak> logelaMotaList = sm.logelaMotaEduki();;
	ArrayList<Aireportu> iata = sm.aireportuaEduki();
	ArrayList<Airelinea> airelineaList = sm.airelineaEduki();
	ArrayList<String> aireportuKodea = new ArrayList<>(), hiria = new ArrayList<>(), airelineaKodea = new ArrayList<>(),
			airelinea = new ArrayList<>(), ibilbidea = new ArrayList<>(), zerbitzuMota = new ArrayList<>(),
			logelaMotak = new ArrayList<>(), logelaMotakKod = new ArrayList<>();
	private JButton btnGorde, btnEzeztatu, btnBiliatu, btnBiliatuOstatu;
	private SimpleDateFormat dataFormatua = new SimpleDateFormat("yyyy/MM/dd"); // Formato DD/MM/YYYY
	private boolean sartu = false;

	public Zerbitzuak(int idBidaia) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 573, 695);
		getContentPane().setLayout(null);

		zerbitzuMota.add("Hegaldia");
		zerbitzuMota.add("Ostatua");
		zerbitzuMota.add("Jarduera");

		comboBoxMotak = new JComboBox<>(zerbitzuMota.toArray(new String[0]));
		comboBoxMotak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxMotak.setEnabled(false); // Deshabilita para evitar múltiples eventos seguidos
				actualizarInterfaz(comboBoxMotak.getSelectedIndex(), idBidaia);
				comboBoxMotak.setEnabled(true); // Reactiva después de actualizar
			}
		});
		comboBoxMotak.setBounds(158, 201, 150, 22);
		getContentPane().add(comboBoxMotak);

		erakutsiHegaldia(idBidaia);
	}

	private void erakutsiZerbitzuaBase(int idBidaia) {

		lblTituloa = new JLabel("Zerbitzuak");
		lblTituloa.setFont(new Font("ROG Fonts", Font.PLAIN, 27));
		lblTituloa.setBounds(45, 69, 243, 61);
		getContentPane().add(lblTituloa);

		lblMota = new JLabel("Zerbitzuen mota");
		lblMota.setBounds(45, 206, 94, 14);
		getContentPane().add(lblMota);

		btnEzeztatu = new JButton("Ezeztatu");
		btnEzeztatu.addActionListener(e -> {
			new LehioarenFuntioak().irekiHasiera();
			dispose();
		});
		btnEzeztatu.setBounds(358, 622, 89, 23);
		getContentPane().add(btnEzeztatu);

		btnGorde = new JButton("Gorde");
		btnGorde.addActionListener(e -> {
			if (comboBoxMotak.getSelectedIndex() == 0) {
				if (comboBoxIbilbidea.getSelectedIndex() == 0) {
					int jatorriaIndex = comboBoxJatorria.getSelectedIndex();
					int helmugaIndex = comboBoxHelmuga.getSelectedIndex();
					int aerolineaIndex = comboBoxAerolinea.getSelectedIndex();

					String jatorrizkoAireportua = aireportuKodea.get(jatorriaIndex);
					String helmugakoAireportua = aireportuKodea.get(helmugaIndex);
					Date joanekoData = dataAukeratuJoaneko.getDate();
					String sJoanekoData = dataFormatua.format(joanekoData);
					String hegaldiKodea = txtBidaiarenKodea.getText();
					String aerolinea = airelineaKodea.get(aerolineaIndex);
					float prezio = Float.parseFloat(txtPrezioa.getText());
					Time orduteguia = Time.valueOf(txtOrdutegia.getText());
					Time iraupena = Time.valueOf(txtIraupena.getText());

					sm.zerbitzuJoanEgin(idBidaia, jatorrizkoAireportua, helmugakoAireportua, sJoanekoData, hegaldiKodea,
							aerolinea, prezio, orduteguia, iraupena);

				} else {
					int jatorriaIndex = comboBoxJatorria.getSelectedIndex();
					int helmugaIndex = comboBoxHelmuga.getSelectedIndex();
					int aerolineaIndex = comboBoxAerolinea.getSelectedIndex();

					String jatorrizkoAireportua = aireportuKodea.get(jatorriaIndex);
					String helmugakoAireportua = aireportuKodea.get(helmugaIndex);
					Date joanekoData = dataAukeratuJoaneko.getDate();
					String sJoanekoData = dataFormatua.format(joanekoData);
					String hegaldiKodea = txtBidaiarenKodea.getText();
					String aerolinea = airelineaKodea.get(aerolineaIndex);
					float prezio = Float.parseFloat(txtPrezioa.getText());
					Time orduteguia = Time.valueOf(txtOrdutegia.getText());
					Time iraupena = Time.valueOf(txtIraupena.getText());

					String jatorrizkoAireportuaE = aireportuKodea.get(jatorriaIndex);
					String helmugakoAireportuaE = aireportuKodea.get(helmugaIndex);
					Date joanekoDataE = dataAukeratuEtorria.getDate();
					String sJoanekoDataE = dataFormatua.format(joanekoDataE);
					String hegaldiKodeaE = txtBidaiarenKodeaEtorria.getText();
					String aerolineaE = airelineaKodea.get(aerolineaIndex);
					float prezioE = Float.parseFloat(txtPrezioEtorria.getText());
					Time orduteguiaE = Time.valueOf(txtEtorriOrdutegia.getText());
					Time iraupenaE = Time.valueOf(txtIraupenaEtorria.getText());

					sm.zerbitzuJoanEtorriEgin(idBidaia, jatorrizkoAireportua, helmugakoAireportua, sJoanekoData,
							hegaldiKodea, aerolinea, prezio, orduteguia, iraupena, jatorrizkoAireportuaE,
							helmugakoAireportuaE, sJoanekoDataE, hegaldiKodeaE, aerolineaE, prezioE, orduteguiaE,
							iraupenaE);

				}
			} else if (comboBoxMotak.getSelectedIndex() == 1) {
				int logelaMotaIndex = comboBoxLogela.getSelectedIndex();

				String logelaMota = logelaMotakKod.get(logelaMotaIndex);
				String hiria = txtOstatuHiria.getText();
				float prezioO = Float.parseFloat(txtOstatuPrezio.getText());
				Date sarreraDate = dataOstatuSarrera.getDate();
				String sSarreraDate = dataFormatua.format(sarreraDate);
				Date irteeraDate = dataOstatuIrteera.getDate();
				String sIrteeraDate = dataFormatua.format(irteeraDate);

				sm.zerbitzuOstatuEgin(idBidaia, logelaMota, hiria, prezioO, sSarreraDate, sIrteeraDate);

			} else {
				String jardueraIzena = txtizena.getText();
				String jardueraDeskribapena = txtJardueraDeskribapena.getText();
				float prezioJ = Float.parseFloat(txtJardueraPrezioa.getText());
				Date jardueraDate = dataJarduera.getDate();
				String sJardueraDate = dataFormatua.format(jardueraDate);

				sm.zerbitzuJardueraEgin(idBidaia, jardueraIzena, jardueraDeskribapena, prezioJ, sJardueraDate);

			}
			new LehioarenFuntioak().irekiHasiera();
			dispose();
		});
		btnGorde.setBounds(115, 622, 89, 23);
		getContentPane().add(btnGorde);

		cargarLogotipoYColor();
	}

	private void erakutsiHegaldia(int idBidaia) {
		erakutsiZerbitzuaBase(idBidaia);

		if (sartu == false) {
			for (Aireportu aireportu : iata) {
				aireportuKodea.add(aireportu.getAireportuKodea()); // Usar el getter para obtener deskribapena
				hiria.add(aireportu.getHiria());
			}

			for (Airelinea airelineak : airelineaList) {
				airelinea.add(airelineak.getAirelineIzena()); // Usar el getter para obtener deskribapena
				airelineaKodea.add(airelineak.getAirelineaKodea());
			}

			ibilbidea.add("Joan");
			ibilbidea.add("Joan eta etorri");

			sartu = true;
		}

		comboBoxIbilbidea = new JComboBox<>(ibilbidea.toArray(new String[0]));
		comboBoxIbilbidea.setBounds(158, 234, 150, 22);
		comboBoxIbilbidea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxIbilbidea.setEnabled(false); // Deshabilita para evitar múltiples eventos seguidos
				actualizarInterfazIbilbidea(comboBoxIbilbidea.getSelectedIndex(), idBidaia);
				comboBoxIbilbidea.setEnabled(true); // Reactiva después de actualizar
			}
		});
		getContentPane().add(comboBoxIbilbidea);

		lblIbilbide = new JLabel("Ibilbidea");
		lblIbilbide.setBounds(87, 239, 52, 14);
		getContentPane().add(lblIbilbide);

		lblJatorrizkoaireportua = new JLabel("Jatorrizko aireportua");
		lblJatorrizkoaireportua.setBounds(30, 272, 115, 14);
		getContentPane().add(lblJatorrizkoaireportua);

		comboBoxJatorria = new JComboBox<>(hiria.toArray(new String[0]));
		comboBoxJatorria.setBounds(158, 267, 150, 22);
		getContentPane().add(comboBoxJatorria);

		lblHelmugakoAireportua = new JLabel("Helmugako aireportua");
		lblHelmugakoAireportua.setBounds(19, 306, 132, 14);
		getContentPane().add(lblHelmugakoAireportua);

		comboBoxHelmuga = new JComboBox<>(hiria.toArray(new String[0]));
		comboBoxHelmuga.setBounds(158, 300, 150, 22);
		getContentPane().add(comboBoxHelmuga);

		btnBiliatu = new JButton("Bilatu Bidaia");
		btnBiliatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bilatuBidaia();
			}
		});
		btnBiliatu.setBounds(329, 283, 89, 23);
		getContentPane().add(btnBiliatu);

		erakutsiJoan(idBidaia);

		getContentPane().setVisible(true);
	}

	private void erakutsiOstatua(int idBidaia) {
		erakutsiZerbitzuaBase(idBidaia);

		btnGorde.setBounds(115, 420, 89, 23);
		btnEzeztatu.setBounds(358, 420, 89, 23);

		for (Logela_motak logelaMota : logelaMotaList) {
			logelaMotakKod.add(logelaMota.getLogelaKodea()); // Usar el getter para obtener deskribapena
			logelaMotak.add(logelaMota.getLogelaDeskribapena()); // Usar el getter para obtener deskribapena
			
		}

		comboBoxLogela = new JComboBox<>(logelaMotak.toArray(new String[0]));
		comboBoxLogela.setBounds(158, 234, 150, 22);
		getContentPane().add(comboBoxLogela);

		lblHiria = new JLabel("Hiria");
		lblHiria.setBounds(113, 275, 35, 14);
		getContentPane().add(lblHiria);

		txtOstatuHiria = new JTextField();
		txtOstatuHiria.setBounds(158, 267, 150, 30);
		getContentPane().add(txtOstatuHiria);

		lblPrezioaOstatu = new JLabel("Prezioa");
		lblPrezioaOstatu.setBounds(102, 308, 46, 14);
		getContentPane().add(lblPrezioaOstatu);

		txtOstatuPrezio = new JTextField();
		txtOstatuPrezio.setBounds(158, 300, 150, 30);
		getContentPane().add(txtOstatuPrezio);

		btnBiliatuOstatu = new JButton("Bilatu ostatua");
		btnBiliatuOstatu.setBounds(329, 283, 118, 23);
		getContentPane().add(btnBiliatuOstatu);

		dataOstatuSarrera = new JDateChooser();
		dataOstatuSarrera.setBounds(115, 353, 150, 25);
		getContentPane().add(dataOstatuSarrera);

		lblOstatuSarrera = new JLabel("Sarrera data");
		lblOstatuSarrera.setBounds(22, 360, 78, 14);
		getContentPane().add(lblOstatuSarrera);

		dataOstatuIrteera = new JDateChooser();
		dataOstatuIrteera.setBounds(382, 353, 150, 25);
		getContentPane().add(dataOstatuIrteera);

		lblOstatuIrteera = new JLabel("Irteera data");
		lblOstatuIrteera.setBounds(289, 360, 78, 14);
		getContentPane().add(lblOstatuIrteera);

		lblLogelaMota = new JLabel("Logela mota");
		lblLogelaMota.setBounds(69, 238, 70, 14);
		getContentPane().add(lblLogelaMota);

	}

	private void erakutsiJarduera(int idBidaia) {
		erakutsiZerbitzuaBase(idBidaia);

		txtizena = new JTextField();
		txtizena.setBounds(158, 160, 150, 30);
		txtizena.setColumns(10);
		getContentPane().add(txtizena);

		lblIzena = new JLabel("Zerbitzuaren izena");
		lblIzena.setBounds(40, 169, 99, 14);
		getContentPane().add(lblIzena);

		btnGorde.setBounds(115, 420, 89, 23);
		btnEzeztatu.setBounds(358, 420, 89, 23);

		txtJardueraDeskribapena = new JTextArea();
		txtJardueraDeskribapena.setBounds(158, 234, 150, 78);
		getContentPane().add(txtJardueraDeskribapena);

		lblJardueraDeskribapena = new JLabel("Deskribapena");
		lblJardueraDeskribapena.setBounds(57, 263, 82, 14);
		getContentPane().add(lblJardueraDeskribapena);

		txtJardueraPrezioa = new JTextField();
		txtJardueraPrezioa.setColumns(10);
		txtJardueraPrezioa.setBounds(158, 325, 150, 30);
		getContentPane().add(txtJardueraPrezioa);

		lblJardueraData = new JLabel("Jarduera data");
		lblJardueraData.setBounds(70, 381, 78, 14);
		getContentPane().add(lblJardueraData);

		dataJarduera = new JDateChooser();
		dataJarduera.setBounds(158, 370, 150, 25);
		getContentPane().add(dataJarduera);

		lblJardueraPrezioa = new JLabel("Prezioa");
		lblJardueraPrezioa.setBounds(61, 333, 78, 14);
		getContentPane().add(lblJardueraPrezioa);

	}

	private void erakutsiJoan(int idBidaia) {

		dataAukeratuJoaneko = new JDateChooser();
		dataAukeratuJoaneko.setBounds(158, 333, 150, 25);
		getContentPane().add(dataAukeratuJoaneko);

		lblJoanekoData = new JLabel("Joaneko data");
		lblJoanekoData.setBounds(65, 340, 78, 14);
		getContentPane().add(lblJoanekoData);

		lblBidaiarenKodea = new JLabel("Bidaiaren_kodea");
		lblBidaiarenKodea.setBounds(51, 378, 100, 14);
		getContentPane().add(lblBidaiarenKodea);

		txtBidaiarenKodea = new JTextField();
		txtBidaiarenKodea.setColumns(10);
		txtBidaiarenKodea.setBounds(158, 369, 150, 30);
		getContentPane().add(txtBidaiarenKodea);

		lblAerolinea = new JLabel("Aerolinea");
		lblAerolinea.setBounds(92, 419, 59, 14);
		getContentPane().add(lblAerolinea);

		comboBoxAerolinea = new JComboBox<>(airelinea.toArray(new String[0]));
		comboBoxAerolinea.setBounds(158, 415, 150, 22);
		getContentPane().add(comboBoxAerolinea);

		lblPrezioa = new JLabel("Prezioa");
		lblPrezioa.setBounds(102, 460, 49, 14);
		getContentPane().add(lblPrezioa);

		txtPrezioa = new JTextField();
		txtPrezioa.setColumns(10);
		txtPrezioa.setBounds(158, 451, 150, 30);
		getContentPane().add(txtPrezioa);

		lblIrteeraOrdutegia = new JLabel("Irteera ordutegia");
		lblIrteeraOrdutegia.setBounds(52, 501, 99, 14);
		getContentPane().add(lblIrteeraOrdutegia);

		txtOrdutegia = new JTextField();
		txtOrdutegia.setColumns(10);
		txtOrdutegia.setBounds(158, 492, 150, 30);
		getContentPane().add(txtOrdutegia);

		lblIraupena = new JLabel("Iraupena");
		lblIraupena.setBounds(92, 542, 59, 14);
		getContentPane().add(lblIraupena);

		txtIraupena = new JTextField();
		txtIraupena.setColumns(10);
		txtIraupena.setBounds(158, 533, 150, 30);
		getContentPane().add(txtIraupena);

	}

	private void erakutsiJoanEtorria(int idBidaia) {
		erakutsiJoan(idBidaia);

		dataAukeratuEtorria = new JDateChooser();
		dataAukeratuEtorria.setBounds(455, 332, 150, 25);
		getContentPane().add(dataAukeratuEtorria);

		lblEtorriData = new JLabel("Etorri data");
		lblEtorriData.setBounds(362, 339, 78, 14);
		getContentPane().add(lblEtorriData);

		lblBidaiarenKodeaEtorria = new JLabel("Bidaiaren_kodea Etorria");
		lblBidaiarenKodeaEtorria.setBounds(348, 377, 100, 14);
		getContentPane().add(lblBidaiarenKodeaEtorria);

		txtBidaiarenKodeaEtorria = new JTextField();
		txtBidaiarenKodeaEtorria.setColumns(10);
		txtBidaiarenKodeaEtorria.setBounds(455, 368, 150, 30);
		getContentPane().add(txtBidaiarenKodeaEtorria);

		lblAerolineaEtorria = new JLabel("Aerolinea Etorria");
		lblAerolineaEtorria.setBounds(389, 418, 59, 14);
		getContentPane().add(lblAerolineaEtorria);

		lblPrezioaEtorria = new JLabel("Prezioa Etorria");
		lblPrezioaEtorria.setBounds(399, 459, 49, 14);
		getContentPane().add(lblPrezioaEtorria);

		txtPrezioEtorria = new JTextField();
		txtPrezioEtorria.setColumns(10);
		txtPrezioEtorria.setBounds(455, 450, 150, 30);
		getContentPane().add(txtPrezioEtorria);

		lblEtorriOrdutegia = new JLabel("Etorri ordutegia");
		lblEtorriOrdutegia.setBounds(349, 500, 99, 14);
		getContentPane().add(lblEtorriOrdutegia);

		txtEtorriOrdutegia = new JTextField();
		txtEtorriOrdutegia.setColumns(10);
		txtEtorriOrdutegia.setBounds(455, 491, 150, 30);
		getContentPane().add(txtEtorriOrdutegia);

		lblIraupenaEtorria = new JLabel("Iraupena Etorria");
		lblIraupenaEtorria.setBounds(389, 541, 59, 14);
		getContentPane().add(lblIraupenaEtorria);

		txtIraupenaEtorria = new JTextField();
		txtIraupenaEtorria.setColumns(10);
		txtIraupenaEtorria.setBounds(455, 532, 150, 30);
		getContentPane().add(txtIraupenaEtorria);

		comboBoxAerolineaEtorria = new JComboBox<>(airelinea.toArray(new String[0]));
		;
		comboBoxAerolineaEtorria.setBounds(455, 415, 150, 22);
		getContentPane().add(comboBoxAerolineaEtorria);
	}

	private void actualizarInterfaz(int index, int idBidaia) {
		getContentPane().removeAll(); // Elimina componentes anteriores
		getContentPane().add(comboBoxMotak); // Vuelve a agregar el ComboBox si es necesario
		switch (index) {
		case 0:
			setBounds(100, 100, 573, 695);
			erakutsiHegaldia(idBidaia);
			;
			break;
		case 1:
			setBounds(100, 100, 573, 500);
			erakutsiOstatua(idBidaia);
			break;
		case 2:
			setBounds(100, 100, 573, 500);
			erakutsiJarduera(idBidaia);
			break;
		}
		getContentPane().revalidate();
		getContentPane().repaint();
	}

	private void actualizarInterfazIbilbidea(int index, int idBidaia) {
		getContentPane().removeAll(); // Elimina componentes anteriores
		addGuztia();

		switch (index) {
		case 0: // "Joan"
			setBounds(100, 100, 573, 695);
			lblLogoa.setBounds(346, 40, 170, 160);
			erakutsiJoan(idBidaia);
			;
			break;
		case 1: // "Joan eta etorri"
			setBounds(100, 100, 650, 695);
			lblLogoa.setBounds(346, 40, 350, 160);
			erakutsiJoanEtorria(idBidaia);
			;
			break;
		}
		getContentPane().revalidate(); // Refresca la interfaz
		getContentPane().repaint(); // Redibuja la interfaz
	}

	@SuppressWarnings("deprecation")
	private void cargarLogotipoYColor() {
		try {
			String logoUrl = cache.getAgentzia().getLogoa();
			if (logoUrl != null && !logoUrl.isEmpty()) {

				ImageIcon imageIcon = new ImageIcon(new URL(new URI(logoUrl).toURL().toString()));
				lblLogoa = new JLabel(
						new ImageIcon(imageIcon.getImage().getScaledInstance(170, 160, Image.SCALE_SMOOTH)));
				lblLogoa.setBounds(346, 40, 170, 160);
				getContentPane().add(lblLogoa);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			String hexKolor = cache.getAgentzia().getMarkarenKolorea();
			if (hexKolor != null && !hexKolor.isEmpty()) {
				getContentPane().setBackground(Color.decode(hexKolor));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addGuztia() {
		getContentPane().add(comboBoxMotak); // Añadir el ComboBox si es necesario
		getContentPane().add(lblIbilbide);
		getContentPane().add(lblJatorrizkoaireportua);
		getContentPane().add(comboBoxJatorria);
		getContentPane().add(lblHelmugakoAireportua);
		getContentPane().add(lblTituloa);
		getContentPane().add(comboBoxHelmuga);
		getContentPane().add(lblMota);
		getContentPane().add(btnEzeztatu);
		getContentPane().add(btnGorde);
		getContentPane().add(btnBiliatu);
		getContentPane().add(comboBoxIbilbidea); // Asegúrate de que el comboBoxIbilbidea vuelva a estar presente
		cargarLogotipoYColor();
	}

	private void bilatuBidaia() {
		try {
			int jatorrizkoIndex = comboBoxJatorria.getSelectedIndex();
			int helmugakoIndex = comboBoxHelmuga.getSelectedIndex();
			String jatorrizkoAireportua = aireportuKodea.get(jatorrizkoIndex);
			String helmugaAireportua = aireportuKodea.get(helmugakoIndex);
			Date joanekoData = dataAukeratuJoaneko.getDate();

			// Formatear la fecha en formato YYYYMMDD (sin espacios ni caracteres no
			// válidos)
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			String formattedDate = dateFormat.format(joanekoData);

			// Construir la URL con codificación segura
			String skyscannerUrl = "https://www.skyscanner.es/transporte/vuelos/"
					+ URLEncoder.encode(jatorrizkoAireportua, StandardCharsets.UTF_8) + "/"
					+ URLEncoder.encode(helmugaAireportua, StandardCharsets.UTF_8) + "/" + formattedDate
					+ "/?adultsv2=1&cabinclass=economy&childrenv2=&ref=home&rtn=0&preferdirects=false&outboundaltsenabled=false&inboundaltsenabled=false";

			// Redirigir al navegador
			URI uri = new URI(skyscannerUrl);
			Desktop.getDesktop().browse(uri);
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		}
	}
}
