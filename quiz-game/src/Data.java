import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class Data {
	
	private final static String path = "dat.txt";
	private File file;
	private int numberOfTopics;
	private int numberOfQuestions;
	private ArrayList<String> titles;
	private int chosenTitle = -1;
	
	//UPGRADED: this should stay as for the upgrade that the data is loaded once if the user play with the same quiz once again
	private ArrayList<String> questions = new ArrayList<>();
	private ArrayList<ArrayList<String>> answers = new ArrayList<>();
	private ArrayList<Character> correct = new ArrayList<>();
	
	private ArrayList<String> pickedQuestions = new ArrayList<>();
	private ArrayList<ArrayList<String>> pickedAnswers = new ArrayList<>();
	private ArrayList<Character> pickedCorrect = new ArrayList<>();
	
	
	
	Data() {
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
	
	void generateQuiz( final int howMany, final String title ) {
		if( chosenTitle != titles.indexOf( title ) ) getQuizFromFile( title );
		generateRandom( howMany );
	}
	
	void generateRandom( final int howMany ) {
		Random rand = new Random();
		int randomIndex;
		for( int i = 0; i < howMany; i++ ) {
			randomIndex = rand.nextInt( numberOfQuestions );
			if( !pickedQuestions.contains( questions.get( randomIndex ) ) ) {
				pickedQuestions.add( questions.get( randomIndex ) );
				pickedAnswers.add( answers.get( randomIndex ) );
				pickedCorrect.add( correct.get( randomIndex ) );
			}
			else {
				i--;
			}
		}
	}
	
	void getQuizFromFile( final String title ) {
		try {
			pickedClear();
			dataClear();
			
			BufferedReader buff = new BufferedReader( new FileReader( file ) );
			String line = null;
			
			for( int i = 0; i < 5; i++ ) line = buff.readLine();
			
			chosenTitle = titles.indexOf( title );
			for( int i = 0; i < chosenTitle * ( numberOfQuestions + 3 ); i++ ) line = buff.readLine();
			
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
			
			buff.close();
		}
		catch( Exception exp ){
			System.err.println( exp );
			System.exit( -1 ); //closes the app because there is a trouble using it
		}
	}
	
	void generateFishe( final String title ) {
		if( chosenTitle != titles.indexOf( title ) ) getQuizFromFile( title );
		generateRandom( numberOfQuestions );
	}
	
	ArrayList<String> getQuestions() {
		return pickedQuestions;
	}
	
	ArrayList<ArrayList<String>> getAnswers() {
		return pickedAnswers;
	}
	
	ArrayList<Character> getCorrect() {
		return pickedCorrect;
	}
	
	ArrayList<String> getCorrectAnswersOnly() {
		ArrayList<String> correctAnswers = new ArrayList<>();
		
		for( int i = 0; i < numberOfQuestions; i++ ) correctAnswers.add( pickedAnswers.get( i ).get( ( int ) pickedCorrect.get( i ) - 'A' ) );
		return correctAnswers;
	}
	
	void pickedClear() {
		if( pickedQuestions != null ) pickedQuestions.clear();
		if( pickedAnswers != null ) pickedAnswers.clear();
		if( pickedCorrect != null ) pickedCorrect.clear();
	}
	
	void dataClear() {
		if( questions != null ) questions.clear();
		if( answers != null ) answers.clear();
		if( correct != null ) correct.clear();
	}
}
