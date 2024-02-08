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

public class Quiz_pick implements ActionListener, MouseListener{

	private JLabel quiz_pickL = new JLabel();
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
	
	
	Data data = new Data();
	
	
	
	Quiz_pick( JFrame frame ) {
		
		//we need all the quizes titles
		data.start();
		size = data.get_size();
		titles = data.get_titles();
		
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

	    
		quiz_pickL.setBounds( 0, 0, 450, 800 );
		quiz_pickL.add( scrollP );
		quiz_pickL.add( backB );
		quiz_pickL.add( backgroundL ); 
		quiz_pickL.setVisible( false );
		
		frame.add( quiz_pickL );
	}
	
	
	
	void visible() {
		quiz_pickL.setVisible( true );
	}
	
	//the next window to create should be something like - two rows:
	//	first with number of questions (depends on the whole amount...) and the second one with time limits (maybe horizontal scrollbar?) 
	//	a start button and unless the two options are set the user cannot click the start button
	void time_n_count() {
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
			timeB[i].setBounds( 40 + ( i * 130 ), 165, 110, 65 );
			timeB[i].setFont( new Font( "Trebuchet MS", Font.BOLD, 25 ) );
			timeB[i].setForeground( Color.white );
			timeB[i].setContentAreaFilled( false );
			timeB[i].setFocusable( false );
			timeB[i].setBorder( BorderFactory.createLineBorder( Color.white, 7 ) );
			timeB[i].addActionListener( this );
			timeB[i].addMouseListener( this );
			timeB[i].setVisible( true );
		}
		
		for( int i = 0; i < 3; i++ ) {
			countB[i] = new JButton();
			countB[i].setBounds( 40 + ( i * 130 ), 350, 110, 65 );
			countB[i].setFont( new Font( "Trebuchet MS", Font.BOLD, 25 ) );
			countB[i].setForeground( Color.white );
			countB[i].setContentAreaFilled( false );
			countB[i].setFocusable( false );
			countB[i].setBorder( BorderFactory.createLineBorder( Color.white, 7 ) );
			countB[i].addActionListener( this );
			countB[i].addMouseListener( this );
			countB[i].setVisible( true );
		}
		
		quiz_pickL.add( timeF );
		quiz_pickL.add( countF );
		for( int i = 0; i < 3; i++ ) {
			quiz_pickL.add( countB[i] );
			quiz_pickL.add( timeB[i] );
		}
		quiz_pickL.add( backgroundL );
	}
	
	void scroll() {
		timeF.setVisible( false );
		countF.setVisible( false );
		
		scrollP.setVisible( true );
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
		for( JButton button : titlesB ) {
			if( e.getSource() == button ) {
				button.setForeground( new Color(  171, 171, 171 ) );
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
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if( e.getSource() == backB ) {
			scroll();
            quiz_pickL.setVisible( false );
        }
		
		for( JButton button : titlesB ) {
			if( e.getSource() == button ) {
				choice = button.getText();
				time_n_count();
				
				//quiz_pickL.setVisible( false );
				//button.setForeground( Color.white );
				//Menu.frame.dispose();
				
				//here I need to collect all the necessary data from dat.txt
				ArrayList<String> questions = new ArrayList<String>();
				ArrayList<String> answers = new ArrayList<String>();
				ArrayList<Character> correct = new ArrayList<Character>();
				
				//Quiz quiz = new Quiz( choice, questions, answers, correct );
			}
		}
		
	}

	
	
}
