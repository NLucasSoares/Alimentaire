package simulation.gui.object;

import java.awt.Color;
import java.awt.event.MouseEvent;

import simulation.math.point.Point;

public class RectangleButton extends Rectangle
{
	private ButtonState buttonState = ButtonState.BUTTON_RELEASED;
	private int mouseButton = MouseEvent.BUTTON1_MASK;
	
	public RectangleButton( int w,
			int h,
			int x,
			int y,
			boolean filled,
			Color color )
		{
			super( w,
				h,
				x,
				y,
				filled,
				color );
		}
	
	public RectangleButton( )
	{
		super( );
	}
	
	public RectangleButton(Point<Integer> size, Point<Integer> position, boolean filled, Color color)
	{
		super(size,
				position,
				filled,
				color);
		
		this.buttonState = ButtonState.BUTTON_RELEASED;		
	}
	

	
	public boolean isCursorInside(int x, int y)
	{
		return (x>=super.getPosition().getX()
				&& y>=super.getPosition().getY()
				&& x<=super.getPosition().getX() + super.getSize().getX()
				&& y<=super.getPosition().getY() + super.getSize().getY());				
	}
	
	
	/**
	 * @param mouseButton
	 * 		the mouse button to set
	 */
	public void setMouseButton(int mouseButton)
	{
		this.mouseButton = mouseButton;
	}
	
	/**
	 * Reset the mouse button
	 */
	public void resetMouseButton()
	{
		this.mouseButton = MouseEvent.BUTTON1_MASK;
	}
	
	public void resetButtonState()
	{
		this.buttonState = ButtonState.BUTTON_RELEASED;
	}

	public void update( MouseEvent me )
	{
		// Save mouse position
		Point<Integer> mousePosition = new Point<Integer>( me.getX( ), me.getY( ) );
		
		// Check mouse release
		if( me.getID() == MouseEvent.MOUSE_RELEASED )
			this.buttonState = ButtonState.BUTTON_RELEASED;
			
		// Check if mouse is inside the box
		if( this.isCursorInside(mousePosition.getX(), mousePosition.getY( ) ) )
		{
			switch( this.buttonState )
			{
				case BUTTON_RELEASED:
					this.buttonState = ButtonState.BUTTON_OVERFLOWN;
					break;
					
				case BUTTON_OVERFLOWN:
					switch( me.getID( ) )
					{
						case MouseEvent.MOUSE_PRESSED:
							if( ( me.getModifiers() & this.mouseButton ) != 0 )
								this.buttonState = ButtonState.BUTTON_PRESSED;
							break;
							
						default:
							break;
					}
				
				default:
					break;
			}
		}
		else
		{
			switch(this.buttonState)
			{
				case BUTTON_OVERFLOWN:
					this.buttonState = ButtonState.BUTTON_RELEASED;
					break;
				
				default:
					break;
					
			}			
		}
	}

	/**
	 * @return the button state
	 */
	public ButtonState getButtonState()
	{
		return buttonState;
	}	
}
