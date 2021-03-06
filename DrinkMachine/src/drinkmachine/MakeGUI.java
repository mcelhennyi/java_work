package drinkmachine;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.Timer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;


public class MakeGUI extends JFrame
{

	///////////
	//Objects//
	///////////
	EditGUI editGUI;
	Canvas canvas = new Canvas();
	JProgressBar progressBar = new JProgressBar();
	JLabel drinkProgBarLabel = new JLabel("Drink Progress:");
	JLabel doneLabel = new JLabel("Done!");
	JComboBox comboBox = new JComboBox();
	JButton makeDrinkButton = new JButton("Make Drink");
	JButton backButton = new JButton("Back");
	JMenuBar menuBar = new JMenuBar();
	FileWorker fileWorker = new FileWorker();


	private int pumpCount = 8;
	private String[] drinkList = new String[500];
	private String makeDrinkName;
	private double[] mixerAmount = new double[pumpCount];
	private int pwmDataArraySize = 26;
	private double[] pwmDataFlowRate = fileWorker.importFlowRateDataFile(pwmDataArraySize, "PumpData1.txt");
	private int[] pwmDataPwm = fileWorker.importPwmRateDataFile(pwmDataArraySize, "PumpData1.txt");	
	private double flowRate = pwmDataFlowRate[0]; //  second per oz
	private int totalTime;
	private String comPort;


	public MakeGUI()
	{


	}

	public MakeGUI(String comPort)
	{
		this.comPort = comPort;
		MakeListener makeListener = new MakeListener();
		BackListener backListener = new BackListener();

		//canvas
		canvas.setBackground(new Color(255, 153, 102));
		canvas.setBounds(0, 0, 1000, 208);
		//progress bar
		progressBar.setToolTipText("Just wait a second, Damn...");
		progressBar.setBounds(229, 455, 529, 20);

		//Labels
		drinkProgBarLabel.setBounds(229, 434, 117, 16);
		doneLabel.setEnabled(false);
		doneLabel.setBounds(697, 487, 61, 16);
		//combo box
		getDrinkList();//populate drink list
		comboBox.setToolTipText("Pick a drink from this list");
		comboBox.setModel(new DefaultComboBoxModel(drinkList));
		comboBox.setBounds(395, 214, 210, 30);

		///////////
		//Buttons//
		///////////
		//make
		makeDrinkButton.setToolTipText("Click here to make selected drink.");
		makeDrinkButton.setBounds(395, 401, 210, 29);
		makeDrinkButton.addActionListener(makeListener);
		//back
		backButton.setBounds(900, 545, 80, 30);
		backButton.addActionListener(backListener);




		///////
		//add//
		///////
		getContentPane().add(canvas);
		//		getContentPane().add(progressBar);
		//		getContentPane().add(drinkProgBarLabel);
		//		getContentPane().add(doneLabel);
		getContentPane().add(comboBox);
		getContentPane().add(makeDrinkButton);
		getContentPane().add(backButton);


		////////////
		//Menu bar//
		////////////
		setJMenuBar(menuBar);
	}

	public void setComPort(String comPort)
	{
		this.comPort = comPort;
//		System.out.println("Make gui comport: "  + this.comPort);
	}
	
