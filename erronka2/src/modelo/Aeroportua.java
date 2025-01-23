package modelo;

public class Aeroportua {
	private String izena;
	private String hiria;

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

	@Override
	public String toString() {
		return "Aeroportua [izena=" + izena + ", hiria=" + hiria + "]";
	}
}
