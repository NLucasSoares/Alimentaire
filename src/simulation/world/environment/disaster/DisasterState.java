package simulation.world.environment.disaster;

/**
 * Disaster State
 * 
 * The state of the disaster is composed by a start round and by the type of
 * disaster occurring.
 * 
 * @author acamps
 */

public class DisasterState
{
	/**
	 * The round when the disaster will start.
	 */
	private int startRound;

	/**
	 * The type of the disaster.
	 */
	private DisasterType type;

	/**
	 * @return the startRound
	 */
	public int getStartRound()
	{
		return startRound;
	}

	/**
	 * @return the type
	 */
	public DisasterType getType()
	{
		return type;
	}

}
