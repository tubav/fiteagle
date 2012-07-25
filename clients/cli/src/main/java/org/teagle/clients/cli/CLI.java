package org.teagle.clients.cli;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import org.teagle.api.util.APIProperties;
import org.teagle.clients.api.LegacyTeagleClient;
import org.teagle.clients.api.VctPrinter;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

public class CLI {
	@Parameter(names = "-repourl", description = "The repository URL")
	public String repoUrl;
	@Parameter(names = "-requrl", description = "The request processor URL")
	public String reqUrl;
	@Parameter(names = "-user", description = "The username")
	public String user;
	@Parameter(names = "-pwd", description = "The password", password = true)
	public String password;

	private static final String CMD_BOOK_VCT = "createVCT";
	private static final String CMD_START_VCT = "startVCT";
	private static final String CMD_STOP_VCT = "stopVCT";
	private static final String CMD_DELETE_VCT = "deleteVCT";
	private static final String CMD_LIST_VCTS = "listVCTs";
	private static final String CMD_LIST_RIS = "listRIs";
	private final transient CommandBookVCT commandBookVCT = new CommandBookVCT();
	private final transient CommandStartVCT commandStartVCT = new CommandStartVCT();
	private final transient CommandStopVCT commandStopVCT = new CommandStopVCT();
	private final transient CommandDeleteVCT commandDeleteVCT = new CommandDeleteVCT();
	private final transient CommandListVCTs commandListVCTs = new CommandListVCTs();
	private final transient CommandListRIs commandListRIs = new CommandListRIs();
	private LegacyTeagleClient client;
	private final JCommander parameter;

	public static void main(final String[] args) {
		System.out.println(new CLI().parse(args));
	}

	public CLI() {
		initDefaultParameter();
		this.parameter = setupParameter();
	}

	public String parse(final String[] args) {
		String result;
		try {
			parameter.parse(args);
			this.client = new LegacyTeagleClient(this.user, this.password,
					this.reqUrl, this.repoUrl);
			final String command = parameter.getParsedCommand();
			if (CLI.CMD_BOOK_VCT.equals(command))
				result = this.commandBookVCT.exec();
			else if (CLI.CMD_START_VCT.equals(command))
				result = this.commandStartVCT.exec();
			else if (CLI.CMD_STOP_VCT.equals(command))
				result = this.commandStopVCT.exec();
			else if (CLI.CMD_DELETE_VCT.equals(command))
				result = this.commandDeleteVCT.exec();
			else if (CLI.CMD_LIST_VCTS.equals(command))
				result = this.commandListVCTs.exec();
			else if (CLI.CMD_LIST_RIS.equals(command))
				result = this.commandListRIs.exec();
			else
				result = CLI.getUsage(parameter);
		} catch (final Exception ex) {
			result = "ERROR: " + ex.getMessage() + "\n";
			result += CLI.getUsage(parameter);
		}
		return result;
	}

	private JCommander setupParameter() {
		final JCommander parameter = new JCommander(this);

		parameter.setProgramName("fiteagle_cli");
		parameter.addCommand(CLI.CMD_BOOK_VCT, this.commandBookVCT);
		parameter.addCommand(CLI.CMD_START_VCT, this.commandStartVCT);
		parameter.addCommand(CLI.CMD_STOP_VCT, this.commandStopVCT);
		parameter.addCommand(CLI.CMD_DELETE_VCT, this.commandDeleteVCT);
		parameter.addCommand(CLI.CMD_LIST_VCTS, this.commandListVCTs);
		parameter.addCommand(CLI.CMD_LIST_RIS, this.commandListRIs);
		return parameter;
	}

	private void initDefaultParameter() {
		this.repoUrl = APIProperties.getRepoUrl();
		this.reqUrl = APIProperties.getReqUrl();
		this.user = APIProperties.getUser();
		this.password = APIProperties.getPassword();

		if (this.repoUrl.isEmpty())
			this.repoUrl = "http://localhost:9080/repository/rest";
		if (this.reqUrl.isEmpty())
			this.reqUrl = "http://localhost:9000/reqproc";
		if (this.user.isEmpty())
			this.user = "root";
		if (this.password.isEmpty())
			this.password = "root";
	}

	private static String getUsage(final JCommander parameter) {
		final ByteArrayOutputStream stream = new ByteArrayOutputStream();
		final PrintStream printStream = new PrintStream(stream);
		final PrintStream stdout = System.out;
		final PrintStream stderr = System.err;

		System.setOut(printStream);
		System.setErr(printStream);
		parameter.usage();
		System.setOut(stdout);
		System.setErr(stderr);
		printStream
				.println("Hint: you can set defaults at ~/.fiteagle/api.properties.");

		return stream.toString();
	}

	@Parameters(separators = "=", commandDescription = "Book a VCT based on a file")
	private class CommandBookVCT {
		@Parameter(names = "-filename", description = "The filename with the VCT description", required = true)
		private final String filename = "";

		public String exec() throws IOException {
			CLI.this.client.bookVct(new File(this.filename));
			return CLI.this.client.getResult().message;
		}
	}

	@Parameters(separators = "=", commandDescription = "Starts a booked VCT")
	private class CommandStartVCT {
		@Parameter(names = "-vctname", description = "The name of the VCT", required = true)
		private final String vctName = "";

		public String exec() throws IOException {
			CLI.this.client.startVct(CLI.this.user, this.vctName);
			return CLI.this.client.getResult().message;
		}
	}

	@Parameters(separators = "=", commandDescription = "Stops a started VCT")
	private class CommandStopVCT {
		@Parameter(names = "-vctname", description = "The name of the VCT", required = true)
		private final String vctName = "";

		public String exec() throws IOException {
			CLI.this.client.stopVct(CLI.this.user, this.vctName);
			return CLI.this.client.getResult().message;
		}
	}

	@Parameters(separators = "=", commandDescription = "Deletes a booked VCT")
	private class CommandDeleteVCT {
		@Parameter(names = "-vctname", description = "The name of the VCT", required = true)
		private final String vctName = "";

		public String exec() throws IOException {
			CLI.this.client.deleteVct(CLI.this.user, this.vctName);
			return CLI.this.client.getResult().message;
		}
	}

	@Parameters(separators = "=", commandDescription = "Lists booked VCTs")
	private class CommandListVCTs {
		public String exec() throws IOException {
			return VctPrinter.vctsToString(CLI.this.client.getVCTs());
		}
	}

	@Parameters(separators = "=", commandDescription = "Lists Resource Instances")
	private class CommandListRIs {
		public String exec() throws IOException {
			return VctPrinter.risToString(CLI.this.client.getResourceInstances());
		}
	}
}