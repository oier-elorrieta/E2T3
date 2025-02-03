package windowBuilder;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import modelo.*;


import sqlKonexioa.SqlMetodoak;

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

public class Bidaiak extends JFrame {

	private JPanel contentPane;
	private JLabel lblLogoa, lblIzena, lblBidaiarenMota, lblHasiera, lblAmaiera, lblEgunak, lblHerrialdea,
			lblDeskribapena, lblEzBarne;
	private Cache cache = new Cache();
	private JDateChooser dataAukeratuAmaiera, dataAukeratuHasiera;
	private JTextField txtizena, txtFieldEgunak;
	private JComboBox<String> comboBoxHerrialdea, comboBoxMotak;
	private JTextArea textAreaDeskribapena, textAreaEzBarne;
	private SqlMetodoak sm = new SqlMetodoak();
	private JButton btnGorde, btnEzeztatu;
	private SimpleDateFormat dataFormatua = new SimpleDateFormat("yyyy/MM/dd"); // Formato DD/MM/YYYY

	public Bidaiak() {

		 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 573, 616);
		getContentPane().setLayout(null);

		txtizena = new JTextField();
		txtizena.setBounds(137, 111, 150, 30);
		getContentPane().add(txtizena);
		txtizena.setColumns(10);
		
		ArrayList<Bidai_Motak> bidaiaList = sm.bidaiMotaEduki();
		
		ArrayList<String> bidaiMotaDeskribapena = new ArrayList<>();
		ArrayList<String> bidaiMotaKodea = new ArrayList<>();


		for (Bidai_Motak bidaia : bidaiaList) {
			bidaiMotaDeskribapena.add(bidaia.getDeskribapena()); // Usar el getter para obtener deskribapena
			bidaiMotaKodea.add(bidaia.getBidaiKodea());
		}

		comboBoxMotak = new JComboBox<>(bidaiMotaDeskribapena.toArray(new String[0]));
		comboBoxMotak.setBounds(137, 151, 150, 22);
		getContentPane().add(comboBoxMotak);

		ArrayList<Herrialde> herrialdeList = sm.herrialdeMotaEduki();
		
		ArrayList<String> herrialdeDeskribapena = new ArrayList<>();
		ArrayList<String> herrialdeKodea = new ArrayList<>();


		for (Herrialde herrialde : herrialdeList) {
			herrialdeDeskribapena.add(herrialde.getHelmuga()); // Usar el getter para obtener deskribapena
			herrialdeKodea.add(herrialde.getHerrialdeKodea());
		}
		
		comboBoxHerrialdea = new JComboBox<>(herrialdeDeskribapena.toArray(new String[0]));
		comboBoxHerrialdea.setBounds(137, 298, 150, 22);
		getContentPane().add(comboBoxHerrialdea);
		

		dataAukeratuHasiera = new JDateChooser();
		dataAukeratuHasiera.setBounds(137, 184, 150, 25);
		getContentPane().add(dataAukeratuHasiera);
		
		dataAukeratuAmaiera = new JDateChooser();
		dataAukeratuAmaiera.setBounds(137, 220, 150, 25);
		getContentPane().add(dataAukeratuAmaiera);
		

		
		dataAukeratuHasiera.addPropertyChangeListener("date", evt -> kalkulatuDiferentzia());
		dataAukeratuAmaiera.addPropertyChangeListener("date", evt -> kalkulatuDiferentzia());
		
		txtFieldEgunak = new JTextField();
		txtFieldEgunak.setEditable(false);
		txtFieldEgunak.setColumns(10);
		txtFieldEgunak.setBounds(137, 256, 150, 30);
		getContentPane().add(txtFieldEgunak);

		textAreaDeskribapena = new JTextArea();
		textAreaDeskribapena.setLineWrap(true);
		textAreaDeskribapena.setBounds(137, 331, 200, 75);
		getContentPane().add(textAreaDeskribapena);

		textAreaEzBarne = new JTextArea();
		textAreaEzBarne.setLineWrap(true);
		textAreaEzBarne.setBounds(137, 424, 200, 75);
		getContentPane().add(textAreaEzBarne);

		btnEzeztatu = new JButton("Ezeztatu");
		btnEzeztatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LehioarenFuntioak fv = new LehioarenFuntioak();
				fv.irekiHasiera();
				dispose();
			}
		});
		btnEzeztatu.setBounds(361, 543, 89, 23);
		getContentPane().add(btnEzeztatu);

		btnGorde = new JButton("Gorde");
		btnGorde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int herrialdeIndex = comboBoxHerrialdea.getSelectedIndex();
				int bidaiMotaIndex = comboBoxMotak.getSelectedIndex();
				
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
		       
		        sm.bidaiaEgin(izena, deskribapena, ezBarne, dataHasiera, dataAmaiera, herriKodea, bidaiMotaKod, agentziaKodea);
		        
				LehioarenFuntioak fv = new LehioarenFuntioak();
				fv.irekiHasiera();
				dispose();
		        
			}
		});
		btnGorde.setBounds(118, 543, 89, 23);
		getContentPane().add(btnGorde);

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
	
	//esto es para calcular los días de diferencia de la fecha inicio y fecha fin.
    private void kalkulatuDiferentzia() {
    	
        Date hasieraData = dataAukeratuHasiera.getDate();
        Date amaieraData = dataAukeratuAmaiera.getDate();

        if (hasieraData != null && amaieraData != null) {
            long diff = amaieraData.getTime() - hasieraData.getTime();
            long egunDiferentzia = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            txtFieldEgunak.setText(egunDiferentzia >= 0 ? String.valueOf(egunDiferentzia) : "Data baliogabea");
        }
    }

}
