package MediaRental;

import java.util.Scanner;

/**
 * Created by Obberton13 on 2/20/2015.
 */
public class mainClass {
	public static void main(String args[])
	{
		System.out.println("Hey, it did something!");
		Scanner sc = new Scanner(System.in);

		//this is the main loop for the program.
		while(sc.hasNext())
		{
			String line = sc.nextLine();
			System.out.println("Hey, it scanned something!" + line);
			if(line.equals("quit")) break;
		}
		sc.close();
	}
}
