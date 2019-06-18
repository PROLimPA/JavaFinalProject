package edu.handong.csee.java;

import java.io.File;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import edu.handong.csee.java.Reader.ExcelManager;
import edu.handong.csee.java.Reader.ZipReader;
import edu.handong.csee.java.Errors.NotEnoughArgumentException;

public class Main {

	String dataPath; 
	String resultPath; 
	boolean help;
	
	public static void main(String[] args) {
		Main main = new Main();
		main.run(args);
	}

private void run(String[] args) {
	Options options = createOptions();
	
	if(parseOptions(options, args)) {
		if(help) {
			printHelp(options);
			return;
		}
		
		try {
			if(args.length < 2)
				throw new NotEnoughArgumentException();
		} catch(NotEnoughArgumentException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}
	
	File file = new File(dataPath);	//폴더
	File[] fileList = file.listFiles();
	
	ZipReader zipReader = new ZipReader();
	
	String contentAll = "";
	
	for(int i = 0; i < fileList.length; i++) {
		contentAll = contentAll + zipReader.readFileInZip(fileList[i]);
	}
	
    ExcelManager myReader = new ExcelManager();
    myReader.writeData(contentAll, resultPath);
}

private boolean parseOptions(Options options, String[] args) {
	CommandLineParser parser = new DefaultParser();

	try {
		CommandLine cmd = parser.parse(options, args);

		dataPath = cmd.getOptionValue("i");
		resultPath = cmd.getOptionValue("o");
		help = cmd.hasOption("h");

	} catch (Exception e) {
		printHelp(options);
		return false;
	}
	return true;
}

private Options createOptions() {
	Options options = new Options();
	
	options.addOption(Option.builder("i").longOpt("input")
			.desc("Set an input file path")
			.hasArg()
			.argName("Input path")
			.required()
			.build());
	
	options.addOption(Option.builder("o").longOpt("output")
			.desc("Set an output file path")
			.hasArg()
			.argName("Output path")
			.required()
			.build());
	
	options.addOption(Option.builder("h").longOpt("help")
			.desc("Show a Help page")
			.argName("Help")
			.build());
	
	return options;
}

private void printHelp(Options options) {
	HelpFormatter formatter = new HelpFormatter();
	String header = "JavaFinalProject";
	String footer ="";
	formatter.printHelp("JavaFinalProject", header, options, footer, true);
}

}
