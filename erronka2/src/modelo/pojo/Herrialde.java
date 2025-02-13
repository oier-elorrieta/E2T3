package modelo.pojo;

public class Herrialde {
	
	private String herrialdeKodea;
	private String helmuga;
	
	public Herrialde(String herrialdeKodea, String helmuga) {
		this.herrialdeKodea = herrialdeKodea;
		this.helmuga = helmuga;
	}
	
	public String getHerrialdeKodea() {
		return herrialdeKodea;
	}
	public void setHerrialdeKodea(String herrialdeKodea) {
		this.herrialdeKodea = herrialdeKodea;
	}
	public String getHelmuga() {
		return helmuga;
	}
	public void setHelmuga(String helmuga) {
		this.helmuga = helmuga;
	}

	@Override
	public String toString() {
		return "Herrialde [herrialdeKodea=" + herrialdeKodea + ", helmuga=" + helmuga + "]";
	}
	
	
	
}
