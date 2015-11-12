package drinkmachine;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class ChangeMixerGUI extends JFrame
{

	ChangeMixerGUI frame;
	private JTextField mixerNameTF;
	private JTextField mixerSizeTF;
	FileWorker fileWorker = new FileWorker();
	private String toChange;
	private String changeTo;
	private double doubleVolume;
	private int intVolume;
	private String stringVolume;
	private String mixerList;
	private String[] mixersArrayVol = new String[8];
	private String[] mixersArrayNoVol = new String[8];
	private String changeToTextOnly;
	private boolean fail = false;

	//////////////////
	//Create Objects//
	//////////////////
	JLabel selectMixerLabel = new JLabel("Select Mixer to Change");
	JComboBox comboBox = new JComboBox();
	JButton saveButton = new JButton("Save");
	JRadioButton mlRadioButton = new JRadioButton("ml");
	JRadioButton lRadioButton = new JRadioButton("Liter");
	JRadioButton ozRadioButton = new JRadioButton("Ounce");
	JLabel mixerSizeLabel = new JLabel("Volume");
	JLabel mixerNameLabel = new JLabel("Mixer Name");
	JButton backButton = new JButton("Back");
	JLabel volumeTypeLabel = new JLabel("Type");
	ButtonGroup buttonGroup = new ButtonGroup();




	public ChangeMixerGUI()
	{
		initialize();
	}

	public void initialize()
	{
		////////////
		//Listener//
		////////////
		BackListener backListener = new BackListener();
		SaveListener saveListener = new SaveListener();
		ComboBoxListener comboBoxListener = new ComboBoxListener();

		//////////
		//Labels//
		//////////
		selectMixerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		selectMixerLabel.setBounds(6, 6, 160, 16);
		mixerSizeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mixerSizeLabel.setBounds(240, 132, 74, 16);
		mixerNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mixerNameLabel.setBounds(94, 132, 134, 16);
		volumeTypeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		volumeTypeLabel.setBounds(326, 132, 61, 16);

		////////////////////////
		//editable text fields//
		////////////////////////
		//mixer name
		mixerNameTF = new JTextField();
		mixerNameTF.setBounds(94, 147, 134, 28);
		mixerNameTF.setColumns(10);
		//mixer volume
		mixerSizeTF = new JTextField();
		mixerSizeTF.setBounds(240, 147, 74, 28);
		mixerSizeTF.setColumns(10);

		/////////////
		//combo box//
		/////////////
		comboBox.setModel(new DefaultComboBoxModel(fileWorker.getMixersNoVol()));
		comboBox.setBounds(6, 28, 160, 27);


		///////////
		//Buttons//
		///////////
		saveButton.setBounds(6, 271, 63, 29);
		backButton.setBounds(400, 271, 80, 29);
		//radio
		mlRadioButton.setBounds(326, 149, 63, 23);
		mlRadioButton.setSelected(true);
		lRadioButton.setBounds(326, 184, 63, 23);
		ozRadioButton.setBounds(326, 219, 80, 23);
		buttonGroup.add(mlRadioButton);
		buttonGroup.add(lRadioButton);
		buttonGroup.add(ozRadioButton);

		/////////////////
		//Add Listeners//
		/////////////////
		backButton.addActionListener(backListener);
		saveButton.addActionListener(saveListener);
		comboBox.addActionListener(comboBoxListener);



		////////////////
		//Add to frame//
		////////////////
		getContentPane().add(selectMixerLabel);
		getContentPane().add(comboBox);
		getContentPane().add(mixerNameTF);
		getContentPane().add(saveButton);
		getContentPane().add(mixerSizeTF);
		getContentPane().add(mlRadioButton);
		getContentPane().add(lRadioButton);
		getContentPane().add(ozRadioButton);
		getContentPane().add(mixerSizeLabel);
		getContentPane().add(mixerNameLabel);
		getContentPane().add(backButton);
		getContentPane().add(volumeTypeLabel);
		mixerNameTF.setText(comboBox.getSelectedItem().toString());//sets the first mixer in the file to the editable text field

		///////////
		//menubar//
		///////////
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
	}

	class  SaveListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{


			///////
			//get//
			///////

			//get selected from combo box
			//get new drink name
			//get volume
			//change to ounces
			try
			{
				//get selected from combo box
				toChange = comboBox.getSelectedItem().toString();
				//get new drink name
				changeToTextOnly = mixerNameTF.getText();
				changeTo = mixerNameTF.getText();
				//get volume
				doubleVolume = Double.parseDouble(mixerSizeTF.getText());
			}
			catch(NumberFormatException f)
			{
				JOptionPane.showMessageDialog(null, "Please fill in all fields.");
				fail = true;
			}

			if(!fail)
			{
				//Changes volume to oz for storage assumes oz is selected otherwise
				if(mlRadioButton.isSelected())//if ml is selected
				{
					doubleVolume = doubleVolume * 0.033814;
				}
				else if(lRadioButton.isSelected())//if liter is selected
				{
					doubleVolume = doubleVolume * 33.814;
				}
				intVolume = (int) Math.round(doubleVolume);//changes volume to a integer rounded down

				changeTo = changeTo + ": " + intVolume;//adds volume to name of mixer

				////////
				//Save//
				////////

				//import mixer list with volume
				//deleted selected mixer toChange
				//add mixer changeTo with volume

				mixersArrayNoVol = fileWorker.getMixersNoVol();
				mixersArrayVol = fileWorker.getMixersVol();
				
				for(int x = 0; x < mixersArrayNoVol.length; x++)
				{
					if(new String(toChange).equalsIgnoreCase(mixersArrayNoVol[x]))
					{
						mixersArrayVol[x] = new String(changeTo);
					}
				}
				
				fileWorker.saveMixers(mixersArrayVol);
				comboBox.setModel(new DefaultComboBoxModel(fileWorker.getMixersNoVol()));

			}

		}
	}



	class  ComboBoxListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			mixerNameTF.setText(comboBox.getSelectedItem().toString());
		}
	}


	class  BackListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			//add are you sure this will not save warning
			int option = JOptionPane.showConfirmDialog(null, "Did you save your changes?");
			if(option == 0)
			{
				dispose();//close the window
			}
		}
	}


	public void ChangeAMixer()
	{
		frame = new ChangeMixerGUI();
		//frame.getContentPane().setBackground(new Color(0, 153, 255));
		frame.setBounds(100, 100, 500, 350);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
