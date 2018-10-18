import java.util.Random;
import java.util.Scanner;

/**
 * A program to carry on conversations with a human user.
 * This version:
 * @author Brooklyn Tech CS Department
 * @version September 2018
 */
public class ChatBot1
{
	//emotion can alter the way our bot responds. Emotion can become more negative or positive over time.
	int emotion = 0;
	boolean likegame = false;
	String[] games  = {"street Fighter", "smash Brothers", "tekken"};
  	String[] games2  = {"injustice", "mortal kombat", "dragon ball fighterZ"};
  	String[] allgames = {"street fighter", "smash brothers", "tekken", "injustice", "mortal kombat","dragon ball fighterz","soul calibur","mario party"};
   	String game = games[(int) Math.floor(Math.random() * 3)];
	boolean start = true;
	boolean asking = false;
	String statement = "";
    ChatBot2 chatbot2 = new ChatBot2();
    ChatBot3 chatbot3 = new ChatBot3();
    Scanner in = new Scanner (System.in);




	/**
	 * Runs the conversation for this particular chatbot, should allow switching to other chatbots.
	 * @param statement the statement typed by the user
	 */
	public void chatLoop(String statement)
	{


		System.out.println (getGreeting());
		System.out.println("whats your name?");

		statement=in.nextLine();
		String name = statement;
		System.out.println("hi "+name);
		System.out.println("Do you like fighting games like " + game + "?");



		while (!statement.equals("Bye"))
		{
			statement = in.nextLine();
			//getResponse handles the user reply
			System.out.println(getResponse(statement));
		}

	//	if (statement.equals("yurr"))
	//	{
		if (statement.equals("Bye")){

			if (likegame == true)
				System.out.println("Good bye my fellow fighting game fan ;)");

			else if (emotion ==1)
				System.out.println("don't forget to try out "+ game +":)");


			else if (emotion <= 0)
				System.out.println("see ya loser");
		}

		if (statement.equals("bye"))
		{
			if (likegame == true)
				System.out.println("Good bye my fellow fighting game fan ;)");

			else if (emotion ==1)
				System.out.println("don't forget to try out"+ game);


			else if (emotion <= 0)
				System.out.println("see ya loser");
		}


	}



	/**
	 * Get a default greeting 	
	 * @return a greeting
	 */	
	public String getGreeting()
	{
		return "whats poppin B";
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
			response = "Could you repeat that?";
		}

		else if (findKeyword(statement,
				"yes") >= 0)
		{
			if (start ==true)
			{
				System.out.println("yo, i like that game too!");
				likegame = true;
				response = "do you like 2d or 3d fighting games?";
				emotion++;
				start = false;
			}
			else
				response = "what?";
		}

		else if (findKeyword(statement,
				"2d") >= 0)
		{
			response = "what games do you like?";
			System.out.println("I see, I like them both equally.");
			asking = true;

			emotion++;
		}

		else if (findKeyword(statement,
				"3d") >= 0)
		{
			response = "what games do you like?";
			System.out.println("3d is nice too");

			emotion++;
		}

        else if (findKeyword(statement,
                "why") >= 0)
        {
            response = "no particular reason";
            emotion++;
			System.out.println("what games do you like?");

		}

		else if (findKeyword(statement,
				"why") >= 0)
		{
			response = "no particular reason";
			emotion++;
			System.out.println("what games do you like?");

		}


		else if (findKeyword(statement,
				"no") >= 0) {
			if (start == true) {
                emotion --;
                start = false;
                return "How about " + games2[(int) Math.floor(Math.random() * 3)] +" then?";
            }
                if (emotion == -1) {
                    System.out.println ("ah, I see.. Maybe you would be better off talking with my other friends.");
                    System.out.println("my first friend is really knowledgable about rpgs if thats your schtick.");
                    System.out.println("my second friend is really good at about shooters if you want to talk to him.");
					System.out.println("Do you like rpgs or shooting games?");


					String choice = in.nextLine();
					choice = choice.toLowerCase();

                    if (choice.equals("rpgs") || choice.equals("i like rpg games") || choice.equals("rpg"))
                        chatbot2.chatLoop(statement);

					if (choice.equals("shooting") || choice.equals("i like shooting games") || choice.equals("shooter"))
                        chatbot3.chatLoop(statement);

					else java.lang.System.exit(0);
                    start = false;

                }

			else if (start == false)
				response = "no to what?";
		}
		
