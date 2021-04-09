public abstract class Student {
    private String nume;
    private String prenume;
    private Integer an;
    Student(String nume, String prenume, Integer an){
        this.nume = nume;
        this.prenume = prenume;
        this.an = an;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public Integer getAn() {
        return an;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setAn(Integer an) throws Exception{
        this.an = an;
    }

    @Override
    public String toString() {
        return this.nume + " " + this.prenume + " " + " este student in anul " + this.an;
    }


}
