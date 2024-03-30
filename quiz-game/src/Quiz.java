import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import javax.swing.*;

public class Quiz implements ActionListener, MouseListener{
	
	private ArrayList<String> questions;
	private ArrayList<ArrayList<String>> answers;
	private ArrayList<Character> correct;
		
	private final int numberOfAnswers = 4; //in Java there is no #define
	private final int time = 2000; //how much time the solusion is displayed
	
	private int timeForQuestion;
	private int numberOfQuestions;
	private int timeCounter;
	private int index = 0;
	private int correctGuesses = 0;
	private BigDecimal result; //so the percentage doesn't be rounded
	
	private JFrame frame;
	private JTextField questionNumberF = new JTextField();
	private JTextField questionF = new JTextField();
	private JButton[] buttonsB = new JButton[numberOfAnswers];
	private JButton[] answerB = new JButton[numberOfAnswers];
	private JLabel secondsL = new JLabel();
	private JTextField numberF = new JTextField();
	private JTextField percentageF = new JTextField();
	private String timeString = "00:";
	
	private ImageIcon icon = new ImageIcon( "mini.png" ); //an icon for menu display
	
	private JButton backB = new JButton();
	private ImageIcon heart1 = new ImageIcon( "heart2.png" );
	private ImageIcon heart2 = new ImageIcon( "heart2.png" );
	private ImageIcon heart3 = new ImageIcon( "heart2.png" );
	private JLabel heart1L;
	private JLabel heart2L;
	private JLabel heart3L;
	private JPanel hearts = new JPanel();
	private int heartCount = 3;
	
	Timer timer =  new Timer( 1000, new ActionListener() { //every one second
		@Override
		public void actionPerformed( ActionEvent e ) {
			timeCounter--;
			if( timeCounter >= 10 ) timeString = "00:" + String.valueOf( timeCounter );
			else timeString = "00:0" + String.valueOf( timeCounter );
			secondsL.setText( timeString );
			if( timeCounter <= 0 ) {
				heartCount--;
				displayAnswer();
			}
		}
	});
	
	
	Quiz( int timeLimit, ArrayList<String> q, ArrayList<ArrayList<String>> a, ArrayList<Character> ch ) {
		timeCounter = timeForQuestion = timeLimit;
		numberOfQuestions = q.size();
		questions = new ArrayList<>( q );
		answers = new ArrayList<>( a );
		correct = new ArrayList<>( ch );
		
		frame = new JFrame( "FishE" );
		frame.setSize( 450, 800 );
		frame.getContentPane().setBackground( new Color( 104, 105, 191 ) );
		frame.setLayout( null );
		frame.setResizable( false ); //people cannot resize the window
		frame.setLocationRelativeTo( null ); //appears in the center
		frame.setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE );
		frame.setIconImage( icon.getImage() );
		
		questionNumberF.setBounds( 20, 80, 400, 50 );
		questionNumberF.setBackground( new Color ( 104, 105, 191 ) );
		questionNumberF.setFocusable( false );
		questionNumberF.setForeground( new Color ( 255, 255, 255 ) );
		questionNumberF.setBorder( javax.swing.BorderFactory.createEmptyBorder() ); //to remove the border
		questionNumberF.setFont( new Font( "Trebuchet MS", Font.BOLD, 35 ) );
		questionNumberF.setHorizontalAlignment( JTextField.CENTER );
		questionNumberF.setEditable( false );
		
		questionF.setBounds( 20, 130, 400, 50 );
		questionF.setBackground( new Color ( 104, 105, 191 ) );
		questionF.setFocusable( false );
		questionF.setForeground( new Color ( 255, 255, 255 ) );
		questionF.setFont( new Font( "Trebuchet MS", Font.PLAIN, 25 ) );
		questionF.setBorder( javax.swing.BorderFactory.createEmptyBorder() );
		questionF.setEditable( false );
		questionF.setHorizontalAlignment( JTextField.CENTER );
		
		for( int i = 0; i < numberOfAnswers; i++ ) {
			buttonsB[i] = new JButton();
			buttonsB[i].setBounds( 50, 230 + ( i * 80 ), 50, 50 );
			buttonsB[i].setFont( new Font( "Trebuchet MS", Font.BOLD, 25 ) );
			buttonsB[i].setForeground( Color.white );
			buttonsB[i].setContentAreaFilled( false );
			buttonsB[i].setFocusable( false );
			buttonsB[i].setBorder( BorderFactory.createLineBorder( Color.white, 5 ) );
			buttonsB[i].addActionListener( this );
			buttonsB[i].addMouseListener( this );
			char c = ( char )( 65 + i );
			buttonsB[i].setText( String.valueOf( c ) );
			buttonsB[i].setVisible( true );
		}
		
