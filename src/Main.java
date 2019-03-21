import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;


public class Main {
	

	
	public static void main(String[] args) {
		System.out.println("Hello");
		int stations = 3;
		int stations2 = 3;
		final Engine tasma = new Engine(stations, "TASMA1");
		final Engine tasma2 = new Engine(stations, "TASMA2");
		final List<Worker> workerList = new ArrayList<>();
		List<Thread> workerThread = new ArrayList<>();
		for(int i = 0;i < stations; i++) {
			workerList.add(new Worker(tasma, tasma2, "WORKER" + i, i));
			 workerThread.add(new Thread(workerList.get(i)));
		}
		/*
		Worker worker = new Worker(tasma, tasma2, "WORKER1");
	//	Worker worker2 = new Worker(tasma, tasma2, "WORKER2");
		Thread workerT = new Thread(worker);
		//Thread worker2T = new Thread(worker2);
		*/
		
		final Robot robot = new Robot("ROBOT1", tasma, tasma2, 2000, true);
		final Robot robot2 = new Robot("ROBOT2", tasma, tasma2, 2000, true);
		final Robot robot3 = new Robot("ROBOT3", tasma2, tasma2, 2000, false);
		final Robot robot4 = new Robot("ROBOT4", tasma2, tasma2, 2000, false);
		
		
		Thread robotT = new Thread(robot);
		Thread robot2T = new Thread(robot2);
		Thread robot3T = new Thread(robot3);
		Thread robot4T = new Thread(robot4);
		
		//tasma.setWorker(worker);
		Thread tasmaT = new Thread(tasma);
		Thread tasma2T = new Thread(tasma2);
		
		robotT.start();
		robot2T.start();
		robot3T.start();
		robot4T.start();
		workerThread.get(0).start();
		workerThread.get(1).start();


		tasmaT.start();
		tasma2T.start();
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new GUI(robot, robot2, robot3, robot4, workerList, tasma, tasma2);
			}
		});
		
	}

}
