package algo;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;

import io.OutputWriter;
import model.Problem;
import model.Ride;
import model.SortByEarliestStart;
import model.SortByNearestDistance;
import model.Vehicle;

public class AdvancedSolver implements Solver {

    public Semaphore semaphore;

    public AdvancedSolver(Semaphore semaphore) {
	super();
	this.semaphore = semaphore;
    }

    public Problem problem;

    @Override
    public void compute() {
	if (problem != null) {

	    System.out.println("start " + problem.name);
	    System.out.println(problem.noOfRides + "should be " + problem.rides.size());
	    System.out.println(problem.noOfVehicles + "should be " + problem.vehicles.size());

	    sort();
	    List<Ride> rides = problem.rides;
	    List<Vehicle> vehicles = problem.vehicles;

	    int maximumOfRides = 10;
	    int remainingRides = rides.size();
	    while (!rides.isEmpty()) {
		for (int i = 0; i < vehicles.size(); i++) {
		    int counter = 0;
		    int minimum = Integer.MAX_VALUE;
		    for (int j = 0; j < rides.size(); j++) {
			int currentDiff = Math.abs(vehicles.get(i).diffTime(rides.get(j)));
			if (currentDiff < minimum) {
			    if (vehicles.get(i).makesSense(rides.get(j))) {
				minimum = currentDiff;
				vehicles.get(i).addRide(rides.get(j));
				rides.remove(j);
				counter++;

				if (counter >= maximumOfRides) {
				    break;
				}
			    }
			}
		    }

		}

		if (rides.size() == remainingRides) {
		    break;
		} else {
		    remainingRides = rides.size();
		}

	    }

	    System.out.println(rides.size());

	    OutputWriter outputWriter = new OutputWriter(problem, semaphore);
	    outputWriter.write("output/" + problem.name);
	}

    }

    @Override
    public void setProblem(Problem problem) {
	this.problem = problem;
    }

    private void sort() {
	Collections.sort(problem.rides, new SortByEarliestStart());
	Collections.sort(problem.rides, new SortByNearestDistance());
    }
}
