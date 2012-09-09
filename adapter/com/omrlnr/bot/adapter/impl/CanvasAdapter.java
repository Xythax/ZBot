package com.omrlnr.bot.adapter.impl;

import com.omrlnr.bot.adapter.Adapter;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.ListIterator;

/**
 * Handles the subclassing of the {@link java.awt.Canvas} class (replaces all
 * instances of java/awt/Canvas with our own implementation)
 * 
 * @author Omer Elnour
 */
public class CanvasAdapter implements Adapter
{
	@Override
	public boolean adapt(ClassNode node)
	{
		if (!node.superName.equals("java/awt/Canvas"))
			return false;
		System.out.println("Found Canvas Class: " + node.name);
		node.superName = "com/omrlnr/bot/hooks/Canvas";
		ListIterator<?> mli = node.methods.listIterator();
		while (mli.hasNext())
		{
			MethodNode mn = (MethodNode) mli.next();
			if (mn.name.equals("<init>"))
			{
				ListIterator<?> ili = mn.instructions.iterator();
				while (ili.hasNext())
				{
					AbstractInsnNode ain = (AbstractInsnNode) ili.next();
					if (ain.getOpcode() == Opcodes.INVOKESPECIAL)
					{
						MethodInsnNode min = (MethodInsnNode) ain;
						min.owner = "com/omrlnr/bot/hooks/Canvas";
						break;
					}
				}
			}
		}
		return true;
	}
}