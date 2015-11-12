package drinkmachine;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

//////////////////////////////////////////////
//This is the edit gui with choice for drink//
//////////////////////////////////////////////
public class EditChoiceGUI extends JFrame
{

	EditGUI editGUI;


	private String[] drinkList = new String[500];
	//////////////////
	//Create Objects//
	//////////////////
	Canvas canvas = new Canvas();
	JComboBox comboBox = new JComboBox();
	JButton chooseDrinkButton = new JButton("Select");
	JButton backButton = new JButton("Back");
	
	String password;
	public EditChoiceGUI()
	{


		/////////////
		//Listeners//
		/////////////
		BackListener backListener = new BackListener();
		EditDrinkListener editDrinkListener = new EditDrinkListener();

		//////////
		//Canvas//
		//////////
		canvas.setBackground(new Color(255, 153, 102));
		canvas.setBounds(0, 0, 1000, 208);



		/////////////
		//Combo Box//
		/////////////
		getDrinkList();
		comboBox.setToolTipText("Pick a drink from this list");
		comboBox.setModel(new DefaultComboBoxModel(drinkList));
		comboBox.setBounds(395, 214, 210, 30);


		///////////
		//Buttons//
		///////////
		//choose drink
		chooseDrinkButton.setToolTipText("Click here to add or edit a drink.");
		chooseDrinkButton.setBounds(395, 401, 210, 29);
		//back
		backButton.setBounds(900, 545, 80, 30);

		////////////
		//Menu Bar//
		////////////
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);





		////////////////////
		//Action Listeners//
		////////////////////
		backButton.addActionListener(backListener);
		chooseDrinkButton.addActionListener(editDrinkListener);


		////////////////
		//Add to frame//
		////////////////
		getContentPane().add(canvas);
		getContentPane().add(comboBox);
		getContentPane().add(chooseDrinkButton);
		getContentPane().add(backButton);


	}


	public void getDrinkList()
	{
		//search file for existing drinks
		//import drink names
		//add "Select A Drink to Edit:" to the front
		//add an add option to the end of the drop down
		FileWorker fileWorker = new FileWorker();
		drinkList[0] = "Select A Drink to Edit:";
		String[] tempString = fileWorker.getAllDrinkNames();
		for(int x = 0; x<tempString.length; x++) 
		{
			drinkList[x+1] = tempString[x];
		}
		drinkList[tempString.length+1 ] = "<new>";
	}

	public void setDrinkList()
	{

	}


	class  EditDrinkListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			String drinkName = "";
			EditGUI editGUI = new EditGUI(password);
			try
			{
				drinkName = comboBox.getSelectedItem().toString();

			}
			catch(NullPointerException f )
			{
				JOptionPane.showMessageDialog(null, "Select a drink to edit or select back \n"
						+ "in lower right hand corner to go back \n"
						+ "to previous page.");
			}
			if(drinkName == "Select A Drink to Edit:" )
			{
				JOptionPane.showMessageDialog(null, "Select a drink to edit or select back \n"
						+ "in lower right hand corner to go back \n"
						+ "to previous page.");
			}
			else if(drinkName == "")
			{
				//do nothing
			}
			
			else
			{
				editGUI.EditADrink(drinkName);
				editGUI.setPassword(password);
			}


		}
	}

	class  BackListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			dispose();

		}
	}


	public void EditADrink(String password)
	{
		EditChoiceGUI frame = new EditChoiceGUI();
		frame.setTitle("Al the Drink Machine");
		frame.getContentPane().setBackground(new Color(135, 206, 250));
		frame.setBounds(100, 100, 1000, 625);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		this.password = password;
		//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}
}
