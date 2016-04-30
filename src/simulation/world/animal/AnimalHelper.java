package simulation.world.animal;

/**
 * Work around animals
 * 
 * @author SOARES Lucas
 */
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
		return simulation.math.probability.Operation.random( 0.01d,
			agility / 100.0d );
	}
}
