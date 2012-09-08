package com.omrlnr.bot.adapter;

import org.objectweb.asm.tree.ClassNode;

/**
 * Represents an abstract adapter. Used when we wish to modify the client.
 * 
 * @author Omer Elnour
 */
public interface Adapter
{
	/**
	 * Apply the transformation to the argued {@link ClassNode}
	 * 
	 * @param node
	 *            The {@link ClassNode}
	 * @return Success
	 */
	public boolean adapt(ClassNode node);
}