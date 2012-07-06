package org.teagle.vcttool.control;

import teagle.vct.model.ResourceInstance;
import teagle.vct.tssg.impl.TSSGResourceInstance;

public class MyTSSGResourceInstance extends TSSGResourceInstance implements
		Comparable<ResourceInstance> {

	private static final long serialVersionUID = 1L;

	public int compareTo(ResourceInstance o) {
		return 0;
	}

}
