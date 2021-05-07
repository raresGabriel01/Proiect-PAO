import java.io.File;
import java.util.Scanner;

public class MyFileReader {
    private static MyFileReader instance = null;
    private MyFileReader() {

    }

    public String read(String path) {
        String myContent = "";
        try {
            File myFile = new File(path);
            Scanner myScanner = new Scanner(myFile);
            while(myScanner.hasNextLine()){
                myContent += myScanner.nextLine();
                myContent += "\n";
            }
            myScanner.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        return myContent;
    }

    public static MyFileReader getInstance() {
        if(instance == null)
            instance = new MyFileReader();
        return instance;
    }
}
