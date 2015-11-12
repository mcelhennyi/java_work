package drinkmachine;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
//////////////////////////////////////////////
//This is the edit gui where drink is edited//
//////////////////////////////////////////////

public class EditGUI extends JFrame
{
	EditGUI frame;

	private String drinkNameEditing;
	private String password;
	private boolean noProtection = true;
	private String[] checkedMixer;
	private int[] mixerAmtNum;
	private int totalAmtNum;
	private int cupSizeAmtNum = 16;
	private String wholeDrink;

	//////////////////
	//Create Objects//
	//////////////////
	//create object of FIleHandler
	private FileWorker fileWorker = new FileWorker();
	
	private JMenuBar menuBar = new JMenuBar();

	private JTextField drinkName = new JTextField();
	private JLabel nameDrinkLabel = new JLabel("Drink Name");
	private JTextField mixer1Amt = new JTextField();
	private JTextField mixer2Amt = new JTextField();
	private JTextField mixer3Amt = new JTextField();
	private JTextField mixer4Amt = new JTextField();
	private JTextField mixer5Amt = new JTextField();
	private JTextField mixer6Amt = new JTextField();
	private JTextField mixer7Amt = new JTextField();
	private JTextField mixer8Amt = new JTextField();
	private JTextField totalAmt = new JTextField();
	private JTextField cupSizeAmt = new JTextField();

	private JButton backButton = new JButton("Back");
	private JButton doneButton = new JButton("Done");

	private JLabel cupSizeLabel = new JLabel("Cup Size:");

	private JLabel ozLabel1 = new JLabel("Oz");
	private JLabel ozLabel2 = new JLabel("Oz");
	private JLabel ozLabel3 = new JLabel("Oz");
	private JLabel ozLabel4 = new JLabel("Oz");
	private JLabel ozLabel5 = new JLabel("Oz");
	private JLabel ozLabel6 = new JLabel("Oz");
	private JLabel ozLabel7 = new JLabel("Oz");
	private JLabel ozLabel8 = new JLabel("Oz");
	private JLabel ozLabel9 = new JLabel("Oz");
	private JLabel ozLabel10 = new JLabel("Oz");

	private JPasswordField drinkChangePass = new JPasswordField();

	private JLabel drinkPassLabel = new JLabel("Enter Password:");
	private JLabel selectMixerLabel = new JLabel("Select Mixers:");

	private JCheckBox mixer1Label = new JCheckBox();
	private JCheckBox mixer2Label = new JCheckBox();
	private JCheckBox mixer3Label = new JCheckBox();
	private JCheckBox mixer4Label = new JCheckBox();
	private JCheckBox mixer5Label = new JCheckBox();
	private JCheckBox mixer6Label = new JCheckBox();
	private JCheckBox mixer7Label = new JCheckBox();
	private JCheckBox mixer8Label = new JCheckBox();

	private JLabel totalLabel = new JLabel("Total:");

	private JLabel blank = new JLabel("");

	private JLabel cupSizeError = new JLabel("Error! Cup is too small.");


	public EditGUI()
	{
		
	}

