package modelo;

public class Airelinea {

	private String airelineaKodea;
	private String airelineIzena;
	private String herrialdeKodea;
	
	public Airelinea(String airelineaKodea, String airelineIzena, String herrialdeKodea) {
		this.airelineaKodea = airelineaKodea;
		this.airelineIzena = airelineIzena;
		this.herrialdeKodea = herrialdeKodea;
	}

	public String getAirelineaKodea() {
		return airelineaKodea;
	}

	public void setAirelineaKodea(String airelineaKodea) {
		this.airelineaKodea = airelineaKodea;
	}

	public String getAirelineIzena() {
		return airelineIzena;
	}

	public void setAirelineIzena(String airelineIzena) {
		this.airelineIzena = airelineIzena;
	}

	public String getHerrialdeKodea() {
		return herrialdeKodea;
	}

	public void setHerrialdeKodea(String herrialdeKodea) {
		this.herrialdeKodea = herrialdeKodea;
	}
	
}
