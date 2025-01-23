package modelo;

public class BidaiaMota {
	// Atributuak
	private String kodea;
	private String deskribapena;

	//Getters and setters
	public String getKodea() {
		return kodea;
	}

	public void setKodea(String kodea) {
		this.kodea = kodea;
	}

	public String getDeskribapena() {
		return deskribapena;
	}

	public void setDeskribapena(String deskribapena) {
		this.deskribapena = deskribapena;
	}

	@Override
	public String toString() {
		return "BidaiaMota [kodea=" + kodea + ", deskribapena=" + deskribapena + "]";
	}
}
