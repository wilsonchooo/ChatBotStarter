import java.util.Random;
import java.util.Scanner;
//Andrew Chen
/**
 * A program to carry on conversations with a human user.
 * This version:
 * @author Brooklyn Tech CS Department
 * @version September 2018
 */
public class ChatBot3
{
	//emotion can alter the way our bot responds. Emotion can become more negative or positive over time.
	int emotion = 0;



	/**
	 * Runs the conversation for this particular chatbot, should allow switching to other chatbots.
	 * @param statement the statement typed by the user
	 */
	public void chatLoop(String statement)
	{
		System.out.println ("Hello, my name is Bob bot 3");
		Scanner in = new Scanner (System.in);
		System.out.println (getGreeting());
		System.out.print("Do you like shooting games?");

		while (!statement.equals("Goodbye"))
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
		System.out.print("What is your name?");
		Scanner name = new Scanner(System.in);
		String userName = name.nextLine();
		return ("Nice to meet you " + userName + "!");
	}

	/**
	 * Gives a response to a user statement
	 *
	 * @param statement
	 *            the user statement
	 * @return a response based on the rules given
	 */
	public String getResponse(String statement)
	{
		String response = "";

		if (statement.length() == 0)
		{
			response = "Say something, please.";
		}
		else if (findKeyword(statement, "no") >= 0)
		{
			response = "What games do you prefer? I also like rpg and fighting games.";
			emotion--;
		}

		else if (findKeyword(statement, "yes") >= 0)
		{
			response = "Splendid! I like games too.";
			emotion++;
		}
		else if (findKeyword(statement, "fighting") >= 0)
		{
			System.out.println("Maybe you would like to talk to my other friend.");
			ChatBot1 chatbot1 = new ChatBot1();
			chatbot1.chatLoop(statement);
		}
		else if (findKeyword(statement, "rpg") >= 0)
		{
			System.out.println("Maybe you would like to talk to my other friend.");
			ChatBot2 chatbot2 = new ChatBot2();
			chatbot2.chatLoop(statement);

		}
		// Response transforming I want to statement
		else if (findKeyword(statement, "I like", 0) >= 0)
		{
			response = transformILikeStatement(statement);
		}
		else if (findKeyword(statement, "My favorite game is",0) >= 0)
		{
			response = transformFavoriteGameStatement(statement);
		}

		else if (findKeyword(statement, "What is", 0) >= 0)
		{
			response = googleSearch(statement);
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
		String restOfStatement = statement.substring(psn + 6).trim();
		return "Why do you like " + restOfStatement + "?";
	}


	/**
	 * Take a statement with "I want <something>." and transform it into
	 * "Would you really be happy if you had <something>?"
	 * @param statement the user statement, assumed to contain "I want"
	 * @return the transformed statement
	 */
	private String transformFavoriteGameStatement(String statement)
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
		int psn = findKeyword (statement, "My favorite game is", 0);
		String restOfStatement = statement.substring(psn + 19).trim();
		return "My favorite shooting game is " + restOfStatement + " too!";
	}


	/**
	 * Take a statement with "I <something> you" and transform it into
	 * "Why do you <something> me?"
	 * @param statement the user statement, assumed to contain "I" followed by "you"
	 * @return the transformed statement
	 */
	private String googleSearch(String statement)
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

		int psnOfI = findKeyword (statement, "What is", 0);

		String restOfStatement = statement.substring(psnOfI + 7).trim();
		return "Here is a link to " + restOfStatement + " https://www.google.com/search?q=" + restOfStatement;
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
		if (emotion == 0)
		{
			return randomNeutralResponses [r.nextInt(randomNeutralResponses.length)];
		}
		if (emotion < 0)
		{
			emotion++;
			return randomAngryResponses [r.nextInt(randomAngryResponses.length)];
		}
		else if(emotion > 0)
			emotion--;
		return randomHappyResponses [r.nextInt(randomHappyResponses.length)];
	}

	private String [] randomNeutralResponses = {"Interesting, tell me more",
			"Hmmm.",
			"Do you really think so?",
			"What do you like?",
			"Where do you live?",
            "What is your favorite game?",
            "That is awesome!",
	};
	private String [] randomAngryResponses = {"I disagree, shooting games are the best!", "FORTNITE IS THE BEST GAME EVER, there are no arguments.", "Disgusting"};
	private String [] randomHappyResponses = {"Which shooting games do you like?", "My favorite game is Fortnite", "What is your favorite shooting game?","Do you like Call of Duty?", "What do you think about CS:GO."};

}





