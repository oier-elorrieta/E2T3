package modelo;

import java.time.LocalDate;

public class Ekitaldi {
	// Atributuak
	private String izena;
	private String ekitaldiMota;
	private LocalDate hasieraData;
	private LocalDate amaieraData;
	private String deskripzioa;

	// Getters and setters
	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public String getEkitaldiMota() {
		return ekitaldiMota;
	}

	public void setEkitaldiMota(String ekitaldiMota) {
		this.ekitaldiMota = ekitaldiMota;
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

	public String getDeskripzioa() {
		return deskripzioa;
	}

	public void setDeskripzioa(String deskripzioa) {
		this.deskripzioa = deskripzioa;
	}

}
