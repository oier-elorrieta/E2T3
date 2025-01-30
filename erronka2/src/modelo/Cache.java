package modelo;

//para guardar temporalmente la agentzia

public class Cache {

	private static Agentzia agentzia;

	public Cache() {

	}

	public static Agentzia getAgentzia() {
		return agentzia;
	}

	public static void setAgentzia(Agentzia agentzia) {
		Cache.agentzia = agentzia;
	}

}
