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

	public Zerbitzuak() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 573, 695);
		getContentPane().setLayout(null);

		txtizena = new JTextField();
		txtizena.setBounds(158, 160, 150, 30);
		getContentPane().add(txtizena);
		txtizena.setColumns(10);

		zerbitzuMota.add("Hegaldia");
		zerbitzuMota.add("Ostatua");
		zerbitzuMota.add("Jarduera");

		comboBoxMotak = new JComboBox<>(zerbitzuMota.toArray(new String[0]));
		comboBoxMotak.setBounds(158, 201, 150, 22);
		getContentPane().add(comboBoxMotak);
		
		if(comboBoxMotak.getSelectedIndex() == 0) {
			
			dataAukeratuJoaneko = new JDateChooser();
			dataAukeratuJoaneko.setBounds(158, 333, 150, 25);
			getContentPane().add(dataAukeratuJoaneko);

			lblIzena = new JLabel("Zerbitzuaren izena");
			lblIzena.setBounds(40, 169, 99, 14);
			getContentPane().add(lblIzena);

			lblMota = new JLabel("Zerbitzuen mota");
			lblMota.setBounds(45, 206, 94, 14);
			getContentPane().add(lblMota);

			lblJoanekoData = new JLabel("Joaneko data");
			lblJoanekoData.setBounds(65, 340, 78, 14);
			getContentPane().add(lblJoanekoData);

			for (Aireportu aireportu : iata) {
				aireportuKodea.add(aireportu.getAireportuKodea()); // Usar el getter para obtener deskribapena
				hiria.add(aireportu.getHiria());
			}

			lblHelmugakoAireportua = new JLabel("Helmugako aireportua");
			lblHelmugakoAireportua.setBounds(19, 306, 132, 14);
			getContentPane().add(lblHelmugakoAireportua);

			comboBoxJatorria = new JComboBox<>(hiria.toArray(new String[0]));
			comboBoxJatorria.setBounds(158, 267, 150, 22);
			getContentPane().add(comboBoxJatorria);

			comboBoxHelmuga = new JComboBox<>(hiria.toArray(new String[0]));
			comboBoxHelmuga.setBounds(158, 300, 150, 22);
			getContentPane().add(comboBoxHelmuga);

			ibilbidea.add("Joan");
			ibilbidea.add("Joan eta etorri");

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
			btnBiliatu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					bilatuBidaia();
				}
			});
			btnBiliatu.setBounds(329, 283, 89, 23);
			getContentPane().add(btnBiliatu);

			lblIbilbide = new JLabel("Ibilbidea");
			lblIbilbide.setBounds(87, 239, 52, 14);
			getContentPane().add(lblIbilbide);

			lblJatorrizkoaireportua = new JLabel("Jatorrizko aireportua");
			lblJatorrizkoaireportua.setBounds(30, 272, 115, 14);
			getContentPane().add(lblJatorrizkoaireportua);

			for (Airelinea airelineak : airelineaList) {
				airelinea.add(airelineak.getAirelineIzena()); // Usar el getter para obtener deskribapena
				airelineaKodea.add(airelineak.getAirelineaKodea());
			}

			comboBoxAerolinea = new JComboBox<>(airelinea.toArray(new String[0]));
			comboBoxAerolinea.setBounds(158, 415, 150, 22);
			getContentPane().add(comboBoxAerolinea);
		}
		else if (comboBoxMotak.getSelectedIndex() == 1) {
			
		}

		btnEzeztatu = new JButton("Ezeztatu");
		btnEzeztatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LehioarenFuntioak fv = new LehioarenFuntioak();
				fv.irekiHasiera();
				dispose();
			}
		});
		btnEzeztatu.setBounds(358, 622, 89, 23);
		getContentPane().add(btnEzeztatu);

		btnGorde = new JButton("Gorde");
		btnGorde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				LehioarenFuntioak fv = new LehioarenFuntioak();
				fv.irekiHasiera();
				dispose();

			}
		});
		btnGorde.setBounds(115, 622, 89, 23);
		getContentPane().add(btnGorde);
		

		getContentPane().setVisible(true);

		try {
			// Obtener la URL del logo de la caché (acceso estático)
			String logoUrl = cache.getAgentzia().getLogoa(); // Accedemos al logo desde Cache

			if (logoUrl != null && !logoUrl.isEmpty()) {
				// Convertir la URL a URI y luego a URL
				URI uri = new URI(logoUrl);
				URL url = uri.toURL(); // Convertimos URI a URL

				// Crear un ImageIcon con la URL
				ImageIcon imageIcon1 = new ImageIcon(url);

				// Crear el JLabel
				lblLogoa = new JLabel();

				// Establecer el tamaño del JLabel según el layout o manualmente
				lblLogoa.setBounds(346, 40, 170, 160); // Establecemos el tamaño fijo (puedes ajustarlo según lo
														// necesites)

				// Escalar la imagen al tamaño del JLabel (con el mismo enfoque que en el botón)
				Image image1 = imageIcon1.getImage().getScaledInstance(lblLogoa.getWidth(), lblLogoa.getHeight(),
						Image.SCALE_SMOOTH);
				imageIcon1.setImage(image1);

				// Establecer el icono redimensionado en el JLabel
				lblLogoa.setIcon(imageIcon1);

				getContentPane().add(lblLogoa);

			} else {
				System.out.println("Logoaren URL txarto dago.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		String hexKolor = cache.getAgentzia().getMarkarenKolorea();

		try {

			if (hexKolor != null && !hexKolor.isEmpty()) {

				Color kolor = Color.decode(hexKolor); // Convertir el código hexadecimal a un color

				getContentPane().setBackground(kolor);
			} else {
				System.out.println("Kolorea txarto dago.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
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