		else if (findKeyword(statement, "levin") >= 0)
		{
			response = "More like LevinTheDream, amiright?";
			emotion++;
		}
		else if (findKeyword(statement, "folwell") >= 0)
		{
			response = "Watch your backpacks, Mr. Folwell doesn't fall well.";
			emotion++;
		}
		else if (findKeyword(statement, "goldman") >= 0)
		{
			response = "Go for the gold, man.";
			emotion++;
		}

		else if (findKeyword(statement, "goldman") >= 0)
		{
			response = "Go for the gold, man.";
			emotion++;
		}

		// Response transforming I want to statement
		else if (findKeyword(statement, "I want to", 0) >= 0)
		{
			response = transformIWantToStatement(statement);
		}
		else if (findKeyword(statement, "I like",0) >= 0)
		{
			response = transformILikeStatement(statement);

		}
		else if (findKeyword(statement, "my favorite",0) >= 0)
		{
			response = transformmyfavorite(statement);

		}
		else if (findKeyword(statement, "do you",0) >= 0)
		{
			response = doyou(statement);

		}

		else if (findKeyword(statement, "what is",0) >= 0)
		{
			response = transformmyfavorite(statement);











		}

		else if (findgame(statement,allgames) == true )
		{
			response = "yo i like that game too! I also like " + allgames[(int) Math.floor(Math.random() * allgames.length)];
			asking = false;

		}

		else if (findgame(statement,allgames) == false&& asking == true)
		{
			response = "Oh, I've never heard of that game.";
			asking = false;

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
		return "Why do you want to " + restOfStatement + "?";
	}

	
	/**
	 * Take a statement with "I want <something>." and transform it into 
	 * "Would you really be happy if you had <something>?"
	 * @param statement the user statement, assumed to contain "I want"
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
		return "Wow I really like " + restOfStatement + " as well" ;
	}

	private String transformmyfavorite(String statement)
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
		int psn = findKeyword (statement, "my favorite", 0);
		String restOfStatement = statement.substring(psn + 11).trim();
		return "Wow my favorite character is " + restOfStatement + " too!";
	}

	private String doyou(String statement)
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
		int psn = findKeyword (statement, "do you", 0);
		String restOfStatement = statement.substring(psn + 6).trim();
		return "Nah, i don't " + restOfStatement + ".";
	}

	private String transformwhatis(String statement)
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
		int psn = findKeyword (statement, "what is", 0);
		String restOfStatement = statement.substring(psn + 7).trim();
		return "Why do you want to " + restOfStatement + "?";
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

	private boolean findgame(String statement, String[] allgames)
	{
		statement = statement.toLowerCase();
		for (int i=0;i<allgames.length;i++)
		{
			if (statement.equals(allgames[i]))
				return true;
		}

		return false;
	}

	private String getRandomResponse ()
	{
		Random r = new Random ();
		if (emotion == 0)
		{	
			return randomNeutralResponses [r.nextInt(randomNeutralResponses.length)];
		}
		if (emotion < 0)
		{	
			return randomAngryResponses [r.nextInt(randomAngryResponses.length)];
		}	
		return randomHappyResponses [r.nextInt(randomHappyResponses.length)];
	}
	
	private String [] randomNeutralResponses = {"Interesting, tell me more",
			"Hmmm.",
			"Do you really think so?",
			"You don't say.",
			"It's all boolean to me.",
			"So, would you like to go for a walk?",
			"Could you say that again?",
			"How's the weather my dude",
			"I see..",
			"Tell me more.",



	};


	private String [] randomAngryResponses = {"STOP TALKING TO ME.", "I WANT TO DIE", "The rage consumes me!","AHHHHHHHHHHH", "im big angry"};
	private String [] randomHappyResponses = {"H A P P Y, what's that spell?", "today is lit af", "I wanna touch your hand", "You seem like a good guy.", "you should add me on steam ;)"};

}
