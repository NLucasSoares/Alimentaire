package simulation.world.animal.group;

import java.util.ArrayList;

import simulation.world.animal.group.mortalityRate.MortalityRate;
import simulation.world.animal.species.state.AnimalState;

/**
 * The current state of a particular group of animals. Includes a number of
 * animals, mortality rate and table of current animal states.
 * 
 * @author Clément Thévin
 * 
 */
public class GroupState {

	private int maximumFellowNumber;
	private int[] deadAnimalsOverLastTurns;
	private MortalityRate mortalityRate;
	private ArrayList<AnimalState> animalStates;

	public GroupState(int maximumFellowNumber) {
		this.deadAnimalsOverLastTurns = new int[simulation.constant.SimulationConstant.LAST_TURNS];
		this.mortalityRate = new MortalityRate();
		this.animalStates = new ArrayList<AnimalState>();
		this.maximumFellowNumber = maximumFellowNumber;
	}

	public int getMaximumFellowNumber() {
		return maximumFellowNumber;
	}

	public void setMaximumFellowNumber(int maximumFellowNumber) {
		this.maximumFellowNumber = maximumFellowNumber;
	}

	public int[] getDeadAnimalsOverLastTurns() {
		return deadAnimalsOverLastTurns;
	}

	public void setDeadAnimalsOverLastTurns(int[] deadAnimalsOverLastTurns) {
		this.deadAnimalsOverLastTurns = deadAnimalsOverLastTurns;
	}

	public MortalityRate getMortalityRate() {
		return mortalityRate;
	}

	public void setMortalityRate(MortalityRate mortalityRate) {
		this.mortalityRate = mortalityRate;
	}

	public ArrayList<AnimalState> getAnimalStates() {
		return animalStates;
	}

	public void setAnimalState(ArrayList<AnimalState> animalStates) {
		this.animalStates = animalStates;
	}

}
