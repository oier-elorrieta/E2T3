package vista;

import javax.swing.JFrame;

import com.toedter.calendar.JDateChooser;

import modelo.pojo.Airelinea;
import modelo.pojo.Aireportu;
import modelo.pojo.Cache;
import modelo.pojo.Logela_motak;
import modelo.sql.SqlMetodoak;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
			lblEtorriOrdutegia, lblIraupenaEtorria, lblLogelaMota, lblJardueraPrezioa, lblOstatuSarrera,
			lblOstatuIrteera, lblJardueraDeskribapena;
	private Cache cache = new Cache();
	private JDateChooser dataAukeratuJoaneko, dataJarduera, dataAukeratuEtorria, dataOstatuSarrera, dataOstatuIrteera;
	private JTextField txtizena, txtBidaiarenKodea, txtPrezioa, txtOrdutegia, txtIraupena, txtOstatuHiria,
			txtOstatuPrezio, txtBidaiarenKodeaEtorria, txtEtorriOrdutegia, txtIraupenaEtorria, txtJardueraPrezioa;
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

		// metemos a mano en el arrayList las opciones para elegir el tipo de servicio
		// en el combobox

		zerbitzuMota.add("Hegaldia");
		zerbitzuMota.add("Ostatua");
		zerbitzuMota.add("Jarduera");

		// creación del comboBox de Motak

		comboBoxMotak = new JComboBox<>(zerbitzuMota.toArray(new String[0]));
		comboBoxMotak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxMotak.setEnabled(false); // Deshabilita para evitar múltiples eventos seguidos
				// llamada al metodo de actualizar el la pantalla y mostrar el servicio
				// seleccionado
				interfazaEguneratu(comboBoxMotak.getSelectedIndex(), idBidaia);
				comboBoxMotak.setEnabled(true); // Reactiva después de actualizar
			}
		});
		comboBoxMotak.setBounds(158, 201, 150, 22);
		getContentPane().add(comboBoxMotak);
		// mostramos por defecto hegaldia
		erakutsiHegaldia(idBidaia);
	}

	// este es el metodo para enseñar lo que tienen todos en común y no tener que
	// ponerlo en cada metodo

	private void erakutsiZerbitzuaBase(int idBidaia) {

		// creación de los labels

		lblTituloa = new JLabel("Zerbitzuak");
		lblTituloa.setFont(new Font("ROG Fonts", Font.PLAIN, 27));
		lblTituloa.setBounds(45, 69, 243, 61);
		getContentPane().add(lblTituloa);

		lblMota = new JLabel("Zerbitzuen mota");
		lblMota.setBounds(45, 206, 94, 14);
		getContentPane().add(lblMota);

		// creación del botón de cancelar

		btnEzeztatu = new JButton("Ezeztatu");
		btnEzeztatu.addActionListener(e -> {
			new LeihoarenFuntioak().irekiHasiera();
			dispose();
		});
		btnEzeztatu.setBounds(358, 622, 89, 23);
		getContentPane().add(btnEzeztatu);

		// creación del boton de guardado

		btnGorde = new JButton("Gorde");
		btnGorde.addActionListener(e -> {
			// dependiendo de lo que está seleccionado lo guarda de una manera u otra
			if (comboBoxMotak.getSelectedIndex() == 0) {
				if (comboBoxIbilbidea.getSelectedIndex() == 0) {

					// guardamos el indice de los combobox para luego poder sacar el codigo de cada
					// cosa y poder subirlo

					int jatorriaIndex = comboBoxJatorria.getSelectedIndex();
					int helmugaIndex = comboBoxHelmuga.getSelectedIndex();
					int aerolineaIndex = comboBoxAerolinea.getSelectedIndex();

					// metemos todo en las variables para luego pasarlas por parametro al metodo que
					// hace la consulta

					String jatorrizkoAireportua = aireportuKodea.get(jatorriaIndex);
					String helmugakoAireportua = aireportuKodea.get(helmugaIndex);
					String aerolinea = airelineaKodea.get(aerolineaIndex);
					Date joanekoData = dataAukeratuJoaneko.getDate();
					String sJoanekoData = dataFormatua.format(joanekoData);
					String hegaldiKodea = txtBidaiarenKodea.getText();

					float prezio = Float.parseFloat(txtPrezioa.getText());
					Time orduteguia = Time.valueOf(txtOrdutegia.getText());
					Time iraupena = Time.valueOf(txtIraupena.getText());

					sm.zerbitzuJoanEgin(idBidaia, jatorrizkoAireportua, helmugakoAireportua, sJoanekoData, hegaldiKodea,
							aerolinea, prezio, orduteguia, iraupena);

				} else {
					// guardamos el indice de los combobox para luego poder sacar el codigo de cada
					// cosa y poder subirlo

					int jatorriaIndex = comboBoxJatorria.getSelectedIndex();
					int helmugaIndex = comboBoxHelmuga.getSelectedIndex();
					int aerolineaIndex = comboBoxAerolinea.getSelectedIndex();

					// metemos todo en las variables para luego pasarlas por parametro al metodo que
					// hace la consulta

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
					Time orduteguiaE = Time.valueOf(txtEtorriOrdutegia.getText());
					Time iraupenaE = Time.valueOf(txtIraupenaEtorria.getText());

					sm.zerbitzuJoanEtorriEgin(idBidaia, jatorrizkoAireportua, helmugakoAireportua, sJoanekoData,
							hegaldiKodea, aerolinea, prezio, orduteguia, iraupena, jatorrizkoAireportuaE,
							helmugakoAireportuaE, sJoanekoDataE, hegaldiKodeaE, aerolineaE, orduteguiaE, iraupenaE);

				}
			} else if (comboBoxMotak.getSelectedIndex() == 1) {

				// guardamos el indice de los combobox para luego poder sacar el codigo de cada
				// cosa y poder subirlo

				int logelaMotaIndex = comboBoxLogela.getSelectedIndex();

				// metemos todo en las variables para luego pasarlas por parametro al metodo que
				// hace la consulta

				String logelaMota = logelaMotakKod.get(logelaMotaIndex);
				String hiria = txtOstatuHiria.getText();
				float prezioO = Float.parseFloat(txtOstatuPrezio.getText());
				Date sarreraDate = dataOstatuSarrera.getDate();
				String sSarreraDate = dataFormatua.format(sarreraDate);
				Date irteeraDate = dataOstatuIrteera.getDate();
				String sIrteeraDate = dataFormatua.format(irteeraDate);

				sm.zerbitzuOstatuEgin(idBidaia, logelaMota, hiria, prezioO, sSarreraDate, sIrteeraDate);

			} else {

				// metemos todo en las variables para luego pasarlas por parametro al metodo que
				// hace la consulta

				String jardueraIzena = txtizena.getText();
				String jardueraDeskribapena = txtJardueraDeskribapena.getText();
				float prezioJ = Float.parseFloat(txtJardueraPrezioa.getText());
				Date jardueraDate = dataJarduera.getDate();
				String sJardueraDate = dataFormatua.format(jardueraDate);

				sm.zerbitzuJardueraEgin(idBidaia, jardueraIzena, jardueraDeskribapena, prezioJ, sJardueraDate);

			}
			new LeihoarenFuntioak().irekiHasiera();
			dispose();
		});
		btnGorde.setBounds(115, 622, 89, 23);
		getContentPane().add(btnGorde);

		// llamada al metodo de cargar el logo y el color

		kargatuLogoaKolorea();
	}

	// metodo para enseñar las cosas de hegaldia

	private void erakutsiHegaldia(int idBidaia) {

		// llamada al metodo que enseña lo que tienen en comun

		erakutsiZerbitzuaBase(idBidaia);

		// si el metodo sartu es true es que ya se han metido los valores en el
		// arrayList esto es para prevenir duplicados

		if (sartu == false) {
			for (Aireportu aireportu : iata) {
				aireportuKodea.add(aireportu.getAireportuKodea());
				hiria.add(aireportu.getHiria());
			}

			for (Airelinea airelineak : airelineaList) {
				airelinea.add(airelineak.getAirelineIzena());
				airelineaKodea.add(airelineak.getAirelineaKodea());
			}

			ibilbidea.add("Joan");
			ibilbidea.add("Joan eta etorri");

			sartu = true;
		}

		// creación del comboBox de ibilbideak si es joan o joan_etorria

		comboBoxIbilbidea = new JComboBox<>(ibilbidea.toArray(new String[0]));
		comboBoxIbilbidea.setBounds(158, 234, 150, 22);
		comboBoxIbilbidea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxIbilbidea.setEnabled(false); // Deshabilita para evitar múltiples eventos seguidos
				interfazaEguneratuIbilbidea(comboBoxIbilbidea.getSelectedIndex(), idBidaia);
				comboBoxIbilbidea.setEnabled(true); // Reactiva después de actualizar
			}
		});
		getContentPane().add(comboBoxIbilbidea);

		// creación del los labels

		lblIbilbide = new JLabel("Ibilbidea");
		lblIbilbide.setBounds(87, 239, 52, 14);
		getContentPane().add(lblIbilbide);

		lblJatorrizkoaireportua = new JLabel("Jatorrizko aireportua");
		lblJatorrizkoaireportua.setBounds(30, 272, 115, 14);
		getContentPane().add(lblJatorrizkoaireportua);

		// creación de los combobox de los aeropuertos y sus labels

		comboBoxJatorria = new JComboBox<>(hiria.toArray(new String[0]));
		comboBoxJatorria.setBounds(158, 267, 150, 22);
		getContentPane().add(comboBoxJatorria);

		lblHelmugakoAireportua = new JLabel("Helmugako aireportua");
		lblHelmugakoAireportua.setBounds(19, 306, 132, 14);
		getContentPane().add(lblHelmugakoAireportua);

		comboBoxHelmuga = new JComboBox<>(hiria.toArray(new String[0]));
		comboBoxHelmuga.setBounds(158, 300, 150, 22);
		getContentPane().add(comboBoxHelmuga);

		// creación del botón para buscar un viaje en google

		btnBiliatu = new JButton("Bilatu Bidaia");
		btnBiliatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// llamada al metodo de busqueda en google del vuelo

				bilatuBidaia();
			}
		});
		btnBiliatu.setBounds(329, 283, 89, 23);
		getContentPane().add(btnBiliatu);

		// enseñamos por defecto joan

		erakutsiJoan(idBidaia);

		getContentPane().setVisible(true);
	}

	// metodo para enseñar todo lo de ostatua

	private void erakutsiOstatua(int idBidaia) {
		// llamada al metodo que enseña lo que tienen en comun
		erakutsiZerbitzuaBase(idBidaia);

		// ajustamos donde queremos poner los botones

		btnGorde.setBounds(115, 420, 89, 23);
		btnEzeztatu.setBounds(358, 420, 89, 23);

		// añadir cosas a los ArrayList

		for (Logela_motak logelaMota : logelaMotaList) {
			logelaMotakKod.add(logelaMota.getLogelaKodea()); // Usar el getter para obtener deskribapena
			logelaMotak.add(logelaMota.getLogelaDeskribapena()); // Usar el getter para obtener deskribapena

		}

		// creación del comboBox de logela motak

		comboBoxLogela = new JComboBox<>(logelaMotak.toArray(new String[0]));
		comboBoxLogela.setBounds(158, 234, 150, 22);
		getContentPane().add(comboBoxLogela);

		// creación de los labels y txtField

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

		// creación del boton de busqueda de ostatua

		btnBiliatuOstatu = new JButton("Bilatu ostatua");
		btnBiliatuOstatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bilatuOstatua();
			}
		});
		btnBiliatuOstatu.setBounds(329, 283, 118, 23);
		getContentPane().add(btnBiliatuOstatu);

		// creación de los JDateChooser para elegir las fechas y sus labels

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

	// metodo para enseñar Jarduera

	private void erakutsiJarduera(int idBidaia) {
		// llamada al metodo que enseña lo que tienen en comun
		erakutsiZerbitzuaBase(idBidaia);

		// creación de los txtFields y labels

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

		// creación del JDatechooser y su label para elegir la fecha del evento

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

	// metodo para enseñar el viaje de ida

	private void erakutsiJoan(int idBidaia) {

		// creación del JDatechooser y su label para elegir la fecha del viaje de ida

		dataAukeratuJoaneko = new JDateChooser();
		dataAukeratuJoaneko.setBounds(158, 333, 150, 25);
		getContentPane().add(dataAukeratuJoaneko);

		lblJoanekoData = new JLabel("Joaneko data");
		lblJoanekoData.setBounds(65, 340, 78, 14);
		getContentPane().add(lblJoanekoData);

		// creación de los labels y txtFields y comboBox

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

	// metodo para enseñar ida y vuelta

	private void erakutsiJoanEtorria(int idBidaia) {

		// llamada al viaje de ida para enseñar las dos cosas

		erakutsiJoan(idBidaia);

		// cambio e sitio del precio

		txtPrezioa.setBounds(455, 448, 150, 30);
		lblPrezioa.setBounds(399, 457, 49, 14);

		// creación del JDatechooser y su label para elegir la fecha del viaje de vuelta

		dataAukeratuEtorria = new JDateChooser();
		dataAukeratuEtorria.setBounds(455, 332, 150, 25);
		getContentPane().add(dataAukeratuEtorria);

		lblEtorriData = new JLabel("Etorri data");
		lblEtorriData.setBounds(362, 339, 78, 14);
		getContentPane().add(lblEtorriData);

		// creación de los labels y txtFields

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

		// creación del comboBox para las aerolineas

		comboBoxAerolineaEtorria = new JComboBox<>(airelinea.toArray(new String[0]));
		comboBoxAerolineaEtorria.setBounds(455, 415, 150, 22);
		getContentPane().add(comboBoxAerolineaEtorria);
	}

	// metodo que actualiza la interfaz dependiendo de lo que se eliga en el
	// combobox de motas

	private void interfazaEguneratu(int index, int idBidaia) {
		getContentPane().removeAll(); // Elimina componentes anteriores
		getContentPane().add(comboBoxMotak); // Vuelve a agregar el ComboBox si es necesario
		switch (index) {
		case 0:
			//cambiamos el tamaño de la ventana
			setBounds(100, 100, 573, 695);
			//llamada al metodo para cambiar la pantalla y mostrar lo que esté dentro
			erakutsiHegaldia(idBidaia);
			;
			break;
		case 1:
			//cambiamos el tamaño de la ventana
			setBounds(100, 100, 573, 500);
			//llamada al metodo para cambiar la pantalla y mostrar lo que esté dentro
			erakutsiOstatua(idBidaia);
			break;
		case 2:
			//cambiamos el tamaño de la ventana
			setBounds(100, 100, 573, 500);
			//llamada al metodo para cambiar la pantalla y mostrar lo que esté dentro
			erakutsiJarduera(idBidaia);
			break;
		}
		getContentPane().revalidate();
		getContentPane().repaint();
	}

	private void interfazaEguneratuIbilbidea(int index, int idBidaia) {
		getContentPane().removeAll(); // Elimina componentes anteriores
		//llamada al metodo para añadir las cosas que se quitan
		addGuztia();

		switch (index) {
		case 0: // "Joan"
			//cambiamos el tamaño de la ventana
			setBounds(100, 100, 573, 695);
			//cambiamos de sitio el logo
			lblLogoa.setBounds(346, 40, 170, 160);
			//llamada al metodo para cambiar la pantalla y mostrar lo que esté dentro
			erakutsiJoan(idBidaia);
			;
			break;
		case 1: // "Joan eta etorri"
			//cambiamos el tamaño de la ventana
			setBounds(100, 100, 650, 695);
			//cambiamos de sitio el logo
			lblLogoa.setBounds(346, 40, 350, 160);
			//llamada al metodo para cambiar la pantalla y mostrar lo que esté dentro
			erakutsiJoanEtorria(idBidaia);
			;
			break;
		}
		getContentPane().revalidate(); // Refresca la interfaz
		getContentPane().repaint(); // Redibuja la interfaz
	}

	@SuppressWarnings("deprecation")

	// metodo para poner el color de fondo y el logo

	private void kargatuLogoaKolorea() {
		try {
			// sacamos la url del logo de la agentzia que se ha registrado

			String logoUrl = cache.getAgentzia().getLogoa();
			if (logoUrl != null && !logoUrl.isEmpty()) {

				// convertimos la url a imagen

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

			// pillamos el codigo hexadecimal que está en la base de datos de esa misma
			// agencia y la formateamos en String

			String hexKolor = cache.getAgentzia().getMarkarenKolorea();
			if (hexKolor != null && !hexKolor.isEmpty()) {
				// lo convertimos con el decode para que sepa que color es y ponerlo en el
				// background
				getContentPane().setBackground(Color.decode(hexKolor));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//metodo para añadir las cosas que se quitan

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
		kargatuLogoaKolorea();
	}
	
	//metodo para buscar en google el viaje seleccionado (en este caso es el vuelo y se busca en skyscanner)

	private void bilatuBidaia() {
		try {
			
			//pillamos el index de los combobox para pasarlo a el kodigo del mismo
			
			int jatorrizkoIndex = comboBoxJatorria.getSelectedIndex();
			int helmugakoIndex = comboBoxHelmuga.getSelectedIndex();
			String jatorrizkoAireportua = aireportuKodea.get(jatorrizkoIndex);
			String helmugaAireportua = aireportuKodea.get(helmugakoIndex);
			
			//pillamos la fecha que está seleccionada
			
			Date joanekoData = dataAukeratuJoaneko.getDate();

			// Formatear la fecha en formato YYYYMMDD (sin espacios ni caracteres no
			// válidos)
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			String formattedDate = dateFormat.format(joanekoData);

			// Construir la URL
			String skyscannerUrl = "https://www.skyscanner.es/transporte/vuelos/"
					//esto pasa lo que está en el string a UTF_8 para que lo entienda la página web
					+ URLEncoder.encode(jatorrizkoAireportua, StandardCharsets.UTF_8) + "/"
					+ URLEncoder.encode(helmugaAireportua, StandardCharsets.UTF_8) + "/" + formattedDate
					//esto es para configurar la pagina con las cosas que necesita	
					+ "/?adultsv2=1&cabinclass=economy&childrenv2=&ref=home&rtn=0&preferdirects=false&outboundaltsenabled=false&inboundaltsenabled=false";

			// Redirigir al navegador
			URI uri = new URI(skyscannerUrl);
			Desktop.getDesktop().browse(uri);
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		}	
	}
	
	//metodo para buscar el hotel en booking
	
	private void bilatuOstatua() {
	    try {
	        String hiria = txtOstatuHiria.getText();
	        
	        // Obtener las fechas seleccionadas
	        Date hasieraData = dataOstatuSarrera.getDate();
	        Date amaieraData = dataOstatuIrteera.getDate();

	        // Verificar que las fechas no sean nulas
	        if (hasieraData == null || amaieraData == null) {
	            JOptionPane.showMessageDialog(null, "Por favor, seleccione ambas fechas.", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        // Formatear las fechas en formato YYYY-MM-DD
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        String formattedDate1 = dateFormat.format(hasieraData);
	        String formattedDate2 = dateFormat.format(amaieraData);

	        // Construir la URL de búsqueda en Booking
	        String bookingUrl = "https://www.booking.com/searchresults.html?"
	                + "ss=" + URLEncoder.encode(hiria, StandardCharsets.UTF_8)
	                + "&checkin=" + formattedDate1
	                + "&checkout=" + formattedDate2
	                + "&group_adults=1&no_rooms=1&group_children=0";

	        // Abrir el navegador con la URL generada
	        URI uri = new URI(bookingUrl);
	        Desktop.getDesktop().browse(uri);
	    } catch (URISyntaxException | IOException e) {
	        e.printStackTrace();
	    }
	}


}
