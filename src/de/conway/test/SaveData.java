package de.conway.test;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveData {
	
	private String filename;
	
	public SaveData(String filename) 
	{
		this.filename = filename;
	}
	
	/**
	 *  The method to write the data in to the file.
	 * @param o the data to write
	 */
	public void write(Object o) 
	{
		try (XMLEncoder enc = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filename)))) 
		{
			enc.writeObject(o);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	/**
	 * This method reads the data from the file.
	 * @return the written data from the file
	 */
	public Object read() 
	{
		Object o = null;
		try (XMLDecoder dec = new XMLDecoder(new BufferedInputStream(new FileInputStream(filename)))) 
		{
			o = dec.readObject();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return o;
	}
}
