package drinkmachine;

public class ArduinoComTester 
{
	public static void main(String []args)
	{
		ArduinoComDev test = new ArduinoComDev();
		//format a string for transmission
			//first byte is the amount of time required 
				//...for longest poor.
			//next 8 bytes is the PWM number for amount in
				//...order of the mixers
			//
		String totalTime = "60000"; //This is the total pump time in mS
		String send = "00:" +  totalTime + "/255/255/255/255/0/0/0/0:00";
		
		

		test.setTimeout(Integer.parseInt(totalTime) + 10000);
		test.setDrinkString(send);
		test.ArduinoComDevStart();
	}
}
