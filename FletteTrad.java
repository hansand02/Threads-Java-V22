
import java.util.concurrent.CountDownLatch;
import java.util.HashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FletteTrad implements Runnable {
    Monitor2 monitor;
    CountDownLatch latch;
    Lock laas = new ReentrantLock();
    
    FletteTrad(Monitor2 monitor, CountDownLatch latch){
        this.monitor = monitor;
        this.latch = latch;

    }

    public void run(){
   
        laas.lock();
        try {
            
            HashMap<String, Subsekvens> totalHash = new HashMap<String, Subsekvens>(); 
            
            if(monitor.subsekvensRegister.RegisterStoerrelse() == 1){
                return;
            }
            for(HashMap<String, Subsekvens> hashMap: monitor.subsekvensRegister.lokaltRegister){
                totalHash = monitor.subsekvensRegister.SlaaSammenHashMap(hashMap, totalHash);
                
            }

            monitor.subsekvensRegister.lokaltRegister.clear();
            
            monitor.SettInnHashMap(totalHash);
            

        } catch (Exception e) {
            e.printStackTrace();

        } finally{
            latch.countDown();
        }
    }

}
