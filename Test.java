import java.io.File;
import java.util.HashMap;





public class Test {
     
    SubsekvensRegister nytt = new SubsekvensRegister();
    Test(String filnavn){

    
        try {
            File folder = new File(filnavn);
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
        
        System.out.println(stoersteSekvens);
    }
}
class Test2{
    public static void main(String[] args) {
        Test test = new Test("testdatalitenlike");
        Test test2 = new Test("testdatalike");
    }
}
