package io;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import model.Problem;

public class Parser {

    private final Path path;

    public Parser(Path path) {
	this.path = path;
    }

    public Problem parse() throws ParserException {
	List<String> lines;
	try {
	    lines = Files.readAllLines(path, StandardCharsets.US_ASCII);

	    // TODO start parsing header

	    return new Problem(path.getFileName().toString());
	} catch (IOException e) {
	    throw new ParserException(e);
	}
    }

    private int[] parseHeader(String line) throws ParserException {
	String[] strParts = line.split(" ");
	if (strParts.length != 4) {
	    throw new ParserException("Header row must contain 4 ints");
	}
	// 0: rows, 1: columns, 2: min ingredients per slice, 3: max cells per
	// slice
	int[] parts;
	try {
	    parts = Arrays.stream(strParts).mapToInt(Integer::parseInt).toArray();
	} catch (NumberFormatException e) {
	    throw new ParserException("Header row must only contain ints", e);
	}
	return parts;
    }

}
