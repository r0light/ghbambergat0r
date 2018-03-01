package simulation;

import java.util.stream.Collectors;

import model.Problem;
import model.Ride;
import model.Vehicle;
import util.DistanceCalculator;

public class Simulation {

    public Problem problem;

    public int doSimulate() {
	System.out.println(" -- SIMULATION --");
	int score = 0;

	// reset position to start
	for (Vehicle v : problem.vehicles) {
	    v.positionX = 0;
	    v.positionY = 0;
	}

	// setup the stops for each vehicle
	for (Vehicle v : problem.vehicles) {
	    v.stopsX.add(0);
	    v.stopsY.add(0);
	    for (Ride r : v.rides) {
		v.nextStop(r.startX, r.startY);
		v.nextStop(r.endX, r.endY);
	    }
	}

	for (Vehicle v : problem.vehicles) {
	    System.out.println("VEHICLE " + v.id);
	    String x = v.stopsX.stream().map(foo -> foo + "").collect(Collectors.joining("-"));
	    String y = v.stopsY.stream().map(foo1 -> foo1 + "").collect(Collectors.joining("-"));
	    System.out.println(x);
	    System.out.println(y);
	}

	System.out.println("Starting step-wise simulation ..");
	// step-wise simulation
	for (int timestep = 0; timestep < problem.steps; timestep++) {
	    System.out.println("STEP " + timestep);

	    for (Vehicle v : problem.vehicles) {

		if (v.stopsX.isEmpty() || v.stopsY.isEmpty()) {
		    // this vehicle has reached its final target
		    continue;
		}

		// check if we have reached the start of our new ride
		Ride thisRide = v.rides.get(0);
		if (thisRide.startX == v.stopsX.get(0) && thisRide.startY == v.stopsY.get(0)) {
		    // we have reached the start of the next ride
		    // check if we are in time
		    if (timestep <= thisRide.earliestStart) {
			thisRide.startedInTime = true;
		    } else {
			thisRide.startedInTime = false;
		    }
		    v.tick();
		    continue;
		}

		if (thisRide.endX == v.stopsX.get(0) && thisRide.endY == v.stopsY.get(0)) {
		    score += DistanceCalculator.distance(thisRide);
		    if (thisRide.startedInTime) {
			score += problem.bonus;
		    }
		    System.out.println("Vehicle " + v.id + " has reached the end for ride " + thisRide.id);
		    System.out.println("new score: " + score);
		    v.tick();
		    continue;
		}

		v.tick();
	    }
	}

	System.out.println("Final score: " + score);

	return score;
    }

}
