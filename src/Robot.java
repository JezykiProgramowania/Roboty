


public class Robot implements Runnable {
  
    String name = "";
    int worktime = 0;
    volatile Engine tasma;
    volatile Engine tasma2;
    volatile Worker worker;
    boolean robotType = false;
   volatile boolean isActive = false;
   volatile boolean isActive2 = false;
    private final static Object lock = new Object();
    private final static Object lock2 = new Object();
   
    Robot(String thread, Engine tasma,Engine tasma2, int worktime, boolean robotType/*, Worker worker*/) {
        this.name = thread;
        this.tasma = tasma;
        this.tasma2 = tasma2;
        this.worktime = worktime;
        this.robotType = robotType;
      //  this.worker = worker;
    }
    
    public void place() {
    	synchronized(lock) {
    	if(!tasma.isActive() && tasma.isFree()) {
    		tasma.setFree(false);
    		if(!tasma.getElement()[0]) {
    			try {
            		this.isActive = true;
            		
            		 //System.out.println(this.name + ":  I'm placing an element");   	
                     Thread.sleep(worktime);
                     tasma.setElement(0, true);
                     tasma.setFree(true);
                     this.isActive = false;
                     System.out.println(this.name + " KONCZY");
                     
            	}
                     
                  catch (InterruptedException e) {
                     throw new RuntimeException("Thread interrupted..." + e);
                  }
        	}
    		tasma.setFree(true);
    		}
    		
    	}
    }
    public void take() {
    	synchronized(lock2) {
    		if(!tasma2.isActive() && tasma2.isFree()) {
    			if(tasma2.getElement()[tasma2.hasElement.length-1]) {
    				try {
    					this.isActive2 = true;
                		tasma2.setFree(false);
                		 System.out.println(this.name + ":  I'm removing an element");   	
                         Thread.sleep(worktime);
                         tasma2.setElement(tasma2.hasElement.length-1, false);
                         tasma2.setFree(true);
                         this.isActive2 = false;
                         System.out.println(this.name + " KONCZY");
                         
                	}
                         
                      catch (InterruptedException e) {
                         throw new RuntimeException("Thread interrupted..." + e);
                      }
    			}
    		}
    	}
    }
    
    public boolean isActive() {
    	synchronized(lock) {
    		return isActive;
    	}
    }
    	 public boolean isActive2() {
    		 synchronized(lock2) {
    	    		return isActive2;
    	    	}
    	    	
    }
    	 public void setActive2(boolean isActive2) {
 	    	synchronized(lock2) {
 	    		this.isActive2 = isActive2;
 	    	}
    	 }
    public void run() {
        while (true) {
        	if(robotType) {
        		place();
        	}else {
        		take();
        	}
            
        }
    }

}


