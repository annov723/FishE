import java.awt.event.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.awt.*;
import javax.swing.*;

public class Quiz implements ActionListener{
	 
	
	static final String[] questions = {
										"Which company created Java?",
										"Which year was Java created?",
										"What was Java originally called?",
										"Who is credited with creating Java?"
			
						 			  };
	static final String[][] options = {
										{
											"Sun Microsystems",
											"KFC",
											"Microsoft",
											"Alphabet"
										},
										{
											"1989",
											"1996",
											"1972",
											"1492"
										},
										{
											"Apple",
											"Latte",
											"Oak",
											"Koffing"
										},
										{
											"Steve Jobs",
											"Bill Gates",
											"James Gosling",
											"Harry Potter"
										}
						 };
	static final char[] answers = {
									'A',
									'B',
									'C',
									'C'
					 			  };
		
	private static final int SECONDS = 10; //how much time the user have to answer
	private static final int SIZE = questions.length; //in Java there is no #define
	private static final int TIME = 2000; //how much time the solusion is displayed
	
	int counter = SECONDS;
	int index = 0;
	int correct_guesses = 0;
	BigDecimal result; //so the percentage dan be rounded
	
	JFrame frame = new JFrame();
	JTextField txtF = new JTextField();
	JTextField txtP = new JTextField();
	JButton buttonsB[] = new JButton[SIZE];
	JLabel answerL[] = new JLabel[SIZE];
	JLabel secondsL = new JLabel();
	JTextField numberF = new JTextField();
	JTextField percentageF = new JTextField();
	String s = "00:";
	
	Timer timer =  new Timer( 1000, new ActionListener() { //every one second
		
		@Override
		public void actionPerformed( ActionEvent e ) {
			counter--;
			if( counter >= 10 ) s = "00:" + String.valueOf( counter );
			else s = "00:0" + String.valueOf( counter );
			secondsL.setText( s );
			if( counter <= 0 ) displayAnswer();
		}
	});
	
	
	
	public Quiz() {
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setSize( 450, 650 );
		frame.getContentPane().setBackground( new Color( 104, 105, 191 ) );
		frame.setLayout( null );
		frame.setResizable( false ); //peope cannot resize the window
		
		txtF.setBounds( 20, 80, 400, 50 );
		txtF.setBackground( new Color ( 104, 105, 191 ) );
		txtF.setFocusable( false );
		txtF.setForeground( new Color ( 255, 255, 255 ) );
		txtF.setBorder( javax.swing.BorderFactory.createEmptyBorder() ); //to remove the border
		txtF.setFont( new Font( "Trebuchet MS", Font.BOLD, 35 ) );
		txtF.setHorizontalAlignment( JTextField.CENTER );
		txtF.setEditable( false );
		//txtF.setText( "question 1" );
		
		txtP.setBounds( 20, 130, 400, 50 );
		txtP.setBackground( new Color ( 104, 105, 191 ) );
		txtP.setFocusable( false );
		txtP.setForeground( new Color ( 255, 255, 255 ) );
		txtP.setFont( new Font( "Trebuchet MS", Font.PLAIN, 25 ) );
		txtP.setBorder( javax.swing.BorderFactory.createEmptyBorder() );
		txtP.setEditable( false );
		txtP.setHorizontalAlignment( JTextField.CENTER );
		//txtP.setText( questions[0] );
		
		for( int i = 0; i < SIZE; i++ ) {
			buttonsB[i] = new JButton();
			buttonsB[i].setBounds( 50, 230 + ( i * 80 ), 50, 50 );
			buttonsB[i].setFont( new Font( "Trebuchet MS", Font.BOLD, 25 ) );
			buttonsB[i].setForeground( Color.white );
			buttonsB[i].setContentAreaFilled( false );
			buttonsB[i].setFocusable( false );
			buttonsB[i].setBorder( BorderFactory.createLineBorder( Color.white, 5) );
			buttonsB[i].addActionListener( this );
			char c = ( char )( 65 + i );
			buttonsB[i].setText( String.valueOf( c ) );
			buttonsB[i].setVisible( false );
		}
		for( int i = 0; i < SIZE; i++ ) {
			answerL[i] = new JLabel();
			answerL[i].setBounds( 120, 230 + ( i * 80 ), 280, 50 );
			answerL[i].setBackground( new Color( 104, 105, 191 ));
			answerL[i].setForeground( Color.white );
			answerL[i].setFont( new Font( "Trebuchet MS", Font.PLAIN, 25 ) );
			//answerL[i].setText( options[0][i] );
			answerL[i].setHorizontalAlignment( JLabel.LEFT );
		}
		
		secondsL.setBounds( 330, 10, 100, 50 );
		secondsL.setBackground( new Color( 104, 105, 191 ) );
		secondsL.setForeground( Color.white );
		secondsL.setFont( new Font( "Trebuchet MS", Font.BOLD, 25 ) );
		secondsL.setHorizontalAlignment( JLabel.CENTER );
		secondsL.setVisible( false );
		
		numberF.setBounds( 20, 230, 400, 80 );
		numberF.setBackground( new Color( 104, 105, 191 ) );
		numberF.setForeground( Color.white );
		numberF.setFont( new Font( "Trebuchet MS", Font.BOLD, 60 ) );
		numberF.setEditable( false );
		numberF.setBorder( javax.swing.BorderFactory.createEmptyBorder() );
		numberF.setHorizontalAlignment( JTextField.CENTER );
		//numberF.setText( "4/5" );
		
		percentageF.setBounds( 20, 300, 400, 50 );
		percentageF.setBackground( new Color( 104, 105, 191 ) );
		percentageF.setForeground( Color.white );
		percentageF.setFont( new Font( "Trebuchet MS", Font.PLAIN, 35 ) );
		percentageF.setEditable( false );
		percentageF.setBorder( javax.swing.BorderFactory.createEmptyBorder() );
		percentageF.setHorizontalAlignment( JTextField.CENTER );
		//percentageF.setText( "80%" );
		
		
		
		frame.add( txtF );
		frame.add( txtP );
		frame.add( secondsL );
		//frame.add( numberF );
		//frame.add( percentageF );
		for( int i = 0; i < SIZE; i++ ) {
			frame.add( buttonsB[i] );
			frame.add( answerL[i] );
		}
		frame.setVisible( true );
		
		nextQuestion();
	}
	
