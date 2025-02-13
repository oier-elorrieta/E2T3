package modelo.pojo;

import java.sql.Date;
import java.sql.Time;

public class Zerbitzu {
	// Zerbitzu taula

	private int zerbitzuKodea;
	private String zerbitzuIzena;
	private int bidaiKodea;

	// Ostatu taula

	private float ostatuPrezioa;
	private Date ostatuSarreraEguna;
	private Date ostatuIrteraEguna;
	private String hiria;
	private String logelaMKodea;

	// Beste batzuk taula

	private Date egun;
	private String besteBatzukDeskribapena;
	private float besteBatzukPrezioa;

	// Hegaldia taula

	private String hegaldiKodea;
	private Date hegaldiIrteraData;
	private Time hegaldiIrteeraOrdutegia;
	private Time hegaldiBidaiarenIraupena;
	private float hegaldiPrezioa;
	private String hegaldiJatorrizkoAireportua;
	private String hegaldiHelmugakoAireportua;
	private String airelinaKodea;

	// Joan eta etorri taula

	private String hegaldiKodeaEtorri;
	private Time itzuleraOrdua;
	private Date etorriaEguna;
	private Time bueltakoIraupena;
	private String joanJatorrizkoAireportua;
	private String joanHelmugakoAireportua;
	private String bueltakoAirelineaKodea;

	public Zerbitzu(int zerbitzuKodea, String zerbitzuIzena, int bidaiMotaKodea, float ostatuPrezioa,
			Date ostatuSarreraEguna, Date ostatuIrteraEguna, String hiria, String logelaMKodea) {
		this.zerbitzuKodea = zerbitzuKodea;
		this.zerbitzuIzena = zerbitzuIzena;
		this.bidaiKodea = bidaiMotaKodea;
		this.ostatuPrezioa = ostatuPrezioa;
		this.ostatuSarreraEguna = ostatuSarreraEguna;
		this.ostatuIrteraEguna = ostatuIrteraEguna;
		this.hiria = hiria;
		this.logelaMKodea = logelaMKodea;
	}

	public Zerbitzu(int zerbitzuKodea, String zerbitzuIzena, int bidaiMotaKodea, String hegaldiKodea,
			Date hegaldiIrteraData, Time hegaldiIrteeraOrdutegia, Time hegaldiBidaiarenIraupena, float hegaldiPrezioa,
			String hegaldiJatorrizkoAireportua, String hegaldiHelmugakoAireportua, String airelinaKodea) {
		this.zerbitzuKodea = zerbitzuKodea;
		this.zerbitzuIzena = zerbitzuIzena;
		this.bidaiKodea = bidaiMotaKodea;
		this.hegaldiKodea = hegaldiKodea;
		this.hegaldiIrteraData = hegaldiIrteraData;
		this.hegaldiIrteeraOrdutegia = hegaldiIrteeraOrdutegia;
		this.hegaldiBidaiarenIraupena = hegaldiBidaiarenIraupena;
		this.hegaldiPrezioa = hegaldiPrezioa;
		this.hegaldiJatorrizkoAireportua = hegaldiJatorrizkoAireportua;
		this.hegaldiHelmugakoAireportua = hegaldiHelmugakoAireportua;
		this.airelinaKodea = airelinaKodea;
	}

	public Zerbitzu(int zerbitzuKodea, String zerbitzuIzena, int bidaiMotaKodea, String hegaldiKodea,
			Date hegaldiIrteraData, Time hegaldiIrteeraOrdutegia, Time hegaldiBidaiarenIraupena, float hegaldiPrezioa,
			String hegaldiJatorrizkoAireportua, String hegaldiHelmugakoAireportua, String airelinaKodea,
			String hegaldiKodeaEtorri, Time itzuleraOrdua, Date etorriaEguna, Time bueltakoIraupena,
			String joanJatorrizkoAireportua, String joanHelmugakoAireportua, String bueltakoAirelineaKodea) {
		this.zerbitzuKodea = zerbitzuKodea;
		this.zerbitzuIzena = zerbitzuIzena;
		this.bidaiKodea = bidaiMotaKodea;
		this.hegaldiKodea = hegaldiKodea;
		this.hegaldiIrteraData = hegaldiIrteraData;
		this.hegaldiIrteeraOrdutegia = hegaldiIrteeraOrdutegia;
		this.hegaldiBidaiarenIraupena = hegaldiBidaiarenIraupena;
		this.hegaldiPrezioa = hegaldiPrezioa;
		this.hegaldiJatorrizkoAireportua = hegaldiJatorrizkoAireportua;
		this.hegaldiHelmugakoAireportua = hegaldiHelmugakoAireportua;
		this.airelinaKodea = airelinaKodea;
		this.hegaldiKodeaEtorri = hegaldiKodeaEtorri;
		this.itzuleraOrdua = itzuleraOrdua;
		this.etorriaEguna = etorriaEguna;
		this.bueltakoIraupena = bueltakoIraupena;
		this.joanJatorrizkoAireportua = joanJatorrizkoAireportua;
		this.joanHelmugakoAireportua = joanHelmugakoAireportua;
		this.bueltakoAirelineaKodea = bueltakoAirelineaKodea;
	}

