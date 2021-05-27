/*import javax.xml.crypto.Data;
import java.sql.*;
import java.util.Date;

public class JDBC implements AutoCloseable, IDBCRUD{

    private Connection connection;

    public JDBC() throws SQLException {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        }catch(Exception e) {
            e.printStackTrace();
        }

        this.connection = DriverManager.getConnection("jdbc:derby:catalogDB;create=true");
        String tablesNotFound = "";
        String tabele = "note;examene;studenti;cursuri;profesori;tabela_asociativa";
        ResultSet results = connection.getMetaData().getTables(null, null, null, new String[]{"TABLE"});
        while(results.next()){

            for(String tabel : tabele.split(";")){
                if(tabel.equalsIgnoreCase(results.getString("TABLE_NAME"))){
                    tablesNotFound += tabel + ';';
                }
            }

        }

        for(String tabelInexistent : tablesNotFound.split(";")) {
            if(tabelInexistent.equalsIgnoreCase("note")){
                this.connection.createStatement()
                        .execute("CREATE TABLE note (id char(36) primary key, valoare int, dataPrimire DATETIME)");
            }

            else if (tabelInexistent.equalsIgnoreCase("examene")){
                this.connection.createStatement()
                        .execute("CREATE TABLE examene( id (char 36) primary key, data DATETIME, " +
                                "idNota char(36)," +
                                "CONSTRAINT idNota FOREIGN KEY (idNota) REFERENCES note(id) " +
                                " ON DELETE CASCADE ON UPDATE CASCADE )");
            }

            else if (tabelInexistent.equalsIgnoreCase("studenti")) {
                this.connection.createStatement()
                        .execute("CREATE TABLE studenti (id char(36) primary key, nume char (36), prenume char(36), " +
                                "domeniu char(36), an int)");
            }

            else if(tabelInexistent.equalsIgnoreCase("cursuri")) {
                this.connection.createStatement()
                        .execute("CREATE TABLE cursuri(id char(36) primary key, nume char(36), nrCredite int, idExamen char(36)," +
                                "CONSTRAINT idExamen FOREIGN KEY (idExamen) REFERENCES examene(id) " +
                                "ON DELETE CASCADE ON UPDATE CASCADE)");
            }

            else if (tabelInexistent.equalsIgnoreCase("profesori")) {
                this.connection.createStatement()
                        .execute("CREATE TABLE profesori(id char(36) primary key, nume char(36), prenume char(36), " +
                                "titlu char(36), idCurs char(36), " +
                                "CONSTRAINT idCurs FOREIGN KEY (idCurs) REFERENCES cursuri(id)" +
                                "ON DELETE CASCADE ON UPDATE CASCADE)");
            }

            else {
                this.connection.createStatement()
                        .execute("CREATE TABLE tabela_asociativa (idStudent char(36), idCurs char(36)," +
                                "CONSTRAINT idStudent FOREIGN KEY (idStudent) REFERENCES studenti(id)," +
                                "CONSTRAINT idCurs FOREIGN KEY (idCurs) REFERENCES cursuri(id) )");
            }
        }

        //if(notFound){
        //    connection.createStatement()
        //            .execute("CREATE TABLE note (id char(36) primary key, valoare int, dataPrimire DATETIME)");
        //}

    }

    public DBNota adaugaNota(int value, Date dataPrimire) throws Exception {
        DBNota myNota = new DBNota(value,dataPrimire);
        PreparedStatement statement = connection.prepareStatement("INSERT INTO note (id, valoare, data) VALUES (?,?,?)");
        statement.setString(1,myNota.getId());
        statement.setString(2,myNota.getValue());
        statement.setString(3,myNota.getDataPrimire());

        if(statement.executeUpdate() == 1) {
            return myNota;
        }
        return null;
    }

    public List<DBNota> citesteNote() throws Exception {
        List<DBNota> note = new ArrayList<>();
        ResultSet results = this.connection.createStatement.executeQuery("select * from note");
        while(results.next()) {
            note.add(new DBNota(results.getString(1), Integer.parseInt(results.getString(2)), Date(results.getString(3));
        }
        return note;
    }

    public boolean updateNota()

    @Override
    public void close() throws Exception {
        this.connection.close();
    }
}
*/