/*
 * Copyright (C) 2009 FhG FOKUS, Institute for Open Communication Systems
 *
 * This file is part of OMACO - the Open IMS Core Management Console
 * 
 * OMACO is proprietary software that is licensed 
 * under the FhG FOKUS "SOURCE CODE LICENSE for FOKUS Open IMS COMPONENTS".
 * You should have received a copy of the license along with this 
 * program; if not, write to Fraunhofer Institute FOKUS, Kaiserin-
 * Augusta Allee 31, 10589 Berlin, GERMANY 
 * 
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * It has to be noted that this software is not intended to become 
 * or act as a product in a commercial context! It is a PROTOTYPE
 * IMPLEMENTATION for IMS technology testing and IMS application 
 * development for research purposes, typically performed in IMS 
 * test-beds. See the attached license for more details. 
 *
 * For a license to use this software under conditions
 * other than those described here, please contact Fraunhofer FOKUS 
 * via e-mail at the following address:
 *     info@open-ims.org
 *
 */

package teagle.vct.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * An essential implementation of document templates, primarily for html.
 * This is only used by the vct tool for generating the booking summary and
 * confirmation. 
 * 
 * The syntax for templates is as simple as it gets: only the ${keyword} 
 * construct is allowed, and such occurrences are substituted by calling 
 * the render() method. No recursive rendering is done (for an html.template
 * that contains a list of sections that can themselves be generated from 
 * section.html, you will have to define a ${sections} placeholder, then
 * generate all sections, concatenate them manually and render the html
 * page using the result. Rudimentary but not really a nuisance given the
 * cost of alternatives. 
 * 
 * This code comes from the OMACO project, with perhaps very small changes.
 */
public class Template {
	private String file;
	private String text;
	private Map<String, String> defaults = new HashMap<String, String>();

	/**
	 * create a new template that is to be loaded from the *resource* passed
	 * as parameter.
	 * @param file
	 */
	public Template(String resource) {
		this.file = resource;
	}
	
	public Map<String, String> defaults() {
		return defaults;
	}
	
	private String replace(String result, Map<String, String> dict) {
		for (Map.Entry<String, String> entry: dict.entrySet()) {
			String pattern = "${" + entry.getKey() + "}";
			result = result.replace(pattern, entry.getValue());
		}
		return result; 
	}

	public String render(Map<String, String> dict) throws IOException {
		if (text==null) {
			InputStream s = Util.resourceStream(file);
			if (s==null) throw new IOException("can't open template: " + file);
			text = Util.readStream(s);
		}
		
		return replace(
				replace(text, dict),
				defaults);
	}
	
	/**
	 * Convenience function for rendering a template. It accepts as parameters
	 * a list of pairs of strings, representing keywords and values. 
	 * @return The generated document as a string.
	 */
	public String render(String ... args) throws IOException {
		if (args.length % 2 == 1)
			throw new IOException("expecting even number of arguments");
		
		Map<String, String> dict = new HashMap<String, String>();
		for (int i=1; i<args.length; i += 2)
			dict.put(args[i-1], args[i]);
		
		return render(dict);		
	}
}
