package modelo;

import java.time.LocalDate;

public class Ostatua {
	// Atributuak
	private String izena;
	private String hiria;
	private double prezioEurotan;
	private LocalDate hasieraData;
	private LocalDate amaieraData;

	//getters and setters
	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public String getHiria() {
		return hiria;
	}

	public void setHiria(String hiria) {
		this.hiria = hiria;
	}

	public double getPrezioEurotan() {
		return prezioEurotan;
	}

	public void setPrezioEurotan(double prezioEurotan) {
		this.prezioEurotan = prezioEurotan;
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

}
