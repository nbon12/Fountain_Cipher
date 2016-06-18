import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;


public class CipherMain 
{
	Scanner keyboard = new Scanner(System.in);
	/**
	 * Input unencrypted string, output encrypted String
	 * @param unencryptedString
	 * @return encryptedString
	 */
	String theAnswser;


	public void encryptMenu()
	{
		System.out.println("Welcome to the encrypt Menu.");
		System.out.println("Enter a password. \n"
				+ "NOTE: This password must be used to decrypt the message.");
		String password = keyboard.next();
		System.out.println("Enter the message that is to be encrypted.");
		String unencryptedString = keyboard.next();
		String answer = encrypt(unencryptedString, password);
		System.out.println(answer);
		System.exit(0);
	}
	public String encrypt(String unencryptedString, String password)
	{
		String encryptedString = "";
		HashMap<Character, Integer> m1 = new HashMap();
		int countAlpha = 1;
		for(char alphabet = 'a'; alphabet <= 'z'; alphabet++, countAlpha++)
		{
			m1.put(alphabet, countAlpha);
		}
		char firstCharacter = password.charAt(0);
		char secondCharacter = password.charAt(1);

		//time for random generation

		//end random generation
		for(int i = 0; i < unencryptedString.length(); i++)
		{
			char currentCharOfMessage = unencryptedString.charAt(i);
			char currentCharOfPass = password.charAt((i+1) % password.length());
			int currentCharOfPassInt = m1.get(currentCharOfPass);
			//filling gaps with currentCharInt number of random chars
			for(int j = 0; j < currentCharOfPassInt; j++)
			{
				char randomChar = randomChar();
				encryptedString += randomChar;
			}
			encryptedString += currentCharOfMessage;
			//System.out.println(encryptedString);
			//for(int j = 0; j < m1.get(i))
		}

		return encryptedString;
	}

	public char randomChar()
	{
		Random r = new Random();
		char returnMe = 'Z';
		String alphabet = "abcdefghijklmnopqrxtuvwxyz";

		returnMe = alphabet.charAt(r.nextInt(alphabet.length()));

		return returnMe;
	}
	/**
	 * Input encrypted String, output unencrypted String
	 * @param encryptedString
	 * @return unencryptedString
	 */
	public void decryptMenu()
	{
		System.out.println("Welcome to the decrypt Menu.");
		System.out.println("Enter the password for this message \n");
		String password = keyboard.next();
		System.out.println("Enter the message.");
		String encryptedString = keyboard.next();
		String answer = decrypt(encryptedString, password);
		System.out.println(answer);
		System.exit(0);

	}
	public String decrypt(String messageToDecrypt, String password)
	{
		String unencryptedString = "";
		String messageToReturn = "";
		HashMap<Character, Integer> m1 = new HashMap();
		int countAlpha = 1;
		for(char alphabet = 'a'; alphabet <= 'z'; alphabet++, countAlpha++)
		{
			m1.put(alphabet, countAlpha);
		}
		char firstCharacter = password.charAt(0);
		char secondCharacter = password.charAt(1);
		boolean keepGoing = true;
		int i = 0;
		while(keepGoing)  
		{ //1 instead of 0 because first character is not used.
			char currentCharOfPass = password.charAt((i+1) % password.length());
			int currentCharOfPassInt = m1.get(currentCharOfPass);
			//System.out.println("The current letter of the password is :" + currentCharOfPass);
			//System.out.println("The current char value is: "+ currentCharOfPassInt);
			//System.out.println("The number of characters we will remove from the "
				//	+ "beginning of the string is " + currentCharOfPassInt);
			//filling gaps with currentCharInt number of random chars
			//while(messageToDecrypt.length() > 0)
			//{
			//System.out.println("deleting " + currentCharOfPassInt +  " characters");
			messageToDecrypt = messageToDecrypt.substring(currentCharOfPassInt); //MIGHT BE + OR - 1

			messageToReturn += messageToDecrypt.charAt(0);
			messageToDecrypt = messageToDecrypt.substring(1);
			//System.out.println("message so far: " + messageToReturn);
			//}
			System.out.println(messageToReturn);
			i++;
			//for(int j = 0; j < m1.get(i))
			if(messageToDecrypt.isEmpty())
			{
				//System.out.println(messageToReturn);
				System.exit(0);
				
			}
		}
		return messageToReturn;
	}

	public void start()
	{
		int canProceed = 1;
		for(;canProceed > 0;)
		{
			System.out.println("To encrypt, press 1.\n"
					+ "To decrypt, press 2. \n"
					+ "To quit, press 0.\n");
			int menuChoice = keyboard.nextInt();
			if (!(menuChoice > 2) && !(menuChoice < 0))
			{
				canProceed = -1;
				System.out.println("End"); //TODO:
				menu(menuChoice);
			}
		}
	}

	public void menu(int userChoice)
	{
		switch (userChoice)
		{
		case 0:
			System.exit(0);
			break;
		case 1:
			encryptMenu();
			break;
		case 2:
			decryptMenu(); 
			break;
		default:
			System.out.println("HOW DID YOU EVEN GET HERE??");
		}
	}

	public static void main(String[] args)
	{
		CipherMain c1 = new CipherMain();
		c1.start();
		//System.out.println(c1.randomChar());
	}




}

