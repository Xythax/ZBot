package com.omrlnr.bot;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

import com.omrlnr.bot.adapter.impl.CanvasAdapter;

/**
 * Injects modifications into the client and then dumps it. The bot will use the
 * injected client.
 * 
 * @author Omer Elnour
 */
public class Injector
{
	/**
	 * A {@link HashMap} containing all the {@link ClassNode}s in the JAR file.
	 */
	private static HashMap<String, ClassNode> classes = new HashMap<>();

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
		dumpClient();
	}

	/**
	 * Make modifications to the client (e.g subclassing the canvas) and then
	 * save it.
	 * 
	 * @throws Exception
	 *             If client dumping fails
	 */
	private static void dumpClient() throws Exception
	{
		/**
		 * First, let's add all the class files from the untouched JAR to the
		 * {@link HashMap}.
		 */
		JarFile jf = new JarFile(new File("./client/client.jar"));
		Enumeration<JarEntry> entries = jf.entries();
		while (entries.hasMoreElements())
		{
			JarEntry entry = entries.nextElement();
			if (entry == null)
				break;
			if (entry.getName().endsWith(".class"))
			{
				ClassNode node = new ClassNode();
				new ClassReader(jf.getInputStream(entry)).accept(node, 0);
				classes.put(node.name, node);
			}
		}

		jf.close();

		/**
		 * Let's make some modifications to the client, shall we?
		 */
		apply();

		/**
		 * Finally, we dump the JAR!
		 */
		File newJar = new File("./client/injected.jar");
		FileOutputStream stream = new FileOutputStream(newJar);
		JarOutputStream out = new JarOutputStream(stream);

		for (ClassNode node : classes.values())
		{
			JarEntry entry = new JarEntry(node.name + ".class");
			out.putNextEntry(entry);
			ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
			node.accept(cw);
			out.write(cw.toByteArray());
		}

		out.close();
		stream.close();
	}

	/**
	 * Apply the {@link com.omrlnr.bot.adapter.Adapter}s to the JAR before
	 * dumping.
	 */
	private static void apply()
	{
		ClassNode canvas = null;
		for (ClassNode node : classes.values())
		{
			if (node.superName.equals("java/awt/Canvas"))
			{
				canvas = node;
				break;
			}
		}

		new CanvasAdapter().adapt(canvas);
	}
}