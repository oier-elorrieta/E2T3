package modelo;

import java.sql.Date;
import java.util.ArrayList;

public class Bidaia {
	private int bidaiKodea;
	private String izena;
	private String deskribapena;
	private String EzBarne;
	private Date bidaiHasiera;
	private Date bidaiAmaiera;
	private String herrialdeKod;
	private String bidaiaMKod;
	private int agentziaKod;

	public Bidaia(int bidaiKodea, String izena, String deskribapena, String ezBarne,
			Date bidaiHasiera, Date bidaiAmaiera, String herrialdeKod, String bidaiaMKod, int agentziaKod) {
		this.bidaiKodea = bidaiKodea;
		this.izena = izena;
		this.deskribapena = deskribapena;
		EzBarne = ezBarne;
		this.bidaiHasiera = bidaiHasiera;
		this.bidaiAmaiera = bidaiAmaiera;
		this.herrialdeKod = herrialdeKod;
		this.bidaiaMKod = bidaiaMKod;
		this.agentziaKod = agentziaKod;
	}
	
	

	public Bidaia() {
		
	}

	public int getBidaiKodea() {
		return bidaiKodea;
	}

	public void setBidaiKodea(int bidaiKodea) {
		this.bidaiKodea = bidaiKodea;
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

	public String getEzBarne() {
		return EzBarne;
	}

	public void setEzBarne(String ezBarne) {
		EzBarne = ezBarne;
	}

	public Date getBidaiHasiera() {
		return bidaiHasiera;
	}

	public void setBidaiHasiera(Date bidaiHasiera) {
		this.bidaiHasiera = bidaiHasiera;
	}

	public Date getBidaiAmaiera() {
		return bidaiAmaiera;
	}

	public void setBidaiAmaiera(Date bidaiAmaiera) {
		this.bidaiAmaiera = bidaiAmaiera;
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

	@Override
	public String toString() {
		return "Bidaia [bidaiKodea=" + bidaiKodea + ", izena=" + izena
				+ ", deskribapena=" + deskribapena + ", EzBarne=" + EzBarne + ", bidaiHasiera=" + bidaiHasiera
				+ ", bidaiAmaiera=" + bidaiAmaiera + ", herrialdeKod=" + herrialdeKod + ", bidaiaMKod=" + bidaiaMKod
				+ ", agentziaKod=" + agentziaKod + "]";
	}

}
