package drinkmachine;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;

import javax.swing.JOptionPane;


public class FileWorker 
{
	private int pumpCount = 8;

	private File drinkListFile = new File("DrinkList.txt");
	private File mixerListFile = new File("MixerList.txt");
	private File loggerFile = new File("LogFile.txt");
	private BufferedWriter drinkListFileOutput;
	private BufferedWriter mixerListFileOutput;
	private BufferedWriter loggerFileOutput;
	private String wholeDrink;
	private boolean read;
	private String exportDrinkList;
	private String exportMixerList;
	private String importDrinkList;
	private String importMixerList;
	private String drinkName;
	private String[] mixersToSave = new String[8];
	private String[] noVolFinal = new String[8];
	

	public FileWorker()
	{

	}

	public String handleFile()
	{
		if(!read)//exports file
		{
			boolean sameName = false;
			//keep track of matched lines in for loop
			int match = 0;
			//import the file
			importFile();

			//check for the same drink in file already
			findDrinkName();

			//decide if list imported contains same drink name
			if(importDrinkList.toLowerCase().contains(drinkName.toLowerCase()) )
			{
				//BufferedReader reader = new BufferedReader(readFile);
				int index = importDrinkList.toLowerCase().indexOf(drinkName.toLowerCase());
				sameName = true;

				String partsSubstring = importDrinkList.substring(index);

				String[] drinkListFileParts = partsSubstring.split("\n");
				String[] wholeDrinkParts = wholeDrink.split("\n");

				//checks drink that had the same name line by line for a matched mixture\

				for(int x = 0; x <= 8; x++)
				{
					for(int i = 0; i <= 8; i ++)
						//decide if a line matches anoth line
						if(new String(drinkListFileParts[x]).equalsIgnoreCase(wholeDrinkParts[i]))
						{
							//out put message saying that drink already exists.
							match++;

						}

				}
			}

			//Decide to save or not save drink
			if(match == 9)
			{
				JOptionPane.showMessageDialog(null, "This drink mixture and name already exists" );

			}
			else if(match != 9 && sameName)
			{
				int option = JOptionPane.showConfirmDialog(null, "This drink name already exists.\nWould you like to add this drink\nwith the same name?" );
				if(option == 0)
				{
					exportDrinkList = importDrinkList + "\n" + wholeDrink;
					exportFile();
				}

			}
			else
			{
				int option = JOptionPane.showConfirmDialog(null, "This drink does not exist.\nWould you like to add it?");
				if(option == 0)//add drink
				{
					exportDrinkList = importDrinkList + "\n" + wholeDrink;
					exportFile();

				}
				else//dont add drink
				{
					System.out.println("Drink not added");

				}
			}





		}
		else//imports file
		{
			//import file
			//importFile();

		}
		return importDrinkList;
	}

	//gets the name of the drink that is in the wholeDrink String
	private void findDrinkName()
	{
		BufferedReader reader = new BufferedReader(new StringReader(wholeDrink));
		try{
			drinkName = reader.readLine();
		}
		catch(IOException e)
		{
			e.toString();
			JOptionPane.showMessageDialog(null, "drinkName read error in findDrinkName(): " + e.toString());
		}

	}

