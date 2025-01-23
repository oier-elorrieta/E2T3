package modelo;

import java.util.ArrayList;

public class Memoria {
    
    private ArrayList<Aeroportua> aeroportua;
    private ArrayList<Agentzia> agentzia;
    private ArrayList<AgentziaMota> agentziaMota;
    private ArrayList<Airelineak> airelineak;
    private ArrayList<Bidaia> bidaia;
    private ArrayList<BidaiaMota> bidaiaMota;
    private ArrayList<Ekitaldi> ekitaldi;
    private ArrayList<Hegaldia> hegaldia;
    private ArrayList<Herrialde> herrialde;
    private ArrayList<Lang_Kopurua> langKop;
    private ArrayList<LogelaMotak> logelaMota;
    private ArrayList<Ostatua> ostatua;
    private ArrayList<Zerbitzu> zerbitzu;
    
    public Memoria() {
        aeroportua = new ArrayList<>();
        agentzia = new ArrayList<>();
        agentziaMota = new ArrayList<>();
        airelineak = new ArrayList<>();
        bidaia = new ArrayList<>();
        bidaiaMota = new ArrayList<>();
        ekitaldi = new ArrayList<>();
        hegaldia = new ArrayList<>();
        herrialde = new ArrayList<>();
        langKop = new ArrayList<>();
        logelaMota = new ArrayList<>();
        ostatua = new ArrayList<>();
        zerbitzu = new ArrayList<>();
    }
    
    public void gehituAeroportua(Aeroportua m) {
        aeroportua.add(m);
    }
    
    public void gehituAgentzia(Agentzia m) {
        agentzia.add(m);
    }
    
    public void gehituAgentziaMota(AgentziaMota m) {
        agentziaMota.add(m);
    }
    
    public void gehituAirelineak(Airelineak m) {
        airelineak.add(m);
    }
    
    public void gehituBidaia(Bidaia m) {
        bidaia.add(m);
    }
    
    public void gehituBidaiaMota(BidaiaMota m) {
        bidaiaMota.add(m);
    }
    
    public void gehituEkitaldi(Ekitaldi m) {
        ekitaldi.add(m);
    }
    
    public void gehituHegaldia(Hegaldia m) {
        hegaldia.add(m);
    }
    
    public void gehituHerrialde(Herrialde m) {
        herrialde.add(m);
    }
    
    public void gehituLang_Kopurua(Lang_Kopurua m) {
        langKop.add(m);
    }
    
    public void gehituLogelaMotak(LogelaMotak m) {
        logelaMota.add(m);
    }
    
    public void gehituOstatua(Ostatua m) {
        ostatua.add(m);
    }
    
    public void gehituZerbitzu(Zerbitzu m) {
        zerbitzu.add(m);
    }
    
    public void erakutsiAeroportuak() {
        for (Aeroportua a : aeroportua) {
            System.out.println(a);
        }
    }
    
    public void erakutsiAgentziak() {
        for (Agentzia a : agentzia) {
            System.out.println(a);
        }
    }
    
    public void erakutsiAgentziaMotak() {
        for (AgentziaMota a : agentziaMota) {
            System.out.println(a);
        }
    }
    
    public void erakutsiAirelineak() {
        for (Airelineak a : airelineak) {
            System.out.println(a);
        }
    }
    
    public void erakutsiBidaia() {
        for (Bidaia b : bidaia) {
            System.out.println(b);
        }
    }
    
    public void erakutsiBidaiaMotak() {
        for (BidaiaMota b : bidaiaMota) {
            System.out.println(b);
        }
    }
    
    public void erakutsiEkitaldiak() {
        for (Ekitaldi e : ekitaldi) {
            System.out.println(e);
        }
    }
    
    public void erakutsiHegaldia() {
        for (Hegaldia h : hegaldia) {
            System.out.println(h);
        }
    }
    
    public void erakutsiHerrialdeak() {
        for (Herrialde h : herrialde) {
            System.out.println(h);
        }
    }
    
    public void erakutsiLangKopuruak() {
        for (Lang_Kopurua l : langKop) {
            System.out.println(l);
        }
    }
    
    public void erakutsiLogelaMotak() {
        for (LogelaMotak l : logelaMota) {
            System.out.println(l);
        }
    }
    
    public void erakutsiOstatuak() {
        for (Ostatua o : ostatua) {
            System.out.println(o);
        }
    }
    
    public void erakutsiZerbitzuak() {
        for (Zerbitzu z : zerbitzu) {
            System.out.println(z);
        }
    }
}




	 /*
	public void ezabatu(int pos) {
	    if (pos >= this.kontagailu) {
	        System.out.println("Ez dago emailik");
	    } else {
	        montes[pos] = null;

	        for (int i = pos; i < this.kontagailu - 1; i++) {  // Corrige el límite aquí
	            montes[i] = montes[i + 1];  // Desplazar los elementos hacia la izquierda
	        }
	        montes[this.kontagailu - 1] = null;  // Poner null en la última posición
	        this.kontagailu--;  // Disminuir el contador
	    }
	}
	
  */
	
	