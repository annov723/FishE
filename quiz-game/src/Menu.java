import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Menu implements ActionListener, MouseListener{
	
	JFrame frame = new JFrame( "FishE" ); //a title for menu display
	ImageIcon icon = new ImageIcon( "mini.png" ); //an icon for menu display
	JButton exitB = new JButton();
	JButton quizB = new JButton();
	JButton fisheB = new JButton();
	JPanel menuP = new JPanel();
	
	
	public Menu() {
		
		frame.setSize( 450, 800 );
		frame.getContentPane().setBackground( new Color( 104, 105, 191 ) );
		frame.setLayout( null );
		frame.setResizable( false ); //people cannot resize the window
		frame.setLocationRelativeTo( null ); //appears in the center
		frame.setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE );
		frame.setIconImage( icon.getImage() );
		
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
		
		quizB.setBounds( 142, 405, 150, 65 );
		quizB.setForeground( Color.white );
		quizB.setFont( new Font( "Trebuchet MS", Font.PLAIN, 25 ) );
		quizB.setContentAreaFilled( false );
		quizB.setFocusable( false );
		quizB.setBorder( BorderFactory.createLineBorder( Color.white, 7 ) );
		quizB.addActionListener( this );
		quizB.addMouseListener( this );
		quizB.setText( "quiz" );
		quizB.setVisible( true );
		
		fisheB.setBounds( 142, 315, 150, 65 );
		fisheB.setForeground( Color.white );
		fisheB.setFont( new Font( "Trebuchet MS", Font.PLAIN, 25 ) );
		fisheB.setContentAreaFilled( false );
		fisheB.setFocusable( false );
		fisheB.setBorder( BorderFactory.createLineBorder( Color.white, 7 ) );
		fisheB.addActionListener( this );
		fisheB.addMouseListener( this );
		fisheB.setText( "FishE" );
		fisheB.setVisible( true );
		
		menuP.setSize( 450, 800 );
		menuP.setOpaque( false );
		menuP.add( exitB );
		menuP.add( quizB );
		menuP.add( fisheB );
		
		frame.add( menuP );
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
		
	}

}
