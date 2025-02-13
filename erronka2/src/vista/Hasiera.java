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
import java.io.File;
import java.io.FileWriter;
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
	private int kontagailuZerb = 0;
	private ArrayList<Bidaia> bidaiaList;

	public Hasiera() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 768, 560);
		getContentPane().setLayout(null);

		// Crear la tabla y su modelo ANTES de llenarla con datos
		tablaBidaiak = new JTable();
		// habilitamos el poder seleccionar una celda o una columna entera
		tablaBidaiak.setCellSelectionEnabled(true);
		tablaBidaiak.setColumnSelectionAllowed(true);
		// Detectar cambios en la selección de la tabla y ejecutar acciones cuando se
		// selecciona una celda.
		tablaBidaiak.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting()) { // Para evitar múltiples eventos en una sola selección
					int row = tablaBidaiak.getSelectedRow();
					int column = tablaBidaiak.getSelectedColumn();
					if (row != -1 && column != -1) { // Verifica que haya una celda seleccionada
						btnZerbitzuBerria.setEnabled(true);
						int bidaiKodea = bidaiaList.get(row).getBidaiKodea();
						// llamar el metodo para poder llenar la tabla de sevicios dependiendo la ID del
						// bidaia que haya
						taulaBeteZerbitzuak(bidaiKodea);
					}
				}
			}
		});

		// creación del modelo de la tabla bidaia pero vacio

		DefaultTableModel bidaiModeloa = new DefaultTableModel(new Object[][] {},
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

		// creación de la tabla de zerbitzuak

		tableZerbitzuak = new JTable();
		tableZerbitzuak.setCellSelectionEnabled(true);
		tableZerbitzuak.setColumnSelectionAllowed(true);

		// creación del modelo de la tabla zerbitzuak pero vacio

		DefaultTableModel zerbitzuModeloa = new DefaultTableModel(new Object[][] {},
				new String[] { "Zerbitzuen izena", "Mota", "Data", "Prezioa" });

		// Asociar el modelo a la tabla
		tableZerbitzuak.setModel(zerbitzuModeloa);

		// agregar la tabla dentro del scroll pane

		scrollPaneZerbitzuak = new JScrollPane(tableZerbitzuak);
		scrollPaneZerbitzuak.setBounds(125, 350, 431, 95);
		getContentPane().add(scrollPaneZerbitzuak);

		// creación del label de bidaiak

		lblBidaiak = new JLabel("Bidaiak");
		lblBidaiak.setHorizontalAlignment(SwingConstants.CENTER);
		lblBidaiak.setFont(new Font("Arial", Font.PLAIN, 17));
		lblBidaiak.setForeground(new Color(0, 0, 0));
		lblBidaiak.setBounds(286, 194, 89, 15);
		getContentPane().add(lblBidaiak);

		// creación del label de zerbitzuak

		lblZerbitzuak = new JLabel("Zerbitzuak");
		lblZerbitzuak.setHorizontalAlignment(SwingConstants.CENTER);
		lblZerbitzuak.setForeground(Color.BLACK);
		lblZerbitzuak.setFont(new Font("Arial", Font.PLAIN, 17));
		lblZerbitzuak.setBounds(288, 327, 89, 15);
		getContentPane().add(lblZerbitzuak);

		// creación del boton de bezero que es el que crea el txt del aurrekontua

		btnBezero = new JButton("Bezero-eskaintza sortzea");
		btnBezero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// llamamos al metodo que crea el txt
				txtIdatzi();
			}
		});
		btnBezero.setBounds(297, 479, 171, 23);
		getContentPane().add(btnBezero);

		// creación del boton para eliminar bidaiak

		btnEzabatuBidaiak = new JButton("");
		btnEzabatuBidaiak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int errenkada = tablaBidaiak.getSelectedRow();
				// miramos si hay alguna fila seleccionada si no sale un pop up
				if (errenkada != -1) {
					// Eliminar la fila del modelo de datos
					bidaiModeloa.removeRow(errenkada);
					// pillamos el bidaiKodea para pasarlo a la consulta de borrar
					int bidaiKodea = bidaiaList.get(errenkada).getBidaiKodea();
					// llamamos al metodo de la consulta de borrar
					sm.ezabatuBidaia(bidaiKodea);
					// borramos del arraylist el viaje
					bidaiaList.remove(errenkada);
					bidaiModeloa.fireTableDataChanged(); // Notificar a la tabla que los datos han cambiado
					tablaBidaiak.repaint(); // Forzar el repintado de la tabla bidaia
					tableZerbitzuak.repaint(); // Forzar el repintado de la tabla zerbitzuak
					kontagailuBidai--; // Decrementar el contador
				} else {
					JOptionPane.showMessageDialog(null, "Aukeratu errenkada bat ezabatzeko.");
				}
			}
		});
		btnEzabatuBidaiak.setBounds(618, 210, 26, 23);
		getContentPane().add(btnEzabatuBidaiak);

		// creación del boton de borrar los zerbitzuak

		btnEzabatuZerbitzuak = new JButton("");
		btnEzabatuZerbitzuak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int errenkada = tableZerbitzuak.getSelectedRow(); // Fila seleccionada

				if (errenkada != -1) { // Verifica si hay un servicio seleccionado
					int bidaiaIndex = tablaBidaiak.getSelectedRow(); // Fila seleccionada en la tabla de viajes

					if (bidaiaIndex != -1) { // Verifica si hay un viaje seleccionado
						// llena el arraylist con todos los servicios que hallan en el viaje
						// seleccionado
						ArrayList<Zerbitzu> zerbitzuak = bidaiaList.get(bidaiaIndex).getZerbitzuak();
						// metemos en zerbitzuKod la id del zerbitzu seleccionado
						int zerbitzuKod = zerbitzuak.get(errenkada).getZerbitzuKodea();
						//
						// Verifica que la lista de servicios (zerbitzuak) no esté vacía y que el índice
						// errenkada sea válido
						if (!zerbitzuak.isEmpty() && errenkada < zerbitzuak.size()) {

							// Elimina el servicio en la fila seleccionada
							zerbitzuak.remove(errenkada);

							// Llama al método ezabatuZerbitzua para eliminar el servicio de la base de
							// datos o sistema
							sm.ezabatuZerbitzua(zerbitzuKod);

							// Actualiza la tabla de servicios con la lista modificada
							eguneratuTaulaZerbitzuak(zerbitzuak);

							// Actualiza el contador de servicios después de la eliminación
							kontagailuZerb = zerbitzuak.size();

						} else {
							// Muestra un mensaje si la lista está vacía o si el índice es inválido
							JOptionPane.showMessageDialog(null, "No hay servicios en la lista.");
						}

					} else {
						// Muestra un mensaje si no se ha seleccionado un viaje antes de intentar
						// eliminar un servicio
						JOptionPane.showMessageDialog(null, "Selecciona un viaje antes de eliminar un servicio.");
					}

				} else {
					// Muestra un mensaje si no se ha seleccionado ningún servicio para eliminar
					JOptionPane.showMessageDialog(null, "Selecciona un servicio para eliminar.");
				}

			}
		});

		btnEzabatuZerbitzuak.setBounds(565, 337, 26, 23);
		getContentPane().add(btnEzabatuZerbitzuak);

		// creación del boton para un nuevo viaje

		btnBidaiBerria = new JButton("Bidai berria");
		btnBidaiBerria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LeihoarenFuntioak fv = new LeihoarenFuntioak();
				fv.irekiBidaiak();
				dispose();
			}
		});
		btnBidaiBerria.setBounds(625, 258, 103, 23);
		getContentPane().add(btnBidaiBerria);

		// creación del boton para un nuevo servicio

		btnZerbitzuBerria = new JButton("Zerbitzu berria");
		btnZerbitzuBerria.setEnabled(false);
		btnZerbitzuBerria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tablaBidaiak.getSelectedRow();
				int idBidaia = bidaiaList.get(row).getBidaiKodea();
				LeihoarenFuntioak fv = new LeihoarenFuntioak();
				fv.irekiZerbitzuak(idBidaia);
				dispose();
			}
		});
		btnZerbitzuBerria.setBounds(566, 388, 115, 23);
		getContentPane().add(btnZerbitzuBerria);

		// creación del boton para deslogearse

		btnAtzera = new JButton("Log Out");
		btnAtzera.setBounds(653, 9, 89, 23);
		btnAtzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LeihoarenFuntioak fv = new LeihoarenFuntioak();
				fv.irekiLogin();
				dispose();
			}
		});
		getContentPane().add(btnAtzera);

		kargatuLogoaKolorea();
		
	}

	// metodo para llenar la tabla de bidaia

	private void taulaBeteBidaia() {
		// ponemos el modelo de la tabla
		DefaultTableModel modelo = (DefaultTableModel) tablaBidaiak.getModel();
		modelo.setRowCount(0); // Limpiar la tabla antes de insertar nuevos datos

		String herrialdeIzena, bidaiMota, egunak;
		kontagailuBidai = 0; // Reiniciar contador antes de rellenar

		// recorrer el arraylist de bidaiak para añadir todos los viajes en la tabla

		for (Bidaia b : bidaiaList) {

			// esto es para pasar del kode de la base de datos a la los nombres de cada cosa
			// o para calcular los días del viaje

			herrialdeIzena = convertHerrialde();
			bidaiMota = convertBidaiMota();
			egunak = kalkulatuDiferentzia();

			// añadir todo a la tabla

			modelo.addRow(new Object[] { b.getIzena(), bidaiMota, egunak, b.getBidaiHasiera(), b.getBidaiAmaiera(),
					herrialdeIzena });

			kontagailuBidai++; // Incrementamos el contador cuando agregamos una fila
		}
	}

	// metodo para llenar la tabla de servicios pasamos el bidaiKod por parametro
	// para poder meter los servicios de ese viaje en concreto

	private void taulaBeteZerbitzuak(int bidaiKodea) {
		DefaultTableModel modeloZerbitzu = (DefaultTableModel) tableZerbitzuak.getModel();
		modeloZerbitzu.setRowCount(0); // Limpiar la tabla antes de insertar nuevos datos

		// recorremos el arraylist de bidaia para compararlo con el codigo que hemos
		// pasado por parametro

		for (Bidaia bidaia : bidaiaList) {
			if (bidaiKodea == bidaia.getBidaiKodea()) {
				// Si Bidaia tiene un método para obtener sus servicios, imprimirlos también
				for (Zerbitzu zerbitzu : bidaia.getZerbitzuak()) {
					if (zerbitzu.getZerbitzuIzena().equals("Hegaldia")) {
						// Añadir solo"Hegaldia"
						modeloZerbitzu.addRow(new Object[] { zerbitzu.getZerbitzuIzena(), "Hegaldia",
								zerbitzu.getHegaldiIrteraData(), zerbitzu.getHegaldiPrezioa() });
					} else if (zerbitzu.getZerbitzuIzena().equals("Joan_Etorri")) {
						// Añadir solo "Joan_Etorri"
						modeloZerbitzu.addRow(new Object[] { zerbitzu.getZerbitzuIzena(), "Joan_Etorri",
								zerbitzu.getEtorriaEguna(), zerbitzu.getHegaldiPrezioa() });
					} else if (zerbitzu.getZerbitzuIzena().equals("Ostatua")) {
						// Añadir solo "Ostatua"
						modeloZerbitzu.addRow(new Object[] { zerbitzu.getZerbitzuIzena(), "Ostatua",
								zerbitzu.getOstatuSarreraEguna(), zerbitzu.getOstatuPrezioa() });
					} else {
						// Añadir solo "Jarduera"
						modeloZerbitzu.addRow(new Object[] { zerbitzu.getZerbitzuIzena(), "Jarduera",
								zerbitzu.getEgun(), zerbitzu.getBesteBatzukPrezioa() });
					}
					kontagailuZerb++;
				}
			}
		}
	}

	// metodo para convertir el kodigo a texto

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

	// metodo para convertir el kodigo a texto

	private String convertBidaiMota() {
		// cargamos el arrayList de bidaiMotak desde el metodo de la bd
		ArrayList<Bidai_Motak> bidaiMotaList = sm.bidaiMotaEduki();
		// recorremos el arraylist empezando por el numero que esté puesto en el
		// contador para que meta en el String el codigo que se va a convertir
		for (int i = kontagailuBidai; i < bidaiaList.size(); i++) {
			String bidaiMotaKod = bidaiaList.get(i).getBidaiaMKod();
			// recorremos el arraylist de los nombres de los bidaiMotas y comparamos el ID
			// con el nombre
			for (int j = 0; j < bidaiMotaList.size(); j++) {
				if (bidaiMotaList.get(j).getBidaiKodea().equals(bidaiMotaKod)) {
					// returneamos lo que hemos sacado del array
					return bidaiMotaList.get(j).getDeskribapena();

				}
			}

		}
		return "Ez dago deskribapenarik";
	}

	// metodo para sacar los días del viaje

	private String kalkulatuDiferentzia() {
		// recorremos el array para sacar las fechas del mismo
		for (int i = kontagailuBidai; i < bidaiaList.size(); i++) {
			Bidaia b = bidaiaList.get(i);
			Date bidaiHasiera = b.getBidaiHasiera();
			Date bidaiAmaiera = b.getBidaiAmaiera();
			// miramos si es null, si no lo es realiza el calculo
			if (bidaiHasiera != null && bidaiAmaiera != null) {
				// restamos las fechas con el diff
				long diff = bidaiAmaiera.getTime() - bidaiHasiera.getTime();
				// convertimos la fecha esta en dias
				long egunDiferentzia = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
				// metemos todo lo que nos de en el string para poder pasarlo con el return, si
				// da error me va a decir que está mal
				String totala = egunDiferentzia >= 0 ? String.valueOf(egunDiferentzia) : "Data baliogabea";
				return totala;
			}
		}
		return "Data baliogabea";
	}

	// Metodo para actualizar la tabla cuando se borra un servicio o un bidaia

	private void eguneratuTaulaZerbitzuak(ArrayList<Zerbitzu> zerbitzuak) {
		DefaultTableModel modeloZerbitzu = (DefaultTableModel) tableZerbitzuak.getModel();
		modeloZerbitzu.setRowCount(0); // Vaciar la tabla antes de actualizar

		for (Zerbitzu z : zerbitzuak) {
			// Verificamos qué tipo de servicio es y lo agregamos a la tabla con la fecha y
			// el precio correspondientes

			// todo lo que está dentro de los if y else if es para comprar el nombre si no
			// concuerda con el que está salta al siguiente
			if ("Hegaldia".equals(z.getZerbitzuIzena())) {
				modeloZerbitzu.addRow(new Object[] { z.getZerbitzuIzena(), "Hegaldia", // Tipo de servicio
						z.getHegaldiIrteraData(), // Fecha de salida
						z.getHegaldiPrezioa() // Precio del vuelo
				});
			} else if ("Joan_Etorri".equals(z.getZerbitzuIzena())) {
				modeloZerbitzu.addRow(new Object[] { z.getZerbitzuIzena(), "Joan_Etorri", // Tipo de servicio
						z.getEtorriaEguna(), // Fecha de llegada
						z.getHegaldiPrezioa() // Precio del vuelo
				});
			} else if ("Ostatua".equals(z.getZerbitzuIzena())) {
				modeloZerbitzu.addRow(new Object[] { z.getZerbitzuIzena(), "Ostatua", // Tipo de servicio
						z.getOstatuSarreraEguna(), // Fecha de entrada al hotel
						z.getOstatuPrezioa() // Precio del hotel
				});
			} else {
				modeloZerbitzu.addRow(new Object[] { z.getZerbitzuIzena(), "Jarduera", // Tipo de servicio
						z.getEgun(), // Fecha de la actividad
						z.getBesteBatzukPrezioa() // Precio de la actividad
				});
			}
		}

		modeloZerbitzu.fireTableDataChanged(); // Notificar a la tabla que los datos han cambiado
		tableZerbitzuak.repaint(); // Redibujar la tabla para reflejar los cambios
	}

	// este es el metodo para crear el .txt (aurrekontua)

	public void txtIdatzi() {
		try {

			// ponemos la ruta que queremos que se cree

			File f = new File("C:/Users/in1dm3-d/Desktop/aurrekontua.txt");

			// creamos el filewriter

			FileWriter wf = new FileWriter(f);

			// metemos el indice de la tabla para luego sacar el presupuesto de todo ello

			int indexTaula = tablaBidaiak.getSelectedRow();

			// hacemos el toString para crear el TXT (puede ser otra cosa pero yo uso el
			// toString)

			String txt = bidaiaList.get(indexTaula).toStringTxt();
			wf.write(txt);

			//cerramos el filewriter (literal que es como un scanner)
			
			wf.close();
		} catch (Exception e) {
			System.out.println(e);
		}
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
				lblLogoa.setBounds(33, 23, 170, 160);
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

}
