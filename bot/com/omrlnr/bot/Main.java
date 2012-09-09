package com.omrlnr.bot;

import com.omrlnr.bot.gui.Bot;

/**
 * This is the main entry point for the bot.
 * 
 * @author Omer Elnour
 */
public final class Main
{
	/**
	 * The main entry point for the JVM.
	 * 
	 * @param args
	 *            The command-line arguments
	 * @throws Exception
	 *             If the bot fails at startup
	 */
	public static void main(String[] args) throws Exception
	{
		new Bot();
	}
}