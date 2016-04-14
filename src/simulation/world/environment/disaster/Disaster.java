package simulation.world.environment.disaster;

import simulation.world.environment.disaster.step.Step;

/**
 * Disaster
 * 
 * The description of a disaster.
 * 
 * @author CAMPS Aurèle
 */
public class Disaster
{
	/**
	 * When the disaster will start.
	 */
	private int[ ] stepTime;
	
	/**
	 * One step of the disaster.
	 */
	private Step[ ] step;

	/**
	 * Construct the disaster
	 */
	public Disaster( )
	{
		
	}
	
	/**
	 * @return the stepTime
	 */
	public int[ ] getStepTime()
	{
		return stepTime;
	}

	/**
	 * @return the step
	 */
	public Step[ ] getStep()
	{
		return step;
	}
	
}
