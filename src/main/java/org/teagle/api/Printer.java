package org.teagle.api;

import java.util.Collection;

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
	
}
