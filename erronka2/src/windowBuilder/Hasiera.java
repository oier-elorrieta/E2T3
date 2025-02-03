package windowBuilder;

import java.awt.Color;
import java.awt.Image;

import modelo.*;
import sqlKonexioa.SqlMetodoak;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;

public class Hasiera extends JFrame {

	private JLabel lblLogoa;
	private JButton btnAtzera, btnBidaiBerria, btnZerbitzuBerria;
	private JTable tablaBidaiak, tableZerbitzuak;
	private JScrollPane scrollPaneBidaiak, scrollPaneZerbitzuak;
	private Cache cache = new Cache();
	private SqlMetodoak sm = new SqlMetodoak();

	public Hasiera() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 768, 560);
		getContentPane().setLayout(null);
		
		String hexKolor = cache.getAgentzia().getMarkarenKolorea();

		try {

			if (hexKolor != null && !hexKolor.isEmpty()) {

				Color kolor = Color.decode(hexKolor); // Convertir el código hexadecimal a un color

				getContentPane().setBackground(kolor);
			} else {
				System.out.println("El color es inválida o no está disponible.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		btnAtzera = new JButton("Log Out");
		btnAtzera.setBounds(653, 9, 89, 23);
		getContentPane().add(btnAtzera);
		
		ArrayList<Bidaia> bidaiaList = sm.bidaiakEduki();

		// 1️⃣ Crear la tabla y su modelo ANTES de llenarla con datos
		tablaBidaiak = new JTable();
		tablaBidaiak.setCellSelectionEnabled(true);
		tablaBidaiak.setColumnSelectionAllowed(true);

		DefaultTableModel modelo = new DefaultTableModel(
		    new Object[][] {}, // Tabla vacía inicialmente
		    new String[] { "Bidaiak", "Mota", "Egunak", "Hasiera data", "Amaiera data", "Herrialdea" }
		);

		// Asociar el modelo a la tabla
		tablaBidaiak.setModel(modelo);

		// Agregar la tabla dentro del JScrollPane
		scrollPaneBidaiak = new JScrollPane(tablaBidaiak);
		scrollPaneBidaiak.setBounds(77, 219, 533, 95);
		getContentPane().add(scrollPaneBidaiak);

		// 2️⃣ Obtener los datos DESPUÉS de crear la tabla
		bidaiaList = sm.bidaiakEduki(); // Llamada a la base de datos

		// 3️⃣ Llenar la tabla con los datos obtenidos
		llenarTabla(bidaiaList);

		scrollPaneZerbitzuak = new JScrollPane();
		scrollPaneZerbitzuak.setBounds(125, 350, 431, 95);
		getContentPane().add(scrollPaneZerbitzuak);

		tableZerbitzuak = new JTable();
		tableZerbitzuak.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Zerbitzuen izena", "Mota", "Data", "Prezioa"
			}
		));
		scrollPaneZerbitzuak.setViewportView(tableZerbitzuak);
		
		btnBidaiBerria = new JButton("Bidai berria");
		btnBidaiBerria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LehioarenFuntioak fv = new LehioarenFuntioak();
				fv.irekiBidaiak();
				dispose();
			}
		});
		btnBidaiBerria.setBounds(625, 258, 103, 23);
		getContentPane().add(btnBidaiBerria);
		
		btnZerbitzuBerria = new JButton("Zerbitzu berria");
		btnZerbitzuBerria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LehioarenFuntioak fv = new LehioarenFuntioak();
				fv.irekiZerbitzuak();
				dispose();
			}
		});
		btnZerbitzuBerria.setBounds(566, 388, 115, 23);
		getContentPane().add(btnZerbitzuBerria);
		
		JLabel lblBidaiak = new JLabel("Bidaiak");
		lblBidaiak.setHorizontalAlignment(SwingConstants.CENTER);
		lblBidaiak.setFont(new Font("Arial", Font.PLAIN, 17));
		lblBidaiak.setForeground(new Color(0, 0, 0));
		lblBidaiak.setBounds(286, 194, 89, 15);
		getContentPane().add(lblBidaiak);
		
		JLabel lblZerbitzuak = new JLabel("Zerbitzuak");
		lblZerbitzuak.setHorizontalAlignment(SwingConstants.CENTER);
		lblZerbitzuak.setForeground(Color.BLACK);
		lblZerbitzuak.setFont(new Font("Arial", Font.PLAIN, 17));
		lblZerbitzuak.setBounds(288, 327, 89, 15);
		getContentPane().add(lblZerbitzuak);
		
		JButton btnBezero = new JButton("Bezero-eskaintza sortzea");
		btnBezero.setBounds(297, 479, 171, 23);
		getContentPane().add(btnBezero);

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
				lblLogoa.setBounds(33, 23, 170, 160); // Establecemos el tamaño fijo (puedes ajustarlo según lo
														// necesites)

				// Escalar la imagen al tamaño del JLabel (con el mismo enfoque que en el botón)
				Image image1 = imageIcon1.getImage().getScaledInstance(lblLogoa.getWidth(), lblLogoa.getHeight(),
						Image.SCALE_SMOOTH);
				imageIcon1.setImage(image1);

				// Establecer el icono redimensionado en el JLabel
				lblLogoa.setIcon(imageIcon1);

				getContentPane().add(lblLogoa);

			} else {
				System.out.println("La URL del logo es inválida o no está disponible.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		btnAtzera.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LehioarenFuntioak fv = new LehioarenFuntioak();
				fv.irekiLogin();
				dispose();
			}
		});

	}
	
	private void llenarTabla(ArrayList<Bidaia> bidaiaList) {
	    DefaultTableModel modelo = (DefaultTableModel) tablaBidaiak.getModel();
	    modelo.setRowCount(0); // Limpiar la tabla antes de insertar nuevos datos

	    for (Bidaia b : bidaiaList) {
	        modelo.addRow(new Object[]{
	            b.getIzena(),
	            b.getBidaiaMKod(),
	            b.getBidaiKodea(),
	            b.getBidaiHasiera(),
	            b.getBidaiAmaiera(),
	            b.getHerrialdeKod()
	        });
	    }
	}

}
