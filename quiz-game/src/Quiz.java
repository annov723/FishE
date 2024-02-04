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

import javax.swing.*;

public class Quiz implements ActionListener, MouseListener{
	 
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
	
	private int counter = SECONDS;
	private int index = 0;
	private int correct_guesses = 0;
	private BigDecimal result; //so the percentage dan be rounded
	
	private JFrame frame = new JFrame();
	private JTextField txtF = new JTextField();
	private JTextField txtP = new JTextField();
	private JButton buttonsB[] = new JButton[SIZE];
	private JButton answerB[] = new JButton[SIZE];
	private JLabel secondsL = new JLabel();
	private JTextField numberF = new JTextField();
	private JTextField percentageF = new JTextField();
	private String s = "00:";
	
	private ImageIcon icon = new ImageIcon( "mini.png" ); //an icon for menu display
	
	private JButton backB = new JButton();
	private ImageIcon heart1 = new ImageIcon( "heart2.png" );
	private ImageIcon heart2 = new ImageIcon( "heart2.png" );
	private ImageIcon heart3 = new ImageIcon( "heart2.png" );
	private JLabel heart1L;
	private JLabel heart2L;
	private JLabel heart3L;
	private JPanel hearts = new JPanel();
	int heart_count = 3;
	
	Timer timer =  new Timer( 1000, new ActionListener() { //every one second
		@Override
		public void actionPerformed( ActionEvent e ) {
			counter--;
			if( counter >= 10 ) s = "00:" + String.valueOf( counter );
			else s = "00:0" + String.valueOf( counter );
			secondsL.setText( s );
			if( counter <= 0 ) {
				heart_count--;
				displayAnswer();
			}
		}
	});
	
	
	
	public Quiz( String title ) {
		
		//get quiz data from file!
		//questions has to be in a random order!
		
		frame.setSize( 450, 800 );
		frame.getContentPane().setBackground( new Color( 104, 105, 191 ) );
		frame.setLayout( null );
		frame.setResizable( false ); //people cannot resize the window
		frame.setLocationRelativeTo( null ); //appears in the center
		frame.setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE );
		frame.setIconImage( icon.getImage() );
		frame.setTitle( "quiz" );
		
		txtF.setBounds( 20, 80, 400, 50 );
		txtF.setBackground( new Color ( 104, 105, 191 ) );
		txtF.setFocusable( false );
		txtF.setForeground( new Color ( 255, 255, 255 ) );
		txtF.setBorder( javax.swing.BorderFactory.createEmptyBorder() ); //to remove the border
		txtF.setFont( new Font( "Trebuchet MS", Font.BOLD, 35 ) );
		txtF.setHorizontalAlignment( JTextField.CENTER );
		txtF.setEditable( false );
		
		txtP.setBounds( 20, 130, 400, 50 );
		txtP.setBackground( new Color ( 104, 105, 191 ) );
		txtP.setFocusable( false );
		txtP.setForeground( new Color ( 255, 255, 255 ) );
		txtP.setFont( new Font( "Trebuchet MS", Font.PLAIN, 25 ) );
		txtP.setBorder( javax.swing.BorderFactory.createEmptyBorder() );
		txtP.setEditable( false );
		txtP.setHorizontalAlignment( JTextField.CENTER );
		
		for( int i = 0; i < SIZE; i++ ) {
			buttonsB[i] = new JButton();
			buttonsB[i].setBounds( 50, 230 + ( i * 80 ), 50, 50 );
			buttonsB[i].setFont( new Font( "Trebuchet MS", Font.BOLD, 25 ) );
			buttonsB[i].setForeground( Color.white );
			buttonsB[i].setContentAreaFilled( false );
			buttonsB[i].setFocusable( false );
			buttonsB[i].setBorder( BorderFactory.createLineBorder( Color.white, 5) );
			buttonsB[i].addActionListener( this );
			buttonsB[i].addMouseListener( this );
			char c = ( char )( 65 + i );
			buttonsB[i].setText( String.valueOf( c ) );
			buttonsB[i].setVisible( true );
		}
		for( int i = 0; i < SIZE; i++ ) {
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
		
		
		
		frame.add( txtF );
		frame.add( txtP );
		frame.add( secondsL );
		frame.add( hearts );
		
		for( int i = 0; i < SIZE; i++ ) {
			frame.add( buttonsB[i] );
			frame.add( answerB[i] );
		}
		frame.add( backB );
		frame.setVisible( true );
		
		nextQuestion();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if( e.getSource()==backB && !percentageF.isVisible() ) {
			int result = JOptionPane.showConfirmDialog( frame, "Are you sure you want to go back to menu?\nYour progress will be lost.", "", JOptionPane.YES_NO_OPTION );
            if ( result == JOptionPane.YES_OPTION ) {
            	frame.dispose();
            	Menu menu = new Menu();
            }
            if ( result == JOptionPane.NO_OPTION ) {
            	
            }
		}
		else if( e.getSource()==backB && percentageF.isVisible() ) {
            frame.dispose();
            Menu menu = new Menu();
     
		}
		else {
			for( int i = 0; i < SIZE; i++ ) {
				buttonsB[i].setEnabled( false );
				answerB[i].setEnabled( false );
			}
			
			for( int i = 0; i < SIZE; i++ ) {
				if( e.getSource()==buttonsB[i] ) {
					if( buttonsB[i].getText().charAt( 0 ) == answers[index] ) {
						correct_guesses++;
					}
					else {
						heart_count--;
						if( heart_count == 2 ) heart3L.setVisible( false );
						else if( heart_count == 1 ) heart2L.setVisible( false );
					}
				}
			}
			
			for( int i = 0; i < SIZE; i++ ) {
				if( e.getSource()==answerB[i] ) {
					if( buttonsB[i].getText().charAt( 0 ) == answers[index] ) {
						correct_guesses++;
					}
					else {
						heart_count--;
						if( heart_count == 2 ) heart3L.setVisible( false );
						else if( heart_count == 1 ) heart2L.setVisible( false );						
					}
				}
			}
			
			displayAnswer();
		}
		
		
		
	}
	
	
	
