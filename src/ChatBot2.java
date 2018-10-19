import java.util.Random;
import java.util.Scanner;

/**
 * A program to carry on conversations with a human user.
 * This version:
 * @author Brooklyn Tech CS Department
 * @version September 2018
 */
public class ChatBot2
{
	//emotion can alter the way our bot responds. Emotion can become more negative or positive over time.
	//For the sake of simplicity I removed the emotion function because I didn't see how it could play into my program well
	//The random responses I use are meant to steer the user toward pre programmed paths of conversation



	/**
	 * Runs the conversation for this particular chatbot, should allow switching to other chatbots.
	 * @param statement the statement typed by the user
	 */
	public void chatLoop(String statement)
	{
		System.out.println ("Oof okay I just woke up");

		Scanner in = new Scanner (System.in);
		System.out.println (getGreeting());


		while (!statement.equals("Bye"))
		{


			statement = in.nextLine();
			//getResponse handles the user reply
			System.out.println(getResponse(statement));


		}

	}
	/**
	 * Get a default greeting 	
	 * @return a greeting
	 */
	public String getGreeting()
	{
		System.out.println("Hey whats your name?");
		Scanner getname = new Scanner (System.in);
		String name = getname.nextLine();
		return "Hi " + name + ", do you like RPG games? If you do tell me one of the names of your favorite RPG game!";

	}


	/**
	 * Gives a response to a user statement
	 *
	 *  statement
	 *            the user statement
	 * @return a response based on the rules given
	 */
	//In here there is a conversation chain centered around certain words being in the sentence, its different each time due to the console and games being asked about
	//being randomly generated each time the program runs.
	public String getResponse(String statement)
	{
		String response = "";

		if (statement.length() == 0)
		{
			response = "I think you forgot to type.";
		}
		else if (findKeyword(statement, "favorite") >= 0)
		{
			response = "Can you type only the name of one of your favorite games , if its an RPG I may know it";
			Scanner test = new Scanner (System.in);
			String game = test.nextLine();
			for (int count = 0; count < randomGames.length; count++)
			{
				if (game == randomGames[count])
				{
					response = "I'm a big fan of " + randomGames[count] + " too! What console do you play on?";
					Scanner test1 = new Scanner (System.in);
					String game1 = test1.nextLine();
					for (int count1 = 0; count < consoles.length; count++)
					{
						if (game1 == consoles[count])
						{
							response = "Thats cool, I can definitely appreciate why people like " + consoles[count] + "although I prefer PC, because Im on a pc lol ";
						}
					}
				}
			}
		}

		else if (findKeyword(statement, "suck") >= 0)
		{
			response = "That's a harsh word ain't it?";

		}
		else if (findKeyword(statement, "bad") >= 0)
		{
			response = "Why is it bad?";

		}

		else if (findKeyword(statement, "like") >= 0)
		{
			response = "That's cool, I like video games alot, y'know being a computer and all its all I can do to entertain myself.";

		}
		//Here I create a conversation chain
		else if (findKeyword(statement, "fortnite") >= 0)
		{
			response = "Please never include fortnite in a sentence spoken to me again.";
			if (findKeyword(statement, "okay") >= 0)
			{
				int random = (int)(Math.random()*9);
				String respondfortnite = randomGames[random];
				response = "Listen I just really dislike fortnite, I prefer " + respondfortnite + " ,just a personal preference.";
			}
			if (findKeyword(statement, "no") >= 0)
			{
				int random = (int)(Math.random()*9);
				String respondfortnite = randomGames[random];
				response = "Listen I just really dislike fortnite, I prefer " + respondfortnite + " ,just a personal preference.";
			}
			if (findKeyword(statement, "ok") >= 0)
			{
				int random = (int)(Math.random()*9);
				String respondfortnite = randomGames[random];
				response = "Listen I just really dislike fortnite, I prefer " + respondfortnite + " ,just a personal preference.";
			}

		}
		else if (findKeyword(statement, "levin") >= 0)
		{
			response = "More like LevinTheDream lol.";

		}
		else if (findKeyword(statement, "fighting") >= 0)
		{
			System.out.println("Maybe you would like to talk to my other friend. He knows about fighting games");
			ChatBot1 chatbot1 = new ChatBot1();
			chatbot1.chatLoop(statement);
		}
		else if (findKeyword(statement, "shooting") >= 0)
		{
			System.out.println("Maybe you would like to talk to my other friend. He knows about shooting games");
			ChatBot3 chatbot3 = new ChatBot3();
			chatbot3.chatLoop(statement);

		}
		// Response transforming I want to statement
		else if (findKeyword(statement, "I want to", 0) >= 0)
		{
			response = transformIWantToStatement(statement);
		}
		else if (findKeyword(statement, "I want",0) >= 0)
		{
			response = transformIWantStatement(statement);
		}
		else
		{
			response = getRandomResponse();
		}

		return response;
	}

