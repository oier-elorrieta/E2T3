package modelo.pojo;

import java.sql.Date;
import java.util.ArrayList;

public class Bidaia {
	private ArrayList<Zerbitzu> zerbitzuak = new ArrayList<>();
	private int bidaiKodea;
	private String izena;
	private String deskribapena;
	private String EzBarne;
	private Date bidaiHasiera;
	private Date bidaiAmaiera;
	private String herrialdeKod;
	private String bidaiaMKod;
	private int agentziaKod;

	public Bidaia(int bidaiKodea, String izena, String deskribapena, String ezBarne, Date bidaiHasiera,
			Date bidaiAmaiera, String herrialdeKod, String bidaiaMKod, int agentziaKod) {
		this.bidaiKodea = bidaiKodea;
		this.izena = izena;
		this.deskribapena = deskribapena;
		EzBarne = ezBarne;
		this.bidaiHasiera = bidaiHasiera;
		this.bidaiAmaiera = bidaiAmaiera;
		this.herrialdeKod = herrialdeKod;
		this.bidaiaMKod = bidaiaMKod;
		this.agentziaKod = agentziaKod;
	}

	public Bidaia(ArrayList<Zerbitzu> zerbitzuak, int bidaiKodea, String izena, String deskribapena, String ezBarne,
			Date bidaiHasiera, Date bidaiAmaiera, String herrialdeKod, String bidaiaMKod, int agentziaKod) {
		super();
		this.zerbitzuak = zerbitzuak;
		this.bidaiKodea = bidaiKodea;
		this.izena = izena;
		this.deskribapena = deskribapena;
		EzBarne = ezBarne;
		this.bidaiHasiera = bidaiHasiera;
		this.bidaiAmaiera = bidaiAmaiera;
		this.herrialdeKod = herrialdeKod;
		this.bidaiaMKod = bidaiaMKod;
		this.agentziaKod = agentziaKod;
	}

	public Bidaia() {

	}

	public void gehituZerbitzuak(Zerbitzu z) {
		zerbitzuak.add(z);
	}

	public ArrayList<Zerbitzu> getZerbitzuak() {
		return zerbitzuak;
	}

	public void setZerbitzuak(ArrayList<Zerbitzu> zerbitzuak) {
		this.zerbitzuak = zerbitzuak;
	}

	public int getBidaiKodea() {
		return bidaiKodea;
	}

	public void setBidaiKodea(int bidaiKodea) {
		this.bidaiKodea = bidaiKodea;
	}

	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public String getDeskribapena() {
		return deskribapena;
	}

	public void setDeskribapena(String deskribapena) {
		this.deskribapena = deskribapena;
	}

	public String getEzBarne() {
		return EzBarne;
	}

	public void setEzBarne(String ezBarne) {
		EzBarne = ezBarne;
	}

	public Date getBidaiHasiera() {
		return bidaiHasiera;
	}

	public void setBidaiHasiera(Date bidaiHasiera) {
		this.bidaiHasiera = bidaiHasiera;
	}

	public Date getBidaiAmaiera() {
		return bidaiAmaiera;
	}

	public void setBidaiAmaiera(Date bidaiAmaiera) {
		this.bidaiAmaiera = bidaiAmaiera;
	}

	public String getHerrialdeKod() {
		return herrialdeKod;
	}

	public void setHerrialdeKod(String herrialdeKod) {
		this.herrialdeKod = herrialdeKod;
	}

	public String getBidaiaMKod() {
		return bidaiaMKod;
	}

	public void setBidaiaMKod(String bidaiaMKod) {
		this.bidaiaMKod = bidaiaMKod;
	}

	public int getAgentziaKod() {
		return agentziaKod;
	}

	public void setAgentziaKod(int agentziaKod) {
		this.agentziaKod = agentziaKod;
	}

	@Override
	public String toString() {
		return "Bidaia [zerbitzuak=" + zerbitzuak + ", bidaiKodea=" + bidaiKodea + ", izena=" + izena
				+ ", deskribapena=" + deskribapena + ", EzBarne=" + EzBarne + ", bidaiHasiera=" + bidaiHasiera
				+ ", bidaiAmaiera=" + bidaiAmaiera + ", herrialdeKod=" + herrialdeKod + ", bidaiaMKod=" + bidaiaMKod
				+ ", agentziaKod=" + agentziaKod + "]";
	}