		for( int i = 0; i < numberOfAnswers; i++ ) {
			answerB[i] = new JButton();
			answerB[i].setBounds( 120, 230 + ( i * 80 ), 280, 50 );
			answerB[i].setForeground( Color.white );
			answerB[i].setFont( new Font( "Trebuchet MS", Font.PLAIN, 25 ) );
			answerB[i].setHorizontalAlignment( JLabel.LEFT );
			answerB[i].setContentAreaFilled( false );
			answerB[i].setFocusable( false );
			answerB[i].setBorder( null );
			answerB[i].addActionListener( this );
			answerB[i].addMouseListener( this );
			
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
		
		percentageF.setBounds( 20, 300, 400, 50 );
		percentageF.setBackground( new Color( 104, 105, 191 ) );
		percentageF.setForeground( Color.white );
		percentageF.setFont( new Font( "Trebuchet MS", Font.PLAIN, 35 ) );
		percentageF.setEditable( false );
		percentageF.setBorder( javax.swing.BorderFactory.createEmptyBorder() );
		percentageF.setHorizontalAlignment( JTextField.CENTER );
		percentageF.setVisible( false );
		
		backB.setBounds( 250, 665, 150, 65 );
		backB.setForeground( Color.white );
		backB.setFont( new Font( "Trebuchet MS", Font.PLAIN, 25 ) );
		backB.setContentAreaFilled( false );
		backB.setFocusable( false );
		backB.setBorder( BorderFactory.createLineBorder( Color.white, 7 ) );
		backB.addActionListener( this );
		backB.addMouseListener( this );
		backB.setText( "back" );
		backB.setVisible( true );
		
		//hearts
		Image logoBefore = heart1.getImage();
		Image logoAfter = logoBefore.getScaledInstance( 40, 40, java.awt.Image.SCALE_SMOOTH );
		heart1 = new ImageIcon( logoAfter );
		heart1L = new JLabel( heart1 );
		logoBefore = heart2.getImage();
		logoAfter = logoBefore.getScaledInstance( 40, 40, java.awt.Image.SCALE_SMOOTH );
		heart2 = new ImageIcon( logoAfter );
		heart2L = new JLabel( heart2 );
		logoBefore = heart3.getImage();
		logoAfter = logoBefore.getScaledInstance( 40, 40, java.awt.Image.SCALE_SMOOTH );
		heart3 = new ImageIcon( logoAfter );
		heart3L = new JLabel( heart3 );
		
		hearts.setLayout( new GridLayout( 1, 3 ) );
		hearts.setBounds( 20, 20, 120, 40 );
		hearts.setOpaque( false );
		hearts.add( heart1L );
		hearts.add( heart2L );
		hearts.add( heart3L );
		
		numberF.setVisible( false );
		percentageF.setVisible( false );
		
		frame.add( questionNumberF );
		frame.add( questionF );
		frame.add( secondsL );
		frame.add( hearts );
		
		for( int i = 0; i < numberOfAnswers; i++ ) {
			frame.add( buttonsB[i] );
			frame.add( answerB[i] );
		}
		frame.add( backB );
		frame.add( numberF );
		frame.add( percentageF );
		frame.setVisible( true );
		
		nextQuestion();
	}
		
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if( e.getSource() == backB && !percentageF.isVisible() ) {
			int result = JOptionPane.showConfirmDialog( frame, "Are you sure you want to go back to menu?\nYour progress will be lost.", "", JOptionPane.YES_NO_OPTION );
            if ( result == JOptionPane.YES_OPTION ) {
            	frame.dispose();
            	Menu.frame.setVisible( true );
            }
            if ( result == JOptionPane.NO_OPTION ) {
            	
            }
		}
		else if( e.getSource() == backB && percentageF.isVisible() ) {
			frame.dispose();
            Menu.frame.setVisible( true );
		}
		else {
			for( int i = 0; i < numberOfAnswers; i++ ) {
				buttonsB[i].setEnabled( false );
				answerB[i].setEnabled( false );
			}
			
			for( int i = 0; i < numberOfAnswers; i++ ) {
				if( e.getSource()==buttonsB[i] ) {
					if( buttonsB[i].getText().charAt( 0 ) == correct.get( index ) ) {
						correctGuesses++;
					}
					else {
						heartCount--;
						if( heartCount == 2 ) heart3L.setVisible( false );
						else if( heartCount == 1 ) heart2L.setVisible( false );
					}
				}
			}
			
			for( int i = 0; i < numberOfAnswers; i++ ) {
				if( e.getSource()==answerB[i] ) {
					if( buttonsB[i].getText().charAt( 0 ) == correct.get( index ) ) {
						correctGuesses++;
					}
					else {
						heartCount--;
						if( heartCount == 2 ) heart3L.setVisible( false );
						else if( heartCount == 1 ) heart2L.setVisible( false );						
					}
				}
			}
			
			displayAnswer();
		}
		
	}
	
	
	
	void nextQuestion() {
		if( index >= numberOfQuestions || heartCount == 0 ) {
			results();
			return;
		}
		
		questionNumberF.setText( "question " + ( index + 1 ) );
		questionF.setText( questions.get( index ) );
		for( int i = 0; i < numberOfAnswers; i++ ) answerB[i].setText( answers.get( index ).get( i ) );
		if( timeForQuestion >= 10 ) timeString = "00:" + String.valueOf( timeForQuestion );
		else timeString = "00:0" + String.valueOf( timeForQuestion );
		secondsL.setText( timeString );
		secondsL.setVisible( true );
		timer.start();
	}
	
	private void displayAnswer() {
		timer.stop();
		secondsL.setVisible( false );

		UIManager.put( "Button.disabledText", Color.white ); //to control the color of disabled JButtons
		UIManager.put( "Button.disabledText", UIManager.getDefaults().getColor( "Button.disabledText" ) );
		
		for( int i = 0; i < numberOfAnswers; i++ ) {
			buttonsB[i].setEnabled( false );
			answerB[i].setEnabled( false );
			answerB[i].setForeground( Color.white );
			buttonsB[i].setForeground( Color.white );
			if( correct.get( index ) != buttonsB[i].getText().charAt( 0 ) ) {
				buttonsB[i].setBorder( BorderFactory.createLineBorder( new Color( 255, 92, 92 ), 5) ); //border color can be changed, no matter if the button is enabled or not
				
			}
			else {
				buttonsB[i].setBorder( BorderFactory.createLineBorder( new Color( 131, 252, 139 ), 5) );
			}
		}
		
		if( heartCount == 2 && heart3L.isVisible() ) heart3L.setVisible( false );
		else if( heartCount == 1 && heart2L.isVisible() ) heart2L.setVisible( false );
		else if( heartCount == 0 && heart1L.isVisible() ) heart1L.setVisible( false );
		
		//delay - after the delay, the action is performed
		Timer pause =  new Timer( time, new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				for( int i = 0; i < numberOfAnswers; i++ ) {
					buttonsB[i].setEnabled( true );
					answerB[i].setEnabled( true );
					buttonsB[i].setBorder( BorderFactory.createLineBorder( Color.white, 5) );
				}
				timeCounter = timeForQuestion;
				secondsL.setText( timeString );
				index++;
				nextQuestion();
			}
		});
		
		pause.setRepeats( false ); //timer ActionPerformed executed once
		pause.start();
	}
	
	private void results() {
		timer.stop();
		secondsL.setVisible( false );
		hearts.setVisible( false );
		
		for( int i = 0; i < numberOfAnswers; i++ ) {
			buttonsB[i].setEnabled( false );
			buttonsB[i].setVisible( false );
			answerB[i].setVisible( false );
		}
		result = BigDecimal.valueOf( ( correctGuesses / ( double ) numberOfQuestions ) * 100 ).setScale(1, RoundingMode.HALF_UP);		
	
		questionNumberF.setFont( new Font( "Trebuchet MS", Font.BOLD, 60 ) );
		questionNumberF.setBounds( 20, 160, 400, 70 );
		questionF.setVisible( false );
		numberF.setText( correctGuesses + "/" + numberOfQuestions );
		numberF.setVisible( true );
		percentageF.setText( result.toString() + "%" );
		percentageF.setVisible( true );
		if( heartCount == 0 ) {
			questionNumberF.setFont( new Font( "Trebuchet MS", Font.BOLD, 35 ) );
			questionNumberF.setText( "Oops! No hearts left." );
			numberF.setVisible( false );
			percentageF.setText( "" );
		}
		else if( result.doubleValue() > 75 ) questionNumberF.setText( "Congrats!" );
		else if( result.doubleValue() > 50 ) questionNumberF.setText( "Well done!" );
		else if( result.doubleValue() > 25 ) questionNumberF.setText( "Good effort!" );
		else questionNumberF.setText( "Keep going!" );
		
		backB.setBounds( 142, 450, 150, 65 );
	}

	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if( e.getSource() == backB ) {
			backB.setBorder( BorderFactory.createLineBorder( new Color(  171, 171, 171 ), 7 ) );
			backB.setForeground( new Color(  171, 171, 171 ) );
		}
		
		for( int i = 0; i < numberOfAnswers; i++ ) {
			if( e.getSource() == answerB[i] && timer.isRunning() ) {
				answerB[i].setForeground( new Color(  171, 171, 171 ) );
			}
		}
		
		for( int i = 0; i < numberOfAnswers; i++ ) {
			if( e.getSource() == buttonsB[i] && timer.isRunning() ) {
				buttonsB[i].setBorder( BorderFactory.createLineBorder( new Color(  171, 171, 171 ), 5 ) );
				buttonsB[i].setForeground( new Color(  171, 171, 171 ) );
			}
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if( e.getSource() == backB ) {
			backB.setBorder( BorderFactory.createLineBorder( Color.white, 7 ) );
			backB.setForeground( Color.white );
		}
		
		for( int i = 0; i < numberOfAnswers; i++ ) {
			if( e.getSource() == answerB[i] && timer.isRunning() ) {
				answerB[i].setForeground( Color.white );
			}
		}
		
		for( int i = 0; i < numberOfAnswers; i++ ) {
			if( e.getSource() == buttonsB[i] && timer.isRunning() ) {
				buttonsB[i].setBorder( BorderFactory.createLineBorder( Color.white, 5 ) );
				buttonsB[i].setForeground( Color.white );
			}
		}
	}
	
}
