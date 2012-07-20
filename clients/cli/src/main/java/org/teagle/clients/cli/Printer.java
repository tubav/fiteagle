package org.teagle.clients.cli;

import java.util.Collection;
import java.util.List;

import teagle.vct.model.ResourceInstance;
import teagle.vct.model.Vct;

public class Printer {

	public static String toStringVct(final Vct vct) {
		return vct.getCommonName() + " (" + vct.getState() + ")";
	}

	public static String vctsToString(final Collection<Vct> vcts) {
		String result = "";
		for (final Vct vct : vcts)
			result += " * " + Printer.toStringVct(vct) + "\n";
		return result;
	}

	public static String risToString(
			final List<ResourceInstance> resourceInstances) {
		String result = "";
		for (final ResourceInstance ri : resourceInstances)
			result += " * " + Printer.toStringRI(ri) + "\n";
		return result;
	}

	private static String toStringRI(final ResourceInstance ri) {
		return ri.getCommonName() + " (" + ri.getDescription() + ")";
	}

}
