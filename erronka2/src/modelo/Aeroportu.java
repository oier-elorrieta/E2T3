package modelo;

public class Aeroportu {
	
	private String aireportuKodea;
	private String hiria;
	
	public String getAireportuKodea() {
		return aireportuKodea;
	}
	public void setAireportuKodea(String aireportuKodea) {
		this.aireportuKodea = aireportuKodea;
	}
	public String getHiria() {
		return hiria;
	}
	public void setHiria(String hiria) {
		this.hiria = hiria;
	}
	
	@Override
	public String toString() {
		return "Aeroportu [aireportuKodea=" + aireportuKodea + ", hiria=" + hiria + "]";
	}
	
}
