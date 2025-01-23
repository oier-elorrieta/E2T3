package modelo;


public class Agentzia {

	private String izena;
	private String markaKolorea;
	private int langileKopurua;
	private String logoaUrl;
	private String pasahitza;

	// Aukera onargarriak langile-kopururako
	public static final int[] LANGILE_AUKERAK = { 2, 10, 100, 1000 };

	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public String getMarkaKolorea() {
		return markaKolorea;
	}

	public void setMarkaKolorea(String markaKolorea) {
		this.markaKolorea = markaKolorea;
	}

	public int getLangileKopurua() {
		return langileKopurua;
	}

	public void setLangileKopurua(int langileKopurua) {
		this.langileKopurua = langileKopurua;
	}

	public String getLogoaUrl() {
		return logoaUrl;
	}

	public void setLogoaUrl(String logoaUrl) {
		this.logoaUrl = logoaUrl;
	}

	public String getPasahitza() {
		return pasahitza;
	}

	public void setPasahitza(String pasahitza) {
		this.pasahitza = pasahitza;
	}

	public static int[] getLangileAukerak() {
		return LANGILE_AUKERAK;
	}

	@Override
	public String toString() {
		return "Agentzia [izena=" + izena + ", markaKolorea=" + markaKolorea + ", langileKopurua=" + langileKopurua
				+ ", logoaUrl=" + logoaUrl + ", pasahitza=" + pasahitza + "]";
	}



}
