import java.util.Date;

public class Examen {
    private Date data;

    Examen(Date data) {
        this.data = data;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
