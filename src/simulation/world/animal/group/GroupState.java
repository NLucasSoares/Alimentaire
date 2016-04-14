package simulation.world.animal.group;

import java.util.ArrayList;

import simulation.world.animal.group.mortalityRate.MortalityRate;
import simulation.world.animal.species.state.AnimalState;

/**
 * The current state of a group of animals.
 * 
 * @author SOARES Lucas
 */
public class GroupState
{
	/**
	 * The maximum number of fellows
	 */
	private int maximumFellowNumber;
	
	/**
	 * The last count of animals
	 */
	private int[ ] lastAnimalCount;
	
	/**
	 * The mortality rate
	 */
	private MortalityRate mortalityRate;
	
	/**
	 * The state of animals
	 */
	private ArrayList<AnimalState> animalState;

	/**
	 * Construct the group state
	 * 
	 * @param maximumFellowNumber
	 * 		The maximum fellow number
	 */
	public GroupState( int maximumFellowNumber )
	{
		this.lastAnimalCount = new int [ simulation.constant.SimulationConstant.LAST_TURNS ];
		this.mortalityRate = new MortalityRate( );
		this.animalState = new ArrayList<AnimalState>( );
		this.maximumFellowNumber = maximumFellowNumber;
	}

	/**
	 * @return the maximum fellow number
	 */
	public int getMaximumFellowNumber( )
	{
		return maximumFellowNumber;
	}

	/**
	 * Set the maximum fellow number
	 * 
	 * @param maximumFellowNumber
	 *		The maximum fellow number
	 */
	public void setMaximumFellowNumber( int maximumFellowNumber )
	{
		this.maximumFellowNumber = maximumFellowNumber;
	}

	/**
	 * @return the historical of animal count
	 */
	public int[ ] getLastAnimalCount( )
	{
		return lastAnimalCount;
	}

	/**
	 * @return the mortality rate
	 */
	public MortalityRate getMortalityRate( )
	{
		return mortalityRate;
	}

	/**
	 * @return the animal state
	 */
	public ArrayList<AnimalState> getAnimalState( )
	{
		return animalState;
	}
}