	//gets list of drinks that have mixers available in the mixer file
	public String[] getAllDrinkNames()
	{
		//String[]
		String drinkNames = "";
		importFile();

		//read first line
		//read every line after a null
		//iff null null end
		try
		{
			int count = 0;
			boolean firstLine = true;
			BufferedReader reader = new BufferedReader(new StringReader(importDrinkList));

			String drinkListLine = reader.readLine();
			boolean end = false;
			getMixersNoVol();
			while(drinkListLine != null && end == false)
			{
				if(firstLine)//reads first line aka the first drink on file
				{
					String tempName = drinkListLine;
					int yes = 0;
					//if all the mixers are the same as available mixers after this line.
					for(int x = 0; x<=pumpCount-1; x++)//hold constant the drink list line for each line of the mixers
					{
						drinkListLine = reader.readLine();
						String[] tempLine = drinkListLine.split(" ");//assumes after first space is number for volume
						if(Double.parseDouble(tempLine[tempLine.length-1])!=0)//if the amount for the mix is not zero check for that mixer.
						{
							for(int i = 0; i <= pumpCount-1; i++ )//checks each line in file versus the stored mixer
							{
								if(new String(tempLine[0]).equalsIgnoreCase(noVolFinal[i].split(" ")[0]))
								{
									yes++;
									break;
								}
							}
						}
						else
						{
							yes++;
						}

					}
					if(yes == 8)
					{
						drinkNames += tempName + "\n";
						//drinkListLine = reader.readLine();

					}
					firstLine = false;
					drinkListLine = reader.readLine();
				}
				if(new String(drinkListLine).equals(""))//reads lines after
				{

					drinkListLine = reader.readLine();//read line after null line
					String tempName = drinkListLine;//saves name of drink

					int yes = 0;
					//if all the mixers are the same as available mixers after this line.
					for(int x = 0; x<=pumpCount-1; x++)//holds a drinkListLine constant
					{
						drinkListLine = reader.readLine();
						String[] tempLine = drinkListLine.split(" ");//assumes after first space is number for volume
						if(Double.parseDouble(tempLine[tempLine.length-1])!=0)//if the amount for the mix is not zero check for that mixer.
						{
							for(int i = 0; i <= pumpCount-1; i++ )//checks each mixer
							{
								if(new String(tempLine[0]).equalsIgnoreCase(noVolFinal[i].split(" ")[0]))
								{
									yes++;
									break;
								}
							}
						}
						else
						{
							yes++;
						}
					}
					if(yes == 8)
					{
						drinkNames += tempName + "\n";
						//drinkListLine = reader.readLine();
						//firstLine = false;
					}

					//drinkNames += drinkListLine + "\n";
					drinkListLine = reader.readLine();//read line after null line




					if(drinkListLine == null)
					{
						end = true;
					}
				}
				if(drinkListLine == null)
				{
					end = true;
				}
				//				else
				//				{
				//					drinkListLine = reader.readLine();//read line after null line
				//				}
			}



		}
		catch(IOException e)
		{
			e.toString();//change this
			JOptionPane.showMessageDialog(null, "getAllDrinkNames error " + e.toString());
		}
		String[] allDrinkNames = drinkNames.split("\n");



		return allDrinkNames;
	}
	private void importFile()
	{
		//used locally for checking for empty lines
		int count = 0;

		try{
			FileReader readFile = new FileReader(drinkListFile);
			BufferedReader reader = new BufferedReader(readFile);

			int i = 0;
			importDrinkList = "";
			String drinkListFileLine = reader.readLine();
			do//this leaves you with a string of the drinkListFile with a space at the end. 
			{
				importDrinkList += drinkListFileLine + "\n";//adds the line on the txt file to the string
				drinkListFileLine = reader.readLine();//goes to the next line.

				//keeps track of times through with a null line
				if(drinkListFileLine == null) //increments count if there is a space sensed
				{
					count++;
				}
				else if(drinkListFileLine != null)//resets count if there is only one space in between drinks
				{
					count = 0;
				}

				i++;
			}
			while(drinkListFileLine!= null && count != 2);
			reader.close();


		}
		catch(IOException e)
		{
			e.toString();//change this
			JOptionPane.showMessageDialog(null, "Drink file read error: " + e.toString());
		}


	}

	private void exportFile()
	{

		//saves drinks to file
		try
		{
			drinkListFileOutput = new BufferedWriter(new FileWriter(drinkListFile));
			drinkListFileOutput.write(exportDrinkList);//insert string of drinks
			drinkListFileOutput.close();
			JOptionPane.showMessageDialog(null, "Successfully saved drinks");
		}
		catch(IOException e)
		{
			e.toString();//change this
			JOptionPane.showMessageDialog(null, "Drink save error: " + e.toString());//change this);
		}



	}

	//sets the drink with mixer amounts to be saved
	public void setString(String wholeDrink)
	{
		this.wholeDrink = wholeDrink;
	}

	public void setRead(boolean read)
	{
		this.read = read;
	}



	//////////
	//Mixers//
	//////////