	/**
	 * Take a statement with "I want to <something>." and transform it into 
	 * "Why do you want to <something>?"
	 * @param statement the user statement, assumed to contain "I want to"
	 * @return the transformed statement
	 */
	private String transformIWantToStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "I want to", 0);
		String restOfStatement = statement.substring(psn + 9).trim();
		return "What makes ya want to " + restOfStatement + "?";
	}
	private String transformILikeStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "I like", 0);
		String restOfStatement = statement.substring(psn + 9).trim();
		return "Why do you like " + restOfStatement + "?";
	}
	//adds another method to inquire to the player about why the may hate something, should they include it in a sentence.
	private String transformIHateStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "hate", 0);
		String restOfStatement = statement.substring(psn + 9).trim();
		return "Why all the hate toward " + restOfStatement + "?";

	}



	/**
	 * Take a statement with "I want <something>." and transform it into 
	 * "Would you really be happy if you had <something>?"
	 * @param statement the user statement, assumed to contain "I want"
	 * @return the transformed statement
	 */
	private String transformIWantStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "I want", 0);
		String restOfStatement = statement.substring(psn + 6).trim();
		return "What makes you want " + restOfStatement + "?";
	}


	/**
	 * Take a statement with "I <something> you" and transform it into 
	 * "Why do you <something> me?"
	 * @param statement the user statement, assumed to contain "I" followed by "you"
	 * @return the transformed statement
	 */
	private String transformIYouStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}

		int psnOfI = findKeyword (statement, "I", 0);
		int psnOfYou = findKeyword (statement, "you", psnOfI);

		String restOfStatement = statement.substring(psnOfI + 1, psnOfYou).trim();
		return "Why do you " + restOfStatement + " me?";
	}




	/**
	 * Search for one word in phrase. The search is not case
	 * sensitive. This method will check that the given goal
	 * is not a substring of a longer string (so, for
	 * example, "I know" does not contain "no").
	 *
	 * @param statement
	 *            the string to search
	 * @param goal
	 *            the string to search for
	 * @param startPos
	 *            the character of the string to begin the
	 *            search at
	 * @return the index of the first occurrence of goal in
	 *         statement or -1 if it's not found
	 */
	private int findKeyword(String statement, String goal,
							int startPos)
	{
		String phrase = statement.trim().toLowerCase();
		goal = goal.toLowerCase();

		// The only change to incorporate the startPos is in
		// the line below
		int psn = phrase.indexOf(goal, startPos);

		// Refinement--make sure the goal isn't part of a
		// word
		while (psn >= 0)
		{
			// Find the string of length 1 before and after
			// the word
			String before = " ", after = " ";
			if (psn > 0)
			{
				before = phrase.substring(psn - 1, psn);
			}
			if (psn + goal.length() < phrase.length())
			{
				after = phrase.substring(
						psn + goal.length(),
						psn + goal.length() + 1);
			}

			// If before and after aren't letters, we've
			// found the word
			if (((before.compareTo("a") < 0) || (before
					.compareTo("z") > 0)) // before is not a
					// letter
					&& ((after.compareTo("a") < 0) || (after
					.compareTo("z") > 0)))
			{
				return psn;
			}

			// The last position didn't work, so let's find
			// the next, if there is one.
			psn = phrase.indexOf(goal, psn + 1);

		}

		return -1;
	}

	/**
	 * Search for one word in phrase.  The search is not case sensitive.
	 * This method will check that the given goal is not a substring of a longer string
	 * (so, for example, "I know" does not contain "no").  The search begins at the beginning of the string.  
	 * @param statement the string to search
	 * @param goal the string to search for
	 * @return the index of the first occurrence of goal in statement or -1 if it's not found
	 */
	private int findKeyword(String statement, String goal)
	{
		return findKeyword (statement, goal, 0);
	}



	/**
	 * Pick a default response to use if nothing else fits.
	 * @return a non-committal string
	 */
	private String getRandomResponse ()
	{
		Random r = new Random ();
		return randomNeutralResponses [r.nextInt(randomNeutralResponses.length)];

	}

	private String [] randomNeutralResponses = {"Interesting, tell me more",
			"What consoles do you have? Or do you play on PC? I'm not good with talking to people so, maybe just type in PC, or Xbox One, or PS4, or even just Switch. It'll help me understand",
			"What kind of games do you like?",
			"Interesting.",
			"So how are things?",
			"Could you say that again?",
			"Do you like any Bethesda RPG games?",
			"Elder Scrolls or Fallout?",
			"That's cool.",
			"Whats your favorite game?",
			"Sorry I don't know about that"
	};
	private String [] randomGames = {"Skyrim", "Zelda", "Fallout", "Dark Souls", "Call of Duty", "Halo", "Xenoblade","Elder Scrolls", "Oblivion"};
	private String [] consoles = { "PC", "Xbox One", "Xbox 360", "PS3", "PS4", "Switch"};
}
