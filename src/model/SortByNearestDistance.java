package model;

import java.util.Comparator;

public class SortByNearestDistance implements Comparator<Ride> {

    @Override
    public int compare(Ride rideOne, Ride rideTwo) {
	if (rideOne.earliestStart == rideTwo.earliestStart) {
	    return (rideOne.startX + rideOne.startY) - (rideTwo.startX + rideTwo.startY);
	}
	return 1;
    }

}
