import java.util.Date;
import java.util.List;
public interface IDBCRUD {
    DBNota adaugaNota(int value, Date dataPrimire) throws Exception;
    List<DBNota> citesteNote() throws Exception;
    boolean actualizeazaNota(DBNota nota) throws Exception;
    boolean stergeNota(String id) throws Exception;
}
