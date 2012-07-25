package org.teagle.clients.api;

import java.util.Collection;
import java.util.List;

import teagle.vct.model.ResourceInstance;
import teagle.vct.model.Vct;

public class VctPrinter {

	public static String toStringVct(final Vct vct) {
		return "Common Name: " + vct.getCommonName() + "\n" +
	           "State: " + vct.getState() + "\n" + 
			   "User: " + vct.getPerson().getUserName() + "\n";
	}

	public static String vctsToString(final Collection<Vct> vcts) {
		String result = "";
		for (final Vct vct : vcts)
			result += " * " + VctPrinter.toStringVct(vct) + "\n";
		return result;
	}

	public static String risToString(
			final List<ResourceInstance> resourceInstances) {
		String result = "";
		for (final ResourceInstance ri : resourceInstances)
			result += " * " + VctPrinter.toStringRI(ri) + "\n";
		return result;
	}

	private static String toStringRI(final ResourceInstance ri) {
		return ri.getCommonName() + " (" + ri.getDescription() + ")";
	}

}
