package de.conway.pattern.io;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class XmlIO {
	
	/**
	 *  The method to write the data in to the file.
	 * @param <T>
	 * @param o the data to write
	 * @throws FileNotFoundException 
	 */
	public void write(String file, Object object) throws FileNotFoundException {
		
		try (XMLEncoder enc = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(file)))) 
		{
			enc.writeObject(object);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	/**
	 * This method reads the data from the file.
	 * @param <T>
	 * @return the written data from the file
	 * @throws FileNotFoundException 
	 * @throws XmlClassException 
	 */
	public Object read(String file) throws FileNotFoundException {
		
		Object o = null;
		XMLDecoder dec = new XMLDecoder(new BufferedInputStream(new FileInputStream(file)));
		
			
		o = dec.readObject();
		
		return o;
		
	}
}