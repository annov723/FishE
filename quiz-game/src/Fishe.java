import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Fishe implements ActionListener, MouseListener {
	
	private JFrame frame;
	private ImageIcon icon = new ImageIcon( "mini.png" ); //an icon for menu display
	
	private JButton backB = new JButton();
	private JTextField answerF = new JTextField();
	private JTextField questionF = new JTextField();
	private JLabel arrowRightL;
	private JLabel arrowLeftL;
	private JLabel  backgroundL = new JLabel( new ImageIcon( "background.png" ) );
	
	private ArrayList<String> questions;
	private ArrayList<String> answers;
	private int index = 0;
	private int numberOfQuestions;
	
	private final int arrowMaxWidth = 110;
    private final int arrowMaxHeight = 110;
	
	Fishe( ArrayList<String> q, ArrayList<String> a ) {
		questions = new ArrayList<>( q );
		answers = new ArrayList<>( a );
		numberOfQuestions = questions.size();
		
		frame = new JFrame();
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
		
		answerF.setBounds( 20, 230, 400, 50 );
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
		
		BufferedImage originalImage1 = loadImageFromFile( "arrow_r.png" );
        BufferedImage scaledImage1 = scaleImage( originalImage1, arrowMaxWidth, arrowMaxHeight );
        ImageIcon icon1 = new ImageIcon( scaledImage1 );
        arrowRightL = new JLabel( icon1 );
        BufferedImage originalImage2 = loadImageFromFile( "arrow_l.png" );
        BufferedImage scaledImage2 = scaleImage( originalImage2, arrowMaxWidth, arrowMaxHeight );
        ImageIcon icon2 = new ImageIcon( scaledImage2 );
        arrowLeftL = new JLabel( icon2 );
        arrowLeftL.setBounds( 20, 300, 110, 110 );
        arrowRightL.setBounds( 310, 300, 110, 110 );
        arrowLeftL.addMouseListener( this );
        arrowRightL.addMouseListener( this );
        
        backgroundL.setBounds( -10, 0, 450, 800 );
		backgroundL.setVisible( true );
        
		
		
		
		frame.add( backB );
		frame.add( questionF );
		frame.add( answerF );
		frame.add( arrowRightL );
		frame.add( arrowLeftL );
		frame.add( backgroundL );
		frame.setVisible( true );
		
		nextQuestion();
	}
	
	private static BufferedImage loadImageFromFile(String filePath) {
        try {
            return ImageIO.read(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static BufferedImage scaleImage(BufferedImage originalImage, int maxWidth, int maxHeight) {
        if (originalImage == null) {
            return null;
        }

        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        double scaleFactor = Math.min((double) maxWidth / width, (double) maxHeight / height);

        int scaledWidth = (int) (width * scaleFactor);
        int scaledHeight = (int) (height * scaleFactor);
        BufferedImage scaledImage = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = scaledImage.createGraphics();
        g2d.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();

        return scaledImage;
    }
	
	void nextQuestion() {
		answerF.setText( answers.get( index ) );
		questionF.setText( questions.get( index ) );
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if( e.getSource() == backB ) {
			int result = JOptionPane.showConfirmDialog( frame, "Are you sure you want to go back to menu?", "", JOptionPane.YES_NO_OPTION );
            if ( result == JOptionPane.YES_OPTION ) {
            	frame.dispose();
            	Menu.frame.setVisible( true );
            }
            if ( result == JOptionPane.NO_OPTION ) {
            	
            }
		}
		
	}
	
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if( e.getSource()==arrowRightL ) {
			index++;
			if( index >= numberOfQuestions ) index = index - numberOfQuestions;
			nextQuestion();
		}
		else if( e.getSource()==arrowLeftL ) {
			index--;
			if( index < 0 ) index = numberOfQuestions + index;
			nextQuestion();
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
