package simulation.gui.animation;

import java.awt.image.BufferedImage;

/**
 * 	A frame of an animation
 * 	
 * 	@author
 * 		Lucas Soares
 */
public class Frame
{
	/**
	 * The content picture of the frame
	 */
	private BufferedImage content;
	
	/**
	 *	Construct the Frame
	 * 
	 *	@param content
	 *		The content picture of the frame
	 */
	public Frame( BufferedImage content )
	{
		// Save
		this.content = content;
	}

	/**
	 * 	@return the content of the frame
	 */
	public BufferedImage getContent( )
	{
		return this.content;
	}
	
	
}
