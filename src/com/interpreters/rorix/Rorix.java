package com.interpreters.rorix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Rorix {
    public static void main(String[] args) {
        if (args.length > 1) {
            System.out.println("Usage: rorix [script]");
            System.exit(64); // Command was used incorrectly i.e. wrong number of arguments
        }
        else if (args.length == 1) {
            runFile(args[0]);
        }
        else {
            runPrompt();
        }
    }
}
