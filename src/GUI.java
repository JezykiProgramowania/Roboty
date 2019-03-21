import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;


public class GUI extends JFrame{

	private JFrame frame;
	Robot robot, robot2, robot3, robot4;
	List<Worker> workerList;
	Engine tasma, tasma2;
	
	public GUI(final Robot robot, final Robot robot2, final Robot robot3, final Robot robot4, final List<Worker> workerList, final Engine tasma, final Engine tasma2) {
			super("Linia Produkcyjna");
			
		
		setSize(600,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		JButton magBtn = new JButton("Click me");
		magBtn.setBounds(0, 428, 582, 25);
		magBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		getContentPane().setLayout(null);
		getContentPane().add(magBtn);
		
		final JTextArea txtrRobot = new JTextArea();
		txtrRobot.setBackground(Color.RED);
		txtrRobot.setText("ROBOT1");
		txtrRobot.setFont(new Font("Monospaced", Font.PLAIN, 19));
		txtrRobot.setBounds(39, 34, 123, 61);
		getContentPane().add(txtrRobot);
		
		final JTextArea txtrRobot_3 = new JTextArea();
		txtrRobot_3.setText("ROBOT2");
		txtrRobot_3.setFont(new Font("Monospaced", Font.PLAIN, 19));
		txtrRobot_3.setBackground(Color.RED);
		txtrRobot_3.setBounds(199, 34, 123, 61);
		getContentPane().add(txtrRobot_3);
		
		final JTextArea txtrRobot_1 = new JTextArea();
		txtrRobot_1.setText("ROBOT3");
		txtrRobot_1.setFont(new Font("Monospaced", Font.PLAIN, 19));
		txtrRobot_1.setBackground(Color.RED);
		txtrRobot_1.setBounds(39, 283, 123, 61);
		getContentPane().add(txtrRobot_1);
		
		final JTextArea txtrRobot_2 = new JTextArea();
		txtrRobot_2.setText("ROBOT4");
		txtrRobot_2.setFont(new Font("Monospaced", Font.PLAIN, 19));
		txtrRobot_2.setBackground(Color.RED);
		txtrRobot_2.setBounds(199, 283, 123, 61);
		getContentPane().add(txtrRobot_2);
		
		final JTextArea txtrTasma = new JTextArea();
		txtrTasma.setText("TASMA1");
		txtrTasma.setFont(new Font("Monospaced", Font.PLAIN, 19));
		txtrTasma.setBackground(Color.RED);
		txtrTasma.setBounds(39, 108, 288, 25);
		getContentPane().add(txtrTasma);
		
		final JTextArea txtrTasma_1 = new JTextArea();
		txtrTasma_1.setText("TASMA2");
		txtrTasma_1.setFont(new Font("Monospaced", Font.PLAIN, 19));
		txtrTasma_1.setBackground(Color.RED);
		txtrTasma_1.setBounds(39, 245, 288, 25);
		getContentPane().add(txtrTasma_1);
		
		final JTextArea txtrWorker = new JTextArea();
		txtrWorker.setText("WORKER1");
		txtrWorker.setFont(new Font("Monospaced", Font.PLAIN, 19));
		txtrWorker.setBackground(Color.RED);
		txtrWorker.setBounds(39, 145, 123, 61);
		getContentPane().add(txtrWorker);
		
		final JTextArea txtrWorker_1 = new JTextArea();
		txtrWorker_1.setText("WORKER2");
		txtrWorker_1.setFont(new Font("Monospaced", Font.PLAIN, 19));
		txtrWorker_1.setBackground(Color.RED);
		txtrWorker_1.setBounds(177, 146, 123, 61);
		getContentPane().add(txtrWorker_1);
		
		
		Thread thread = new Thread(){
		    public void run(){
		    	while(true) {
					if(robot.isActive) {
						txtrRobot.setBackground(Color.GREEN);
					}else {
						txtrRobot.setBackground(Color.RED);
					}
					if(robot2.isActive) {
						txtrRobot_3.setBackground(Color.GREEN);
					}else {
						txtrRobot_3.setBackground(Color.RED);
					}
					if(robot3.isActive2) {
						txtrRobot_1.setBackground(Color.GREEN);
					}else {
						txtrRobot_1.setBackground(Color.RED);
					}
					if(robot4.isActive2) {
						txtrRobot_2.setBackground(Color.GREEN);
					}else {
						txtrRobot_2.setBackground(Color.RED);
					}
					if(workerList.get(0).isActive() && !workerList.get(0).placing) {
						txtrWorker.setBackground(Color.GREEN);
					}else if(workerList.get(0).isActive() && workerList.get(0).placing){
						txtrWorker.setBackground(Color.ORANGE);
					}else if(!workerList.get(0).isActive()) {
						txtrWorker.setBackground(Color.RED);
					}
					if(workerList.get(1).isActive && !workerList.get(1).placing) {
						txtrWorker_1.setBackground(Color.GREEN);
					}else if(workerList.get(1).isActive && workerList.get(1).placing){
						txtrWorker_1.setBackground(Color.ORANGE);
					}else if(!workerList.get(1).isActive) {
						txtrWorker_1.setBackground(Color.RED);
					}
					if(tasma.isActive) {
						txtrTasma.setBackground(Color.GREEN);
					}else {
						txtrTasma.setBackground(Color.RED);
					}
					if(tasma2.isActive) {
						txtrTasma_1.setBackground(Color.GREEN);
					}else {
						txtrTasma_1.setBackground(Color.RED);
					}
				}
		    }
		  };
		  thread.start();
		}
	
}
