import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;


class Update implements Runnable{
	Robot robot;
	Robot robot2;
	Robot robot3;
	Robot robot4;
	Engine tasma,tasma2;
	Worker worker;
	Worker worker2;
	RobotGUI window;
	Container pane;
	
	Update(Robot robot,Robot robot2, Robot robot3, Robot robot4, Worker worker, Worker worker2, Engine tasma, Engine tasma2, Container pane){
	this.robot = robot;
	this.robot2 = robot2;
	this.robot3 = robot3;
	this.robot4 = robot4;
	this.worker = worker;
	this.worker2 = worker2;
	this.tasma = tasma;
	this.tasma2 = tasma2;
	
	this.pane = pane;
	
	
	
	}
	public void run() {
		
		while(true) {
			if(robot.isActive) {
				pane.getComponent(0).setBackground(Color.green);
			}else {
				pane.getComponent(0).setBackground(Color.red);
			}
			if(robot2.isActive) {
				pane.getComponent(1).setBackground(Color.green);
			}else {
				pane.getComponent(1).setBackground(Color.red);
			}
			if(robot3.isActive2) {
				pane.getComponent(2).setBackground(Color.green);
			}else {
				pane.getComponent(2).setBackground(Color.red);
			}
			if(robot4.isActive2) {
				pane.getComponent(3).setBackground(Color.green);
			}else {
				pane.getComponent(3).setBackground(Color.red);
			}
			if(worker.isActive && !worker.placing) {
				pane.getComponent(4).setBackground(Color.green);
			}else if(worker.isActive && worker.placing){
				pane.getComponent(4).setBackground(Color.orange);
			}else if(!worker.isActive) {
				pane.getComponent(4).setBackground(Color.red);
			}
			if(worker2.isActive && !worker2.placing) {
				pane.getComponent(7).setBackground(Color.green);
			}else if(worker2.isActive && worker2.placing){
				pane.getComponent(7).setBackground(Color.orange);
			}else if(!worker2.isActive) {
				pane.getComponent(7).setBackground(Color.red);
			}
			if(tasma.isActive) {
				pane.getComponent(5).setBackground(Color.green);
			}else {
				pane.getComponent(5).setBackground(Color.red);
			}
			if(tasma2.isActive) {
				pane.getComponent(6).setBackground(Color.green);
			}else {
				pane.getComponent(6).setBackground(Color.red);
			}
		}
			}
}

public class RobotGUI {

	public JFrame frame;
	public JTextField txtTama;
	private JTextField txtTasma;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					int stations = 3;
					int stations2 = 3;
					Engine tasma = new Engine(stations, "TASMA1");
					Engine tasma2 = new Engine(stations, "TASMA2");
					List<Worker> workerList = new ArrayList<>();
					List<Thread> workerThread = new ArrayList<>();
					
					Robot robot = new Robot("ROBOT1", tasma, tasma2, 2000, true);
					Robot robot2 = new Robot("ROBOT2", tasma, tasma2, 2000, true);
					Robot robot3 = new Robot("ROBOT3", tasma, tasma2, 2000, false);
					Robot robot4 = new Robot("ROBOT4", tasma, tasma2, 2000, false);
					
					for(int i = 0;i < stations; i++) {
						workerList.add(new Worker(tasma, tasma2, "WORKER" + i, i));
						 workerThread.add(new Thread(workerList.get(i)));
						 
					}
					Thread robotT = new Thread(robot);
					Thread robot2T = new Thread(robot2);
					Thread robot3T = new Thread(robot3);
					Thread robot4T = new Thread(robot4);
					
				
					Thread tasmaT = new Thread(tasma);
					Thread tasma2T = new Thread(tasma2);
					
					robotT.start();
					//robot2T.start();
					robot3T.start();
					robot4T.start();
					workerThread.get(0).start();
					workerThread.get(1).start();