	public EditGUI(String drinkNameEditing)
	{
		this.drinkNameEditing = drinkNameEditing;
		//		System.out.println(this.drinkNameEditing);

		////////////
		//Listener//
		////////////
		BackListener backListener = new BackListener();
		DoneListener doneListener = new DoneListener();


		///////////
		//buttons//
		///////////
		backButton.setBounds(431, 280, 63, 29);
		doneButton.setBounds(366, 280, 63, 29);

		/////////////////////
		//mixer check boxes//
		/////////////////////
		//label available mixers
		mixer1Label.setText(fileWorker.getMixersNoVol()[0]);
		mixer2Label.setText(fileWorker.getMixersNoVol()[1]);
		mixer3Label.setText(fileWorker.getMixersNoVol()[2]);
		mixer4Label.setText(fileWorker.getMixersNoVol()[3]);
		mixer5Label.setText(fileWorker.getMixersNoVol()[4]);
		mixer6Label.setText(fileWorker.getMixersNoVol()[5]);
		mixer7Label.setText(fileWorker.getMixersNoVol()[6]);
		mixer8Label.setText(fileWorker.getMixersNoVol()[7]);
		//place check boxes
		mixer1Label.setBounds(170, 22, 128, 23);
		mixer2Label.setBounds(170, 50, 128, 23);		
		mixer3Label.setBounds(170, 78, 128, 23);		
		mixer4Label.setBounds(170, 106, 128, 23);		
		mixer5Label.setBounds(170, 134, 128, 23);				
		mixer6Label.setBounds(170, 162, 128, 23);		
		mixer7Label.setBounds(170, 190, 128, 23);		
		mixer8Label.setBounds(170, 218, 128, 23);

		//drink name editing
		nameDrinkLabel.setBounds(31, 20, 80, 16);
		drinkName.setText(drinkNameEditing);
		drinkName.setBounds(6, 34, 134, 28);
		drinkName.setColumns(10);

		//Mixer amount editing
		//1
		mixer1Amt.setText("0");
		mixer1Amt.setBounds(300, 20, 35, 28);
		mixer1Amt.setColumns(10);
		//2
		mixer2Amt.setText("0");
		mixer2Amt.setColumns(10);
		mixer2Amt.setBounds(300, 48, 35, 28);
		//3
		mixer3Amt.setText("0");
		mixer3Amt.setColumns(10);
		mixer3Amt.setBounds(300, 76, 35, 28);
		//4
		mixer4Amt.setText("0");
		mixer4Amt.setColumns(10);
		mixer4Amt.setBounds(300, 104, 35, 28);
		//5
		mixer5Amt.setText("0");
		mixer5Amt.setColumns(10);
		mixer5Amt.setBounds(300, 132, 35, 28);
		//6
		mixer6Amt.setText("0");
		mixer6Amt.setColumns(10);
		mixer6Amt.setBounds(300, 160, 35, 28);
		//7
		mixer7Amt.setText("0");
		mixer7Amt.setColumns(10);
		mixer7Amt.setBounds(300, 188, 35, 28);
		//8
		mixer8Amt.setText("0");
		mixer8Amt.setColumns(10);
		mixer8Amt.setBounds(300, 216, 35, 28);

		//total amount of mixer label
		totalAmt.setText("0");
		totalAmt.setColumns(10);
		totalAmt.setBounds(300, 244, 35, 28);
		totalAmt.setEditable(false);


		//cup size section
		cupSizeLabel.setBounds(16, 74, 61, 16);
		cupSizeAmt.setText("0");
		cupSizeAmt.setBounds(76, 68, 35, 28);
		cupSizeAmt.setColumns(10);

		//oz labels
		ozLabel1.setBounds(113, 74, 27, 16);//cup size	
		ozLabel2.setBounds(339, 26, 27, 16);//mixer 		
		ozLabel3.setBounds(339, 54, 27, 16);//mixer		
		ozLabel4.setBounds(339, 82, 27, 16);//mixer		
		ozLabel5.setBounds(339, 110, 27, 16);//mixer		
		ozLabel6.setBounds(339, 138, 27, 16);//mixer		
		ozLabel7.setBounds(339, 166, 27, 16);//mixer		
		ozLabel8.setBounds(339, 194, 27, 16);//mixer		
		ozLabel9.setBounds(339, 222, 27, 16);//mixer		
		ozLabel10.setBounds(339, 250, 27, 16);//total mixer


		//Password
		drinkChangePass.setBounds(366, 244, 128, 28);
		drinkPassLabel.setBounds(372, 228, 128, 16);

		//label above mixers 
		selectMixerLabel.setBounds(170, 6, 90, 16);

		//total of mixers
		totalLabel.setBounds(199, 250, 61, 16);

		//weird formatting fix
		blank.setBounds(199, 315, 61, 16);

		//move to a listener or method only active if the cup doesnt maatch
		cupSizeError.setEnabled(true);
		cupSizeError.setBounds(199, 276, 162, 16);

		////////////////
		//Add Listener//
		////////////////
		backButton.addActionListener(backListener);
		doneButton.addActionListener(doneListener);

		///////////////
		//Add to pane//
		///////////////
		//column 1
		getContentPane().add(nameDrinkLabel);
		getContentPane().add(drinkName);//editable text
		getContentPane().add(cupSizeLabel);
		getContentPane().add(cupSizeAmt);//number
		getContentPane().add(ozLabel1);


		//column two
		getContentPane().add(selectMixerLabel);
		getContentPane().add(mixer1Label);//check boxes
		getContentPane().add(mixer1Amt);//editable amount
		getContentPane().add(ozLabel2);
		getContentPane().add(mixer2Label);//check boxes
		getContentPane().add(mixer2Amt);//editable amount
		getContentPane().add(ozLabel3);
		getContentPane().add(mixer3Label);//check boxes
		getContentPane().add(mixer3Amt);//editable amount
		getContentPane().add(ozLabel4);
		getContentPane().add(mixer4Label);//check boxes
		getContentPane().add(mixer4Amt);//editable amount
		getContentPane().add(ozLabel5);
		getContentPane().add(mixer5Label);//check boxes
		getContentPane().add(mixer5Amt);//editable amount
		getContentPane().add(ozLabel6);
		getContentPane().add(mixer6Label);//check boxes
		getContentPane().add(mixer6Amt);//editable amount
		getContentPane().add(ozLabel7);
		getContentPane().add(mixer7Label);//check boxes
		getContentPane().add(mixer7Amt);//editable amount
		getContentPane().add(ozLabel8);
		getContentPane().add(mixer8Label);//check boxes
		getContentPane().add(mixer8Amt);//editable amount
		getContentPane().add(ozLabel9);

		getContentPane().add(totalLabel);//label of total of mixers
		getContentPane().add(totalAmt);//shows the amount total this is a number
		getContentPane().add(ozLabel10);


		//column 3		
		getContentPane().add(drinkPassLabel);
		getContentPane().add(drinkChangePass);
		getContentPane().add(backButton);
		getContentPane().add(doneButton);
		getContentPane().add(blank);

		//getContentPane().add(cupSizeError);

		

		setJMenuBar(menuBar);


	}

