import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.concurrent.locks;

public class monitor1 {

    SubsekvensRegister subsekvensRegister = new SubsekvensRegister();
    Lock laas = new ReentrantLock();

    public void SettInnHashMap(HashMap<String,Subsekvens> hashMap){
        laas.lock();
        try {
            subsekvensRegister.SettInnHashMap(hashMap);
        } catch (Exception e) {
            
        } finally{
            laas.unlock();
        } 
    }

    public void lagHashMapFraFil(File data) {
        LeseTrad nyLeseTrad = new LeseTrad(data, this); 
    }
}
