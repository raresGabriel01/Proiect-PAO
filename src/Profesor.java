public class Profesor {
    private String nume;
    private String prenume;
    private String titlu;

    Profesor(String nume, String prenume, String titlu) {
        this.nume = nume;
        this.prenume = prenume;
        this.titlu = titlu;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getNume() {
        return nume;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    @Override
    public String toString() {
        return titlu + " " + nume + " " + prenume;
    }
}
