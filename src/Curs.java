import javafx.util.Pair;

import java.util.Date;

public class Curs {
    private String nume;
    private Integer credite;
    private Pair<Date,Date> perioadaDesfasurare;
    private Date dataExamen;
    private Profesor profesor;

    Curs(String nume, Integer credite, Pair<Date,Date> perioadaDesfasurare, Date dataExamen, Profesor profesor){
        this.nume = nume;
        this.credite = credite;
        this.perioadaDesfasurare = new Pair<>(perioadaDesfasurare.getKey(),perioadaDesfasurare.getValue());
        this.dataExamen = dataExamen;
        this.profesor = profesor; // nu stiu cat e de corect, dar vreau ca daca ceva se intampla cu un anume profesor
                                    // spre ex daca isi schimba numele
                                    // sa se modifice si cursul aferent
                                // deci ma gandesc ca e mai ok sa ii retin aici ca referinta, nu ?

    }

    @Override
    public String toString() {
        return "Cursul de " + this.nume + " este sustinut de " + this.profesor.toString() + " in perioada "
                + this.perioadaDesfasurare.getKey().toString() + " - " + this.perioadaDesfasurare.getValue().toString() +
                " si are examenul pe data de " + this.dataExamen.toString() + " si valoreaza " + this.credite + " credite";
    }

    public String getNume() {
        return nume;
    }

    public Date getDataExamen() {
        return dataExamen;
    }

    public Integer getCredite() {
        return credite;
    }

    public Pair<Date, Date> getPerioadaDesfasurare() {
        return perioadaDesfasurare;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setCredite(Integer credite) {
        this.credite = credite;
    }

    public void setDataExamen(Date dataExamen) {
        this.dataExamen = dataExamen;
    }

    public void setPerioadaDesfasurare(Pair<Date, Date> perioadaDesfasurare) {
        this.perioadaDesfasurare = perioadaDesfasurare;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
}
