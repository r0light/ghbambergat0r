package io;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import model.Problem;
import model.Ride;

public class Parser {

    private final Path path;

    public Parser(Path path) {
        this.path = path;
    }

    public Problem parse() throws ParserException {
        List<String> lines;
        try {
            lines = Files.readAllLines(path, StandardCharsets.US_ASCII);

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

            List<Ride> ridesList = lines.subList(1, lines.size()).stream().parallel().map(Parser::RideParser).collect
                    (Collectors.toList());
            int i = 0;
            for (Ride ride : ridesList) {
                ride.id = i;
                i++;
            }
            Problem problem = new Problem(path.getFileName().toString(), rows, columns, vehicles, rides, bonus, steps);

            // List<Vehi>

            problem.rides = ridesList;

            return problem;
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

    private static Ride RideParser(String s) {
        String[] array = s.split(" ");
        int startX = Integer.parseInt(array[0]);
        System.out.println("startX: " + startX);
        int startY = Integer.parseInt(array[1]);
        System.out.println("startY: " + startY);

        int endX = Integer.parseInt(array[2]);
        int endY = Integer.parseInt(array[3]);
        int earliestStart = Integer.parseInt(array[4]);
        int latestFinish = Integer.parseInt(array[5]);
        int id = -1;
        return new Ride(startX, startY, endX, endY, earliestStart, latestFinish, id);
    }

}
