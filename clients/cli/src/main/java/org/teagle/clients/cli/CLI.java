package org.teagle.clients.cli;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

public class CLI {
	@Parameter(names = "-repourl", description = "The repository URL")
	public String repoUrl = "http://localhost:9080/repository/rest";
	@Parameter(names = "-requrl", description = "The request processor URL")
	public String reqUrl = "http://localhost:9000/reqproc";
	@Parameter(names = "-user", description = "The username")
	public String user = "root";
	@Parameter(names = "-pwd", description = "The password")
	public String password = "root";

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
	private TeagleClient client;

	public static void main(final String[] args) {
		System.out.println(new CLI().parse(args));
	}

	public String parse(final String[] args) {
		final JCommander parameter = new JCommander(this);

		parameter.setProgramName("OpenTeagleCLI");
		parameter.addCommand(CLI.CMD_BOOK_VCT, this.commandBookVCT);
		parameter.addCommand(CLI.CMD_START_VCT, this.commandStartVCT);
		parameter.addCommand(CLI.CMD_STOP_VCT, this.commandStopVCT);
		parameter.addCommand(CLI.CMD_DELETE_VCT, this.commandDeleteVCT);
		parameter.addCommand(CLI.CMD_LIST_VCTS, this.commandListVCTs);
		parameter.addCommand(CLI.CMD_LIST_RIS, this.commandListRIs);
		String result = "";

		try {
			parameter.parse(args);
			this.client = new TeagleClient(this.user, this.password,
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
			return Printer.vctsToString(CLI.this.client.getVCTs());
		}
	}

	@Parameters(separators = "=", commandDescription = "Lists Resource Instances")
	private class CommandListRIs {
		public String exec() throws IOException {
			return Printer.risToString(CLI.this.client.getResourceInstances());
		}
	}
}