package com.omrlnr.bot.hooks;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

/**
 * Hacked canvas class.
 * 
 * @author Omer Elnour
 */
@SuppressWarnings("serial")
public class Canvas extends java.awt.Canvas implements MouseMotionListener
{
	/**
	 * The {@link BufferedImage} we will be drawing on (for double buffering)
	 */
	private BufferedImage gameImage = new BufferedImage(840, 680,
			BufferedImage.TYPE_INT_ARGB);
	/**
	 * The x and y coordinates for the mouse.
	 */
	private int x, y;

	/**
	 * Default constructor. We simply add the {@link MouseMotionListener} here.
	 */
	public Canvas()
	{
		addMouseMotionListener(this);
	}

	@Override
	public Graphics getGraphics()
	{
		Graphics2D g = (Graphics2D) gameImage.getGraphics();
		g.setColor(Color.RED);
		g.setFont(new Font("Consolas", Font.BOLD, 12));
		g.drawString("Hacked Canvas 'n' Shit", 50, 50);
		g.drawLine(x, 0, x, gameImage.getHeight());
		g.drawLine(0, y, gameImage.getWidth(), y);
		super.getGraphics().drawImage(gameImage, 0, 0, null);
		return g;
	}

	@Override
	public void mouseDragged(MouseEvent event)
	{
		x = event.getX();
		y = event.getY();
	}

	@Override
	public void mouseMoved(MouseEvent event)
	{
		x = event.getX();
		y = event.getY();
	}
}