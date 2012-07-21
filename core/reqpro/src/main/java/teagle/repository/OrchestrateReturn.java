package teagle.repository;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

class LogEntry
{
	
}

class Logbook extends LogEntry
{
	
	
}

class Entry extends LogEntry
{
	public String message;
	public String time_stamp;
	public String severity;
}

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
	public String[] logbook;
	public Result result;
	private String launchparams;
}
