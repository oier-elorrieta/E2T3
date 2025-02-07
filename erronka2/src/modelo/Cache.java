package modelo;

//para guardar temporalmente la agentzia

public class Cache {

	private static Agentzia agentzia;

	public Cache() {

	}

	public Agentzia getAgentzia() {
		return agentzia;
	}

	public void setAgentzia(Agentzia agentzia) {
		Cache.agentzia = agentzia;
	}

}
