package com.omrlnr.bot.script;

import com.omrlnr.bot.util.Constants;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Don't use this, this is just a test
 * 
 * @author Omer Elnour
 */
public class ScriptManager
{
	private static final ExecutorService executor = Executors
			.newFixedThreadPool(Constants.MAX_SCRIPTS);

	public static void invoke(final Script script)
	{
		executor.submit(new Runnable()
		{
			@Override
			public void run()
			{
				while (true)
				{
					try
					{
						Thread.sleep(script.iterate());
					}
					catch (InterruptedException exception)
					{
						exception.printStackTrace();
						executor.shutdown();
					}
				}
			}
		});
	}
}