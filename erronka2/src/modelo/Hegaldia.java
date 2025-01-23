package modelo;

public class Hegaldia {
    private String ibilbidea;
    private String jatorrizkoAireportua;
    private String helmugaAireportua;
    private String joanekoData;
    private String hegaldiarenKodea;
    private String aerolinea;
    private double prezioa;
    private String irteeraOrdua;
    private String iraupena;

    // Balio lehenetsia klaseko atributeentzat
    public Hegaldia() {
        this.ibilbidea = "Bakarrik Joanekoa";  
        this.jatorrizkoAireportua = "";
        this.helmugaAireportua = "";
        this.joanekoData = "";
        this.hegaldiarenKodea = "";
        this.aerolinea = "";
        this.prezioa = 0.0;
        this.irteeraOrdua = "";
        this.iraupena = "";
    }

    // Parametroak dituen konstruktorea
    public Hegaldia(String ibilbidea, String jatorrizkoAireportua, String helmugaAireportua, String joanekoData, String hegaldiarenKodea, String aerolinea, double prezioa, String irteeraOrdua, String iraupena) {
        this.ibilbidea = ibilbidea;
        this.jatorrizkoAireportua = jatorrizkoAireportua;
        this.helmugaAireportua = helmugaAireportua;
        this.joanekoData = joanekoData;
        this.hegaldiarenKodea = hegaldiarenKodea;
        this.aerolinea = aerolinea;
        this.prezioa = prezioa;
        this.irteeraOrdua = irteeraOrdua;
        this.iraupena = iraupena;
    }

    // Metodok irakurlearentzako eta aldaketentzako
    public String getIbilbidea() {
        return ibilbidea;
    }

    public void setIbilbidea(String ibilbidea) {
        this.ibilbidea = ibilbidea;
    }

    public String getJatorrizkoAireportua() {
        return jatorrizkoAireportua;
    }

    public void setJatorrizkoAireportua(String jatorrizkoAireportua) {
        this.jatorrizkoAireportua = jatorrizkoAireportua;
    }

    public String getHelmugaAireportua() {
        return helmugaAireportua;
    }

    public void setHelmugaAireportua(String helmugaAireportua) {
        this.helmugaAireportua = helmugaAireportua;
    }

    public String getJoanekoData() {
        return joanekoData;
    }

    public void setJoanekoData(String joanekoData) {
        this.joanekoData = joanekoData;
    }

    public String getHegaldiarenKodea() {
        return hegaldiarenKodea;
    }

    public void setHegaldiarenKodea(String hegaldiarenKodea) {
        this.hegaldiarenKodea = hegaldiarenKodea;
    }

    public String getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(String aerolinea) {
        this.aerolinea = aerolinea;
    }

    public double getPrezioa() {
        return prezioa;
    }

    public void setPrezioa(double prezioa) {
        this.prezioa = prezioa;
    }

    public String getIrteeraOrdua() {
        return irteeraOrdua;
    }

    public void setIrteeraOrdua(String irteeraOrdua) {
        this.irteeraOrdua = irteeraOrdua;
    }

    public String getIraupena() {
        return iraupena;
    }

    public void setIraupena(String iraupena) {
        this.iraupena = iraupena;
    }

    @Override
    public String toString() {
        return "Hegaldia{" +
                "ibilbidea='" + ibilbidea + '\'' +
                ", jatorrizkoAireportua='" + jatorrizkoAireportua + '\'' +
                ", helmugaAireportua='" + helmugaAireportua + '\'' +
                ", joanekoData='" + joanekoData + '\'' +
                ", hegaldiarenKodea='" + hegaldiarenKodea + '\'' +
                ", aerolinea='" + aerolinea + '\'' +
                ", prezioa=" + prezioa +
                ", irteeraOrdua='" + irteeraOrdua + '\'' +
                ", iraupena='" + iraupena + '\'' +
                '}';
    }

   
    public void bilatuBidaia() {
        //  Skyscanner
    }
}