	public String[] getMixersVol()
	{
		String[] mixers = new String[pumpCount];

		//import mixer list file
		//break into one mixer per cell with amount
		//file format:
		//Mixer 1: 25
		//....
		//Mixer 8: 25
		try
		{
			FileReader readFile = new FileReader(mixerListFile);
			BufferedReader reader = new BufferedReader(readFile);

			importMixerList = "";
			String mixerListFileLine = reader.readLine();
			while(mixerListFileLine != null)
			{
				importMixerList += mixerListFileLine + "\n";
				mixerListFileLine = reader.readLine();
			}
			reader.close();
			mixers = importMixerList.split("\n");//turns long mixer file string into array of mixers with total volume.
		}
		catch(IOException e)
		{
			e.toString();//change this
			JOptionPane.showMessageDialog(null, "Mixer file read error: " + e.toString());//change this);
		}

		return mixers;
	}

	public String[] getMixersNoVol()
	{
		getMixersVol();//calls method to populate importMixerList
		String[] noVol = importMixerList.split(":");



		for(int x = 0; x<=pumpCount - 1; x++)
		{
			String[] noVol1 = noVol[x].split("\n");

			//this if statement is needed because splitting by \n leaves the first and the last line in a diff. format from the inner lines.
			if(x == 0)
			{
				noVolFinal[x] = noVol1[0];
			}
			else if(x == pumpCount - 1)
			{
				noVolFinal[x] = noVol1[1];
			}
			else
			{
				noVolFinal[x] = noVol1[1];
			}

		}
		return noVolFinal;
	}


	public void saveMixers(String[] mixersToSave)
	{
		//this.mixersToSave = mixersToSave;
		String mixerString = "";
		for(int x = 0; x < mixersToSave.length; x++)
		{
			mixerString = mixerString + mixersToSave[x] + "\n";
		}

		try
		{
			mixerListFileOutput = new BufferedWriter(new FileWriter(mixerListFile));
			mixerListFileOutput.write(mixerString);//insert string of drinks
			mixerListFileOutput.close();
			JOptionPane.showMessageDialog(null, "Successfully saved mixers.");
		}
		catch(IOException e)
		{
			e.toString();//change this
			JOptionPane.showMessageDialog(null, "Mixer save error: " + e.toString());//change this);
		}


	}

	//gets the amount of each mixer in a specific drink in order of mixer list file
	public double[] getDrinkMakeUp(String drinkToMake)
	{
		double[] drinkMixerAmount = new double[pumpCount];
		String drinkBeingMade = "";
		//search line by line for name of the drink

		//once found get each mixer until line is null or ""
		//split the mixer lines and take out amount
		//store amount in array that is returned
		try{
			FileReader readFile = new FileReader(drinkListFile);
			BufferedReader reader = new BufferedReader(readFile);

			//int i = 0;
			int count = 0;

			String drinkListFileLine = reader.readLine();
			do//this leaves you with a string of the drinkListFile with a space at the end. 
			{
				if(drinkListFileLine.equalsIgnoreCase(drinkToMake))//if the drink being made is on this line capture the mixers under it
				{
					drinkListFileLine = reader.readLine();//goes to the next line.
					while(drinkListFileLine != null)
					{
						if(drinkListFileLine.equals(""))
							break;
						drinkBeingMade += drinkListFileLine + "\n";//adds the line on the txt file to the string
						drinkListFileLine = reader.readLine();//goes to the next line.
					}
					break;
				}
				else//drink being made is not on this line, check the next line
				{
					drinkListFileLine = reader.readLine();//goes to the next line.
				}
				//keeps track of times through with a null line
				if(drinkListFileLine == null) //increments count if there is a space sensed
				{
					count++;
				}
				else if(drinkListFileLine != null)//resets count if there is only one space in between drinks
				{
					count = 0;
				}

				//i++;
			}
			while(drinkListFileLine!= null && count != 2);
			reader.close();


		}
		catch(IOException e)
		{
			e.toString();//change this
			JOptionPane.showMessageDialog(null, "Drink file read error: " + e.toString());
		}

		//separate the amount from the mixer name
		String[] tempString = drinkBeingMade.split("\n");//splits the string that is formatted with new lines like the mixer file into 1 line per index of array
		for(int x = 0; x < pumpCount; x++)
		{ 
			String[] tempString2 = tempString[x].split(" ");
			drinkMixerAmount[x] = Double.parseDouble(tempString2[tempString2.length-1]);//splits each index of array tempString, parses the string at the last index (the part containing the amount) to an integer.
//			System.out.println(drinkMixerAmount[x]);
		}

		return drinkMixerAmount;
	}