	public String toStringTxt() {
	    StringBuilder result = new StringBuilder();
	    result.append("Aurrekontua").append("\n" + "\n");
	    // Verificar y agregar la información de la instancia actual
	    if (getDeskribapena() != null) 
	        result.append("Deskribapena = ").append(getDeskribapena()).append("\n");
	    if (getEzBarne() != null) 
	        result.append("EzBarne = ").append(getEzBarne()).append("\n");
	    if (getBidaiHasiera() != null) 
	        result.append("Bidaiaren Hasiera = ").append(getBidaiHasiera()).append("\n");
	    if (getBidaiAmaiera() != null) 
	        result.append("Bidaiaren Amaiera = ").append(getBidaiAmaiera()).append("\n");
	    if (getHerrialdeKod() != null) 
	        result.append("Herrialde Kodea = ").append(getHerrialdeKod()).append("\n");
	    if (getBidaiaMKod() != null) 
	        result.append("Bidai Motaren Kodea = ").append(getBidaiaMKod()).append("\n");
	    if (getAgentziaKod() != 0) 
	        result.append("Agentzia Kodea = ").append(getAgentziaKod()).append("\n");

	    // Verificar y agregar los servicios
	    for (Zerbitzu zerbitzua : zerbitzuak) {
	        if (zerbitzua.getZerbitzuIzena() != null) 
	            result.append("\n" + "Zerbitzuaren izena = ").append(zerbitzua.getZerbitzuIzena()).append("\n");
	        if (zerbitzua.getOstatuPrezioa() != 0) 
	            result.append("Ostatu Prezioa = ").append(zerbitzua.getOstatuPrezioa()).append("\n");
	        if (zerbitzua.getOstatuSarreraEguna() != null) 
	            result.append("Ostatu sarrera eguna = ").append(zerbitzua.getOstatuSarreraEguna()).append("\n");
	        if (zerbitzua.getOstatuIrteraEguna() != null) 
	            result.append("Ostatu irteera eguna = ").append(zerbitzua.getOstatuIrteraEguna()).append("\n");
	        if (zerbitzua.getHiria() != null) 
	            result.append("Hiria = ").append(zerbitzua.getHiria()).append("\n");
	        if (zerbitzua.getLogelaMKodea() != null) 
	            result.append("Logela mota = ").append(zerbitzua.getLogelaMKodea()).append("\n");
	        if (zerbitzua.getEgun() != null) 
	            result.append("Ostatu eguna = ").append(zerbitzua.getEgun()).append("\n");
	        if (zerbitzua.getBesteBatzukDeskribapena() != null) 
	            result.append("Joan-Etorri Deskribapena = ").append(zerbitzua.getBesteBatzukDeskribapena()).append("\n");
	        if (zerbitzua.getBesteBatzukPrezioa() != 0) 
	            result.append("Joan-Etorri prezioa = ").append(zerbitzua.getBesteBatzukPrezioa()).append("\n");

	        if (zerbitzua.getHegaldiKodea() != null) 
	            result.append("Hegaldi kodea = ").append(zerbitzua.getHegaldiKodea()).append("\n");
	        if (zerbitzua.getHegaldiIrteraData() != null) 
	            result.append("Hegaldi irteera data = ").append(zerbitzua.getHegaldiIrteraData()).append("\n");
	        if (zerbitzua.getHegaldiIrteeraOrdutegia() != null) 
	            result.append("Hegaldi irteera ordua = ").append(zerbitzua.getHegaldiIrteeraOrdutegia()).append("\n");
	        if (zerbitzua.getHegaldiBidaiarenIraupena() != null) 
	            result.append("Hegaldi Bidaiaren iraupena = ").append(zerbitzua.getHegaldiBidaiarenIraupena()).append("\n");
	        if (zerbitzua.getHegaldiPrezioa() != 0) 
	            result.append("Hegaldi prezioa = ").append(zerbitzua.getHegaldiPrezioa()).append("\n");
	        if (zerbitzua.getHegaldiJatorrizkoAireportua() != null) 
	            result.append("Hegaldi jatorrizko aireportua = ").append(zerbitzua.getHegaldiJatorrizkoAireportua()).append("\n");
	        if (zerbitzua.getHegaldiHelmugakoAireportua() != null) 
	            result.append("Hegaldi helmugako aireportua = ").append(zerbitzua.getHegaldiHelmugakoAireportua()).append("\n");
	        if (zerbitzua.getAirelinaKodea() != null) 
	            result.append("Airelinea Kodea = ").append(zerbitzua.getAirelinaKodea()).append("\n");

	        // Para la tabla "Joan eta Etorri"
	        if (zerbitzua.getHegaldiKodeaEtorri() != null) 
	            result.append("Etorri hegaldiaren kodea = ").append(zerbitzua.getHegaldiKodeaEtorri()).append("\n");
	        if (zerbitzua.getItzuleraOrdua() != null) 
	            result.append("Itzulera ordua = ").append(zerbitzua.getItzuleraOrdua()).append("\n");
	        if (zerbitzua.getEtorriaEguna() != null) 
	            result.append("Etorri eguna = ").append(zerbitzua.getEtorriaEguna()).append("\n");
	        if (zerbitzua.getBueltakoIraupena() != null) 
	            result.append("Bueltako iraupena = ").append(zerbitzua.getBueltakoIraupena()).append("\n");
	        if (zerbitzua.getJoanJatorrizkoAireportua() != null) 
	            result.append("Joan jatorrizko aireportua = ").append(zerbitzua.getJoanJatorrizkoAireportua()).append("\n");
	        if (zerbitzua.getJoanHelmugakoAireportua() != null) 
	            result.append("Joan helmugako aireportua = ").append(zerbitzua.getJoanHelmugakoAireportua()).append("\n");
	        if (zerbitzua.getBueltakoAirelineaKodea() != null) 
	            result.append("Bueltako airelinea = ").append(zerbitzua.getBueltakoAirelineaKodea()).append("\n");
	    }

	    return result.toString();
	}




}