	public void getDrinkList()
	{
		//search file for existing drinks
		//import drink names
		//add "Select A Drink to Edit:" to the front
		//add an add option to the end of the drop down
		drinkList[0] = "Select A Drink to Make:";
		String[] tempString = fileWorker.getAllDrinkNames();
		for(int x = 0; x<tempString.length; x++) 
		{
			drinkList[x+1] = tempString[x];
		}
	}
	class MakeListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			//are you sure you want to make "" drink?
			if(comboBox.getSelectedItem() == null || comboBox.getSelectedItem().toString() == "Select A Drink to Make:")
			{
				JOptionPane.showMessageDialog(null, "Select a drink first!");
			}
			else
			{
				int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to make " + comboBox.getSelectedItem().toString() + "?");
				if(option == 0)
				{
					//get the name of the drink selected
					makeDrinkName = comboBox.getSelectedItem().toString();
					//find it in the file and pull out each amount of mixer
					getMixerAmount();
					//encode drink into string for arduino
					//					encodeString();
					//send to arduino
					ComWithArduino(encodeString());
					//					progressBar.setMaximum(totalTime);
					//repaint();
					//recieve message from arduino saying complete
					//log mixers amount used
					LogDrink();
					try 
					{
						Thread.sleep(totalTime  + 1000);
//						JOptionPane.showMessageDialog(null, "Drink is Finished");
					}
					catch (InterruptedException ie) 
					{
						JOptionPane.showMessageDialog(null, "ERROR IN DRINK TIMER: " + ie.toString());
					}

				}
			}
			//Things to add:
			//add a drink storage file that
		}

	}
	public String encodeString()
	{
		double bigMixer;
		int[] pwmArray = new int[pumpCount];
		int indexOfBase;
		double desiredFlowRate; 
		String messageToArduino = "";


		bigMixer = mixerAmount[0];
		indexOfBase = 0;
		//find largest mixer to use as total time based on flow rate of 255 pwm 
		for(int x = 1; x <= pumpCount-1; x++)
		{
			if(mixerAmount[x] > bigMixer)
			{
				bigMixer = mixerAmount[x];
				indexOfBase = x;
			}
		}
		//translate the oz of the biggest mixer to total time for all pumps
		totalTime = (int) Math.round(bigMixer * flowRate);

		//loop through each oz per drink, skipping the index that is used as base, find the flow rate needed by Oz of liquid needed 
		//divided by the totalTime, then use that number to search/loop through the pwm rate chart to find the best pwm for that amount.
		for(int x = 0; x <= pumpCount-1; x++)
		{
			//			System.out.println("maker");
			//
			//			System.out.println(mixerAmount[x]);
			if(mixerAmount[x] == 0)
			{
				pwmArray[x] = 0;
			}
			else if(x != indexOfBase)
			{
				desiredFlowRate = totalTime/mixerAmount[x];

				double diff = 100;
				for(int i = 0; i <= pwmDataArraySize-1; i++)
				{
					if(Math.abs(desiredFlowRate - pwmDataFlowRate[i]) < diff)
					{
						diff = Math.abs(desiredFlowRate - pwmDataFlowRate[i]);
						pwmArray[x] = pwmDataPwm[i];
					}
				}
			}
			else
			{
				pwmArray[x] = 255;
			}
		}

		//format the string to be sent: ":totalTime/pwm1/pwm2/pwmx:
		for(int x = 0; x <= pumpCount; x++)
		{
			if(x == 0)
			{
				messageToArduino = "00:" + Integer.toString(totalTime);
			}			
			else if(x == pumpCount)
			{
				messageToArduino = messageToArduino + "/" + Integer.toString(pwmArray[x-1]) + ":00";
			}
			else
			{
				messageToArduino = messageToArduino + "/" + Integer.toString(pwmArray[x-1]);
			}
		}
		System.out.println(comboBox.getSelectedItem().toString());
		System.out.println(messageToArduino);
		return messageToArduino;
	}
	//retrieves an array of integers representing oz of each mixer for a specific drink in order of list in the change mixer combo box
	public void getMixerAmount()
	{
		mixerAmount = fileWorker.getDrinkMakeUp(makeDrinkName);

	}
	//logs a file of drinks made for keeping track of the mixer levels
	public void LogDrink()
	{
		String loggerString = "";

		//get the name of drink
		//get fluid levels of drink
		//get the pwm used for each fluid of the drink
		//get total time for drink











		fileWorker.logDrink(loggerString);
	}
	//communicates a string to the arduino
	public void ComWithArduino(String code)
	{
		ArduinoCom arduinoCom = new ArduinoCom();//creates object of ArduinoCom
//		ArduinoCom arduinoCom = new ArduinoCom(comPort);//creates object of ArduinoCom
//		arduinoCom.setComPort(comPort);
		arduinoCom.setDrinkString(code);//sets the string to send to arduino
		arduinoCom.ArduinoComStart();//initiates the communication to the arduino
	}

	class BackListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{

			dispose();
		}
	}


	public void MakeADrink()
	{
		MakeGUI frame = new MakeGUI(comPort);
//		frame.setComPort(comPort);
		frame.setTitle("Al the Drink Machine");
		frame.getContentPane().setBackground(new Color(135, 206, 250));
		frame.setBounds(100, 100, 1000, 625);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		frame.setVisible(true);




		//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}
}