					tasmaT.start();
					tasma2T.start();
					///////
					RobotGUI window = new RobotGUI();
					window.frame.setVisible(true);
					Update up = new Update(robot,robot2, robot, robot4, workerList.get(0), workerList.get(1), tasma, tasma2, window.frame.getContentPane());
					Thread upT = new Thread(up);
					upT.start();
					//window.frame.getContentPane().getComponent(0).getName();
					//window.frame.getContentPane().getComponent(1).setBackground(Color.black);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}

	/**
	 * Create the application.
	 */
	public RobotGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		
		
		////////////////////////
		frame = new JFrame();
		frame.setBounds(100, 100, 786, 427);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
	    JTextArea txtrRobot = new JTextArea();
	    txtrRobot.addPropertyChangeListener(new PropertyChangeListener() {
	    	public void propertyChange(PropertyChangeEvent arg0) {
	    	}
	    });
		
		
		txtrRobot.setFont(new Font("Monospaced", Font.PLAIN, 19));
		txtrRobot.setTabSize(13);
		txtrRobot.setBackground(Color.RED);
		txtrRobot.setText("Robot1");
		txtrRobot.setBounds(75, 31, 89, 44);
		frame.getContentPane().add(txtrRobot);
		
		JTextArea txtrRobot_1 = new JTextArea();
		txtrRobot_1.setText("Robot2");
		txtrRobot_1.setTabSize(13);
		txtrRobot_1.setFont(new Font("Monospaced", Font.PLAIN, 19));
		txtrRobot_1.setBackground(Color.RED);
		txtrRobot_1.setBounds(189, 31, 89, 44);
		
		frame.getContentPane().add(txtrRobot_1);
		
		JTextArea txtrRobot_2 = new JTextArea();
		txtrRobot_2.setText("Robot3");
		txtrRobot_2.setTabSize(13);
		txtrRobot_2.setFont(new Font("Monospaced", Font.PLAIN, 19));
		txtrRobot_2.setBackground(Color.RED);
		txtrRobot_2.setBounds(82, 260, 89, 44);
		frame.getContentPane().add(txtrRobot_2);
		
		JTextArea txtrRobot_3 = new JTextArea();
		txtrRobot_3.setText("Robot4");
		txtrRobot_3.setTabSize(13);
		txtrRobot_3.setFont(new Font("Monospaced", Font.PLAIN, 19));
		txtrRobot_3.setBackground(Color.RED);
		txtrRobot_3.setBounds(206, 259, 89, 44);
		frame.getContentPane().add(txtrRobot_3);
		
		JTextArea txtrWorker = new JTextArea();
		txtrWorker.setText("worker");
		txtrWorker.setTabSize(13);
		txtrWorker.setFont(new Font("Monospaced", Font.PLAIN, 19));
		txtrWorker.setBackground(Color.RED);
		txtrWorker.setBounds(75, 123, 89, 44);
		frame.getContentPane().add(txtrWorker);
		
		txtTama = new JTextField();
		txtTama.setBackground(Color.RED);
		txtTama.setText("TASMA");
		txtTama.setBounds(75, 88, 349, 22);
		frame.getContentPane().add(txtTama);
		txtTama.setColumns(10);
		
		txtTasma = new JTextField();
		txtTasma.setText("TASMA2");
		txtTasma.setColumns(10);
		txtTasma.setBackground(Color.RED);
		txtTasma.setBounds(75, 199, 349, 22);
		frame.getContentPane().add(txtTasma);
		
		JTextArea txtrWorker_1 = new JTextArea();
		txtrWorker_1.setText("worker2");
		txtrWorker_1.setTabSize(13);
		txtrWorker_1.setFont(new Font("Monospaced", Font.PLAIN, 19));
		txtrWorker_1.setBackground(Color.RED);
		txtrWorker_1.setBounds(189, 123, 89, 44);
		frame.getContentPane().add(txtrWorker_1);
	}
}
