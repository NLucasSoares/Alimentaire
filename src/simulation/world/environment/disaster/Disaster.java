package simulation.world.environment.disaster;

import simulation.world.environment.disaster.step.Step;

/**
 * Disaster
 * 
 * The time of the disaster.
 * 
 * @author acamps
 */
public class Disaster {
	
	/**
	 * When the disaster will start.
	 */
	private int[] stepTime;
	
	/**
	 * One step of the disaster.
	 */
	private Step[] step;

	/**
	 * @return the stepTime
	 */
	public int[] getStepTime()
	{
		return stepTime;
	}

	/**
	 * @return the step
	 */
	public Step[] getStep()
	{
		return step;
	}
	
}