	public void nextQuestion() {
		if( index >= SIZE || heart_count == 0 ) {
			results();
			return;
		}
		
		txtF.setText( "question " + ( index + 1 ) );
		txtP.setText( questions[index] );
		for( int i = 0; i < SIZE; i++ ) answerB[i].setText( options[index][i] );
		if( SECONDS >= 10 ) s = "00:" + String.valueOf( SECONDS );
		else s = "00:0" + String.valueOf( SECONDS );
		secondsL.setText( s );
		secondsL.setVisible( true );
		timer.start();
	}
	
	public void displayAnswer() {
		timer.stop();
		secondsL.setVisible( false );

		UIManager.put( "Button.disabledText", Color.white ); //to control the color of disabled JButtons
		UIManager.put( "Button.disabledText", UIManager.getDefaults().getColor("Button.disabledText") );
		
		for( int i = 0; i < SIZE; i++ ) {
			buttonsB[i].setEnabled( false );
			answerB[i].setEnabled( false );
			answerB[i].setForeground( Color.white );
			buttonsB[i].setForeground( Color.white );
			if( answers[index] != buttonsB[i].getText().charAt( 0 ) ) {
				buttonsB[i].setBorder( BorderFactory.createLineBorder( new Color( 255, 92, 92 ), 5) ); //border color can be changed, no matter if the button is enabled or not
				
			}
			else {
				buttonsB[i].setBorder( BorderFactory.createLineBorder( new Color( 131, 252, 139 ), 5) );
			}
		}
		
		//delay - after the delay, the action is performed
		Timer pause =  new Timer( TIME, new ActionListener() {
			
			@Override
			public void actionPerformed( ActionEvent e ) {
				for( int i = 0; i < SIZE; i++ ) {
					buttonsB[i].setEnabled( true );
					answerB[i].setEnabled( true );
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
		hearts.setVisible( false );
		
		for( int i = 0; i < SIZE; i++ ) {
			buttonsB[i].setEnabled( false );
			buttonsB[i].setVisible( false );
			answerB[i].setVisible( false );
		}
		result = BigDecimal.valueOf( ( correct_guesses / ( double ) SIZE ) * 100 ).setScale(1, RoundingMode.HALF_UP);		
	
		txtF.setFont( new Font( "Trebuchet MS", Font.BOLD, 60 ) );
		txtF.setBounds( 20, 160, 400, 70 );
		txtP.setVisible( false );
		numberF.setText( correct_guesses + "/" + SIZE );
		percentageF.setText( result.toString() + "%" );
		percentageF.setVisible( true );
		if( heart_count == 0 && index < SIZE ) {
			txtF.setFont( new Font( "Trebuchet MS", Font.BOLD, 35 ) );
			txtF.setText( "Oops! No hearts left." );
		}
		else if( result.doubleValue() > 75 ) txtF.setText( "Congrats!" );
		else if( result.doubleValue() > 50 ) txtF.setText( "Well done!" );
		else if( result.doubleValue() > 25 ) txtF.setText( "Good effort!" );
		else txtF.setText( "Keep going!" );
		
		backB.setBounds( 142, 450, 150, 65 );
	
		frame.add( numberF );
		frame.add( percentageF );
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
		
		for( int i = 0; i < SIZE; i++ ) {
			if( e.getSource() == answerB[i] && timer.isRunning() ) {
				answerB[i].setForeground( new Color(  171, 171, 171 ) );
			}
		}
		
		for( int i = 0; i < SIZE; i++ ) {
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
		
		for( int i = 0; i < SIZE; i++ ) {
			if( e.getSource() == answerB[i] && timer.isRunning() ) {
				answerB[i].setForeground( Color.white );
			}
		}
		
		for( int i = 0; i < SIZE; i++ ) {
			if( e.getSource() == buttonsB[i] && timer.isRunning() ) {
				buttonsB[i].setBorder( BorderFactory.createLineBorder( Color.white, 5 ) );
				buttonsB[i].setForeground( Color.white );
			}
		}
		
	}

	
	
}
