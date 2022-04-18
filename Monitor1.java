import java.util.HashMap;
import java.io.File;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.*;

public class Monitor1 {
    
    SubsekvensRegister subsekvensRegister = new SubsekvensRegister();
    Lock laas = new ReentrantLock();
    

    public void SettInnHashMap(HashMap<String,Subsekvens> hashMap){
        laas.lock();
        try {
            
            subsekvensRegister.SettInnHashMap(hashMap);  
            
        } catch (Exception e) {
            System.out.println("Feil");
        } finally{
            laas.unlock();
        } 
    }

    public void lagHashMapFraFil(File data, CountDownLatch Latch) {
        LeseTrad nyLeseTrad = new LeseTrad(data, this, Latch); 
        Thread t1 = new Thread(nyLeseTrad);
        t1.start(); 
        
    }
}