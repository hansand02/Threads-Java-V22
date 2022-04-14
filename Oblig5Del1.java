import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class Oblig5Del1 {
     
    // Lag nytt register for testobjektet
    SubsekvensRegister nytt = new SubsekvensRegister();
    Oblig5Del1(String filnavn){

    
        try {
            File folder = new File(filnavn);
        
            //Lager ny liste med alle filene i en mappe 
            File[] filListe = folder.listFiles();
            
            for(File filer: filListe){
                nytt.lagHashMapFraFil(filer);
            }

        } 
        
        catch (Exception e) {
            System.out.println("Filen finnes ikke");
        }
        
        HashMap<String, Subsekvens> nyHash = new HashMap<>();
        
        for(HashMap<String, Subsekvens> hashMap: nytt.LokaltRegister) {
            
            HashMap<String, Subsekvens> resultatHash = nytt.SlaaSammenHashMap(hashMap, nyHash);
            nyHash = resultatHash;
        }
        
        Subsekvens stoersteSekvens = null; 
        for(Subsekvens items : nyHash.values()){
            
            if(stoersteSekvens != null){
            
                if( stoersteSekvens.hentAntallForekomster() < items.hentAntallForekomster()){
                    stoersteSekvens = items;
                }
            }

            else if( stoersteSekvens == null){
                stoersteSekvens = items;
            }
        }
        
        System.out.println(stoersteSekvens + " forekommer flest ganger");
    }
}

class Test2{
    public static void main(String[] args) {
        Oblig5Del1 test = new Oblig5Del1(args[0]);
    }
}
