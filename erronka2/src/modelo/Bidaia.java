package modelo;

import java.util.ArrayList;

public class Bidaia {
	private ArrayList<Zerbitzu>zerbitzuak;
	private int kodea;
	private String izena;
	private String deskribapena;
	private String herrialdeKod;
	private String bidaiaMKod;
	private int agentziaKod;
	
	public Bidaia(int kodea, String izena, String deskribapena, String herrialdeKod, String bidaiaMKod,
			int agentziaKod) {
		super();
		this.kodea = kodea;
		this.izena = izena;
		this.deskribapena = deskribapena;
		this.herrialdeKod = herrialdeKod;
		this.bidaiaMKod = bidaiaMKod;
		this.agentziaKod = agentziaKod;
	}
	
	public void sartuZerbitzu(Zerbitzu m) {
		zerbitzuak.add(m);
	}
	
	public int getKodea() {
		return kodea;
	}
	public void setKodea(int kodea) {
		this.kodea = kodea;
	}
	public String getIzena() {
		return izena;
	}
	public void setIzena(String izena) {
		this.izena = izena;
	}
	public String getDeskribapena() {
		return deskribapena;
	}
	public void setDeskribapena(String deskribapena) {
		this.deskribapena = deskribapena;
	}
	public String getHerrialdeKod() {
		return herrialdeKod;
	}
	public void setHerrialdeKod(String herrialdeKod) {
		this.herrialdeKod = herrialdeKod;
	}
	public String getBidaiaMKod() {
		return bidaiaMKod;
	}
	public void setBidaiaMKod(String bidaiaMKod) {
		this.bidaiaMKod = bidaiaMKod;
	}
	public int getAgentziaKod() {
		return agentziaKod;
	}
	public void setAgentziaKod(int agentziaKod) {
		this.agentziaKod = agentziaKod;
	}

}
