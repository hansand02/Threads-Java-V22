import java.io.File;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

public class Oblig5Del2A {
    public static void main(String[] args){
        
        Monitor1 nyMonitor = new Monitor1();
        try {
            File folder = new File(args[0]);
            
            //Lager ny liste med alle filene i en mappe 
            File[] filListe = folder.listFiles();
            


            CountDownLatch latch = new CountDownLatch(filListe.length-1);


            for(File filer: filListe){
                nyMonitor.lagHashMapFraFil(filer, latch);
            }

            latch.await();  //Venter til alle traadene har kjoert ferdig foer vi gaar videre   

        } catch (Exception e) {
          
        } 

        HashMap<String, Subsekvens> nyHash = new HashMap<>();

        for(HashMap<String, Subsekvens> hashMap: nyMonitor.subsekvensRegister.lokaltRegister) {
            HashMap<String, Subsekvens> resultatHash = nyMonitor.subsekvensRegister.SlaaSammenHashMap(hashMap, nyHash);
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
