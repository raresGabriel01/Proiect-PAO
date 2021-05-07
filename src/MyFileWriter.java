import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class MyFileWriter {
    private static MyFileWriter instance = null;

    private MyFileWriter() {

    }

    public void write(String content, String path){
        try{
            FileWriter fw = new FileWriter(path,false);
            fw.write(content);
            fw.close();
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeTo(String content, String path) {
        try{
            FileWriter fw = new FileWriter(path,true);
            fw.write(content);
            fw.close();
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static MyFileWriter getInstance() {
        if(instance == null)
            instance = new MyFileWriter();
        return instance;
    }
}
