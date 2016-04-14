package simulation.gui.panels;

import javax.swing.JPanel;

/**
 * The base definition of a panel
 * 
 *	@author
 *		Lucas Soares
 */
public abstract class Panel extends JPanel implements Runnable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5781678304975990433L;
	
	/**
	 * Start the panel
	 */
	public abstract void start( );
	
	/**
	 * Stop the panel
	 */
	public abstract void stop( );
	
	/**
	 * Get continue state
	 */
	public abstract boolean isContinue( );
}

