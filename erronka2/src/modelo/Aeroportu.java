package modelo;

public class Aeroportu {
	
	private String Aireportua;
	private String hiria;
	
	public Aeroportu(String aireportua, String hiria) {
		super();
		Aireportua = aireportua;
		this.hiria = hiria;
	}

	public String getAireportua() {
		return Aireportua;
	}

	public void setAireportua(String aireportua) {
		Aireportua = aireportua;
	}

	public String getHiria() {
		return hiria;
	}

	public void setHiria(String hiria) {
		this.hiria = hiria;
	}
	
	
}
