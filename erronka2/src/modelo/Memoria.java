package modelo;

import java.util.ArrayList;

public class Memoria {
	
	private  ArrayList<Aeroportua> aeroportua = new ArrayList<>();
	private  ArrayList<Agentzia> agentzia = new ArrayList<>();
	private  ArrayList<AgentziaMota> agentziaMota = new ArrayList<>();
	private  ArrayList<Airelineak> airelineak = new ArrayList<>();
	private  ArrayList<Bidaia> bidaia = new ArrayList<>();
	private  ArrayList<BidaiaMota> bidaiaMota = new ArrayList<>();
	private  ArrayList<Ekitaldi> ekitaldi = new ArrayList<>();
	private  ArrayList<Hegaldia> helgaldia = new ArrayList<>();
	private  ArrayList<Herrialde> herrialdea = new ArrayList<>();
	private  ArrayList<Lang_Kopurua> langKop = new ArrayList<>();
	private  ArrayList<LogelaMotak> logelaMota = new ArrayList<>();
	private  ArrayList<Ostatua> ostatua = new ArrayList<>();
	private  ArrayList<Zerbitzu> zerbitzu = new ArrayList<>();
	private int kontagailu;
	
	public Memoria() {
	    kontagailu = 0;
	}
	
	public int mendiKopurua() {
		return this.kontagailu;
	}
	
	public void gehitu(Montes m) {	
		montes[kontagailu] = m;
		this.kontagailu++;
	}

	public Montes erakutsiMendia(int index) {
	    if (index >= 0 && index < kontagailu) {
	        return montes[index];
	    }
	    return null;
	}
	
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
	
	