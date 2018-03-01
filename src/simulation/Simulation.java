package simulation;

import model.Problem;
import model.Ride;
import model.Vehicle;

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
	for (Vehicle v : problem.vehicles) {
	    v.stopsX.add(0);
	    v.stopsY.add(0);
	    for (Ride r : v.rides) {
		v.nextStop(r.startX, r.startY);
		v.nextStop(r.endX, r.endY);
	    }
	}

	Vehicle v = problem.vehicles.get(0);
	for (int i = 0; i < v.stopsX.size(); i++) {
	    System.out.println("(" + v.stopsX.get(i) + ", " + v.stopsY.get(i) + ")");
	}

	return score;
    }

}
