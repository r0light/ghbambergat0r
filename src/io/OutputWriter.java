package io;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import model.Problem;
import model.Vehicle;

public class OutputWriter {
    private Problem problem;

    public OutputWriter(Problem problem) {
	this.problem = problem;
    }

    private String format() {
	StringBuffer s = new StringBuffer();

	List<String> outputs = new ArrayList<String>();
	for (Vehicle vehicle : problem.vehicles) {
	    StringJoiner joinerA = new StringJoiner(" ", "", "");
	    List<String> rides = vehicle.rides.stream().map(r -> Integer.toString(r.id)).collect(Collectors.toList());
	    rides.forEach(joinerA::add);
	    outputs.add((rides.size() + " ") + joinerA.toString());
	}
	StringJoiner joinerB = new StringJoiner(System.lineSeparator(), "", "");
	outputs.forEach(joinerB::add);
	s.append(joinerB.toString());

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
