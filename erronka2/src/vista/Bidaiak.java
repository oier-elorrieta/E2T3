package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import modelo.pojo.Bidai_Motak;
import modelo.pojo.Cache;
import modelo.pojo.Herrialde;
import modelo.sql.SqlMetodoak;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Bidaiak extends JFrame {

	private JPanel contentPane;
	private JLabel lblLogoa, lblIzena, lblBidaiarenMota, lblHasiera, lblAmaiera, lblEgunak, lblHerrialdea,
			lblDeskribapena, lblEzBarne;
	private Cache cache = new Cache();
	private SqlMetodoak sm = new SqlMetodoak();
	private JDateChooser dataAukeratuAmaiera, dataAukeratuHasiera;
	private JTextField txtizena, txtFieldEgunak;
	private JComboBox<String> comboBoxHerrialdea, comboBoxMotak;
	private ArrayList<Bidai_Motak> bidaiaList;
	private ArrayList<Herrialde> herrialdeList;
	private ArrayList<String> bidaiMotaDeskribapena = new ArrayList<>(), bidaiMotaKodea = new ArrayList<>(),
			herrialdeDeskribapena = new ArrayList<>(), herrialdeKodea = new ArrayList<>();
	private JTextArea textAreaDeskribapena, textAreaEzBarne;
	private JButton btnGorde, btnEzeztatu;
	private SimpleDateFormat dataFormatua = new SimpleDateFormat("yyyy/MM/dd"); // Formato DD/MM/YYYY

	public Bidaiak() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 573, 616);
		getContentPane().setLayout(null);

		// creamos el txtfield para el nombre del viaje

		txtizena = new JTextField();
		txtizena.setBounds(137, 111, 150, 30);
		getContentPane().add(txtizena);
		txtizena.setColumns(10);

		// llenamos el arrayList de bidaia con las cosas de las bases de datos

		bidaiaList = sm.bidaiMotaEduki();

		// recorremos el arrayList todo el rato y metemos las cosas que queremos en los
		// arrayList en este caso deskribapena y kodea
		for (Bidai_Motak bidaia : bidaiaList) {
			bidaiMotaDeskribapena.add(bidaia.getDeskribapena()); // Usar el getter para obtener deskribapena
			bidaiMotaKodea.add(bidaia.getBidaiKodea());
		}

		// creación del comboBox para mostrar los deskribapenas de bidaiMota

		comboBoxMotak = new JComboBox<>(bidaiMotaDeskribapena.toArray(new String[0]));
		comboBoxMotak.setBounds(137, 151, 150, 22);
		getContentPane().add(comboBoxMotak);

		// llenamos el arrayList con los tipos de herrialde que hay en la BD

		herrialdeList = sm.herrialdeMotaEduki();

		// recorremos el arrayList todo el rato y metemos las cosas que queremos en los
		// arrayList en este caso deskribapena y kodea

		for (Herrialde herrialde : herrialdeList) {
			herrialdeDeskribapena.add(herrialde.getHelmuga()); // Usar el getter para obtener deskribapena
			herrialdeKodea.add(herrialde.getHerrialdeKodea());
		}

		// creación del comboBox para mostrar los deskribapenas de herrialdeMota

		comboBoxHerrialdea = new JComboBox<>(herrialdeDeskribapena.toArray(new String[0]));
		comboBoxHerrialdea.setBounds(137, 298, 150, 22);
		getContentPane().add(comboBoxHerrialdea);

		// creación el JDateChooser para elegir las fechas

		dataAukeratuHasiera = new JDateChooser();
		dataAukeratuHasiera.setBounds(137, 184, 150, 25);
		getContentPane().add(dataAukeratuHasiera);

		// creación el JDateChooser para elegir las fechas

		dataAukeratuAmaiera = new JDateChooser();
		dataAukeratuAmaiera.setBounds(137, 220, 150, 25);
		getContentPane().add(dataAukeratuAmaiera);

		// llamada a los metodos para calcular los días

		dataAukeratuHasiera.addPropertyChangeListener("date", evt -> kalkulatuDiferentzia());
		dataAukeratuAmaiera.addPropertyChangeListener("date", evt -> kalkulatuDiferentzia());

		// creacion el txtField que dirá los días del viaje

		txtFieldEgunak = new JTextField();
		txtFieldEgunak.setEditable(false);
		txtFieldEgunak.setColumns(10);
		txtFieldEgunak.setBounds(137, 256, 150, 30);
		getContentPane().add(txtFieldEgunak);

		// creación del txtArea para el describapena

		textAreaDeskribapena = new JTextArea();
		textAreaDeskribapena.setLineWrap(true);
		textAreaDeskribapena.setBounds(137, 331, 200, 75);
		getContentPane().add(textAreaDeskribapena);

		// creación del textArea de EzBarne

		textAreaEzBarne = new JTextArea();
		textAreaEzBarne.setLineWrap(true);
		textAreaEzBarne.setBounds(137, 424, 200, 75);
		getContentPane().add(textAreaEzBarne);

		// creación del botón de cancelación

		btnEzeztatu = new JButton("Ezeztatu");
		btnEzeztatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LeihoarenFuntioak fv = new LeihoarenFuntioak();
				fv.irekiHasiera();
				dispose();
			}
		});
		btnEzeztatu.setBounds(361, 543, 89, 23);
		getContentPane().add(btnEzeztatu);

		// creación del botón de guardado del viaje

		btnGorde = new JButton("Gorde");
		btnGorde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// cojemos el indice del comboBox para sacar el código y subirlo a la base de
				// datos
				int herrialdeIndex = comboBoxHerrialdea.getSelectedIndex();
				int bidaiMotaIndex = comboBoxMotak.getSelectedIndex();
				// llenamos con todos los datos metidos del viaje para subirlo a la base de
				// datos
				String izena = txtizena.getText();
				String deskribapena = textAreaDeskribapena.getText();
				String ezBarne = textAreaEzBarne.getText();
				Date hasieraData = dataAukeratuHasiera.getDate();
				String dataHasiera = dataFormatua.format(hasieraData);
				Date amaieraData = dataAukeratuAmaiera.getDate();
				String dataAmaiera = dataFormatua.format(amaieraData);
				String herriKodea = herrialdeKodea.get(herrialdeIndex);
				String bidaiMotaKod = bidaiMotaKodea.get(bidaiMotaIndex);
				int agentziaKodea = cache.getAgentzia().getAgentziaKodea();
				// llamada al metodo de subir las cosas a la base de datos
				sm.bidaiaEgin(izena, deskribapena, ezBarne, dataHasiera, dataAmaiera, herriKodea, bidaiMotaKod,
						agentziaKodea);

				// volvemos a Hasiera

				LeihoarenFuntioak fv = new LeihoarenFuntioak();
				fv.irekiHasiera();
				dispose();

			}
		});
		btnGorde.setBounds(118, 543, 89, 23);
		getContentPane().add(btnGorde);

		// creación de todos los labels

		lblIzena = new JLabel("Bidaiaren izena");
		lblIzena.setBounds(42, 119, 85, 14);
		getContentPane().add(lblIzena);

		lblBidaiarenMota = new JLabel("Bidaiaren mota");
		lblBidaiarenMota.setBounds(42, 155, 85, 14);
		getContentPane().add(lblBidaiarenMota);

		lblHasiera = new JLabel("Bidaiaren hasiera");
		lblHasiera.setBounds(28, 190, 100, 14);
		getContentPane().add(lblHasiera);

		lblAmaiera = new JLabel("Bidaiaren amaiera");
		lblAmaiera.setBounds(25, 225, 103, 14);
		getContentPane().add(lblAmaiera);

		lblEgunak = new JLabel("Egunak");
		lblEgunak.setBounds(79, 267, 47, 14);
		getContentPane().add(lblEgunak);

		lblHerrialdea = new JLabel("Herrialdea");
		lblHerrialdea.setBounds(68, 302, 59, 14);
		getContentPane().add(lblHerrialdea);

		lblDeskribapena = new JLabel("Deskribapena");
		lblDeskribapena.setBounds(47, 357, 80, 14);
		getContentPane().add(lblDeskribapena);

		lblEzBarne = new JLabel("Ez barne zerbitzuak");
		lblEzBarne.setBounds(14, 460, 113, 14);
		getContentPane().add(lblEzBarne);

		getContentPane().setVisible(true);
		// llamada a los metodos del color de fondo y el logo
		kargatuLogoaKolorea();

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

	}

	@SuppressWarnings("deprecation")
	
	//metodo para poner el color de fondo y el logo
	
	private void kargatuLogoaKolorea() {
		try {
			//sacamos la url del logo de la agentzia que se ha registrado
			
			String logoUrl = cache.getAgentzia().getLogoa();
			if (logoUrl != null && !logoUrl.isEmpty()) {

				//convertimos la url a imagen
				
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
			
			//pillamos el codigo hexadecimal que está en la base de datos de esa misma agencia y la formateamos en String
			
			String hexKolor = cache.getAgentzia().getMarkarenKolorea();
			if (hexKolor != null && !hexKolor.isEmpty()) {
				//lo convertimos con el decode para que sepa que color es y ponerlo en el background
				getContentPane().setBackground(Color.decode(hexKolor));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// esto es para calcular los días de diferencia de la fecha inicio y fecha fin.
	private void kalkulatuDiferentzia() {

		// pillo los datos de las fechas
		Date hasieraData = dataAukeratuHasiera.getDate();
		Date amaieraData = dataAukeratuAmaiera.getDate();
		// miramos si es null, si no lo es realiza el calculo
		if (hasieraData != null && amaieraData != null) {
			// restamos las fechas con el diff
			long diff = amaieraData.getTime() - hasieraData.getTime();
			// convertimos la fecha esta en dias
			long egunDiferentzia = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
			// metemos todo lo que nos de en el textField, si da error me va a decir que
			// está mal
			txtFieldEgunak.setText(egunDiferentzia >= 0 ? String.valueOf(egunDiferentzia) : "Data baliogabea");
		}
	}

}