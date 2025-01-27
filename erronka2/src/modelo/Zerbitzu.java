package modelo;

import java.sql.Date;
import java.sql.Time;

public class Zerbitzu {
	//Zerbitzu Taula
	private int zerbitzuKodea;
	private int bidaiarenKodea;
	
	//Ostatu Taula
	private int ostatuKodea;
	private float prezioa;
	private Date sarreraEguna;
	private Date irteraEguna;
	private String hiria;
	private String ostatuIzena;
	private String logelaMKodea;
	
	//BESTE_BATZUK Taula
	
	private int besteKod;
	private String besteIzena;
	private Date egun;
	private String besteDeskribapena;
	private float bestePrezioa;

	//Hegaldia taula
	
	private int hegaldiKod;
	private String jatorrizkoAireportua;
	private String helmugakoAireportua;
	private String hegaldiKodea;
	private Time bidaiarenIraupena;
	private float hegaldiPrezioa;
	private Date ireteeraData;
	private Time irteeraOrdutegia;
	private String aireportua;
	
	
	

	public int getZerbitzuKodea() {
		return zerbitzuKodea;
	}
	public void setZerbitzuKodea(int zerbitzuKodea) {
		this.zerbitzuKodea = zerbitzuKodea;
	}
	public int getBidaiarenKodea() {
		return bidaiarenKodea;
	}
	public void setBidaiarenKodea(int bidaiarenKodea) {
		this.bidaiarenKodea = bidaiarenKodea;
	}
	public int getOstatuKodea() {
		return ostatuKodea;
	}
	public void setOstatuKodea(int ostatuKodea) {
		this.ostatuKodea = ostatuKodea;
	}
	public float getPrezioa() {
		return prezioa;
	}
	public void setPrezioa(float prezioa) {
		this.prezioa = prezioa;
	}
	public Date getSarreraEguna() {
		return sarreraEguna;
	}
	public void setSarreraEguna(Date sarreraEguna) {
		this.sarreraEguna = sarreraEguna;
	}
	public Date getIrteraEguna() {
		return irteraEguna;
	}
	public void setIrteraEguna(Date irteraEguna) {
		this.irteraEguna = irteraEguna;
	}
	public String getHiria() {
		return hiria;
	}
	public void setHiria(String hiria) {
		this.hiria = hiria;
	}
	public String getOstatuIzena() {
		return ostatuIzena;
	}
	public void setOstatuIzena(String ostatuIzena) {
		this.ostatuIzena = ostatuIzena;
	}
	public String getLogelaMKodea() {
		return logelaMKodea;
	}
	public void setLogelaMKodea(String logelaMKodea) {
		this.logelaMKodea = logelaMKodea;
	}
	public int getBesteKod() {
		return besteKod;
	}
	public void setBesteKod(int besteKod) {
		this.besteKod = besteKod;
	}
	public String getBesteIzena() {
		return besteIzena;
	}
	public void setBesteIzena(String besteIzena) {
		this.besteIzena = besteIzena;
	}
	public Date getEgun() {
		return egun;
	}
	public void setEgun(Date egun) {
		this.egun = egun;
	}
	public String getBesteDeskribapena() {
		return besteDeskribapena;
	}
	public void setBesteDeskribapena(String besteDeskribapena) {
		this.besteDeskribapena = besteDeskribapena;
	}
	public float getBestePrezioa() {
		return bestePrezioa;
	}
	public void setBestePrezioa(float bestePrezioa) {
		this.bestePrezioa = bestePrezioa;
	}
	public int getHegaldiKod() {
		return hegaldiKod;
	}
	public void setHegaldiKod(int hegaldiKod) {
		this.hegaldiKod = hegaldiKod;
	}
	public String getJatorrizkoAireportua() {
		return jatorrizkoAireportua;
	}
	public void setJatorrizkoAireportua(String jatorrizkoAireportua) {
		this.jatorrizkoAireportua = jatorrizkoAireportua;
	}
	public String getHelmugakoAireportua() {
		return helmugakoAireportua;
	}
	public void setHelmugakoAireportua(String helmugakoAireportua) {
		this.helmugakoAireportua = helmugakoAireportua;
	}
	public String getHegaldiKodea() {
		return hegaldiKodea;
	}
	public void setHegaldiKodea(String hegaldiKodea) {
		this.hegaldiKodea = hegaldiKodea;
	}
	public Time getBidaiarenIraupena() {
		return bidaiarenIraupena;
	}
	public void setBidaiarenIraupena(Time bidaiarenIraupena) {
		this.bidaiarenIraupena = bidaiarenIraupena;
	}
	public float getHegaldiPrezioa() {
		return hegaldiPrezioa;
	}
	public void setHegaldiPrezioa(float hegaldiPrezioa) {
		this.hegaldiPrezioa = hegaldiPrezioa;
	}
	public Date getIreteeraData() {
		return ireteeraData;
	}
	public void setIreteeraData(Date ireteeraData) {
		this.ireteeraData = ireteeraData;
	}
	public Time getIrteeraOrdutegia() {
		return irteeraOrdutegia;
	}
	public void setIrteeraOrdutegia(Time irteeraOrdutegia) {
		this.irteeraOrdutegia = irteeraOrdutegia;
	}
	public String getAireportua() {
		return aireportua;
	}
	public void setAireportua(String aireportua) {
		this.aireportua = aireportua;
	}
	
	
	public String toStringZerbitzuak() {
		return "Zerbitzu [zerbitzuKodea=" + zerbitzuKodea + ", bidaiarenKodea=" + bidaiarenKodea + "]";
	}
	
	public String toStringOstatu() {
		return "Zerbitzu [ostatuKodea=" + ostatuKodea + ", prezioa=" + prezioa + ", sarreraEguna=" + sarreraEguna
				+ ", irteraEguna=" + irteraEguna + ", hiria=" + hiria + ", ostatuIzena=" + ostatuIzena
				+ ", logelaMKodea=" + logelaMKodea + "]";
	}
	
	public String toStringBesteBatzuk() {
		return "Zerbitzu [besteKod=" + besteKod + ", besteIzena=" + besteIzena + ", egun=" + egun
				+ ", besteDeskribapena=" + besteDeskribapena + ", bestePrezioa=" + bestePrezioa + "]";
	}
	
	public String toStringHegaldia() {
		return "Zerbitzu [hegaldiKod=" + hegaldiKod + ", jatorrizkoAireportua=" + jatorrizkoAireportua
				+ ", helmugakoAireportua=" + helmugakoAireportua + ", hegaldiKodea=" + hegaldiKodea
				+ ", bidaiarenIraupena=" + bidaiarenIraupena + ", hegaldiPrezioa=" + hegaldiPrezioa + ", ireteeraData="
				+ ireteeraData + ", irteeraOrdutegia=" + irteeraOrdutegia + ", aireportua=" + aireportua + "]";
	}
	
}
