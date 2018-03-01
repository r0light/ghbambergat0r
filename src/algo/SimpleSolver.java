package algo;

import java.util.List;

import io.OutputWriter;
import model.Problem;
import model.Ride;
import model.Vehicle;

public class SimpleSolver implements Solver {

    public Problem problem;

    @Override
    public void compute() {
	if (problem != null) {

	    List<Ride> rides = problem.rides;

	    for (int i = 0; i < rides.size(); i++) {
		int minimum = Integer.MAX_VALUE;
		Vehicle candidate = null;
		for (int j = 0; j < problem.vehicles.size(); j++) {
		    int currentDiff = Math.abs(problem.vehicles.get(j).diffTime(rides.get(i)));
		    if (currentDiff < minimum) {
			if (problem.vehicles.get(j).makesSense(rides.get(j))) {
			    minimum = currentDiff;
			    candidate = problem.vehicles.get(j);
			}
		    }

		}

		if (candidate != null) {
		    candidate.addRide(rides.get(i));
		}
	    }

	    OutputWriter outputWriter = new OutputWriter(problem);
	    outputWriter.write("output/" + problem.name);
	}

    }

    @Override
    public void setProblem(Problem problem) {
	this.problem = problem;
    }
}
