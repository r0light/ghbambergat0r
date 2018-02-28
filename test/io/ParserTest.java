package io;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import model.Problem;

public class ParserTest {

    @Test
    public void testParseStringSimple() throws ParserException {
	Path input = Paths.get("input/example.in");
	Parser parser = new Parser(input);

	Problem problem = parser.parse();
    }

}
