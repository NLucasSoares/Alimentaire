package simulation.world.environment.biome;

import java.awt.image.BufferedImage;
import java.io.IOException;

import art.Art;
import simulation.world.environment.biome.nearBiome.NearBiomes;
import simulation.world.environment.climate.Climate;

/**
 *	A biome definition. It is determined by a name and a climate.
 *	Every biome will have a	different image.
 * 
 *	@author CAMPS Aurèle
 */

public class Biome
{
	/**
	 * Name of the biome
	 */
	private String name;

	/**
	 * Climate of the biome
	 */
	private Climate climate;
	
	/**
	 * Id of the biome.
	 */
	private int id;
	
	/**
	 * Arround allowed biomes
	 */
	private NearBiomes nearAllowedBiomes;
	
	/**
	 * Hexagonal representation for map
	 * painting
	 */
	private BufferedImage biomeTile;
	
	/**
	 * Construct the biome
	 * 
	 * @throws IOException 
	 */
	public Biome( String name,
		Climate climate,
		String tileset,
		int id,
		NearBiomes nearAllowedBiomes ) throws IOException
	{
		// Save
		this.name = name;
		this.climate = climate;
		this.id = id;
		this.nearAllowedBiomes = nearAllowedBiomes;
		
		// Load the biome
		this.biomeTile = javax.imageio.ImageIO.read(
				art.Art.class.getResourceAsStream( Art.artLink
					+ "/"
					+ tileset ) );
	}
	
	/**
	 * @return the id
	 */
	public int getId()
	{
		return this.id;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return this.name;
	}

	/**
	 * @return the climate
	 */
	public Climate getClimate( )
	{
		return this.climate;
	}

	/**
	 * @return the tileset
	 */
	public BufferedImage getTileset()
	{
		return this.biomeTile;
	}
	
	/**
	 * @return the arount allowed biomes
	 */
	public NearBiomes getNearAllowedBiomes( )
	{
		return this.nearAllowedBiomes;
	}

	
	/**
	 * Export to String
	 */
	public String toString( )
	{
		return "Biome [name="
			+ name
			+ ", climate="
			+ climate
			+ ", id="
			+ id
			+ "]";
	}

	
}
