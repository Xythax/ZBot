package com.omrlnr.bot.script;

/**
 * The {@link Manifest} gives a description of the script (e.g the author, the
 * description, etc.)
 * 
 * @author Omer Elnour
 */
public interface Manifest
{
	/**
	 * Returns the author of the script.
	 * 
	 * @return The author
	 */
	public String getAuthor();

	/**
	 * Returns the description of this script.
	 * 
	 * @return The description
	 */
	public String getDescription();
}