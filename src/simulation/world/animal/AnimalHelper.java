package simulation.world.animal;

public class AnimalHelper
{
	/**
	 * Get a animal speed based on his agility (and
	 * some random)
	 * 
	 * @param agility
	 * 		The agility of animal
	 */
	public static double calculateAnimalSpeed( double agility )
	{
		return simulation.math.Operation.random( 0.01d,
			agility / 100.0d );
	}
}
