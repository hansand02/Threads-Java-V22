import java.io.File;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.Scanner;

public class Oblig5Hele {
    public static void main(String[] args) {
        Monitor2 monitorIkkeHattVirus = new Monitor2();
        Monitor2 monitorHattVirus = new Monitor2();
        HashMap<String, String> metadataHashMap = new HashMap<>();
        try {
            File folder = new File(args[0]);
            
            //Lager ny liste med alle filene i en mappe 
            File[] filListe = folder.listFiles();
            
            


            for(File filer: filListe){
                if(filer.getName().compareTo("metadata.csv") == 0){
                    Scanner metadata = new Scanner(filer); 
                    while(metadata.hasNext()){
                        String linje = metadata.next();
                        String[] harHatt = linje.split(",");
                        metadataHashMap.put(harHatt[0], harHatt[1]);
                        
                    }
                }
            }

            // tar -1 så man ikke leser metadata. 
            CountDownLatch latch = new CountDownLatch(filListe.length-1);
            

            for(File filer: filListe){
                if(filer.getName().compareTo("metadata.csv") !=  0){
                    
                    if(metadataHashMap.get(filer.getName()).compareTo("True") == 0){
                        monitorHattVirus.lagHashMapFraFil(filer, latch);
                        
                    }
                    if(metadataHashMap.get(filer.getName()).compareTo("False") == 0){
                        
                        monitorIkkeHattVirus.lagHashMapFraFil(filer, latch);
                        
                    }
                }
            }
            
            latch.await();
            //Venter til alle traadene har kjoert ferdig foer vi gaar videre 
            
            CountDownLatch fletteLatch = new CountDownLatch(1);
            CountDownLatch fletteLatch2 = new CountDownLatch(1);
            
            //Lager en loop med lengde aatte for aa faa saa mange traader. 
            for(int i = 0; i <= 8; i++){
                monitorHattVirus.flettHashMaps(fletteLatch);
                monitorIkkeHattVirus.flettHashMaps(fletteLatch2);
            }
            
            
            fletteLatch.await(); // Venter til flettetraadene har flettet hashmapene i monitoren sitt subsekvensregister
            Subsekvens storstedifferanse = null;
            for(HashMap<String, Subsekvens> subsekvens: monitorHattVirus.subsekvensRegister.lokaltRegister){
                for(Subsekvens subsekvensObjekt: subsekvens.values()){
                    /* 
                    Hei beklager dette var her jeg gikk tom for tid. Videre her ville jeg sammenlignet alle subsekvensobjektene i de to 
                    monitorene jeg nå har lagd, og sett hvor det var stoesrt differanse, etter det vil jeg lagt inn stoette for å alltid vise elementer med differanse på over 7 for å løse oppgaven helt. 
                    
                    
                    */
                }
            }


        } catch (Exception e) {
          
        } finally {
            
        }
    }
}