	/////////////////////
	//Pump data methods//
	/////////////////////

	public double[] importFlowRateDataFile(int pumpDataArraySize, String pumpDataFileName)
	{
		double[] pumpData = new double[pumpDataArraySize]; //26 as of first design

		////////////////////
		//Import file data//
		////////////////////
		String pumpDataString = "";
		String line = "";
		//search line by line 
		try{
			FileReader readFile = new FileReader(pumpDataFileName);
			BufferedReader reader = new BufferedReader(readFile);

			pumpDataString = reader.readLine();//reads first line
			pumpDataString = reader.readLine();//reads second line so first line isnt remebered
			while(line != null)
			{
				pumpDataString += line + "\n"; 
				line = reader.readLine();
			}
		}
		catch(IOException e)
		{
			System.out.println("Error at 'importPumpDataFile' located in FileWorker: " + e.toString());
		}

		//////////////
		//Parse Data//
		//////////////
		String[] stringArray = pumpDataString.split("\n");
		for(int x = 0; x<= pumpDataArraySize-1; x++)
		{
			pumpData[x] = Double.parseDouble(stringArray[x].split("-")[1]); //get the splitted string array, gets each element, splits and takes the data portion then parses to int and store in final array
		}

		return pumpData;
	}

	public int[] importPwmRateDataFile(int pumpDataArraySize, String pumpDataFileName)
	{
		int[] pumpData = new int[pumpDataArraySize]; //26 as of first design

		////////////////////
		//Import file data//
		////////////////////
		String pumpDataString = "";
		String line = "";
		//search line by line 
		try{
			FileReader readFile = new FileReader(pumpDataFileName);
			BufferedReader reader = new BufferedReader(readFile);

			pumpDataString = reader.readLine();//reads first line
			pumpDataString = reader.readLine();//reads second line so first line isnt remebered
			while(line != null)
			{
				pumpDataString += line + "\n"; 
				line = reader.readLine();
			}
		}
		catch(IOException e)
		{
			System.out.println("Error at 'importPumpDataFile' located in FileWorker: " + e.toString());
		}

		//////////////
		//Parse Data//
		//////////////
		String[] stringArray = pumpDataString.split("\n");
		for(int x = 0; x<= pumpDataArraySize-1; x++)
		{
			pumpData[x] = Integer.parseInt(stringArray[x].split("-")[0]); //get the splitted string array, gets each element, splits and takes the data portion then parses to int and store in final array
		}

		return pumpData;
	}

	//////////////////
	//Log drink made//
	//////////////////

	public void logDrink(String loggerString)
	{
		////////////////////
		//Import file data//
		////////////////////
		String loggedData = "";
		String line = "";
		//search line by line 
		try{
			FileReader readFile = new FileReader(loggerFile);
			BufferedReader reader = new BufferedReader(readFile);

			loggedData = reader.readLine();
			while(line != null)
			{
				loggedData += line + "\n"; 
				line = reader.readLine();
			}
		}
		catch(IOException e)
		{
			System.out.println("Error at 'importPumpDataFile' located in FileWorker: " + e.toString());
		}
		
		////////////////////////////
		//Add new data to log file//
		////////////////////////////
		loggedData = loggedData + "\n" + loggerString;
		
		////////////////
		//Log new data//
		////////////////
		try
		{
			loggerFileOutput = new BufferedWriter(new FileWriter(loggerFile));
			loggerFileOutput.write(loggedData);//insert string of drinks
			loggerFileOutput.close();
//			JOptionPane.showMessageDialog(null, "Successfully logged drink.");
		}
		catch(IOException e)
		{
			e.toString();//change this
			JOptionPane.showMessageDialog(null, "LogFile write error: " + e.toString());//change this);
		}

		
		
		
		
	}

}