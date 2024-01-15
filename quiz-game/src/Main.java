//class for storing all the quizes data
class DataStruct {
	int amount;
	String title;
    String[] questions;
    String[] options;
    String[] answers;

    public DataStruct( int amount, String title, String[] questions, String[] options, String[] answers ) {
        this.amount = amount;
        this.title = title;
        this.questions = questions;
        this.options = options;
        this.answers = answers;
    }
}

public class Main {

	public static void main(String[] args) {
		
		Quiz quiz = new Quiz();
		
	}

}

/**
 * ### DATA DESCRIPTION ###
 * load quizes data from file
 * there is a some kind of sturcture which holds a stucture elements, each with 5 elements: int number of questions, string title of the quiz, string[] questions, string[][] answers, string[] good answer
 * when the file is open, the info is sent to the Menu class
 * one structure element in one line, all new strings seperated with ";"
 */

/**
 * ### MENU DESCRIPTION ###
 * game logo and title (animated?)
 * 3 buttons - quiz, FishE, quit
 * window appears at the center of the screen, blocked for size changes, can be closed only with quit button
 * all the buttons should be animated when enetered!
 * when quiz or FishE clicked:
 * 			titles of all the quizes appeared (maybe in a slide list?) and you can pick one
 * 			then:	
 * FishE:
 * 			there are questions
 * 			back button
 * 			arrow left and arrow right buttons
 * 			the "empty" button (with no text, just a frame), where the answer appears when is clicked
 * 			the user can go through all the questions and learn the correct answers
 * 			the questions are shuffled each time and the HashMap is created? to hold the questions and corrects answers
 * Quiz:
 * 			there is a panel with 2 things to set:
 * 					amount of time for each question (5, 10, 20, 30) - plus and minus to change the number at the left and right
 * 					amount of questions (5, 10, 15, ..., 30), not more than possible in the chosen category - the same appearance as the amount of time
 * 			this data is send to the Quiz.java
 * 			also the structure with chosen categeory elements is sent
 * 			questions and options order is shuffled every time the quiz is run
 */

/**
 * ### QUIZ DESCRIPTION ###
 * 3 hearts in the left upper corner (wrong answer or time is up = lost 1 heart -> without hearts the quiz ends)
 * DONE: back button to go back to the main menu with pop-up info if the user is sure about ending the quiz
 * DONE: window appears at the center of the screen
 * DONE: window cannot be closed with the close button!
 */

/* Some ideas:
 * - hearts you lose when you make wrong choice
 * - quizes info loaded from file
 * - main menu with play (some quizes you can choose from), FishE and exit button
 * - from 5 to for example 20 questions per game (user can choose how many, and different number of hearts in different quiz option)
 * - questions are randomly picked from the list (maybe a HashMap to store if an answer is good or not? it will be easier to go through the questions)
 * - nice logo and name for the app
 * - different difficulty lvl's depend on how much time do you have to answer the question
 * - easter egg: if you tap the logo the new window appear where the herd of wild fishes is chasing (following) the mouse ^^
 */

/* Ideas for further upgrades:
 * - when back button in quiz is clicked the time stops? (it is something like a game pause)
 * 
 */
