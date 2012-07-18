package org.teagle.vcttool.control;

public class RandomKey
{
	public static String randomKey()
	{
		return String.valueOf((int)(Math.random()*(1<<30)));
	}
}
