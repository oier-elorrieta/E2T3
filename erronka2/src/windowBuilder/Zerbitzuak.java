package windowBuilder;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import modelo.*;

import sqlKonexioa.SqlMetodoak;

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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Zerbitzuak extends JFrame {

	private JPanel contentPane;
	private SqlMetodoak sm = new SqlMetodoak();
	private JLabel lblLogoa, lblIzena, lblMota, lblJoanekoData, lblHelmugakoAireportua, lblIbilbide,
			lblJatorrizkoaireportua, lblBidaiarenKodea, lblAerolinea, lblPrezioa, lblIrteeraOrdutegia, lblIraupena;
	private Cache cache = new Cache();
	private JDateChooser dataAukeratuJoaneko;
	private JTextField txtizena, txtBidaiarenKodea, txtPrezioa, txtOrdutegia, txtIraupena;
	private JComboBox<String> comboBoxHelmuga, comboBoxMotak, comboBoxJatorria, comboBoxIbilbidea, comboBoxAerolinea;
	ArrayList<Aireportu> iata = sm.aireportuaEduki();
	ArrayList<Airelinea> airelineaList = sm.airelineaEduki();
	ArrayList<String> aireportuKodea = new ArrayList<>(), hiria = new ArrayList<>(), airelineaKodea = new ArrayList<>(),
			airelinea = new ArrayList<>(), ibilbidea = new ArrayList<>(), zerbitzuMota = new ArrayList<>();
	private JButton btnGorde, btnEzeztatu, btnBiliatu;
	private SimpleDateFormat dataFormatua = new SimpleDateFormat("yyyy/MM/dd"); // Formato DD/MM/YYYY
	private boolean sartu = false;

	public Zerbitzuak() {

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
				actualizarInterfaz(comboBoxMotak.getSelectedIndex());
				comboBoxMotak.setEnabled(true); // Reactiva después de actualizar
			}
		});
		comboBoxMotak.setBounds(158, 201, 150, 22);
		getContentPane().add(comboBoxMotak);

		erakutsiHegaldia();
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

	private void actualizarInterfaz(int index) {
		getContentPane().removeAll(); // Elimina componentes anteriores
		getContentPane().add(comboBoxMotak); // Vuelve a agregar el ComboBox si es necesario
		switch (index) {
		case 0:
			erakutsiHegaldia();
			break;
		case 1:
			erakutsiOstatua();
			break;
		case 2:
			erakutsiJarduera();
			break;
		}
		getContentPane().revalidate();
		getContentPane().repaint();
	}

	private void erakutsiZerbitzuaBase() {
		lblIzena = new JLabel("Zerbitzuaren izena");
		lblIzena.setBounds(40, 169, 99, 14);
		getContentPane().add(lblIzena);

		lblMota = new JLabel("Zerbitzuen mota");
		lblMota.setBounds(45, 206, 94, 14);
		getContentPane().add(lblMota);

		txtizena = new JTextField();
		txtizena.setBounds(158, 160, 150, 30);
		txtizena.setColumns(10);
		getContentPane().add(txtizena);

		btnEzeztatu = new JButton("Ezeztatu");
		btnEzeztatu.addActionListener(e -> {
			new LehioarenFuntioak().irekiHasiera();
			dispose();
		});
		btnEzeztatu.setBounds(358, 622, 89, 23);
		getContentPane().add(btnEzeztatu);

		btnGorde = new JButton("Gorde");
		btnGorde.addActionListener(e -> {
			new LehioarenFuntioak().irekiHasiera();
			dispose();
		});
		btnGorde.setBounds(115, 622, 89, 23);
		getContentPane().add(btnGorde);

		cargarLogotipoYColor();
	}

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

	private void erakutsiHegaldia() {
		erakutsiZerbitzuaBase();
		
		if(sartu == false){
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

		dataAukeratuJoaneko = new JDateChooser();
		dataAukeratuJoaneko.setBounds(158, 333, 150, 25);
		getContentPane().add(dataAukeratuJoaneko);

		lblJoanekoData = new JLabel("Joaneko data");
		lblJoanekoData.setBounds(65, 340, 78, 14);
		getContentPane().add(lblJoanekoData);

		lblHelmugakoAireportua = new JLabel("Helmugako aireportua");
		lblHelmugakoAireportua.setBounds(19, 306, 132, 14);
		getContentPane().add(lblHelmugakoAireportua);

		comboBoxJatorria = new JComboBox<>(hiria.toArray(new String[0]));
		comboBoxJatorria.setBounds(158, 267, 150, 22);
		getContentPane().add(comboBoxJatorria);

		comboBoxHelmuga = new JComboBox<>(hiria.toArray(new String[0]));
		comboBoxHelmuga.setBounds(158, 300, 150, 22);
		getContentPane().add(comboBoxHelmuga);

		comboBoxIbilbidea = new JComboBox<>(ibilbidea.toArray(new String[0]));
		comboBoxIbilbidea.setBounds(158, 234, 150, 22);
		getContentPane().add(comboBoxIbilbidea);

		lblBidaiarenKodea = new JLabel("Bidaiaren_kodea\t");
		lblBidaiarenKodea.setBounds(51, 378, 100, 14);
		getContentPane().add(lblBidaiarenKodea);

		txtBidaiarenKodea = new JTextField();
		txtBidaiarenKodea.setColumns(10);
		txtBidaiarenKodea.setBounds(158, 369, 150, 30);
		getContentPane().add(txtBidaiarenKodea);

		lblAerolinea = new JLabel("Aerolinea");
		lblAerolinea.setBounds(92, 419, 59, 14);
		getContentPane().add(lblAerolinea);

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

		btnBiliatu = new JButton("Bilatu Bidaia");
		btnBiliatu.setBounds(329, 283, 89, 23);
		getContentPane().add(btnBiliatu);

		lblIbilbide = new JLabel("Ibilbidea");
		lblIbilbide.setBounds(87, 239, 52, 14);
		getContentPane().add(lblIbilbide);

		lblJatorrizkoaireportua = new JLabel("Jatorrizko aireportua");
		lblJatorrizkoaireportua.setBounds(30, 272, 115, 14);
		getContentPane().add(lblJatorrizkoaireportua);

		comboBoxAerolinea = new JComboBox<>(airelinea.toArray(new String[0]));
		comboBoxAerolinea.setBounds(158, 415, 150, 22);
		getContentPane().add(comboBoxAerolinea);

		getContentPane().setVisible(true);

	}

	private void erakutsiOstatua() {
		erakutsiZerbitzuaBase();

	}

	private void erakutsiJarduera() {
		erakutsiZerbitzuaBase();

	}

}
