

public class Worker implements Runnable{
	private final static Object lock = new Object();
    public volatile boolean run = true;
    String name ="";
    volatile Engine tasma;
    volatile Engine tasma2;
   
    int position = 0;
      boolean isActive = false;
    volatile boolean placing = false;
   
    public void stop(){
        run = false;
    }
public void setActive(boolean active) {
	synchronized(lock) {
		this.isActive = active;
	}
	
}
    public Worker(Engine tasma, Engine tasma2, String name, int position) {
        this.tasma = tasma;
        this.tasma2 = tasma2;
        this.name = name;
        this.position = position;
    }
    
    public boolean isActive() {
    	synchronized(this) {
    		return this.isActive;
    	}
    	
    }
    public void run() {
        //  System.out.println("Thread started:::" + Thread.currentThread().getName());

        try {
            while (true){
            	work();
            }

        } catch (InterruptedException e) {
            System.out.println("Interruption HANDLED" + e);
        }
        //System.out.println("Thread ended:::" + Thread.currentThread().getName());

    }

	public void work() throws InterruptedException {
		synchronized(lock) {
			if(tasma.isFree() && !tasma.isActive()) {
				
				if(tasma.getElement()[position] && !tasma2.getElement()[0]) {
					setActive(true);
					tasma.setFree(false);
					
					if(!tasma2.isActive()  && tasma2.isFree()) {
						System.out.println( this.name + ": I'm taking this element  to 2nd line");
						
						tasma.setElement(0, false);
						tasma.setFree(true);
						tasma2.setFree(false);
						Thread.sleep(1000);
						placing = true;
						Thread.sleep(8000);
						tasma2.setElement(0, true);
						tasma2.setFree(true);
						placing = false;
						setActive(false);
						System.out.println(this.name + " KONCZY");
					}
					tasma.setFree(true);
			}	
				
			}
		}
		
		
	}

}




