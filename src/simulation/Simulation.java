package simulation;

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

	// step-wise simulation
	for (int timestep = 0; timestep < problem.steps; timestep++) {

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
		    if (timestep <= v.currentRide.earliestStart) {
			v.currentRide.startedInTime = true;
		    } else {
			v.currentRide.startedInTime = false;
		    }
		    v.tick();
		    continue;
		}

		if (thisRide.endX == v.stopsX.get(0) && thisRide.endY == v.stopsY.get(0)) {
		    score += DistanceCalculator.distance(thisRide);
		    if (thisRide.startedInTime) {
			score += problem.bonus;
		    }
		    System.out.println("Vehicle " + v.id + " has reached the end for ride " + v.currentRide.id);
		    System.out.println("new score: " + score);
		}
	    }
	}

	return score;
    }

}
