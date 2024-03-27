import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Fishe implements ActionListener, MouseListener {
	
	private JFrame frame = new JFrame();
	private ImageIcon icon = new ImageIcon( "mini.png" ); //an icon for menu display
	
	private JButton backB = new JButton();
	private JTextField answerF = new JTextField();
	private JTextField questionF = new JTextField();
	
	private ArrayList<String> questions;
	private ArrayList<String> answers;
	private int index = 0;
	private int numberOfQuestions;
	
	Fishe( ArrayList<String> q, ArrayList<String> a ) {
		questions = new ArrayList<>( q );
		answers = new ArrayList<>( a );
		numberOfQuestions = questions.size();
		
		frame.setSize( 450, 800 );
		frame.getContentPane().setBackground( new Color( 104, 105, 191 ) );
		frame.setLayout( null );
		frame.setResizable( false ); //people cannot resize the window
		frame.setLocationRelativeTo( null ); //appears in the center
		frame.setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE );
		frame.setIconImage( icon.getImage() );
		frame.setTitle( "FishE" );
		
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
		
		answerF.setBounds( 20, 250, 400, 50 );
		answerF.setBackground( new Color ( 104, 105, 191 ) );
		answerF.setFocusable( false );
		answerF.setForeground( new Color ( 255, 255, 255 ) );
		answerF.setBorder( javax.swing.BorderFactory.createEmptyBorder() ); //to remove the border
		answerF.setFont( new Font( "Trebuchet MS", Font.BOLD, 35 ) );
		answerF.setHorizontalAlignment( JTextField.CENTER );
		answerF.setEditable( false );
		
		questionF.setBounds( 20, 170, 400, 50 );
		questionF.setBackground( new Color ( 104, 105, 191 ) );
		questionF.setFocusable( false );
		questionF.setForeground( new Color ( 255, 255, 255 ) );
		questionF.setFont( new Font( "Trebuchet MS", Font.PLAIN, 25 ) );
		questionF.setBorder( javax.swing.BorderFactory.createEmptyBorder() );
		questionF.setEditable( false );
		questionF.setHorizontalAlignment( JTextField.CENTER );
		
		
		
		frame.add( backB );
		frame.add( questionF );
		frame.add( answerF );
		frame.setVisible( true );
		
		nextQuestion();
	}
	
	void nextQuestion() {
		if( index >= numberOfQuestions ) {
			
		}
		else if( index <= 0 ) {
			
		}
		else {
			
		}
		
		answerF.setText( answers.get( index ) );
		questionF.setText( questions.get( index ) );
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if( e.getSource()==backB ) {
			int result = JOptionPane.showConfirmDialog( frame, "Are you sure you want to go back to menu?", "", JOptionPane.YES_NO_OPTION );
            if ( result == JOptionPane.YES_OPTION ) {
            	frame.dispose();
            	Menu menu = new Menu();
            }
            if ( result == JOptionPane.NO_OPTION ) {
            	
            }
		}
		
		//left and right arrows
		
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
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if( e.getSource() == backB ) {
			backB.setBorder( BorderFactory.createLineBorder( Color.white, 7 ) );
			backB.setForeground( Color.white );
		}
	}

	
	
}
