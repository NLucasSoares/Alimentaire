package simulation.world.aim;

import simulation.math.point.Point;

public interface AimedObject
{
	/**
	 * Get the position of the aimed object
	 * 
	 * @return the position
	 */
	public abstract Point<Double> getPosition( );
}
