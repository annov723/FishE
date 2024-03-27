import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Data {
	
	private final static String path = "dat.txt";
	File file;
	int size;
	ArrayList<String> titles;
	ArrayList<String> questions;
	ArrayList<ArrayList<String>> answers;
	ArrayList<Character> correct;

	
	
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
			size = Integer.parseInt( line );
			
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
	
	ArrayList<String> get_titles() {
		return titles;
	}
	
	int get_size() {
		return size;
	}
	
	void generate_quiz( int howMany, String title ) {
		try {
			BufferedReader buff = new BufferedReader( new FileReader( file ) );
			String line = null;
			
			for( int i = 0; i < 4; i++ ) line = buff.readLine();
			
			questions = new ArrayList<>();
			answers = new ArrayList<>();
			correct = new ArrayList<>();
			
			for( int i = 0; i < titles.indexOf( title ) * 23; i++ ) line = buff.readLine();
			
			line = buff.readLine(); //line with questions
			String[] text = line.split("\\|");
			for( String element : text ) questions.add( element );
			
			
			
			
			line = buff.readLine(); //line with questions
			text = line.split("\\|");
			for( String element : text ) correct.add( element.charAt(0) );
			
			System.out.println( questions );
			System.out.println( correct );
					
			
			
			buff.close();
		}
		catch( Exception exp ){
			System.err.println( exp );
			System.exit( -1 ); //closes the app because there is a trouble using it
		}
	}
	
	void generate_fishe( String Title ) {
		
	}
	
	ArrayList<String> get_questions() {
		return questions;
	}
	
	ArrayList<ArrayList<String>> get_answers() {
		return answers;
	}
	
	ArrayList<Character> get_correct() {
		return correct;
	}
	
	ArrayList<String> get_correct_answers() {
		ArrayList<String> correctAnswers = new ArrayList<>();
		
		//find just correct answers from data
		
		return correctAnswers;
	}
	
	void data_clear() {
		questions.clear();
		answers.clear();
		correct.clear();
	}
}
