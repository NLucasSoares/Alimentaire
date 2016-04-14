package simulation.gui.panels.simulation;

import simulation.constant.Direction;
import simulation.math.counter.DelayedPingCyclicCounter;

public class AlphaEvolution extends DelayedPingCyclicCounter
{	
	/**
	 * Construct the alpha evolution
	 * 
	 * @param alphaStart
	 * 		The alpha at start
	 * @param alphaEnd
	 * 		The alpha at end
	 * @param delay
	 * 		Delay between changes
	 */
	public AlphaEvolution( int alphaStart,
		int alphaEnd,
		int delay )
	{
		// Parent constructor
		super( alphaStart,
			alphaStart,
			alphaEnd,
			Direction.DIRECTION_RIGHT,
			delay );
	}
	
	/**
	 * Get the current alpha, double value
	 * 
	 * @return the current alpha
	 */
	public double getCurrentAlpha( )
	{
		return (double)this.getCount( ) / 255.0d;
	}
	
}
