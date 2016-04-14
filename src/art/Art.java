package art;

/**
 * Art definitions
 * 
 * @author
 * 		Lucas SOARES
 */
public class Art
{
	/**
	 * The name of the art
	 */
	private static final String artName[ ] =
	{
		"backgroundSplit",
		"cowSplit",
		"lionSplit",
		"treeSplit",
		"desertHexagon",
		"forestHexagon",
		"jungleHexagon",
		"plainHexagon",
		"savannaHexagon",
		"snowPlainHexagon",
		"taigaHexagon",
		"waterHexagon"
	};
	
	/**
	 * The extension of an art file
	 */
	public static final String artType = "png";
	
	/**
	 * Link to an art file
	 */
	public static final String artLink = "/art";
	
	/**
	 * Compose an art name
	 * 
	 * 	@param al
	 * 		The art to treat
	 * 		
	 * 	@return the link to the art file
	 */
	public static String composeArtLink( art.ArtList al )
	{
		return art.Art.artLink
			+ "/"
			+ art.Art.artName[ al.ordinal( ) ]
			+ "."
			+ art.Art.artType;
	}
	
	/**
	 * Get BufferedImage from an art
	 * 
	 * @param al
	 * 		The art to treat
	 * 
	 * @return the image of art
	 */
	public static java.awt.image.BufferedImage getArtImage( art.ArtList al ) throws java.io.IOException
	{
		return javax.imageio.ImageIO.read(
			art.Art.class.getResourceAsStream( art.Art.composeArtLink( al ) ) );
	}
}
