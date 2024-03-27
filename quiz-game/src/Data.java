import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class Data {
	
	private final static String path = "dat.txt";
	File file;
	int numberOfTopics;
	int numberOfQuestions;
	ArrayList<String> titles;
	
	//this should stay as for the upgrade that the data is loaded once if the user play with the same quiz once again
	ArrayList<String> questions;
	ArrayList<ArrayList<String>> answers;
	ArrayList<Character> correct;
	
	ArrayList<String> pickedQuestions;
	ArrayList<ArrayList<String>> pickedAnswers;
	ArrayList<Character> pickedCorrect;
	
	
	
	Data() {
		System.out.println( "konstruktor data" );
		exist();
	}
	
	void exist() {
		//check if file exists because without it there is no point in opening the app
		file = new File( path );
		if( !file.exists() ) {
			System.out.println( "There is no \"dat.txt\" file!" );
			System.exit( -1 );
		}
	}
	
	void start() {
		
		try {
			BufferedReader buff = new BufferedReader( new FileReader( file ) );
			String line = null;
			
			line = buff.readLine();
			if( !line.equals( "__FishE__" ) ) {
				System.out.println( "\"dat.txt\" file is incorrect!" );
				System.exit( -1 );
			}
			
			line = buff.readLine();
			numberOfTopics = Integer.parseInt( line );
			line = buff.readLine();
			numberOfQuestions = Integer.parseInt( line );
			
			titles = new ArrayList<>();
			line = buff.readLine();
			String[] tit = line.split("\\|");
			
			for( String element : tit ) titles.add( element );
					
			buff.close();
		}
		catch( Exception exp ){
			System.err.println( exp );
			System.exit( -1 ); //closes the app because there is a trouble using it
		}
	}
	
	ArrayList<String> getTitles() {
		return titles;
	}
	
	int getSize() {
		return numberOfTopics;
	}
	
	void generateQuiz( int howMany, String title ) {
		try {
			BufferedReader buff = new BufferedReader( new FileReader( file ) );
			String line = null;
			
			for( int i = 0; i < 5; i++ ) line = buff.readLine();
			
			questions = new ArrayList<>();
			answers = new ArrayList<>();
			correct = new ArrayList<>();
			
			for( int i = 0; i < titles.indexOf( title ) * ( numberOfQuestions + 3 ); i++ ) line = buff.readLine();
			
			line = buff.readLine(); //line with questions
			String[] text = line.split("\\|");
			for( String element : text ) questions.add( element );
			
			for( int i = 0; i < numberOfQuestions; i++ ) {
				ArrayList<String> nextQuestionsAnswers = new ArrayList<>();
				line = buff.readLine(); //line with questions
				text = line.split("\\|");
				for( String element : text ) nextQuestionsAnswers.add( element );
				answers.add( nextQuestionsAnswers );
			}
			
			
			line = buff.readLine(); //line with questions
			text = line.split("\\|");
			for( String element : text ) correct.add( element.charAt(0) );
			
			System.out.println( questions );
			System.out.println( answers );
			System.out.println( correct );
					
			
			//now get random questions
			Random rand = new Random();
			int randomIndex;
			for( int i = 0; i < howMany; i++ ) {
				randomIndex = rand.nextInt(questions.size());
			}
		    
			
			
			buff.close();
		}
		catch( Exception exp ){
			System.err.println( exp );
			System.exit( -1 ); //closes the app because there is a trouble using it
		}
	}
	
	void generateFishe( String title ) {
		
	}
	
	ArrayList<String> getQuestions() {
		return questions;
	}
	
	ArrayList<ArrayList<String>> getAnswers() {
		return answers;
	}
	
	ArrayList<Character> getCorrect() {
		return correct;
	}
	
	ArrayList<String> getCorrectAnswersOnly() {
		ArrayList<String> correctAnswers = new ArrayList<>();
		
		//find just correct answers from data
		
		return correctAnswers;
	}
	
	void dataClear() {
		//it will not be needed due to reusing upgrade
		if( questions != null ) questions.clear();
		if( answers != null ) answers.clear();
		if( correct != null ) correct.clear();
		
		if( pickedQuestions != null ) pickedQuestions.clear();
		if( pickedAnswers != null ) pickedAnswers.clear();
		if( pickedCorrect != null ) pickedCorrect.clear();
	}
}
