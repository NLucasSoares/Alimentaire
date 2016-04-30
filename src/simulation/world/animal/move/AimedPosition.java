package simulation.world.animal.move;

import simulation.constant.SimulationConstant;
import simulation.gui.object.Circle;
import simulation.math.point.Point;

public class AimedPosition
{	
	/**
	 * Aimed position
	 */
	private Circle aimedPosition;
	
	/**
	 * Construct random move
	 */
	public AimedPosition( )
	{
		// Init
		this.aimedPosition = null;
	}
	
	/**
	 * @return the aimed position
	 */
	public Point<Double> getPosition( )
	{
		if( this.isAimingSomething( ) )
			return this.aimedPosition.getPosition( );
		else
			return null;
	}
	
	/**
	 * @return if there's an aiming
	 */
	public boolean isAimingSomething( )
	{
		return this.aimedPosition != null;
	}
	
	/**
	 * Set new aimed point
	 * 
	 * @param x
	 * 		The x coordinate
	 * @param y
	 * 		The y coordinate
	 */
	public void setNewAimedPoint( double x,
		double y )
	{
		this.setNewAimedPoint( new Point<Double>( x,
			y ) );
	}
	
	/**
	 * Set new aimed point
	 * 
	 * @param point
	 * 		The point to aim
	 */
	public void setNewAimedPoint( Point<Double> point )
	{
		// Aim
		this.aimedPosition = new Circle( point,
			SimulationConstant.POSITION_AIMING_PRECISION );
	}
	
	/**
	 * Remove aim
	 */
	public void removeAim( )
	{
		this.aimedPosition = null;
	}
	
	/**
	 * Reach the position?
	 *
	 * @param currentPosition
	 * 		The current position
	 * 
	 * @return if it reach the goal position
	 */
	public boolean isReachedPosition( Point<Double> currentPosition )
	{
		if( this.aimedPosition == null )
			return false;
		else
			return this.aimedPosition.contains( currentPosition );
	}
	
}
