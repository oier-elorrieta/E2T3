package modelo;

public class Agentzia_Motak {
	private String agentziaMKodea;
	private String deskribapena;
	
	public Agentzia_Motak(String agentziaMKodea, String deskribapena) {
		super();
		this.agentziaMKodea = agentziaMKodea;
		this.deskribapena = deskribapena;
	}
	
	public String getAgentziaMKodea() {
		return agentziaMKodea;
	}
	public void setAgentziaMKodea(String agentziaMKodea) {
		this.agentziaMKodea = agentziaMKodea;
	}
	public String getDeskribapena() {
		return deskribapena;
	}
	public void setDeskribapena(String deskribapena) {
		this.deskribapena = deskribapena;
	}
	
	@Override
	public String toString() {
		return "Agentzia_Motak [agentziaMKodea=" + agentziaMKodea + ", deskribapena=" + deskribapena + "]";
	}
}
