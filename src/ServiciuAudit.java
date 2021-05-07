import java.time.LocalDateTime;
import java.util.Date;

public class ServiciuAudit {
    public static void audit(String actiune, LocalDateTime timestamp) {
        MyFileWriter myFileWriter = MyFileWriter.getInstance();
        myFileWriter.writeTo(String.format("%s,%s\n",actiune,timestamp.toString()) ,"CSV/audit.csv");
    }
}
