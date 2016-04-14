package simulation.file.operation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 	Operations on the files
 * 
 * 	@author
 * 		Lucas Soares
 */
public class Operation
{
	/**
	 * 	Is file existing?
	 */
	public static boolean isFileExist( String file )
	{
		// Try to open and close
		try
		{
			// Open
			BufferedReader br = new BufferedReader( new FileReader( file ) );
			
			// Close
			br.close( );
			
			// File exists
			return true;
		}
		catch( IOException e )
		{
			return false;
		}
	}
}
