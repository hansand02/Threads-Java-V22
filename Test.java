import java.io.File;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Test {
     
    SubsekvensRegister nytt = new SubsekvensRegister();
    Test(String filnavn){

    
        try {
            File folder = new File(filnavn);
            File[] filListe = folder.listFiles();
            
            for(File filer: filListe){
                System.out.println(filer);
            }

        } 
        
        catch (Exception e) {
            System.out.println("Filen finnes ikke");
        }
    }
}

class Test2{
    public static void main(String[] args) {
        Test test = new Test("testdatalitenlike");
        Test test2 = new Test("testdatalike");
    }
}
