import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Data {
	
	private final static String path = "dat.txt";
	File file;
	int size;
	ArrayList<String> titles;

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
	
	
	
}
