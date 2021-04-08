import java.util.Date;

public class Nota {
    private Double valoare;
    private Date dataPrimire;

    Nota(Double valoare, Date dataPrimire){
        this.valoare = valoare;
        this.dataPrimire = dataPrimire;
    }

    public Date getDataPrimire() {
        return dataPrimire;
    }

    public Double getValoare() {
        return valoare;
    }

    public void setValoare(Double valoare) {
        this.valoare = valoare;
    }

    public void setDataPrimire(Date dataPrimire) {
        this.dataPrimire = dataPrimire;
    }

    @Override
    public String toString() {
        return "Valoarea notei = " + this.valoare + ", data primirii = " + this.dataPrimire.toString();
    }
}