	//////////////////////////////////
	//methods for changing variables//
	//////////////////////////////////
	
	///I dont think this is ever used
	public void setDrink(String drinkNameEditing)
	{
		this.drinkNameEditing = drinkNameEditing;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public void isNotPasswordProtected(boolean noProtection)
	{
		this.noProtection = noProtection;
	}


	////////////////
	//base methods//
	////////////////
	public void calculateDrink()
	{
		totalAmtNum = 0;
		//Check for mixer with check marked
//		returnCheckedMixer();//makes array of names of mixers that have been checked. Also removes any colons replacing them with spaces and trims 
		//(continued) off spaces at beginning and end of string
		returnCheckedAmt();//makes array of values that have been filled in AND checked off
		//add up all the mixers for a total
		for(int x=0; x<=7; x++)
		{
			totalAmtNum = mixerAmtNum[x] + totalAmtNum;
		}
		//display to total output
		totalAmt.setText(Integer.toString(totalAmtNum));
		//compare total to cupsize
		cupSizeAmtNum = Integer.parseInt(cupSizeAmt.getText());
		if(cupSizeAmtNum - 2 > totalAmtNum)
		{
			//save drink
			saveDrink();
		}
		else
		{
			//error message
			//dont save drink
			JOptionPane.showMessageDialog(null, "Cup size too small");


		}

		//format string to send to FileWorker
		//send formatted string to FileWorker
	}

	public void saveDrink()
	{
		//format string to be saved
		//String format:
		//DrinkName\n
		//Mixer1_Amt\n
		//...
		//Mixer8_Amt\n\n
		wholeDrink = 	drinkName.getText() + "\n" + 
				mixer1Label.getText() + " " + mixerAmtNum[0] + "\n" +
				mixer2Label.getText() + " " + mixerAmtNum[1] + "\n" +
				mixer3Label.getText() + " " + mixerAmtNum[2] + "\n" +
				mixer4Label.getText() + " " + mixerAmtNum[3] + "\n" +
				mixer5Label.getText() + " " + mixerAmtNum[4] + "\n" +
				mixer6Label.getText() + " " + mixerAmtNum[5] + "\n" +
				mixer7Label.getText() + " " + mixerAmtNum[6] + "\n" +
				mixer8Label.getText() + " " + mixerAmtNum[7] + "\n";
		
		//setwrite (setRead(false))
		fileWorker.setRead(false);
		//setString
		fileWorker.setString(wholeDrink);
		//run handleFile method
		fileWorker.handleFile();

	}


	public int[] returnCheckedAmt()
	{
		mixerAmtNum = new int[8];

		if(mixer1Label.isSelected())
		{
			//verify selected option has a number and no letters

			//add value in box to array
			mixerAmtNum[0] = Integer.parseInt(mixer1Amt.getText());
		}
		if(mixer2Label.isSelected())
		{
			//add value in box to array
			mixerAmtNum[1] = Integer.parseInt(mixer2Amt.getText());

		}
		if(mixer3Label.isSelected())
		{
			//add value in box to array
			mixerAmtNum[2] = Integer.parseInt(mixer3Amt.getText());

		}
		if(mixer4Label.isSelected())
		{
			//add value in box to array
			mixerAmtNum[3] = Integer.parseInt(mixer4Amt.getText());

		}
		if(mixer5Label.isSelected())
		{
			//add value in box to array
			mixerAmtNum[4] = Integer.parseInt(mixer5Amt.getText());

		}
		if(mixer6Label.isSelected())
		{
			//add value in box to array
			mixerAmtNum[5] = Integer.parseInt(mixer6Amt.getText());

		}
		if(mixer7Label.isSelected())
		{
			//add value in box to array
			mixerAmtNum[6] = Integer.parseInt(mixer7Amt.getText());

		}
		if(mixer8Label.isSelected())
		{
			//add value in box to array
			mixerAmtNum[7] = Integer.parseInt(mixer8Amt.getText());

		}
		else
		{
			//return none selected message

		}
		return mixerAmtNum;	
	}

	/////////////
	//listeners//
	/////////////
	class  DoneListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{

			//Check for password
			if(drinkChangePass.getPassword().toString() == password)
			{
				//continue
				calculateDrink();
			}
			else if(!mixer1Label.isSelected() && !mixer2Label.isSelected() && !mixer3Label.isSelected() &&
					!mixer4Label.isSelected() && !mixer5Label.isSelected() && !mixer6Label.isSelected() &&
					!mixer7Label.isSelected() && !mixer8Label.isSelected())
			{
				JOptionPane.showMessageDialog(null, "Please check off the mixers you want to include");
			}
			else if(noProtection)
			{
				//continue
				calculateDrink();
				fileWorker.getMixersVol();
				fileWorker.getMixersNoVol();
				
			}
			else
			{
				//return a message saying password invalid
				JOptionPane.showMessageDialog(null, "Invalid Password");

			}


		}
	}


	class  BackListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			//add are you sure this will not save warning
			int option = JOptionPane.showConfirmDialog(null, "Changes will not be saved, are you sure?");
			if(option == 0)
			{
				dispose();//close the window
			}
		}
	}

	public void EditADrink(String drinkNameEditing)
	{

		frame = new EditGUI(drinkNameEditing);

		frame.setTitle("Edit A Drink");

		frame.setBounds(100, 100, 500, 350);
		frame.setResizable(false);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}



}