	public Zerbitzu(int zerbitzuKodea, String zerbitzuIzena, int bidaiMotaKodea, Date egun,
			String besteBatzukDeskribapena, float besteBatzukPrezioa) {
		this.zerbitzuKodea = zerbitzuKodea;
		this.zerbitzuIzena = zerbitzuIzena;
		this.bidaiKodea = bidaiMotaKodea;
		this.egun = egun;
		this.besteBatzukDeskribapena = besteBatzukDeskribapena;
		this.besteBatzukPrezioa = besteBatzukPrezioa;
	}

	public Zerbitzu() {

	}

	public int getZerbitzuKodea() {
		return zerbitzuKodea;
	}

	public void setZerbitzuKodea(int zerbitzuKodea) {
		this.zerbitzuKodea = zerbitzuKodea;
	}

	public String getZerbitzuIzena() {
		return zerbitzuIzena;
	}

	public void setZerbitzuIzena(String zerbitzuIzena) {
		this.zerbitzuIzena = zerbitzuIzena;
	}

	public int getBidaiKodea() {
		return bidaiKodea;
	}

	public void setBidaiKodea(int bidaiMotaKodea) {
		this.bidaiKodea = bidaiMotaKodea;
	}

	public float getOstatuPrezioa() {
		return ostatuPrezioa;
	}

	public void setOstatuPrezioa(float ostatuPrezioa) {
		this.ostatuPrezioa = ostatuPrezioa;
	}

	public Date getOstatuSarreraEguna() {
		return ostatuSarreraEguna;
	}

	public void setOstatuSarreraEguna(Date ostatuSarreraEguna) {
		this.ostatuSarreraEguna = ostatuSarreraEguna;
	}

	public Date getOstatuIrteraEguna() {
		return ostatuIrteraEguna;
	}

	public void setOstatuIrteraEguna(Date ostatuIrteraEguna) {
		this.ostatuIrteraEguna = ostatuIrteraEguna;
	}

	public String getHiria() {
		return hiria;
	}

	public void setHiria(String hiria) {
		this.hiria = hiria;
	}

	public String getLogelaMKodea() {
		return logelaMKodea;
	}

	public void setLogelaMKodea(String logelaMKodea) {
		this.logelaMKodea = logelaMKodea;
	}

	public Date getEgun() {
		return egun;
	}

	public void setEgun(Date egun) {
		this.egun = egun;
	}

	public String getBesteBatzukDeskribapena() {
		return besteBatzukDeskribapena;
	}

	public void setBesteBatzukDeskribapena(String besteBatzukDeskribapena) {
		this.besteBatzukDeskribapena = besteBatzukDeskribapena;
	}

	public float getBesteBatzukPrezioa() {
		return besteBatzukPrezioa;
	}

	public void setBesteBatzukPrezioa(float besteBatzukPrezioa) {
		this.besteBatzukPrezioa = besteBatzukPrezioa;
	}

	public String getHegaldiKodea() {
		return hegaldiKodea;
	}

	public void setHegaldiKodea(String hegaldiKodea) {
		this.hegaldiKodea = hegaldiKodea;
	}

	public Date getHegaldiIrteraData() {
		return hegaldiIrteraData;
	}

	public void setHegaldiIrteraData(Date hegaldiIrteraData) {
		this.hegaldiIrteraData = hegaldiIrteraData;
	}

	public Time getHegaldiIrteeraOrdutegia() {
		return hegaldiIrteeraOrdutegia;
	}

	public void setHegaldiIrteeraOrdutegia(Time hegaldiIrteeraOrdutegia) {
		this.hegaldiIrteeraOrdutegia = hegaldiIrteeraOrdutegia;
	}

	public Time getHegaldiBidaiarenIraupena() {
		return hegaldiBidaiarenIraupena;
	}

	public void setHegaldiBidaiarenIraupena(Time hegaldiBidaiarenIraupena) {
		this.hegaldiBidaiarenIraupena = hegaldiBidaiarenIraupena;
	}

	public float getHegaldiPrezioa() {
		return hegaldiPrezioa;
	}

	public void setHegaldiPrezioa(float hegaldiPrezioa) {
		this.hegaldiPrezioa = hegaldiPrezioa;
	}

