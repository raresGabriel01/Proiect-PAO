public class DataBase {
    private String connectionString;
    DataBase(String connectionString){
        this.connectionString = connectionString;
    }

    public void connect() {
        System.out.println("Connecting to " + connectionString + " ...");   // inca nu am invatat despre baze de date
    }
}
