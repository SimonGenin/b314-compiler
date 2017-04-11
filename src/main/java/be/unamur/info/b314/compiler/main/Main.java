package be.unamur.info.b314.compiler.main;

import be.unamur.info.b314.compiler.B314Lexer;
import be.unamur.info.b314.compiler.B314Parser;
import be.unamur.info.b314.compiler.PCode.PCodePrinter;
import be.unamur.info.b314.compiler.exception.ParsingException;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

import static com.google.common.base.Preconditions.checkArgument;

/**
 *
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    private static final String NAME = "b314-compiler";
    private static final String HELP = "h";
    private static final String INPUT = "i";
    private static final String OUTPUT = "o";

    /**
     * Main method launched when starting compiler jar file.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Main main = new Main();
        CommandLineParser parser = new DefaultParser();
        CommandLine line = null;
        try {
            line = parser.parse(main.options, args);
        } catch (ParseException ex) {
            LOG.error("Error while parsing command line!", ex);
            main.printHelpMessage();
        }
        // If help is requested, print help message and exit.
        if (line != null) {
            if (line.hasOption(HELP)) {
                main.printHelpMessage();
            } else {
                // Else start compilation
                try {
                    main.initialise(line);
                    main.compile();
                    System.err.println("OK"); // Print OK on stderr
                } catch (Exception e) {
                    LOG.error("Exception occured during compilation!", e);
                    System.err.println("KO"); // Print KO on stderr if a problem occured
                }
            }
        }
    }

    /**
     * The command line options.
     */
    private final Options options;

    /**
     * The input B314 file.
     */
    private File inputFile;

    /**
     * The output PCode file.
     */
    private File outputFile;
    
    private Main() {
        // Create command line options
        options = new Options();
        options.addOption(Option.builder(HELP)
                .desc("Prints this help message.")
                .build());

        options.addOption(Option.builder(INPUT)
                .desc("The B314 input file.")
                .hasArg()
                .build());

        options.addOption(Option.builder(OUTPUT)
                .desc("The PCOde output file.")
                .hasArg()
                .build());
    }

    /**
     * Prints help message with this options.
     */
    private void printHelpMessage() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.setWidth(128);
        formatter.printHelp(String.format("java -jar %s.jar -%s | %s %s",
                NAME, HELP, INPUT, OUTPUT), options);
    }

    /**
     * Initialise the input compiler using the given input line.
     *
     * @throws Exception If one of the three required arguments is not provided.
     */
    private void initialise(CommandLine line) throws Exception {
        LOG.debug("Initialisation");
        // Check that the arguments are there
        if (!line.hasOption(INPUT)) {
            throw new ParseException(String.format("Option %s is mandatory!", INPUT));
        } else if (!line.hasOption(OUTPUT)) {
            throw new ParseException(String.format("Option %s is mandatory!", OUTPUT));
        }
        // Get given files and check they exist
        inputFile = new File(line.getOptionValue(INPUT));
        checkArgument(inputFile.exists() && inputFile.isFile(), "File %s not found!", inputFile.getName());
        LOG.debug("Input file set to {}", inputFile.getPath());

        outputFile = new File(line.getOptionValue(OUTPUT));
        if(!outputFile.exists()){
            outputFile.createNewFile();
        }
        checkArgument(outputFile.exists() && outputFile.isFile(), "File %s not created!", outputFile.getName());
        LOG.debug("Output file set to {}", outputFile.getPath());

        LOG.debug("Initialisation: done");
    }
    
    /**
     * Compiler Methods, this is where the MAGIC happens !!! \o/
     */
    private void compile() throws IOException, ParsingException {

        // Print the currently compiled program
        printFile(inputFile);

        LOG.debug("Start compilation");

        /*
         * Parse the input.
         * Takes in an input file, returns an abstract syntax tree
         */

        LOG.debug("Parsing input");

        B314Parser.RootContext tree = parse(new ANTLRInputStream(new FileInputStream(inputFile)));
        LOG.debug("Parsing input: done");
        LOG.debug("AST is {}", tree.toStringTree(parser));

        Visitor visitor = new Visitor();
        tree.accept(visitor);

        //Print PCode
        LOG.debug("Print PCode");
        printPCode(tree);
        LOG.debug("Printing PCode: done");
    }

    // the parser
    private B314Parser parser;

    /**
     * Builds the AST from the input.
     *
     * If problems come out while parsing, throw a Parsing Exception
     */
    private B314Parser.RootContext parse (ANTLRInputStream input) throws ParsingException
    {

        /*
         * The lexer is applied to the input and tokenizes it.
         * The result is a stream of tokens.
         */
        CommonTokenStream tokens = new CommonTokenStream(new B314Lexer(input));

        /*
         * Initializes the parser, based on our grammar.
         * We give it the tokens so it can apply the grammar rules on them.
         */
        parser = new B314Parser(tokens);

        /*
         * We provide to the parser our own implementation of an error listener.
         * It will allow us to have more control on error handling.
         */
        parser.removeErrorListeners();
        MyConsoleErrorListener errorListener = new MyConsoleErrorListener();
        parser.addErrorListener(errorListener);

        B314Parser.RootContext tree;

        /*
         * Parse the tokens.
         * If the grammar contains an error, we throw an exception
         */
        try {
            tree = parser.root();
        } catch (RecognitionException e) {
            throw new ParsingException("Error while retrieving parsing tree!", e);
        }

        /*
         * Check if there was a mistake during the parsing.
         * If so, throw an exception
         */
        if (errorListener.errorHasBeenReported()) {
            throw new ParsingException("Error while parsing the input!");
        }

        return tree;

    }

    /**
     *  Print to std output the content of a file.
     *
     * @param file the file to print
     * @throws IOException if the file can't be found, or an error occurs while printing it
     */
    private void printFile (File file) throws IOException
    {

        LOG.debug("Printing the input file...");

        System.out.println();
        System.out.println();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }

        System.out.println();
        System.out.println();

    }

    private void printPCode(B314Parser.RootContext tree) throws FileNotFoundException{
        PCodePrinter printer = new PCodePrinter(outputFile);
        PCodeVisitor visitor = new PCodeVisitor(printer);

        //create environement variable
        visitor.initEnvVar();
        tree.accept(visitor);

        //finish file out
        visitor.endPCode();

        printer.flush();
        printer.close();

    }

}
