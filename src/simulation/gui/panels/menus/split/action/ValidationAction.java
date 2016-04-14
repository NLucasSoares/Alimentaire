package simulation.gui.panels.menus.split.action;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import simulation.gui.panels.menus.split.SplitMenu;

/**
 * The event used by split menu to
 * quit and pass to next menu
 *
 * @author
 * 		Lucas SOARES
 */
public class ValidationAction implements KeyListener 
{
	/**
	 * The split menu
	 */
	private SplitMenu splitMenu;
	
	/**
	 * Construct the validation event
	 * 
	 * @param splitMenu
	 * 		The split menu
	 */
	public ValidationAction( SplitMenu splitMenu )
	{
		// Save
		this.splitMenu = splitMenu;
	}

	@Override
	public void keyPressed( KeyEvent e )
	{
		this.splitMenu.stop( );
	}

	@Override
	public void keyReleased( KeyEvent e )
	{
		
	}

	@Override
	public void keyTyped( KeyEvent e )
	{
		
	}
}

