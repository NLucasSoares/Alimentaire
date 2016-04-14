package simulation.world.plant;

import simulation.world.plant.need.Need;
import simulation.world.plant.need.state.NeedState;

/**
 * Plant Group
 * 
 * 
 * 
 * @author acamps
 */
public class PlantGroup
{

	/**
	 * The size of the plant ( in height ) of a plant group
	 */
	private int height;

	/**
	 * The amount of leaves of a plant group
	 */

	private int leaves;

	/**
	 * The needs state of a plant group
	 */
	private NeedState needsState;

	/**
	 * The needs definition of a plant group
	 */
	private Need needsDefinition;

	/**
	 * The diameter of the plant group
	 */
	private int diameter;

	/**
	 * @return the height
	 */
	public int getHeight()
	{
		return height;
	}

	/**
	 * @return the leaves
	 */
	public int getLeaves()
	{
		return leaves;
	}

	/**
	 * @return the needsState
	 */
	public NeedState getNeedsState()
	{
		return needsState;
	}

	/**
	 * @return the needsDefinition
	 */
	public Need getNeedsDefinition()
	{
		return needsDefinition;
	}

	/**
	 * @return the diameter
	 */
	public int getDiameter()
	{
		return diameter;
	}

}