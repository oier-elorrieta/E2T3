package modelo;

public class Bidai_Motak {
	
	private String bidaiKodea;
	private String deskribapena;
	
	public Bidai_Motak(String bidaiKodea, String deskribapena) {
		this.bidaiKodea = bidaiKodea;
		this.deskribapena = deskribapena;
	}
	
	public String getBidaiKodea() {
		return bidaiKodea;
	}
	public void setBidaiKodea(String bidaiKodea) {
		this.bidaiKodea = bidaiKodea;
	}
	public String getDeskribapena() {
		return deskribapena;
	}
	public void setDeskribapena(String deskribapena) {
		this.deskribapena = deskribapena;
	}

	@Override
	public String toString() {
		return "Herrialde [BidaiKodea=" + bidaiKodea + ", Deskribapena=" + deskribapena + "]";
	}
	
	
	
}
