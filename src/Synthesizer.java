
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class Synthesizer extends JFrame implements MouseMotionListener, MouseListener
{
    Button myButton;
    Note myNote; 
    Button myButton2;
    Note myNote2; 
    Slider mySlider;    
    Dimension dim; 
    SoundMaker myMaker;
    
    BufferedImage offscreen;
    Graphics bg;
    
    public Synthesizer() {
    	this.setSize(300,300);
        dim = getSize(); 
        offscreen = new BufferedImage(dim.width,dim.height,BufferedImage.TYPE_INT_RGB);
        bg = offscreen.getGraphics();
        myButton = new Button(100,100,50,20,"Button");    
        mySlider = new Slider(25,50,100,false);
        myNote = new Note(18.35*32, 1.0, 0.5);  
        myMaker = new SoundMaker(); 
        myMaker.start();
        addMouseListener(this);    
        addMouseMotionListener(this);
    }
    
    public static void main(String[] args) {
		Synthesizer syn = new Synthesizer();
		syn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		syn.setVisible(true);
	}
    
    
    public void paint(Graphics g){
    	bg.setColor(Color.white);
    	bg.fillRect(0, 0, dim.width, dim.height);
        myButton.paint(bg);    
        mySlider.paint(bg);
        g.drawImage(offscreen, 0, 0, null);
    }
    
    public void mouseMoved(MouseEvent e){
        System.out.println("mouseMoved");
        int x = e.getX();     
        int y = e.getY();     
        myButton.checkOver(x,y);    
        mySlider.move(x,y); 
        repaint();
    }
    public void mouseReleased(MouseEvent e){
        myNote = new Note(1500*mySlider.location+50, 2.0, 0.5);
        int x = e.getX();     
        int y = e.getY();    
        myButton.isPressed = false;     
        mySlider.selected = false; 
        repaint();
    }
    public void mousePressed(MouseEvent e){
        int x = e.getX();     
        int y = e.getY();    
        myButton.checkPress(x,y);
        if(myButton.isPressed) myMaker.play(myNote.samples);
        mySlider.select(x,y); 
        repaint();
    }
    public void mouseDragged(MouseEvent e){
        int x = e.getX();     
        int y = e.getY();    
        mySlider.move(x,y);  
        repaint();
    }
    public void mouseExited(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseClicked(MouseEvent e){}

}
