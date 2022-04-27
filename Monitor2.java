import java.util.concurrent.CountDownLatch; 
import java.util.concurrent.locks.*;

public class Monitor2 extends Monitor1 {

    public void flettHashMaps(CountDownLatch fletteLatch){
        
        FletteTrad tmp = new FletteTrad(this, fletteLatch);
        Thread t1 = new Thread(tmp);
        t1.start();
        
        try {
            fletteLatch.await();
           
        } catch (Exception e) {
            
        } finally{
        }
    }
}
