package modelo;

import java.util.ArrayList;

public class Agentzia {
	private ArrayList<Bidaia> bidaia;
	private int agentziaKodea;
	private String langileKopKod;
	private String logoa;
	private String markarenKolorea;
	private String izena;
	private String agentziaMKod;
	private String pasahitza;

	public Agentzia(int agentziaKodea, String langileKopKod, String logoa, String markarenKolorea, String izena,
			String agentziaMKod, String pasahitza) {
		super();
		this.agentziaKodea = agentziaKodea;
		this.langileKopKod = langileKopKod;
		this.logoa = logoa;
		this.markarenKolorea = markarenKolorea;
		this.izena = izena;
		this.agentziaMKod = agentziaMKod;
		this.pasahitza = pasahitza;
	}

	public Agentzia() {
		
	}

	public int getAgentziaKodea() {
		return agentziaKodea;
	}



	public void setAgentziaKodea(int agentziaKodea) {
		this.agentziaKodea = agentziaKodea;
	}



	public String getLangileKopKod() {
		return langileKopKod;
	}



	public void setLangileKopKod(String langileKopKod) {
		this.langileKopKod = langileKopKod;
	}



	public String getLogoa() {
		return logoa;
	}



	public void setLogoa(String logoa) {
		this.logoa = logoa;
	}



	public String getMarkarenKolorea() {
		return markarenKolorea;
	}



	public void setMarkarenKolorea(String markarenKolorea) {
		this.markarenKolorea = markarenKolorea;
	}



	public String getIzena() {
		return izena;
	}



	public void setIzena(String izena) {
		this.izena = izena;
	}



	public String getAgentziaMKod() {
		return agentziaMKod;
	}



	public void setAgentziaMKod(String agentziaMKod) {
		this.agentziaMKod = agentziaMKod;
	}



	public String getPasahitza() {
		return pasahitza;
	}



	public void setPasahitza(String pasahitza) {
		this.pasahitza = pasahitza;
	}

	@Override
	public String toString() {
		return "Agentzia [agentziaKodea=" + agentziaKodea + ", langileKopKod=" + langileKopKod + ", logoa=" + logoa
				+ ", markarenKolorea=" + markarenKolorea + ", izena=" + izena + ", agentziaMKod=" + agentziaMKod
				+ ", pasahitza=" + pasahitza + "]";
	}
	
}
