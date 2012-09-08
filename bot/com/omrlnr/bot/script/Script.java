package com.omrlnr.bot.script;

/**
 * Represents a script to be run by the bot.
 * 
 * @author Omer Elnour
 */
public abstract class Script
{
	/**
	 * The body of this method will be executed when the script is run before
	 * looping.
	 * 
	 * @return The time in milliseconds in time to wait before repeating
	 */
	public abstract int iterate();

	/**
	 * Returns the {@link Manifest}.
	 * 
	 * @return The {@link Manifest}
	 */
	public abstract Manifest manifest();

	/**
	 * Set the {@link Script}'s manifest to the argued one.
	 * 
	 * @param manifest
	 *            The {@link Manifest}
	 */
	public abstract void manifest(Manifest manifest);
}