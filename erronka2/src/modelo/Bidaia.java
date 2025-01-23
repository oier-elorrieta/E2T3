package modelo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Bidaia {
	private String izena;
	private String laburdura;
	private String bidaiaMota;
	private LocalDate hasieraData;
	private LocalDate amaieraData;
	private String herrialdea;
	private String mota;
	private String deskripzioa;
	/*
	 * public Bidaia(String izena, String bidaiaMota, LocalDate hasieraData,
	 * LocalDate amaieraData, String herrialdea, String deskripzioa) { this.izena =
	 * izena; this.bidaiaMota = bidaiaMota; this.hasieraData = hasieraData;
	 * this.amaieraData = amaieraData; this.herrialdea = herrialdea;
	 * this.deskripzioa = deskripzioa; }
	 */

	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public String getLaburdura() {
		return laburdura;
	}

	public void setLaburdura(String laburdura) {
		this.laburdura = laburdura;
	}

	public String getBidaiaMota() {
		return bidaiaMota;
	}

	public void setBidaiaMota(String bidaiaMota) {
		this.bidaiaMota = bidaiaMota;
	}

	public LocalDate getHasieraData() {
		return hasieraData;
	}

	public void setHasieraData(LocalDate hasieraData) {
		this.hasieraData = hasieraData;
	}

	public LocalDate getAmaieraData() {
		return amaieraData;
	}

	public void setAmaieraData(LocalDate amaieraData) {
		this.amaieraData = amaieraData;
	}

	public long getEgunKantitatea() {
		return ChronoUnit.DAYS.between(hasieraData, amaieraData);
	}

	public String getHerrialdea() {
		return herrialdea;
	}

	public void setHerrialdea(String herrialdea) {
		this.herrialdea = herrialdea;
	}

	public String getDeskripzioa() {
		return deskripzioa;
	}

	public void setDeskripzioa(String deskripzioa) {
		this.deskripzioa = deskripzioa;
	}

	@Override
	public String toString() {
		return "Bidaia{" + "izena='" + izena + '\'' + ", bidaiaMota='" + bidaiaMota + '\'' + ", hasieraData="
				+ hasieraData + ", amaieraData=" + amaieraData + ", egunKantitatea=" + getEgunKantitatea()
				+ ", herrialdea='" + herrialdea + '\'' + ", deskripzioa='" + deskripzioa + '\'' + '}';
	}
}
