package org.teagle.clients.cli;

import java.util.Collection;
import java.util.List;

import teagle.vct.model.ResourceInstance;
import teagle.vct.model.Vct;

public class Printer {

	public static String toStringVct(Vct vct) {
		return vct.getCommonName() + " (" + vct.getState() + ")";
	}

	public static String vctsToString(Collection<Vct> vcts) {
			String result = "";
			for (Vct vct : vcts) {
				result += " * " + Printer.toStringVct(vct) + "\n";
			}
			return result;	
	}

	public static String risToString(List<ResourceInstance> resourceInstances) {
		String result = "";
		for (ResourceInstance ri : resourceInstances)
			result += " * " + Printer.toStringRI(ri) + "\n";
		return result;	
	}

	private static String toStringRI(ResourceInstance ri) {
		return ri.getCommonName() + " (" + ri.getDescription() + ")";
	}
	
}
