package io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;

import model.Problem;
import model.Vehicle;

public class OutputWriter {
    private Problem problem;
    private Semaphore semaphore;

    public OutputWriter(Problem problem, Semaphore semaphore) {
	this.problem = problem;
	this.semaphore = semaphore;
    }

    private String format() {
	StringBuffer s = new StringBuffer();

	semaphore.acquireUninterruptibly();
	try {
	    List<String> outputs = new ArrayList<String>();
	    for (Vehicle vehicle : problem.vehicles) {
		StringJoiner joinerA = new StringJoiner(" ", "", "");
		List<String> rides = vehicle.rides.stream().map(r -> Integer.toString(r.id))
			.collect(Collectors.toList());
		rides.forEach(joinerA::add);
		outputs.add((rides.size() + " ") + joinerA.toString());
	    }
	    StringJoiner joinerB = new StringJoiner(System.lineSeparator(), "", "");
	    outputs.forEach(joinerB::add);
	    s.append(joinerB.toString());
	} finally {
	    semaphore.release();
	}

	return s.toString();
    }

    public void print() {
	System.out.println(format());
    }

    public void write(String path) {
	String s = format();

	semaphore.acquireUninterruptibly();
	Path preparedPath = prepareOutputFile(path);

	Charset charset = Charset.forName("US-ASCII");
	try (BufferedWriter writer = Files.newBufferedWriter(preparedPath, charset)) {
	    writer.write(s);
	} catch (IOException e) {
	    System.err.println("Error writing output file '" + path + "': " + e.getMessage());
	} finally {
	    semaphore.release();
	}
    }

    private Path prepareOutputFile(String path) {
	Path file = Paths.get(path);
	if (!Files.exists(file)) {
	    try {
		Files.createDirectories(file.getParent());
		Files.createFile(file);
		return file;
	    } catch (IOException e) {
		System.out.println(e);
		e.printStackTrace();
		return null;
	    }
	}
	return file;
    }
}
