import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Menu implements ActionListener, MouseListener{
	
	public static JFrame frame = new JFrame( "FishE" ); //a title for menu display
	private ImageIcon icon = new ImageIcon( "mini.png" ); //an icon for menu display
	private ImageIcon logo = new ImageIcon( "logo.png" );
	private JLabel logoL;
	private JLabel  backgroundL = new JLabel( new ImageIcon( "background.png" ) );
	private JLabel backL = new JLabel();
	
	private JButton exitB = new JButton();
	private JButton quizB = new JButton();
	private JButton fisheB = new JButton();
	
	public static Quiz_pick pick = new Quiz_pick( frame );
	public static Fishe_pick fishes = new Fishe_pick( frame );
	
	
	
	Menu() {
		System.out.println( "konstruktor menu" );
		frame.setSize( 450, 800 );
		frame.getContentPane().setBackground( new Color( 104, 105, 191 ) );
		frame.setLayout( null );
		frame.setResizable( false ); //people cannot resize the window
		frame.setLocationRelativeTo( null ); //appears in the center
		frame.setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE );
		frame.setIconImage( icon.getImage() );
		
		Image logoBefore = logo.getImage();
		Image logoAfter = logoBefore.getScaledInstance( 332, 249, java.awt.Image.SCALE_SMOOTH );
		logo = new ImageIcon( logoAfter );
		logoL = new JLabel( logo );
		logoL.setBounds( 55, 25, 332, 249 );
		logoL.setVisible( true );
		
		exitB.setBounds( 142, 495, 150, 65 );
		exitB.setForeground( Color.white );
		exitB.setFont( new Font( "Trebuchet MS", Font.PLAIN, 25 ) );
		exitB.setContentAreaFilled( false );
		exitB.setFocusable( false );
		exitB.setBorder( BorderFactory.createLineBorder( Color.white, 7 ) );
		exitB.addActionListener( this );
		exitB.addMouseListener( this );
		exitB.setText( "exit" );
		exitB.setVisible( true );
		
		quizB.setBounds( 142, 315, 150, 65 );
		quizB.setForeground( Color.white );
		quizB.setFont( new Font( "Trebuchet MS", Font.PLAIN, 25 ) );
		quizB.setContentAreaFilled( false );
		quizB.setFocusable( false );
		quizB.setBorder( BorderFactory.createLineBorder( Color.white, 7 ) );
		quizB.addActionListener( this );
		quizB.addMouseListener( this );
		quizB.setText( "quiz" );
		quizB.setVisible( true );
		
		fisheB.setBounds( 142, 405, 150, 65 );
		fisheB.setForeground( Color.white );
		fisheB.setFont( new Font( "Trebuchet MS", Font.PLAIN, 25 ) );
		fisheB.setContentAreaFilled( false );
		fisheB.setFocusable( false );
		fisheB.setBorder( BorderFactory.createLineBorder( Color.white, 7 ) );
		fisheB.addActionListener( this );
		fisheB.addMouseListener( this );
		fisheB.setText( "FishE" );
		fisheB.setVisible( true );
		
		backgroundL.setBounds( -10, 0, 450, 800 );
		backgroundL.setVisible( true );
		
		backL.setBounds( 0, 0, 450, 800 );
		backL.setVisible( true );
		backL.add( exitB );
		backL.add( quizB );
		backL.add( fisheB );
		backL.add( logoL );
		backL.add( backgroundL );
		
		frame.add( backL );
		frame.setVisible( true );
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
		if( e.getSource() == exitB ) {
			exitB.setBorder( BorderFactory.createLineBorder( new Color(  171, 171, 171 ), 7 ) );
			exitB.setForeground( new Color(  171, 171, 171 ) );
		}
		if( e.getSource() == quizB ) {
			quizB.setBorder( BorderFactory.createLineBorder( new Color(  171, 171, 171 ), 7 ) );
			quizB.setForeground( new Color(  171, 171, 171 ) );
		}
		if( e.getSource() == fisheB ) {
			fisheB.setBorder( BorderFactory.createLineBorder( new Color(  171, 171, 171 ), 7 ) );
			fisheB.setForeground( new Color(  171, 171, 171 ) );
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if( e.getSource() == exitB ) {
			exitB.setBorder( BorderFactory.createLineBorder( Color.white, 7 ) );
			exitB.setForeground( Color.white );
		}
		if( e.getSource() == quizB ) {
			quizB.setBorder( BorderFactory.createLineBorder( Color.white, 7 ) );
			quizB.setForeground( Color.white );
		}
		if( e.getSource() == fisheB ) {
			fisheB.setBorder( BorderFactory.createLineBorder( Color.white, 7 ) );
			fisheB.setForeground( Color.white );
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if( e.getSource() == exitB ) {
            frame.dispose();
            System.exit( 0 );
        }
		
		if( e.getSource() == quizB ) {
            pick.visible();
        }
		
		if( e.getSource() == fisheB ) {
			fishes.visible();
        }
		
	}

}
