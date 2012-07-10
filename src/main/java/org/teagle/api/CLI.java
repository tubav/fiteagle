package org.teagle.api;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

public class CLI {
	private Config config;
	private static final String CMD_CRT_VCT = "createVCT";
	private final transient CommandCreateVCT commandCreateVCT = new CommandCreateVCT();

	public static void main(final String[] args) {
		System.out.println(new CLI().parse(args));
	}

	public CLI() {
		this.config = new Config();
	}

	public String parse(final String[] args) {
		final JCommander parameter = new JCommander(this);
		parameter.setProgramName("OpenTeagleCLI");
		parameter.addCommand(CLI.CMD_CRT_VCT, this.commandCreateVCT);
		String result = "";

		try {
			parameter.parse(args);
			final String command = parameter.getParsedCommand();
			if (CLI.CMD_CRT_VCT.equals(command)) {
				result = this.commandCreateVCT.exec(this.config);
			} else {
				result = CLI.getUsage(parameter);
			}
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

	private class Config {
		@Parameter(names = "-repourl", description = "The repository URL")
		public String repoUrl = "http://localhost:9080/repository/rest";
		@Parameter(names = "-requrl", description = "The request processor URL")
		public String reqUrl = "http://localhost:9000/reqproc";
		@Parameter(names = "-user", description = "The username")
		public String user = "root";
		@Parameter(names = "-pwd", description = "The password")
		public String password = "root";
	}

	@Parameters(separators = "=", commandDescription = "Create a VCT based on a file")
	private class CommandCreateVCT {
		@Parameter(names = "-filename", description = "The filename with the VCT description", required = true)
		private String filename = "";

		public String exec(Config config) throws IOException {
			TeagleClient client = new TeagleClient(config.user,
					config.password, config.reqUrl, config.repoUrl);

			client.bookVct(new File(filename));
			
			return client.getResult().message;
		}

	}

}