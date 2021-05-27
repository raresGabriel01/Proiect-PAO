import java.util.Date;
import java.io.Serializable;
import java.util.UUID;

public class DBNota implements Serializable {
    private String id;
    private int value;
    private Date dataPrimire;

    public DBNota(int value, Date dataPrimire) {
        this.id = UUID.randomUUID().toString();
        this.value = value;
        this.dataPrimire = dataPrimire;
    }

    public Date getDataPrimire() {
        return dataPrimire;
    }

    public int getValue() {
        return value;
    }

    public String getId() {
        return id;
    }

    public void setDataPrimire(Date dataPrimire) {
        this.dataPrimire = dataPrimire;
    }

    public void setValue(int value) {
        this.value = value;
    }


}
