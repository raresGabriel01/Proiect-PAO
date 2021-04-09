import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FileWriter {
    public void write(String content, String path) {
        // something like OS.wirteTo(path....)
    }

    public static String read(String path){
        File file = new File(path);
        try {
            BufferedReader bf = new BufferedReader(new FileReader(file));
            String myFileContent = "";
            String s;

            while((s = bf.readLine()) != null){
                myFileContent = myFileContent + s + "\n";
            }

            return myFileContent;
        }catch(Exception e) {
            return e.getMessage();
        }



    }
}
