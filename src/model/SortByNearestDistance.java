package model;

import java.util.Comparator;

public class SortByNearestDistance implements Comparator<Ride> {

    @Override
    public int compare(Ride rideOne, Ride rideTwo) {
	if (rideOne.earliestStart == rideTwo.earliestStart) {
	    return distanceToNull(rideOne) - distanceToNull(rideTwo);
	}
	return 1;
    }

    private int distanceToNull(Ride ride) {
	return ride.startX + ride.startY;
    }

}