	public void nextQuestion() {
		if( index >= SIZE ) {
			results();
			return;
		}
		
		txtF.setText( "question " + ( index + 1 ) );
		txtP.setText( questions[index] );
		for( int i = 0; i < SIZE; i++ ) {
			buttonsB[i].setVisible( true );
			answerL[i].setText( options[index][i] );
		}
		if( SECONDS >= 10 ) s = "00:" + String.valueOf( SECONDS );
		else s = "00:0" + String.valueOf( SECONDS );
		secondsL.setText( s );
		secondsL.setVisible( true );
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		for( int i = 0; i < SIZE; i++ ) {
			buttonsB[i].setEnabled( false );
		}
		
		for( int i = 0; i < SIZE; i++ ) {
			if( e.getSource()==buttonsB[i] ) {
				if( buttonsB[i].getText().charAt( 0 ) == answers[index] ) {
					correct_guesses++;
				}
			}
		}
		displayAnswer();
	}
	
	public void displayAnswer() {
		timer.stop();
		secondsL.setVisible( false );

		UIManager.put( "Button.disabledText", Color.white ); //to control the color of disabled JButtons
		UIManager.put( "Button.disabledText", UIManager.getDefaults().getColor("Button.disabledText") );
		
		for( int i = 0; i < SIZE; i++ ) {
			buttonsB[i].setEnabled( false );
			if( answers[index] != buttonsB[i].getText().charAt( 0 ) ) {
				buttonsB[i].setBorder( BorderFactory.createLineBorder( Color.red, 5) ); //border color can be changed, no matter if the button is enabled or not
				
			}
			else {
				buttonsB[i].setBorder( BorderFactory.createLineBorder( Color.green, 5) );
			}
		}
		
		//delay - after the delay, the action is performed
		Timer pause =  new Timer( TIME, new ActionListener() {
			
			@Override
			public void actionPerformed( ActionEvent e ) {
				for( int i = 0; i < SIZE; i++ ) {
					buttonsB[i].setEnabled( true );
					buttonsB[i].setBorder( BorderFactory.createLineBorder( Color.white, 5) );
				}
				counter = SECONDS;
				secondsL.setText( s );
				index++;
				nextQuestion();
			}
		});
		
		pause.setRepeats( false ); //timer ActionPerformed executed once
		pause.start();
		
		
		
	}
	
	public void results() {
		timer.stop();
		secondsL.setVisible( false );
		
		for( int i = 0; i < SIZE; i++ ) {
			buttonsB[i].setEnabled( false );
			buttonsB[i].setVisible( false );
			answerL[i].setVisible( false );
		}
		result = BigDecimal.valueOf( ( correct_guesses / ( double ) SIZE ) * 100 ).setScale(2, RoundingMode.HALF_UP);		
	
		txtF.setFont( new Font( "Trebuchet MS", Font.BOLD, 60 ) );
		txtF.setBounds( 20, 160, 400, 70 );
		txtP.setVisible( false );
		numberF.setText( correct_guesses + "/" + SIZE );
		percentageF.setText( result.toString() + "%" );
		if( result.doubleValue() > 75 ) txtF.setText( "Congrats!" );
		else if( result.doubleValue() > 50 ) txtF.setText( "Well done!" );
		else if( result.doubleValue() > 25 ) txtF.setText( "Good effort!" );
		else txtF.setText( "Keep going!" );
	
		frame.add( numberF );
		frame.add( percentageF );
		
		//here add some button to close the quiz window or go to main menu? (if it should become a game with several quizes...)
	}
}
