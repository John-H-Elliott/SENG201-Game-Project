package GUI;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;


/**
 * Implements a Listener that checks to see if a Mouse event has occurred on a given component.
 * 
 * @author John Elliott
 * @version 1.2, 20 May 2021.
 */
public class MouseEvents extends MouseInputAdapter {
	
	/**
	 * The label that will show the desc String.
	 */
	JLabel screenLabel;
	
	/**
	 * The text that will be displayed.
	 */
	String desc;
	
	
    /**
     * Initializes an MouseEvents.
     *
     * @param label		The label that will be updated.			
     * @param info		The information that will be shown.
     */
	public MouseEvents(JLabel label, String info) {
		screenLabel = label;
		desc = info;
	}
	
	
	/**
	 * Overrides the mouse entered method that activates when a mouse overlaps a component.
	 * 
	 * @param e		The event that has occurred.
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		screenLabel.setVisible(true);
		screenLabel.setText(desc);
	}
	
	
	/**
	 * Overrides the mouse exited method that activates when a mouse no longer overlaps a component.
	 * 
	 * @param e		The event that has occurred.
	 */
	@Override	
	public void mouseExited(MouseEvent e) {
		screenLabel.setVisible(false);
		screenLabel.setText("");
	}
}
