package modelo;

public class Hiria {
    private String izena;
    private int biztanleria;
    private String deskripzioa;

    public Hiria(String izena, int biztanleria, String deskripzioa) {
        this.izena = izena;
        this.biztanleria = biztanleria;
        this.deskripzioa = deskripzioa;
    }

    public String getIzena() {
        return izena;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    public int getBiztanleria() {
        return biztanleria;
    }

    public void setBiztanleria(int biztanleria) {
        this.biztanleria = biztanleria;
    }

    public String getDeskripzioa() {
        return deskripzioa;
    }

    public void setDeskripzioa(String deskripzioa) {
        this.deskripzioa = deskripzioa;
    }

    @Override
    public String toString() {
        return "Hiria{" +
                "izena='" + izena + '\'' +
                ", biztanleria=" + biztanleria +
                ", deskripzioa='" + deskripzioa + '\'' +
                '}';
    }
}
