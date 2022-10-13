import java.io.File;
import java.util.concurrent.CountDownLatch;

class Oblig5Del2B{
    public static void main(String[] args){
        
        Monitor2 nyMonitor = new Monitor2();
        
        try {
            File folder = new File(args[0]);
            
            //Lager ny liste med alle filene i en mappe 
            File[] filListe = folder.listFiles();
            
            // tar -1 så man ikke leser metadata. 
            CountDownLatch latch = new CountDownLatch(filListe.length-1);


            for(File filer: filListe){
                nyMonitor.lagHashMapFraFil(filer, latch);  
            }

            latch.await();  //Venter til alle traadene har kjoert ferdig foer vi gaar videre 
            
            CountDownLatch fletteLatch = new CountDownLatch(1);
            
            //Lager en loop med lengde aatte for aa faa saa mange traader. 
            for(int i = 0; i <= 8; i++){
                nyMonitor.flettHashMaps(fletteLatch);
            }
            
            
            fletteLatch.await(); // Venter til flettetraadene har flettet hashmapene i monitoren sitt subsekvensregister

        } catch (Exception e) {
          
        } finally {
            
        }
        
        // Koden herifra og ned sjekker hvilken sekvens som forekommer oftest
        Subsekvens stoersteSekvens = null; 
        
        for(Subsekvens items : nyMonitor.subsekvensRegister.HentHashMap(0).values()){  // Etter fletting vil subsekvensregisteret bare ha ett hashmap
            
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