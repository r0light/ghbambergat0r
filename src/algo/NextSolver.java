package algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;

import io.OutputWriter;
import model.Problem;
import model.Ride;
import model.SortByEarliestStart;
import model.SortByNearestDistance;
import model.Vehicle;

public class NextSolver implements Solver {

    public Semaphore semaphore;

    public NextSolver(Semaphore semaphore) {
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

	    int remainingRides = rides.size();
	    while (!rides.isEmpty()) {
		for (int i = 0; i < vehicles.size(); i++) {

		    int minimumDistance = Integer.MAX_VALUE;
		    int maximumPointsPrimary = Integer.MIN_VALUE;
		    int maximumPointsSecondary = Integer.MIN_VALUE;
		    List<Integer> primaryCandidates = new ArrayList<Integer>();
		    List<Integer> secondaryCandidates = new ArrayList<Integer>();

		    for (int j = 0; j < rides.size(); j++) {
			int currentDiff = Math.abs(vehicles.get(i).diffTime(rides.get(j)));
			int currentPoints = rides.get(j).calculatePoints();
			if (currentDiff == 0 && vehicles.get(i).makesSense(rides.get(j))) {
			    if (currentPoints > maximumPointsPrimary) {
				maximumPointsPrimary = currentPoints;
				primaryCandidates.add(j);
			    }
			} else if (currentDiff < minimumDistance && vehicles.get(i).makesSense(rides.get(j))) {
			    if (currentPoints > maximumPointsSecondary) {
				minimumDistance = currentDiff;
				maximumPointsSecondary = currentPoints;
				secondaryCandidates.add(j);
			    }
			}
		    }

		    int bestCandidate = -1;

		    int primaryCandidateIndex = primaryCandidates.size() - 1;
		    while (primaryCandidateIndex > 0) {
			Vehicle testVehicle = new Vehicle(vehicles.get(i).time, vehicles.get(i).positionX,
				vehicles.get(i).positionY, new ArrayList<Ride>(), 0);
			int rideId = primaryCandidates.get(primaryCandidateIndex);
			testVehicle.addRide(rides.get(rideId));
			for (Ride nextRide : rides) {
			    if (testVehicle.makesSense(nextRide)) {
				bestCandidate = rideId;
				break; // break for
			    }
			}
			if (bestCandidate != -1) {
			    break; // break while
			} else {
			    primaryCandidateIndex--;
			}
		    }

		    if (bestCandidate == -1) {
			int secondaryCandidateIndex = secondaryCandidates.size() - 1;
			while (secondaryCandidateIndex > 0) {
			    Vehicle testVehicle = new Vehicle(vehicles.get(i).time, vehicles.get(i).positionX,
				    vehicles.get(i).positionY, new ArrayList<Ride>(), 0);
			    int rideId = secondaryCandidates.get(secondaryCandidateIndex);
			    testVehicle.addRide(rides.get(rideId));
			    for (Ride nextRide : rides) {
				if (testVehicle.makesSense(nextRide)) {
				    bestCandidate = rideId;
				    break; // break for
				}
			    }
			    if (bestCandidate != -1) {
				break; // break while
			    } else {
				secondaryCandidateIndex--;
			    }
			}
		    }

		    if (bestCandidate == -1) {
			if (!primaryCandidates.isEmpty()) {
			    int candidateId = primaryCandidates.get(primaryCandidates.size() - 1);
			    vehicles.get(i).addRide(rides.get(candidateId));
			    rides.remove(candidateId);
			} else {
			    if (!secondaryCandidates.isEmpty()) {
				int candidateId = secondaryCandidates.get(secondaryCandidates.size() - 1);
				vehicles.get(i).addRide(rides.get(candidateId));
				rides.remove(candidateId);
			    }
			}
		    } else {
			vehicles.get(i).addRide(rides.get(bestCandidate));
			rides.remove(bestCandidate);
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
