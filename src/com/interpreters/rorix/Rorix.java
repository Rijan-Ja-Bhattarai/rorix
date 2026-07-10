package com.interpreters.rorix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Rorix {
    static boolean hadError = false;

    static void main(String[] args) throws IOException {
        if (args.length > 1) {
            System.out.println("Usage: rorix script.rx");
            System.out.println("rorix --help");
            System.out.println("rorix --version");
            System.exit(64);
        }
        else if (args.length == 1 && args[0].equals("--version")) {
            printVersion();
            System.exit(0);
        }
        else if (args.length == 1 && args[0].equals("--help")) {
            printConsoleHelp();
            System.exit(0);
        }
        else if (args.length == 1) {
            // Exit with code 65 if scanning/parsing found an error.
            runScript(args[0]);
        }
        else {
            // Open REPL Mode
            runPrompt();
        }
    }

    public static void runScript(String path) throws IOException {
        // Convert the script into String for parsing -> Each character into byte -> new String
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, StandardCharsets.UTF_8));

        if (hadError) System.exit(65);
    }

    static void run(String source) {
        Scanner scanner = new Scanner(source);
        List<Token> tokens = scanner.scanTokens();

        for (Token token : tokens) {
            System.out.println(token);
        }
    }

    static void runPrompt() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        while (true) {
            System.out.print("> ");
            String line = reader.readLine();

            // Ctrl+D / EOF
            if (line == null) {
                System.out.println();
                break;
            }

            // Just pressing Enter
            if (line.trim().isEmpty()) {
                continue;
            }

            if (handleReplCommand(line)) continue;

            run(line);
            hadError = false;
        }
    }

    private static boolean handleReplCommand(String line) {
        String command = line.strip();

        // Check whether the given command is valid REPL command if yes execute the code and return true signal else false signal

        if (!command.startsWith(":")) {
            return false;
        }

        switch (command) {
            case ":exit": System.exit(0);
            case ":help": printREPLHelp(); return true;
            case ":version": printVersion(); return true;
            default:
                System.out.println("Unrecognized REPL command");
                return true;
        }
    }

    public static void error(int line, String message) {
        errorReport(line, "", message);
    }

    private static void errorReport(int line, String where, String message) {
        System.err.println(
                "[line " + line + "] Error" + where + ": " + message
        );
        hadError = true;
    }

    private static void printVersion() {
        System.out.println("Rorix 1.0a0");
    }

    private static void printConsoleHelp() {
        System.out.println("Rorix usage:");
        System.out.println("  rorix              Start REPL mode");
        System.out.println("  rorix script.rx    Run a script file");
        System.out.println("  rorix --help       Show this help message");
        System.out.println("  rorix --version    Show version information");
    }

    private static void printREPLHelp() {
        System.out.println("Welcome to Rorix 1.0a0 help utility! If this is your first time using\n" +
                "Rorix, you should definitely check out the tutorial at\n" +
                "https://rorix.docs\n"
        );
    }
}
