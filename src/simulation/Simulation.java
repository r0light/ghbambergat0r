package simulation;

import model.Problem;
import model.Ride;
import model.Vehicle;
import util.DistanceCalculator;

public class Simulation {

    public Problem problem;

    public int doSimulate() {
	int score = 0;

	// reset position to start
	for (Vehicle v : problem.vehicles) {
	    v.positionX = 0;
	    v.positionY = 0;
	}

	// setup the stops for each vehicle
	for(Vehicle v : problem.vehicles) {
	    v.stopsX.add(0);
	    v.stopsY.add(0);
	    for(Ride r: v.rides) {
		int newX = r.startX;
		int newY = r.startY;

		if(newX == v.getLastStopX() && newY == v.getLastStopY()) {
continue;
		} else {

//		    while(1 < Math.abs(v.getLastStopX() - newX)) {
//
//		    }



	    }
	}

	    for (int timestep = 0; timestep < problem.steps; timestep++) {

	        for (Vehicle v : problem.vehicles) {
	    	if (v.currentRide == null) {
	    	    // this vehicle has no ride at the moment, so we need a new ride
	    	    if (!v.rides.isEmpty()) {
	    		// this vehicle still has rides to do
	    		v.currentRide = v.rides.get(0);
	    		v.rides = v.rides.subList(1, v.rides.size());
	    		if (timestep <= v.currentRide.earliestStart) {
	    		    v.currentRide.startedInTime = true;
	    		} else {
	    		    v.currentRide.startedInTime = false;
	    		}
	    	    } else {
	    		// this vehicle has no further rides
	    		continue;
	    	    }
	    	}

	    	// check if the vehicle has reached the goal of it's ride
	    	Ride currentRide = v.currentRide;
	    	if (v.positionX == currentRide.endX && v.positionY == currentRide.endY) {
	    	    // wuhu, we have reached the end of the ride .. lets increment the score
	    	    score += DistanceCalculator.distance(currentRide);
	    	    if (currentRide.startedInTime) {
	    		score += problem.bonus;
	    	    }
	    	    System.out.println("Vehicle " + v.id + " has reached the end for ride " + v.currentRide.id);
	    	    System.out.println("new score: " + score);
	    	} else {
	    	    // we have not reached the end .. continue driving
	    	    if(v.positionX == v.currentRide.endX)
	    	}

	        }

	    }
	}

    return score;
}

}
