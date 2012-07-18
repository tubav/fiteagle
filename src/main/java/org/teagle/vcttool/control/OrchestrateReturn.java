package org.teagle.vcttool.control;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("return")
public class OrchestrateReturn {
	// ! use this when creating an error return (no mappings)
	public OrchestrateReturn(int status, String message) {
		this.status = status;
		this.message = message;
		this.log = "";
		this.result = new Result();
		this.result.idmapping = new Result.Mapping[] {};
	}
	
	public abstract class AbstractLogEntry {}
	
/*	@XStreamAlias("logentry")
	public class LogEntry extends AbstractLogEntry
	{
		public String logentry;
	}*/
	
	@XStreamAlias("logbook")
	public class LogBook extends AbstractLogEntry
	{
		public String name;
		public String component;
		public Object[] entries;
	}

	@XStreamAlias("result")
	public class Result {
		@XStreamAlias("mapping")
		public class Mapping {
			@XStreamAsAttribute
			public String designid;

			@XStreamAsAttribute
			public String runtimeid;
		}

		public class Return {
			public String exec;
			public String report;
		}

		public Mapping[] idmapping;
		@XStreamAlias("return")
		public Return return_;

	}

	public int status;
	public String message;
	public String log;
	public LogBook logbook;
	public Result result;
}