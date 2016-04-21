package simulation.world.plant.need.state;

import simulation.world.plant.need.Need;

/**
 * Define what the plant has already absorbed to determine if it needs more
 * resources to survive.
 * 
 * @author CAMPS Aurèle
 */
public class NeedState extends Need
{
	/**
	 * Construct the need state
	 */
	public NeedState( )
	{
		// Parent constructor
		super( 0,
			0,
			0 );
	}

}
