import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JPanel;


public class Screen extends JPanel{
	/**
	 * 
	 */
	public static AtomicBoolean IN_USE = new AtomicBoolean(false) ;

	ArrayList<MarsPaintable> paintables;
	Screen(){
		this.setPreferredSize(new Dimension(MarsConstants.SCREEN_X_SIZE,MarsConstants.SCREEN_Y_SIZE));
		paintables = new ArrayList<MarsPaintable>();
	}
	
	public void add(MarsPaintable m){
		paintables.add(m);
		if(MarsConstants.ANIMATED_LAUNCH){
			repaint();
		}
		
	}
	
	public synchronized void paint(Graphics g) {
		
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        for (MarsPaintable p: paintables){
        	p.paint(g2d);
        	
        }
      
    }
}
