package simulation.world.animal.diet;

import java.security.InvalidParameterException;

/**
 * The type of diet
 * 
 * @author CAMPS Aurèle
 */
public enum DietType
{
	/**
	 * Carnivorous diet
	 */
	DIET_TYPE_CARNIVOROUS,
	
	/**
	 * Herbivorous diet
	 */
	DIET_TYPE_HERBIVOROUS;
	
	/**
	 * Name of diet type
	 */
	public static final String[ ] DIET_PROPERTY=
	{
		"carnivorous",
		"herbivorous"
	};
	
	/**
	 * Get name from type
	 * 
	 * @param dt
	 * 		The diet type
	 * 
	 * @return the name
	 */
	public static  String getName( DietType dt )
	{
		return DietType.DIET_PROPERTY[ dt.ordinal( ) ];
	}
	
	/**
	 * Get type from name
	 * 
	 * @param name
	 * 		The name of the type
	 * 
	 * @return the enumerated type
	 * 
	 * @throws InvalidParameterException
	 */
	public static DietType getType( String name ) throws InvalidParameterException
	{
		// Look for name
		for( DietType d : DietType.values( ) )
			if( DietType.DIET_PROPERTY[ d.ordinal() ].equals( name ) )
				return d;
		
		// Can't find
		throw new InvalidParameterException( );
	}
}
