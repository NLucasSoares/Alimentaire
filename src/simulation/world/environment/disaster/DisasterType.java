package simulation.world.environment.disaster;

/**
 * Disaster Type is an enumeration that list all the kind of disaster which can
 * be present while the Simulation is running.
 * 
 * Usually, a disaster will kill some animals and/or destroy resources in the
 * are. In exchange, corpses of dead animals and destroyed resources will
 * fertilized the ground.
 * 
 * @author CAMPS Aurèle
 */
public enum DisasterType
{

	/**
	 * If a meteor rain happen, a huge amount of animals will die.
	 */
	DISASTER_TYPE_METEORITE,

	/**
	 * If a fire happen, resources like trees and grass will decrease in the
	 * area but it will fertilized the ground creating new resources in a near
	 * future. Animals can also die in a fire.
	 */
	DISASTER_TYPE_FIRE,

	/**
	 * If a volcano happen, it will kill animals near the volcano.
	 */
	DISASTER_TYPE_VOLCANO,

	/**
	 * If an earthquake happen, it will kill animals in the area.
	 */
	DISASTER_TYPE_EARTHQUAKE,

	/**
	 * If a Tidal wave happen, it will kill animals near the shore and destroy
	 * resources.
	 */
	DISASTER_TYPE_TIDAL_WAVE,

}
