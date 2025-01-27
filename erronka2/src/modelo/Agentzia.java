package modelo;

public class Agentzia {
	private int kodea;
	private String langileKopKod;
	private String logoa;
	private String MarkarenKolorea;
	private String izena;
	private String agentziaMKod;
	private String pasahitza;
	
	public Agentzia(int kodea, String langileKopKod, String logoa, String markarenKolorea, String izena,
			String agentziaMKod, String pasahitza) {
		super();
		this.kodea = kodea;
		this.langileKopKod = langileKopKod;
		this.logoa = logoa;
		MarkarenKolorea = markarenKolorea;
		this.izena = izena;
		this.agentziaMKod = agentziaMKod;
		this.pasahitza = pasahitza;
	}
	
	public int getKodea() {
		return kodea;
	}
	public void setKodea(int kodea) {
		this.kodea = kodea;
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
		return MarkarenKolorea;
	}
	public void setMarkarenKolorea(String markarenKolorea) {
		MarkarenKolorea = markarenKolorea;
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
		return "Agentzia [kodea=" + kodea + ", langileKopKod=" + langileKopKod + ", logoa=" + logoa
				+ ", MarkarenKolorea=" + MarkarenKolorea + ", izena=" + izena + ", agentziaMKod=" + agentziaMKod
				+ ", pasahitza=" + pasahitza + "]";
	}
	
	
	
}