	public String getHegaldiJatorrizkoAireportua() {
		return hegaldiJatorrizkoAireportua;
	}

	public void setHegaldiJatorrizkoAireportua(String hegaldiJatorrizkoAireportua) {
		this.hegaldiJatorrizkoAireportua = hegaldiJatorrizkoAireportua;
	}

	public String getHegaldiHelmugakoAireportua() {
		return hegaldiHelmugakoAireportua;
	}

	public void setHegaldiHelmugakoAireportua(String hegaldiHelmugakoAireportua) {
		this.hegaldiHelmugakoAireportua = hegaldiHelmugakoAireportua;
	}

	public String getAirelinaKodea() {
		return airelinaKodea;
	}

	public void setAirelinaKodea(String airelinaKodea) {
		this.airelinaKodea = airelinaKodea;
	}

	public String getHegaldiKodeaEtorri() {
		return hegaldiKodeaEtorri;
	}

	public void setHegaldiKodeaEtorri(String hegaldiKodeaEtorri) {
		this.hegaldiKodeaEtorri = hegaldiKodeaEtorri;
	}

	public Time getItzuleraOrdua() {
		return itzuleraOrdua;
	}

	public void setItzuleraOrdua(Time itzuleraOrdua) {
		this.itzuleraOrdua = itzuleraOrdua;
	}

	public Date getEtorriaEguna() {
		return etorriaEguna;
	}

	public void setEtorriaEguna(Date etorriaEguna) {
		this.etorriaEguna = etorriaEguna;
	}

	public Time getBueltakoIraupena() {
		return bueltakoIraupena;
	}

	public void setBueltakoIraupena(Time bueltakoIraupena) {
		this.bueltakoIraupena = bueltakoIraupena;
	}

	public String getJoanJatorrizkoAireportua() {
		return joanJatorrizkoAireportua;
	}

	public void setJoanJatorrizkoAireportua(String joanJatorrizkoAireportua) {
		this.joanJatorrizkoAireportua = joanJatorrizkoAireportua;
	}

	public String getJoanHelmugakoAireportua() {
		return joanHelmugakoAireportua;
	}

	public void setJoanHelmugakoAireportua(String joanHelmugakoAireportua) {
		this.joanHelmugakoAireportua = joanHelmugakoAireportua;
	}

	public String getBueltakoAirelineaKodea() {
		return bueltakoAirelineaKodea;
	}

	public void setBueltakoAirelineaKodea(String bueltakoAirelineaKodea) {
		this.bueltakoAirelineaKodea = bueltakoAirelineaKodea;
	}


	public String toStringTxt() {
		return "\n"
				+ "Zerbitzuaren izena = " + zerbitzuIzena + "\n"
				+ "Ostatu Prezioa = " + ostatuPrezioa + "\n"
				+ "Ostatu sarrera eguna = " + ostatuSarreraEguna + "\n" 
				+ "Ostatu irteera eguna = " + ostatuIrteraEguna + "\n" 
				+ "Hiria = " + hiria + "\n" 
				+ "Logela mota = " + logelaMKodea + "\n"  
				+ "Ostatu eguna = " + egun + "\n"
				+ "Joan-Etorri Deskribapena = "	+ besteBatzukDeskribapena + "\n"
				+ "Joan-Etorri prezioa = " + besteBatzukPrezioa + "\n"
				+ "Hegaldi kodea" + hegaldiKodea + "\n"
				+ "Hegaldi irteera data = " + hegaldiIrteraData + "\n"
				+ "Hegaldi irteera ordua" + hegaldiIrteeraOrdutegia + "\n"
				+ "Hegaldi Bidaiaren iraupena = " + hegaldiBidaiarenIraupena + "\n"
				+ "Hegaldi prezioa = " + hegaldiPrezioa + "\n" 
				+ "Hegaldi jatorrizko aireportua = " + hegaldiJatorrizkoAireportua + "\n"
				+ "Hegaldi helmugako aireportua = " + hegaldiHelmugakoAireportua + "\n"
				+ "Airelinea Kodea = " + airelinaKodea + "\n"
				+ "Etorri hegaldiaren kodea = " + hegaldiKodeaEtorri + "\n"
				+ "Itzulera ordua = " + itzuleraOrdua + "\n"
				+ "Etorri eguna = " + etorriaEguna + "\n"
				+ "Bueltako iraupena = " + bueltakoIraupena + "\n"
				+ "Joan jatorrizko aireportua = "	+ joanJatorrizkoAireportua + "\n"
				+ "Joan helmugako aireportua = " + joanHelmugakoAireportua + "\n"
				+ "Bueltako airelinea = " + bueltakoAirelineaKodea + "" + "\n";
	}

}
