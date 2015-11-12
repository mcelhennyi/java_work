package drinkmachine;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;


public class DrinkMachineGUI 
{
	JFrame frame;

	JButton makeDrink = new JButton("Make a Drink");
	JButton changeMixer = new JButton("Change a Mixer");
	JButton editDrink = new JButton("Edit a Drink");
	JLabel titleLabel = new JLabel("Al: The Drink Bot");
	JMenuBar menuBar = new JMenuBar();
	JMenu fileMenu = new JMenu("File");
	JMenuItem closeMenu = new JMenuItem("Close");
	JMenu optionMenu = new JMenu("Options");
	JMenuItem makeDrinkMenu = new JMenuItem("Make a Drink");
	JMenuItem changeMixerMenu = new JMenuItem("Change a Mixer");
	JMenuItem editDrinkMenu = new JMenuItem("Edit a Drink");
	JMenuItem passwordMenu = new JMenuItem("Password");
	JMenuItem refreshMenu = new JMenuItem("Refresh");
	JMenuItem setComPort = new JMenuItem("Change COM port");
	JMenu aboutMenu = new JMenu("About");
	JMenuItem descriptionMenu = new JMenuItem("Al: The Drink Bot");
	JMenuItem featuresMenu = new JMenuItem("Features");

	SpringLayout springLayout = new SpringLayout();

	int width;
	int height;
	int changeButtonWidth = 60;
	int titleWidth = 125;

	String comPort = "COM3";

	String password = "password";
	String aboutString = "\t\t\t\t\tAl: The Drink Bot\t\t\t\t\t\n"
			+ "The robotic bartender, here for all your alcoholic drink\n";
	//					   + ""

	public DrinkMachineGUI()
	{
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); ;//sets size of frame1 to screen size
		try 
		{ 
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		frame.setVisible(true);
		//////////////////////
		//Layout constraints//
		//////////////////////
		width = frame.getWidth();
		height = frame.getHeight();
		//title
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Kristen ITC", Font.PLAIN, 30));
		//change
		springLayout.putConstraint(SpringLayout.SOUTH, changeMixer, height - height/3, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, changeMixer, width/2 - changeButtonWidth , SpringLayout.WEST, frame.getContentPane());
		//make 
		springLayout.putConstraint(SpringLayout.NORTH, makeDrink, 0, SpringLayout.NORTH, changeMixer);
		springLayout.putConstraint(SpringLayout.EAST, makeDrink, -10, SpringLayout.WEST, changeMixer);
		//edit
		springLayout.putConstraint(SpringLayout.NORTH, editDrink, 0, SpringLayout.NORTH, changeMixer);
		springLayout.putConstraint(SpringLayout.WEST, editDrink, 10, SpringLayout.EAST, changeMixer);
		//title
		springLayout.putConstraint(SpringLayout.NORTH, titleLabel, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, titleLabel, width/2 - titleWidth , SpringLayout.WEST, frame.getContentPane());


		//create listeners
		makeDrinkListener makeDrinkListener = new makeDrinkListener();
		changeMixerListener changeMixerListener = new changeMixerListener();
		editDrinkListener editDrinkListener = new editDrinkListener();
		refreshListener refreshListener = new refreshListener();
		disposeListener disposeListener = new disposeListener();
		passwordListener passwordListener = new passwordListener();
		descriptionListener descriptionListener = new descriptionListener();
		changeComListener changeComListener = new changeComListener();

		//listeners for the buttons and menu
		makeDrink.addActionListener(makeDrinkListener);
		makeDrinkMenu.addActionListener(makeDrinkListener);
		changeMixer.addActionListener(changeMixerListener);
		changeMixerMenu.addActionListener(changeMixerListener);
		editDrink.addActionListener(editDrinkListener);
		editDrinkMenu.addActionListener(editDrinkListener);
		refreshMenu.addActionListener(refreshListener);
		closeMenu.addActionListener(disposeListener);
		passwordMenu.addActionListener(passwordListener);
		descriptionMenu.addActionListener(descriptionListener);
		setComPort.addActionListener(changeComListener);

		//Add to panel
		frame.getContentPane().add(changeMixer);
		frame.getContentPane().add(makeDrink);
		frame.getContentPane().add(editDrink);
		frame.getContentPane().add(titleLabel);
		frame.setJMenuBar(menuBar);
		menuBar.add(fileMenu);
		fileMenu.add(closeMenu);
		menuBar.add(optionMenu);
		optionMenu.add(makeDrinkMenu);
		optionMenu.add(changeMixerMenu);
		optionMenu.add(editDrinkMenu);
		optionMenu.add(passwordMenu);
		optionMenu.add(refreshMenu);
		optionMenu.add(setComPort);
		menuBar.add(aboutMenu);
		aboutMenu.add(descriptionMenu);
		aboutMenu.add(featuresMenu);

	}

	class changeComListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			
			String temp = JOptionPane.showInputDialog("Current COM port is: " + comPort + "\nType in new COM port below as shown above:");
			if(temp == null)
			{
			}
			else if(temp.equals(""))
			{
			}
			else
			{
				comPort = temp.trim();
			}
		}

	}

	class descriptionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			JOptionPane.showMessageDialog(frame, aboutString);
		}

	}
	class passwordListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			password = JOptionPane.showInputDialog("Enter new password:" );

		}

	}
	class disposeListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			frame.dispose();
		}

	}
	class refreshListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			width = frame.getWidth();
			height = frame.getHeight();
			frame.getContentPane().revalidate();
			frame.getContentPane().repaint();
		}

	}
	//creates panel for second page after pressing make on the first page
	class makeDrinkListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			MakeGUI makeGUI = new MakeGUI();
			makeGUI.MakeADrink();
			makeGUI.setComPort(comPort);
		}

	}
	class changeMixerListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{

			ChangeMixerGUI changeMixerGUI = new ChangeMixerGUI();
			changeMixerGUI.ChangeAMixer();
		}
	}

	class editDrinkListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			EditChoiceGUI editGUI = new EditChoiceGUI();
			editGUI.EditADrink(password);


		}
	}

	public static void main(String[] args) 
	{
		DrinkMachineGUI drinkMachineGUI = new DrinkMachineGUI();
		drinkMachineGUI.frame.setVisible(true);

		//		//title
		//		drinkMachineGUI.frame.setTitle("AL the Drink Automator");
		//		//screen size
		//		//show frame1
		//		drinkMachineGUI.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



	}

}
