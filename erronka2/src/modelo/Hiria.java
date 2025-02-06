package modelo;

public class Hiria {
	private int hiriKode;
	private String izena;
	
	public Hiria(int hirikode, String izena) {
		this.hiriKode = hirikode;
		this.izena= izena;
	}
	public int getHiriKode() {
		return hiriKode;
	}
	public void setHiriKode(int hiriKode) {
		this.hiriKode = hiriKode;
	}
	public String getIzena() {
		return izena;
	}
	public void setIzena(String izena) {
		this.izena = izena;
	}
	
	@Override
	public String toString() {
		return "Hiria [hiriKode=" + hiriKode + ", izena=" + izena + "]";
	}

}
