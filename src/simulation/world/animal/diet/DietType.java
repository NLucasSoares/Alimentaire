package simulation.world.animal.diet;

import java.security.InvalidParameterException;

/**
 * 
 * @author acamps
 */
public enum DietType
{
	DIET_TYPE_CARNIVOROUS,
	DIET_TYPE_HERBIVOROUS;
	
	public static final String[ ] DIET_PROPERTY=
	{
		"carnivorous",
		"herbivorous"
	};
	
	/**
	 * Get name from type
	 */
	public static  String getName( DietType dt )
	{
		return DietType.DIET_PROPERTY[ dt.ordinal( ) ];
	}
	
	/**
	 * Get type from name
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
