package simulation.gui.panels.menus.main.action;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import simulation.gui.panels.menus.main.MainMenu;

public class MouseAction implements MouseListener, MouseMotionListener
{
	/**
	 * Main menu
	 */
	private MainMenu mainMenu;

	
	/**
	 * Construct the mouse action
	 */
	public MouseAction( MainMenu m )
	{
		this.mainMenu = m;
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		this.mainMenu.updateButton( e );
	}
	

	@Override
	public void mouseReleased(MouseEvent e)
	{
		this.mainMenu.updateButton( e );
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		this.mainMenu.updateButton( e );
	}

}
