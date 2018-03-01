package io;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.Problem;
import model.Ride;
import model.Vehicle;

public class Parser {

    private final Path path;

    public Parser(Path path) {
	this.path = path;
    }

    public Problem parse() throws ParserException {
	System.out.println("Parsing file: " + path.getFileName().toString());

	List<String> lines;
	try {
	    lines = Files.readAllLines(path, StandardCharsets.US_ASCII);

	    System.out.println("Parsing init line.. ");

	    String[] initLine = lines.get(0).split(" ");
	    int rows = Integer.parseInt(initLine[0]);
	    System.out.println("rows: " + rows);
	    int columns = Integer.parseInt(initLine[1]);
	    System.out.println("columns: " + columns);
	    int vehicles = Integer.parseInt(initLine[2]);
	    System.out.println("vehicles: " + vehicles);
	    int rides = Integer.parseInt(initLine[3]);
	    System.out.println("rides: " + rides);
	    int bonus = Integer.parseInt(initLine[4]);
	    System.out.println("bonus: " + bonus);
	    int steps = Integer.parseInt(initLine[5]);
	    System.out.println("steps: " + steps);
	    System.out.println("Parsing init line .. done !!");

	    // note: .parallel() in the end for performance
	    List<Ride> ridesList = lines.subList(1, lines.size()).stream().map(Parser::RideParser)
		    .collect(Collectors.toList());
	    int i = 0;
	    for (Ride ride : ridesList) {
		ride.id = i;
		i++;
	    }
	    Problem problem = new Problem(path.getFileName().toString(), rows, columns, vehicles, rides, bonus, steps);

	    List<Vehicle> vehiclesList = new ArrayList<>();
	    for (int c = 0; c < vehicles; c++) {
		Vehicle v = new Vehicle(0, 0, 0, new ArrayList<Ride>(), c);
		vehiclesList.add(v);
	    }
	    problem.rides = ridesList;
	    problem.vehicles = vehiclesList;

	    assert problem.vehicles.size() == problem.noOfVehicles;
	    assert problem.rides.size() == problem.noOfRides;

	    return problem;
	} catch (IOException e) {
	    throw new ParserException(e);
	}
    }
    //
    // private int[] parseHeader(String line) throws ParserException {
    // String[] strParts = line.split(" ");
    // if (strParts.length != 4) {
    // throw new ParserException("Header row must contain 4 ints");
    // }
    // // 0: rows, 1: columns, 2: min ingredients per slice, 3: max cells per
    // // slice
    // int[] parts;
    // try {
    // parts = Arrays.stream(strParts).mapToInt(Integer::parseInt).toArray();
    // } catch (NumberFormatException e) {
    // throw new ParserException("Header row must only contain ints", e);
    // }
    // return parts;
    // }

    private static Ride RideParser(String s) {
	// System.out.println("--- Parsing the ride: " + s);
	String[] array = s.split(" ");
	int startX = Integer.parseInt(array[0]);
	// System.out.println("startX: " + startX);
	int startY = Integer.parseInt(array[1]);
	// System.out.println("startY: " + startY);
	int endX = Integer.parseInt(array[2]);
	// System.out.println("endX: " + endX);
	int endY = Integer.parseInt(array[3]);
	// System.out.println("endY: " + endY);
	int earliestStart = Integer.parseInt(array[4]);
	// System.out.println("earliestStart: " + earliestStart);
	int latestFinish = Integer.parseInt(array[5]);
	// System.out.println("latestFinish: " + latestFinish);
	int id = -1;
	// System.out.println("--- Finished parsing the ride");
	return new Ride(startX, startY, endX, endY, earliestStart, latestFinish, id);
    }

}
