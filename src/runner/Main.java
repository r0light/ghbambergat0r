package runner;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import io.Parser;
import io.ParserException;
import model.Problem;

public class Main {

    public static void main(String[] args) throws ParserException {

	// List<String> inputs = Arrays.asList("a_example.in", "b_should_be_easy.in");
	// List<String> inputs = Arrays.asList("a_example.in", "b_should_be_easy.in",
	// "c_no_hurry.in");
	List<String> inputs = Arrays.asList("a_example.in", "b_should_be_easy.in");

	ExecutorService executor = Executors.newCachedThreadPool();
	try {
	    for (String input : inputs) {
		Problem problem = new Parser(Paths.get("input/" + input)).parse();
		Runner runner = new Runner(problem);
		executor.submit(runner);
	    }

	    while (!executor.isTerminated()) {
		try {
		    executor.shutdown();
		    executor.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
		} catch (InterruptedException ignore) {
		}
	    }
	} finally {
	    executor.shutdownNow();
	}
    }

}
