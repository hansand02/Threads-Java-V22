import java.io.File;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.HashMap;

public class LeseTrad implements Runnable {
    Monitor1 monitor;
    File fil;
    CountDownLatch latch;

    public LeseTrad(File fil, Monitor1 monitor, CountDownLatch latch){
        this.monitor = monitor;
        this.fil = fil;
        this.latch = latch; 
    }

    public void run(){

        if(fil.getName().compareTo("metadata.csv") == 0){
            return;
        }
        
        try {

            
            Scanner ny = new Scanner(fil);
            String linje; 
            HashMap<String, Subsekvens> NyttHashMap = new HashMap<>();

            while(ny.hasNext()){
                linje = ny.next();
                int linjelengde = linje.length();

                
                for(int i = 0; i<linjelengde -2; i++){
                    
                    // Maa legge inn character.tostring for å få string som output og ikke integer her.
                    String sekvens = (Character.toString(linje.charAt(i)) + Character.toString(linje.charAt(i+1)) + Character.toString(linje.charAt(i+2))  );
                    //Lag nytt subsekvensobjekt og legge dette til det lokale hashmapet. 
                    Subsekvens sekvensObjekt = new Subsekvens(1, sekvens);
                    NyttHashMap.put(sekvens, sekvensObjekt);

                    
                }
            }
            
            this.monitor.SettInnHashMap(NyttHashMap); //Sett kun inn hashmap en gang :) 
            ny.close();                                 
            latch.countDown(); // Tell ned etter man har satt hashmappet inn i subsekvensregisteret

        } catch (Exception e) {

            System.out.println("Filen finnes ikke");
            
        }
    }
}
