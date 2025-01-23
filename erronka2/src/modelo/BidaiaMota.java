package modelo;

public class BidaiaMota {
private String kodea;
private String deskribapena;
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
