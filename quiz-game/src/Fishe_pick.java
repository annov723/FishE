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

public class Fishe_pick implements ActionListener, MouseListener{

	private JLabel fishe_pickL = new JLabel();
	private JLabel  backgroundL = new JLabel( new ImageIcon( "background.png" ) );
	private JButton backB = new JButton();
	
	private JButton[] titlesB;
	private JPanel titlesP;
	private JScrollPane scrollP;
	private ArrayList<String> titles = new ArrayList<String>();
	private int size;
	
	Data data = new Data();
	
	
	
	Fishe_pick( JFrame frame ) {
		
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
			titlesB[i].setText( titles.get(i) ); //show titles from titles HashMap!
			titlesB[i].setVisible( true );
		}
		
		titlesP = new JPanel();
		titlesP.setLayout( new BoxLayout( titlesP, BoxLayout.Y_AXIS ) );
		for ( JButton button : titlesB ) {
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

	    
		fishe_pickL.setBounds( 0, 0, 450, 800 );
		fishe_pickL.add( scrollP );
		fishe_pickL.add( backB );
		fishe_pickL.add( backgroundL ); 
		fishe_pickL.setVisible( false );
		
		frame.add( fishe_pickL );
	}
	
	void visible() {
		fishe_pickL.setVisible( true );
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
            fishe_pickL.setVisible( false );
        }
		
		for( JButton button : titlesB ) {
			if( e.getSource() == button ) {
				String choice = button.getText();

				Menu.frame.dispose();
				
				//here I need to collect all the necessary data from dat.txt
				ArrayList<String> questions = new ArrayList<String>();
				ArrayList<String> corr_answers = new ArrayList<String>();
				
				Fishe fishe = new Fishe( choice, questions, corr_answers );
			}
		}
		
	}
	
	
	
}
