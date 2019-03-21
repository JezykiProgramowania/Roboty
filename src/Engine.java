import java.util.ArrayList;




public class Engine implements Runnable {

	private final Object lock = new Object();
   public String name = "";
    
    public volatile boolean hasElement[];
    int stations;
    volatile Worker worker;
    ArrayList<Worker> workerList = new ArrayList<>();
    volatile boolean isActive = false;
    volatile boolean isFree = true;
   
    
    public Engine(int stations, String name) {
        hasElement = new boolean[stations];
        this.stations = stations;
        this.name = name;
    }

    public synchronized boolean[] getElement() {
    	
            return hasElement;
        
    }
    public synchronized void setElement(int index, boolean value) {
    	synchronized(lock) {
    		this.hasElement[index] = value;
    	}
            
      
    }
    public synchronized boolean isActive() {
    		synchronized(lock) {
    			return isActive;
    		}
    }
    public synchronized void setActive(boolean isActive) {
    	synchronized(lock) {
    		this.isActive = isActive;
    	}
    	
    }
    public synchronized boolean isFree() {
    	synchronized(lock) {
    		return isFree;
    	}
    }
    	
    
    public void setFree(boolean isFree) {
    		synchronized(lock) {
    			this.isFree = isFree;
    		}
    }
    
    public void setWorker(Worker worker) {
    	this.worker = worker;
    }
    
    public void run() {
        try {
            while (true) {
            	move();         	
            }
        } catch (InterruptedException e) {  
            System.out.println("Thread " + Thread.currentThread().getName() + " interrupted.");
        }
    }

	public void move() throws InterruptedException {
		
if(isFree() && !hasElement[hasElement.length-1] && hasElement[0]) {
				
				setActive(true);
				setFree(false);
				System.out.println(this.name + ": I'm moving");
				 boolean last = hasElement[hasElement.length-1];
		            System.arraycopy(hasElement, 0, hasElement, 1, hasElement.length-1 );
		            hasElement[0] = last;
				Thread.sleep(4000);
				setFree(true);
				setActive(false);
				System.out.println(this.name + " KONCZY");
		}
	}
}
/*
boolean start = tasma2.hasElement[0];
System.arraycopy(tasma2.hasElement, 1, tasma2.hasElement, 0, tasma2.hasElement.length - 1);
tasma2.hasElement[tasma2.hasElement.length - 1] = start;
*/