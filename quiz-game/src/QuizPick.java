import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class QuizPick implements ActionListener, MouseListener{

	private JLabel quizPickL = new JLabel();
	private JLabel  backgroundL = new JLabel( new ImageIcon( "background.png" ) );
	private JButton backB = new JButton();
	
	private JButton[] titlesB;
	private JPanel titlesP;
	private JScrollPane scrollP;
	private ArrayList<String> titles = new ArrayList<String>();
	private int size;
	private String choice;
	
	private JTextField timeF = new JTextField( "Time per question:" );
	private JTextField countF = new JTextField( "Number of questions:" );
	private JButton[] timeB = new JButton[3];
	private JButton[] countB = new JButton[3];
	private JButton playB = new JButton( "play" );
	
	private Color grayC = new Color( 171, 171, 171 );
	private int flagT;
	private int flagC;
	
	Data data = new Data();
	Quiz quiz = new Quiz();
	
	
	
	QuizPick( JFrame frame ) {
		//we need all the quizes titles
		data.start();
		size = data.getSize();
		titles = data.getTitles();
		
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
		
		backgroundL.setBounds( -10, 0, 450, 800 );
		backgroundL.setVisible( true );
		
		//create buttons
		titlesB = new JButton[size];
		for( int i = 0; i < size; i++ ) {
			titlesB[i] = new JButton();
			titlesB[i].setBounds( 0, 0, 600, 70 );
			titlesB[i].setForeground( Color.white );
			titlesB[i].setFont( new Font( "Trebuchet MS", Font.PLAIN, 35 ) );
			titlesB[i].setHorizontalAlignment( JLabel.LEFT );
			titlesB[i].setContentAreaFilled( false );
			titlesB[i].setFocusable( false );
			titlesB[i].addActionListener( this );
			titlesB[i].addMouseListener( this );
			titlesB[i].setBorder( null );
			titlesB[i].setText( titles.get(i) ); //show titles from titles ArrayList!
			titlesB[i].setVisible( true );
		}
		
		titlesP = new JPanel();
		titlesP.setLayout( new BoxLayout( titlesP, BoxLayout.Y_AXIS ) );
		for (JButton button : titlesB ) {
            titlesP.add(button);
            titlesP.add( Box.createVerticalStrut( 30 ) );
        } 
		titlesP.setOpaque( false );
		
		
		scrollP = new JScrollPane( titlesP );
	    scrollP.setBounds( 40, 40, 360, 590 );
	    scrollP.getViewport().setOpaque( false );
        scrollP.setOpaque( false );
        scrollP.setBorder( BorderFactory.createEmptyBorder() );
        scrollP.getVerticalScrollBar().setUnitIncrement( 16 );
        scrollP.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
        scrollP.setHorizontalScrollBar( null );
        scrollP.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_NEVER );
        scrollP.addMouseWheelListener( e -> {
            JScrollBar verticalScrollBar = scrollP.getVerticalScrollBar();
            int scrollAmount = verticalScrollBar.getUnitIncrement();
            int notches = e.getWheelRotation();
            verticalScrollBar.setValue( verticalScrollBar.getValue() + ( scrollAmount * notches ) );
        });

	    
		quizPickL.setBounds( 0, 0, 450, 800 );
		quizPickL.add( scrollP );
		quizPickL.add( backB );
		quizPickL.add( backgroundL ); 
		quizPickL.setVisible( false );
		
		frame.add( quizPickL );
	}
	
	
	
	void visible() {
		quizPickL.setVisible( true );
	}
	
	//the next window to create should be something like - two rows:
	//	first with number of questions and the second one with time limits (maybe horizontal scrollbar?) 
	//	a start button and unless the two options are set the user cannot click the start button
	void timeAndCount() {
		flagT = 0;
		flagC = 0;
		scrollP.setVisible( false );
		
		timeF.setBounds( 40, 90, 360, 50 );
		timeF.setBackground( new Color ( 104, 105, 191 ) );
		timeF.setFocusable( false );
		timeF.setForeground( new Color ( 255, 255, 255 ) );
		timeF.setFont( new Font( "Trebuchet MS", Font.BOLD, 35 ) );
		timeF.setBorder( javax.swing.BorderFactory.createEmptyBorder() );
		timeF.setEditable( false );
		timeF.setHorizontalAlignment( JTextField.CENTER );
		timeF.setVisible( true );
		
		countF.setBounds( 40, 285, 360, 50 );
		countF.setBackground( new Color ( 104, 105, 191 ) );
		countF.setFocusable( false );
		countF.setForeground( new Color ( 255, 255, 255 ) );
		countF.setFont( new Font( "Trebuchet MS", Font.BOLD, 35 ) );
		countF.setBorder( javax.swing.BorderFactory.createEmptyBorder() );
		countF.setEditable( false );
		countF.setHorizontalAlignment( JTextField.CENTER );
		countF.setVisible( true );
		
		for( int i = 0; i < 3; i++ ) {
			timeB[i] = new JButton();
			timeB[i].setBounds( 40 + ( i * 125 ), 165, 110, 65 );
			timeB[i].setFont( new Font( "Trebuchet MS", Font.BOLD, 25 ) );
			timeB[i].setForeground( Color.white );
			timeB[i].setContentAreaFilled( false );
			timeB[i].setFocusable( false );
			timeB[i].setBorder( BorderFactory.createLineBorder( Color.white, 7 ) );
			timeB[i].addActionListener( this );
			timeB[i].addMouseListener( this );
			timeB[i].setVisible( true );
		}
		timeB[0].setText( "5" );
		timeB[1].setText( "10" );
		timeB[2].setText( "15" );
		
		for( int i = 0; i < 3; i++ ) {
			countB[i] = new JButton();
			countB[i].setBounds( 40 + ( i * 125 ), 350, 110, 65 );
			countB[i].setFont( new Font( "Trebuchet MS", Font.BOLD, 25 ) );
			countB[i].setForeground( Color.white );
			countB[i].setContentAreaFilled( false );
			countB[i].setFocusable( false );
			countB[i].setBorder( BorderFactory.createLineBorder( Color.white, 7 ) );
			countB[i].addActionListener( this );
			countB[i].addMouseListener( this );
			countB[i].setVisible( true );
		}
		countB[0].setText( "5" );
		countB[1].setText( "10" );
		countB[2].setText( "20" );
		
		playB.setBounds( 142, 475, 150, 65 );
		playB.setForeground( new Color ( 104, 105, 191 ) );
		playB.setFont( new Font( "Trebuchet MS", Font.BOLD, 25 ) );
		playB.setBackground( Color.WHITE );
		playB.setBorderPainted( false ); //nie podœwietla siê krawêdŸ jak na niego najedziemy
		playB.setFocusable( false );
		playB.addActionListener( this );
		playB.addMouseListener( this );
		playB.setVisible( true );
		
		quizPickL.add( timeF );
		quizPickL.add( countF );
		for( int i = 0; i < 3; i++ ) {
			quizPickL.add( countB[i] );
			quizPickL.add( timeB[i] );
		}
		quizPickL.add( playB );
		quizPickL.add( backgroundL );
		
		flagT = 0;
		flagC = 0;
	}
	
	void scroll() {
		timeF.setVisible( false );
		countF.setVisible( false );
		for( int i = 0; i < 3; i++ ) {
			countB[i].setVisible( false );
			timeB[i].setVisible( false );
		}
		playB.setVisible( false );
		
		scrollP.setVisible( true );
	}
	
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		for( int i = 0; i < timeB.length; i++ ) {
			if( e.getSource() == timeB[i] && flagT != 1 + i ) {
				for( JButton butt : timeB ) {
					butt.setBorder( BorderFactory.createLineBorder( Color.white, 7 ) );
					butt.setForeground( Color.white );
				}
				timeB[i].setBorder( BorderFactory.createLineBorder( grayC, 7 ) );
				timeB[i].setForeground( grayC );
				flagT = i + 1;
			}
			else if( e.getSource() == timeB[i] && flagT == i + 1 ) {
				timeB[i].setBorder( BorderFactory.createLineBorder( Color.white, 7 ) );
				timeB[i].setForeground( Color.white );
				flagT = 0;
			}
		}
		for( int i = 0; i < countB.length; i++ ) {
			if( e.getSource() == countB[i] && flagC != 1 + i ) {
				for( JButton butt : countB ) {
					butt.setBorder( BorderFactory.createLineBorder( Color.white, 7 ) );
					butt.setForeground( Color.white );
				}
				countB[i].setBorder( BorderFactory.createLineBorder( grayC, 7 ) );
				countB[i].setForeground( grayC );
				flagC = i + 1;
			}
			else if( e.getSource() == countB[i] && flagC == i + 1 ) {
				countB[i].setBorder( BorderFactory.createLineBorder( Color.white, 7 ) );
				countB[i].setForeground( Color.white );
				flagC = 0;
			}
		}		
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
			backB.setBorder( BorderFactory.createLineBorder( grayC, 7 ) );
			backB.setForeground( grayC );
		}
		for( JButton button : titlesB ) {
			if( e.getSource() == button ) {
				button.setForeground( grayC );
			}
		}
		if( e.getSource() == playB ) {
			playB.setBackground( grayC );
		}
		for( int i = 0; i < timeB.length; i++ ) {
			if( e.getSource() == timeB[i] && flagT != i + 1 ) {
				timeB[i].setBorder( BorderFactory.createLineBorder( grayC, 7 ) );
				timeB[i].setForeground( grayC );
			}
		}
		for( int i = 0; i < countB.length; i++ ) {
			if( e.getSource() == countB[i] && flagC != 1 + i ) {
				countB[i].setBorder( BorderFactory.createLineBorder( grayC, 7 ) );
				countB[i].setForeground( grayC );
			}
		}
		for( int i = 0; i < timeB.length; i++ ) {
			if( e.getSource() == timeB[i] && flagT == i + 1 ) {
				timeB[i].setBorder( BorderFactory.createLineBorder( Color.white, 7 ) );
				timeB[i].setForeground( Color.white );
			}
		}
		for( int i = 0; i < countB.length; i++ ) {
			if( e.getSource() == countB[i] && flagC == i + 1 ) {
				countB[i].setBorder( BorderFactory.createLineBorder( Color.white, 7 ) );
				countB[i].setForeground( Color.white );
			}
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if( e.getSource() == backB ) {
			backB.setBorder( BorderFactory.createLineBorder( Color.white, 7 ) );
			backB.setForeground( Color.white );
		}
		for( JButton button : titlesB ) {
			if( e.getSource() == button ) {
				button.setForeground( Color.white );
			}
		}
		if( e.getSource() == playB ) {
			playB.setBackground( Color.white );
		}
		for( int i = 0; i < timeB.length; i++ ) {
			if( e.getSource() == timeB[i] && flagT != i + 1 ) {
				timeB[i].setBorder( BorderFactory.createLineBorder( Color.white, 7 ) );
				timeB[i].setForeground( Color.white );
			}
		}
		for( int i = 0; i < countB.length; i++ ) {
			if( e.getSource() == countB[i] && flagC != i + 1 ) {
				countB[i].setBorder( BorderFactory.createLineBorder( Color.white, 7 ) );
				countB[i].setForeground( Color.white );
			}
		}
		for( int i = 0; i < timeB.length; i++ ) {
			if( e.getSource() == timeB[i] && flagT == i + 1 ) {
				timeB[i].setBorder( BorderFactory.createLineBorder( grayC, 7 ) );
				timeB[i].setForeground( grayC );
			}
		}
		for( int i = 0; i < countB.length; i++ ) {
			if( e.getSource() == countB[i] && flagC == i + 1 ) {
				countB[i].setBorder( BorderFactory.createLineBorder( grayC, 7 ) );
				countB[i].setForeground( grayC );
			}
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if( e.getSource() == backB ) {
            quizPickL.setVisible( false );
            flagT = 0;
    		flagC = 0;
            scroll();
        }
		
		if( e.getSource() == playB && flagC != 0 && flagT != 0 ) {
			quizPickL.setVisible( false );
			for( int i = 0; i < timeB.length; i++ ) {
				timeB[i].setBorder( BorderFactory.createLineBorder( Color.white, 7 ) );
				timeB[i].setForeground( Color.white );
			}
			for( int i = 0; i < countB.length; i++ ) {
				countB[i].setBorder( BorderFactory.createLineBorder( Color.white, 7 ) );
				countB[i].setForeground( Color.white );
			}
			playB.setBackground( Color.white );
			scroll();
			Menu.frame.dispose();
			
			//here I need to collect all the necessary data from dat.txt
			if( flagC == 1 ) flagC = 5;
			else if( flagC == 2 ) flagC = 10;
			else flagC = 20;
			data.generateQuiz( flagC, choice );
			
			ArrayList<String> questions = data.getQuestions();
			ArrayList<ArrayList<String>> answers = data.getAnswers();
			ArrayList<Character> correct = data.getCorrect();
		
			if( flagT == 1 ) flagT = 5;
			else if( flagT == 2 ) flagT = 10;
			else flagT = 15;
			
			System.out.println( flagT );
			System.out.println( flagC );
			System.out.println( questions );
			System.out.println( answers );
			System.out.println( correct );
			//Quiz quiz = new Quiz( flagT, questions, answers, correct );
			quiz.runQuiz(flagT, questions, answers, correct);
        }
		
		for( JButton button : titlesB ) {
			if( e.getSource() == button ) {
				choice = button.getText();
				timeAndCount();
			}
		}
		
	}

	
	
}
