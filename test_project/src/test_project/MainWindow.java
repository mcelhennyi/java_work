package test_project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JTabbedPane;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MainWindow 
{

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{

		MainWindow window = new MainWindow();
		window.frame.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public MainWindow() 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frame = new JFrame();

		//				frame.setExtendedState(JFrame.MAXIMIZED_BOTH); ;//sets size of frame1 to screen size

		frame.setBounds(100, 100, 450, 300);



		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		frame.setVisible(true);

		int width = frame.getWidth();
		int height = frame.getHeight();
		int changeButtonWidth = 60;
		int titleWidth = 125;

		//		System.out.println(width + ", " + height);

		JButton makeDrink = new JButton("Make a Drink");
		JButton changeMixer = new JButton("Change a Mixer");
		JButton editDrink = new JButton("New button");
		JLabel titleLabel = new JLabel("Al: The Drink Bot");
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem mntmClose = new JMenuItem("Close");
		JMenu optionMenu = new JMenu("Options");
		JMenuItem makeDrinkMenu = new JMenuItem("Make a Drink");
		JMenuItem changeMixerMenu = new JMenuItem("Change a Mixer");
		JMenuItem editDrinkMenu = new JMenuItem("Edit a Drink");
		JMenuItem passwordMenu = new JMenuItem("Password");
		JMenuItem refreshMenu = new JMenuItem("Refresh");
		JMenu aboutMenu = new JMenu("About");
		JMenuItem descriptionMenu = new JMenuItem("Al: The Drink Bot");
		JMenuItem featuresMenu = new JMenuItem("Features");
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

		frame.getContentPane().add(changeMixer);
		frame.getContentPane().add(makeDrink);
		frame.getContentPane().add(editDrink);
		frame.getContentPane().add(titleLabel);
		frame.setJMenuBar(menuBar);

		menuBar.add(fileMenu);
		fileMenu.add(mntmClose);
		menuBar.add(optionMenu);
		optionMenu.add(makeDrinkMenu);
		optionMenu.add(changeMixerMenu);
		optionMenu.add(editDrinkMenu);
		optionMenu.add(passwordMenu);
		optionMenu.add(refreshMenu);
		menuBar.add(aboutMenu);
		aboutMenu.add(descriptionMenu);
		aboutMenu.add(featuresMenu);

	}
}
