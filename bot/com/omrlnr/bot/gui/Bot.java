package com.omrlnr.bot.gui;

import java.applet.Applet;
import java.applet.AppletContext;
import java.applet.AppletStub;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * The GUI for the bot.
 * 
 * @author Omer Elnour
 */
public class Bot
{
	/**
	 * The {@link JFrame}.
	 */
	private JFrame frame;
	/**
	 * The Loader {@link Applet}.
	 */
	private Applet comp;

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 *             Catch all
	 */
	public Bot() throws Exception
	{
		frame = new JFrame("Bot");
		comp = initializeApplet();

		frame.add(comp, BorderLayout.CENTER);
		frame.add(new JMenuBar()
		{
			{
				add(new JMenu("Scripts")
				{
					{
						add(new JMenuItem("Talk")
						{
							{
								addActionListener(new ActionListener()
								{
									@Override
									public void actionPerformed(
											ActionEvent event)
									{
										comp.dispatchEvent(new KeyEvent(comp,
												KeyEvent.KEY_PRESSED, System
														.currentTimeMillis(),
												0, (int) 'a', 'a'));
									}
								});
							}
						});
					}
				});
			}
		}, BorderLayout.NORTH);
		frame.pack();
		JPopupMenu.setDefaultLightWeightPopupEnabled(false);
		frame.setVisible(true);
	}

	/**
	 * Returns the applet located at Loader.class.
	 * 
	 * @return The applet
	 * @throws Exception
	 *             Catch all
	 */
	private Applet initializeApplet() throws Exception
	{
		URLClassLoader loader = new URLClassLoader(new URL[] { new File(
				"./client/injected.jar").toURI().toURL() });
		Applet applet = (Applet) loader.loadClass("Loader").newInstance();
		loader.close();
		applet.setStub(new AppletStub()
		{
			@Override
			public boolean isActive()
			{
				return true;
			}

			@Override
			public URL getDocumentBase()
			{
				return null;
			}

			@Override
			public URL getCodeBase()
			{
				return getDocumentBase();
			}

			@Override
			public String getParameter(String name)
			{
				return "";
			}

			@Override
			public AppletContext getAppletContext()
			{
				return null;
			}

			@Override
			public void appletResize(int width, int height)
			{
			}
		});

		applet.init();
		applet.start();
		applet.setPreferredSize(new Dimension(840, 680));
		return applet;
	}
}