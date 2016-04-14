package simulation.world.animal.group.mortalityRate;

import simulation.constant.SimulationConstant;
import simulation.world.animal.group.GroupState;

/**
 * 
 * The number of animals in a group that died in the last few days.
 * 
 * @author Clément Thévin
 * 
 */
public class MortalityRate {

	/**
	 * Probability of dying shown by this group of animals in the last few days.
	 * Higher rates will make groups more prone to changing areas.
	 */
	private double[] mortalityRate;
	/**
	 * Number of animals in this group in the last few days.
	 */
	private int[] lastFellowNumber;

	public MortalityRate() {
		this.mortalityRate = new double[SimulationConstant.LAST_TURNS];
		this.lastFellowNumber = new int[SimulationConstant.LAST_TURNS];
	}

	public double[] getMortalityRate() {
		return mortalityRate;
	}

	public void setMortalityRate(double[] mortalityRate) {
		this.mortalityRate = mortalityRate;
	}

	public int[] getLastFellowNumber() {
		return lastFellowNumber;
	}

	public void setLastFellowNumber(int[] lastFellowNumber) {
		this.lastFellowNumber = lastFellowNumber;
	}

	public double calculateMortalityRateOnTurn(GroupState group) {
		double mortalityRateOnTurn = group.getAnimalStates().size() / group.getDeadAnimalsOverLastTurns()[0];
		return mortalityRateOnTurn;
	}

}
