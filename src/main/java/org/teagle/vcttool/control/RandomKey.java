package org.teagle.vcttool.control;

public class RandomKey
{
	public static String randomKey()
	{
		return String.valueOf((int)(Math.random()*(double)(1<<30)));
	}
}
