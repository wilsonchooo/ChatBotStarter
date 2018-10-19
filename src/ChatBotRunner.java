import java.util.Scanner;

/**
 * A simple class to run our chatbot teams.
 * @author Brooklyn Tech CS Department
 * @version September 2018
 */
public class ChatBotRunner
{

	/**
	 * Create instances of each chatbot, give it user input, and print its replies. Switch chatbot responses based on which chatbot the user is speaking too.
	 */
	public static void main(String[] args)
	{
		ChatBot1 chatbot1 = new ChatBot1();
		ChatBot2 chatbot2 = new ChatBot2();
		ChatBot3 chatbot3 = new ChatBot3();
		

		Scanner in = new Scanner (System.in);
		System.out.println("Welcome to the chatbot, nice to meet you.");
		String statement = in.nextLine();
		//String choice = in.nextLine();

		while (!statement.equals("Bye"))
		{
			//Use Logic to control which chatbot is handling the conversation\
			//This example has only chatbot1

			System.out.println("Which chat bot do you want to talk to?");
			System.out.println("Fighting games");
			System.out.println("RPG Games");
			System.out.println("Shooter Games");

			 String choice = in.nextLine();
			if (choice.equals("fighting") || choice.equals("fighter")|| choice.equals("fighting games") || choice.equals("fighter games"))
			chatbot1.chatLoop(statement);

			if (choice.equals("rpg") || choice.equals("rpg games")|| choice.equals("RPG") || choice.equals("RPG games"))
				chatbot2.chatLoop(statement);

			if (choice.equals("shooting") || choice.equals("shooter")|| choice.equals("shooting games") || choice.equals("shooter games"))
				chatbot3.chatLoop(statement);

			statement = in.nextLine();


		}
	}

}
