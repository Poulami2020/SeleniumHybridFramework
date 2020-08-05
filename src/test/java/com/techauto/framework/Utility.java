package com.techauto.framework;

public class Utility {

	/**
	 * To avoid any External instantiation of class
	 * private constructor is used
	 * 
	 */
	private Utility(){
		
	}
	
	/**
	 * It is to seperate string using //
	 * 
	 * @return File Seperator
	 */
	public static String getFileSeperator(){
		return System.getProperty("file.separator");
	}
}
