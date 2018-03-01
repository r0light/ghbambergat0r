package model;

import java.util.Comparator;

public class SortByEarliestStart implements Comparator<Ride> {

    @Override
    public int compare(Ride rideOne, Ride rideTwo) {
	return rideOne.earliestStart - rideTwo.earliestStart;
    }

}
