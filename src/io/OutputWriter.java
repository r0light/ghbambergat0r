package io;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Paths;

import model.Problem;

public class OutputWriter {
    private Problem problem;

    public OutputWriter(Problem problem) {
	this.problem = problem;
    }

    private String format() {
	StringBuffer s = new StringBuffer();

	return s.toString();
    }

    public void print() {
	System.out.println(format());
    }

    public void write(String path) {
	String s = format();

	if (!Paths.get(path).toFile().mkdirs()) {
	    throw new IllegalArgumentException("Couldn't create parent directories of " + path);
	}
	try (PrintWriter writer = new PrintWriter(path)) {
	    writer.print(s);
	} catch (FileNotFoundException e) {
	    System.err.println("Error writing output file '" + path + "': " + e.getMessage());
	}
    }
}
