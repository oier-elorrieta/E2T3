package modelo.pojo;

public class Lang_kopurua {
	private String langKopKodea;
	private String deskribapena;
	
	public Lang_kopurua(String langKopKodea, String deskribapena) {
		super();
		this.langKopKodea = langKopKodea;
		this.deskribapena = deskribapena;
	}
	
	public String getLangKopKodea() {
		return langKopKodea;
	}
	public void setLangKopKodea(String langKopKodea) {
		this.langKopKodea = langKopKodea;
	}
	public String getDeskribapena() {
		return deskribapena;
	}
	public void setDeskribapena(String deskribapena) {
		this.deskribapena = deskribapena;
	}
	
	@Override
	public String toString() {
		return "Lang_kopurua [langKopKodea=" + langKopKodea + ", deskribapena=" + deskribapena + "]";
	}
	
}
