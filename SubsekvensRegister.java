import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.util.Scanner;

public class SubsekvensRegister {
    ArrayList<HashMap<String,Subsekvens>> LokaltRegister = new ArrayList<>();

    public void SettInnHashMap(HashMap<String,Subsekvens> hashMap){
        LokaltRegister.add(hashMap);
    }

    public HashMap<String,Subsekvens> HentHashMap(int indeks ){
        return LokaltRegister.get(indeks);
    }   

    public int RegisterStoerrelse(){
        return LokaltRegister.size();
    }

    public void lagHashMapFraFil(String filnavn) {
        
        try {

            File data = new File(filnavn);
            Scanner ny = new Scanner(data);
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
            this.SettInnHashMap(NyttHashMap); //Sett kun inn hashmap en gang :) 
            
            ny.close();

        } catch (Exception e) {

            System.out.println("Filen finnes ikke");
            
        }
    }

    public static HashMap<String,Subsekvens> SlaaSammenHashMap(HashMap<String, Subsekvens> hash1, HashMap<String, Subsekvens> hash2){
        HashMap<String, Subsekvens> ny = new HashMap<>();

        for (String items: hash1.keySet()){
            
            Subsekvens x = new Subsekvens( 1, items);
            ny.put(items, x);
        }

        for (String items: hash2.keySet()){
            
            Subsekvens subsekvens = (Subsekvens) hash2.get(items);
            
            if(ny.get(items) != null){
                //Hvis subsekvensen allerede finnes, legg antallet på de to sammen, og endre paa eksisterende subsekvens
                ny.get(items).endreAntallForekomster(ny.get(items).hentAntallForekomster() + subsekvens.hentAntallForekomster());
                // Dette sparer forhaapentligvis litt tid kontra aa lage ett nytt objekt og putte det, eller? 
            }

            else{
                //Hvis ikke special caset over gjelder skal vi bare gjøre det like som for elementene i hash1
                Subsekvens x = new Subsekvens(subsekvens.hentAntallForekomster(), items);
                ny.put(items, x);
            }
            
        }

        return ny;
    }
}

/* class Test{

    public static void main(String[] args) {
        SubsekvensRegister ny = new SubsekvensRegister();
        ny.lagHashMapFraFil("testdata.csv");
        ny.lagHashMapFraFil("testdata2.csv");
        HashMap<String, Subsekvens> tmp = ny.HentHashMap(0);
        HashMap<String, Subsekvens> tmp2 = ny.HentHashMap(1);
        SubsekvensRegister.SlaaSammenHashMap(tmp, tmp2);
    }
}
 */