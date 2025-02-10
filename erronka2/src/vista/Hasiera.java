package vista;

import java.awt.Color;
import java.awt.Image;
import modelo.pojo.Bidai_Motak;
import modelo.pojo.Bidaia;
import modelo.pojo.Cache;
import modelo.pojo.Herrialde;
import modelo.pojo.Zerbitzu;
import modelo.sql.SqlMetodoak;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;

@SuppressWarnings("serial")
public class Hasiera extends JFrame {

	private JLabel lblLogoa, lblZerbitzuak, lblBidaiak;
	private JButton btnAtzera, btnBidaiBerria, btnZerbitzuBerria;
	private JTable tablaBidaiak, tableZerbitzuak;
	private JScrollPane scrollPaneBidaiak, scrollPaneZerbitzuak;
	private Cache cache = new Cache();
	private SqlMetodoak sm = new SqlMetodoak();
	private JButton btnEzabatuZerbitzuak, btnEzabatuBidaiak, btnBezero;
	private int kontagailuBidai = 0;
	private int kontagailuzZerb = 0;
	private ArrayList<Bidaia> bidaiaList;

	public Hasiera() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 768, 560);
		getContentPane().setLayout(null);

		koloreaIpini();

		// Crear la tabla y su modelo ANTES de llenarla con datos
		tablaBidaiak = new JTable();
		tablaBidaiak.setCellSelectionEnabled(true);
		tablaBidaiak.setColumnSelectionAllowed(true);

		tablaBidaiak.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting()) { // Para evitar múltiples eventos en una sola selección
					int row = tablaBidaiak.getSelectedRow();
					int column = tablaBidaiak.getSelectedColumn();
					if (row != -1 && column != -1) { // Verifica que haya una celda seleccionada
						btnZerbitzuBerria.setEnabled(true);
						int bidaiKodea = bidaiaList.get(row).getBidaiKodea();
						taulaBeteZerbitzuak(bidaiKodea);
					}
				}
			}
		});

		DefaultTableModel bidaiModeloa = new DefaultTableModel(new Object[][] {}, // Tabla vacía inicialmente
				new String[] { "Bidaiak", "Mota", "Egunak", "Hasiera data", "Amaiera data", "Herrialdea" });

		// Asociar el modelo a la tabla
		tablaBidaiak.setModel(bidaiModeloa);

		// Agregar la tabla dentro del JScrollPane
		scrollPaneBidaiak = new JScrollPane(tablaBidaiak);
		scrollPaneBidaiak.setBounds(77, 219, 533, 95);
		getContentPane().add(scrollPaneBidaiak);

		// Obtener los datos DESPUÉS de crear la tabla

		bidaiaList = sm.bidaiakEduki(); // Llamada a la base de datos

		// Llenar la tabla con los datos obtenidos
		taulaBeteBidaia();

		tableZerbitzuak = new JTable();
		tableZerbitzuak.setCellSelectionEnabled(true);
		tableZerbitzuak.setColumnSelectionAllowed(true);

		DefaultTableModel zerbitzuModeloa = new DefaultTableModel(new Object[][] {}, // Tabla vacía inicialmente
				new String[] { "Zerbitzuen izena", "Mota", "Data", "Prezioa" });

		// Asociar el modelo a la tabla
		tableZerbitzuak.setModel(zerbitzuModeloa);

		scrollPaneZerbitzuak = new JScrollPane(tableZerbitzuak);
		scrollPaneZerbitzuak.setBounds(125, 350, 431, 95);
		getContentPane().add(scrollPaneZerbitzuak);

		lblBidaiak = new JLabel("Bidaiak");
		lblBidaiak.setHorizontalAlignment(SwingConstants.CENTER);
		lblBidaiak.setFont(new Font("Arial", Font.PLAIN, 17));
		lblBidaiak.setForeground(new Color(0, 0, 0));
		lblBidaiak.setBounds(286, 194, 89, 15);
		getContentPane().add(lblBidaiak);

		lblZerbitzuak = new JLabel("Zerbitzuak");
		lblZerbitzuak.setHorizontalAlignment(SwingConstants.CENTER);
		lblZerbitzuak.setForeground(Color.BLACK);
		lblZerbitzuak.setFont(new Font("Arial", Font.PLAIN, 17));
		lblZerbitzuak.setBounds(288, 327, 89, 15);
		getContentPane().add(lblZerbitzuak);

		btnBezero = new JButton("Bezero-eskaintza sortzea");
		btnBezero.setBounds(297, 479, 171, 23);
		getContentPane().add(btnBezero);

		btnEzabatuBidaiak = new JButton("New button");
		btnEzabatuBidaiak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int errenkada = tablaBidaiak.getSelectedRow();

				if (errenkada != -1) {
					// Eliminar la fila del modelo de datos
					bidaiModeloa.removeRow(errenkada);
					int bidaiKodea = bidaiaList.get(errenkada).getBidaiKodea();
					sm.ezabatuBidaia(bidaiKodea);
					bidaiaList.remove(errenkada);
		            bidaiModeloa.fireTableDataChanged(); // Notificar a la tabla que los datos han cambiado
		            tablaBidaiak.repaint(); // Forzar el repintado de la tabla
		            tableZerbitzuak.repaint();
		            kontagailuBidai--; // Decrementar el contador
				} else {
					JOptionPane.showMessageDialog(null, "Aukeratu errenkada bat ezabatzeko.");
				}
			}
		});
		btnEzabatuBidaiak.setBounds(618, 210, 26, 23);
		getContentPane().add(btnEzabatuBidaiak);

		btnEzabatuZerbitzuak = new JButton("New button");
		btnEzabatuZerbitzuak.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int errenkada = tablaBidaiak.getSelectedRow();

		        if (errenkada != -1) {
		            // Obtener el servicio asociado a la fila seleccionada
		        	

		            ArrayList<Zerbitzu> zerbitzuak = bidaiaList.get(errenkada).getZerbitzuak();
		            if(zerbitzuak.get(errenkada).getZerbitzuIzena() == "Joan_Etorri") {
			            int errenkadaKod = (errenkada - 1);
			            for (int i = errenkadaKod; i < zerbitzuak.size(); i++) {
			            	zerbitzuak.remove(i);
						}
			            int zerbitzuKod = zerbitzuak.get(errenkadaKod).getZerbitzuKodea();
			            
			            // Eliminar de la base de datos
			            sm.ezabatuZerbitzua(zerbitzuKod);
			            
			            zerbitzuModeloa.removeRow(errenkada);
			            
			            // Eliminar de la lista de servicios
			            
			            
		            }
		            else {
		            	 int errenkadaKod = (errenkada - 1);
				            
				            int zerbitzuKod = zerbitzuak.get(errenkadaKod).getZerbitzuKodea();
				            
				            // Eliminar de la base de datos
				            sm.ezabatuZerbitzua(zerbitzuKod);
				            
				            zerbitzuModeloa.removeRow(errenkada);
				            
				            // Eliminar de la lista de servicios
				            zerbitzuak.remove(errenkadaKod);
		            }		            
		            // Eliminar la fila de la tabla
		            
		           
		            zerbitzuModeloa.fireTableDataChanged(); // Notificar a la tabla que los datos han cambiado
	                tableZerbitzuak.repaint(); // Forzar el repintado de la tabla
	                kontagailuzZerb--; // Decrementar el contador
		            System.out.println(kontagailuzZerb);
		            
		        } else {
		            JOptionPane.showMessageDialog(null, "Aukeratu errenkada bat ezabatzeko.");
		        }
		  
		    }    
		});
		btnEzabatuZerbitzuak.setBounds(565, 337, 26, 23);
		getContentPane().add(btnEzabatuZerbitzuak);

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
		btnZerbitzuBerria.setEnabled(false);
		btnZerbitzuBerria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tablaBidaiak.getSelectedRow();
				int idBidaia = bidaiaList.get(row).getBidaiKodea();
				LehioarenFuntioak fv = new LehioarenFuntioak();
				fv.irekiZerbitzuak(idBidaia);
				dispose();
			}
		});
		btnZerbitzuBerria.setBounds(566, 388, 115, 23);
		getContentPane().add(btnZerbitzuBerria);

		btnAtzera = new JButton("Log Out");
		btnAtzera.setBounds(653, 9, 89, 23);
		btnAtzera.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LehioarenFuntioak fv = new LehioarenFuntioak();
				fv.irekiLogin();
				dispose();
			}
		});
		getContentPane().add(btnAtzera);

		logoIpini();
	}

	private void logoIpini() {
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
	}

	private void koloreaIpini() {
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
	}

	private void taulaBeteBidaia() {
		DefaultTableModel modelo = (DefaultTableModel) tablaBidaiak.getModel();
		modelo.setRowCount(0); // Limpiar la tabla antes de insertar nuevos datos

		String herrialdeIzena, bidaiMota, egunak;
		kontagailuBidai = 0; // Reiniciar contador antes de rellenar

		for (Bidaia b : bidaiaList) {
			herrialdeIzena = convertHerrialde();
			bidaiMota = convertBidaiMota();
			egunak = kalkulatuDiferentzia();

			modelo.addRow(new Object[] { b.getIzena(), bidaiMota, egunak, b.getBidaiHasiera(), b.getBidaiAmaiera(),
					herrialdeIzena });

			kontagailuBidai++; // Incrementamos el contador cuando agregamos una fila
		}
		System.out.println("Kontagailu eguneratua: " + kontagailuBidai);
	}

	private void taulaBeteZerbitzuak(int bidaiKodea) {
		DefaultTableModel modeloZerbitzu = (DefaultTableModel) tableZerbitzuak.getModel();
		modeloZerbitzu.setRowCount(0); // Limpiar la tabla antes de insertar nuevos datos

		for (Bidaia bidaia : bidaiaList) {
			if (bidaiKodea == bidaia.getBidaiKodea()) {
				// Si Bidaia tiene un método para obtener sus servicios, imprimirlos también
				for (Zerbitzu zerbitzu : bidaia.getZerbitzuak()) {
					if (zerbitzu.getZerbitzuIzena().equals("Hegaldia")) {
						modeloZerbitzu.addRow(new Object[] { zerbitzu.getZerbitzuIzena(), "Hegaldia",
								zerbitzu.getHegaldiIrteraData(), zerbitzu.getHegaldiPrezioa() });
					} else if (zerbitzu.getZerbitzuIzena().equals("Joan_Etorri")) {
						modeloZerbitzu.addRow(new Object[] { zerbitzu.getZerbitzuIzena(), "Hegaldia",
								zerbitzu.getHegaldiIrteraData(), zerbitzu.getHegaldiPrezioa() });
						modeloZerbitzu.addRow(new Object[] { zerbitzu.getZerbitzuIzena(), "Joan_Etorri",
								zerbitzu.getEtorriaEguna(), zerbitzu.getHegaldiPrezioa() });
					} else if (zerbitzu.getZerbitzuIzena().equals("Ostatua")) {
						modeloZerbitzu.addRow(new Object[] { zerbitzu.getZerbitzuIzena(), "Ostatua",
								zerbitzu.getOstatuSarreraEguna(), zerbitzu.getOstatuPrezioa() });
					} else {
						modeloZerbitzu.addRow(new Object[] { zerbitzu.getZerbitzuIzena(), "Jarduera",
								zerbitzu.getEgun(), zerbitzu.getBesteBatzukPrezioa() });
					}
					kontagailuzZerb++;
				}
			}
		}
		System.out.println("Kontagailu eguneratua: " + kontagailuzZerb);
	}

	private String convertHerrialde() {
		ArrayList<Herrialde> herrialdeList = sm.herrialdeMotaEduki();

		// Recorremos cada Bidaia en la lista bidaiaList
		for (int i = kontagailuBidai; i < bidaiaList.size(); i++) {
			// Obtenemos el código de la Bidaia
			String codigoBidaia = bidaiaList.get(i).getHerrialdeKod();

			// Buscamos el Herrialde correspondiente en la lista herrialdeList
			for (int j = 0; j < herrialdeList.size(); j++) {
				// Si el código de Herrialde coincide con el de la Bidaia
				if (herrialdeList.get(j).getHerrialdeKodea().equals(codigoBidaia)) {
					return herrialdeList.get(j).getHelmuga();
				}
			}
		}
		return "Ez dago helmugarik";
	}

	private String convertBidaiMota() {

		ArrayList<Bidai_Motak> bidaiMotaList = sm.bidaiMotaEduki();

		for (int i = kontagailuBidai; i < bidaiaList.size(); i++) {
			String bidaiMotaKod = bidaiaList.get(i).getBidaiaMKod();

			for (int j = 0; j < bidaiMotaList.size(); j++) {
				if (bidaiMotaList.get(j).getBidaiKodea().equals(bidaiMotaKod)) {

					return bidaiMotaList.get(j).getDeskribapena();

				}
			}

		}
		return "Ez dago deskribapenarik";
	}

	private String kalkulatuDiferentzia() {

		for (int i = kontagailuBidai; i < bidaiaList.size(); i++) {
			Bidaia b = bidaiaList.get(i);
			Date bidaiHasiera = b.getBidaiHasiera();
			Date bidaiAmaiera = b.getBidaiAmaiera();

			if (bidaiHasiera != null && bidaiAmaiera != null) {
				long diff = bidaiAmaiera.getTime() - bidaiHasiera.getTime();
				long egunDiferentzia = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
				String totala = egunDiferentzia >= 0 ? String.valueOf(egunDiferentzia) : "Data baliogabea";
				return totala;
			}
		}
		return "Data baliogabea";
	}
}
