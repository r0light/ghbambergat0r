package model;

import java.util.Comparator;

public class SortByNearestDistance implements Comparator<Ride> {

    @Override
    public int compare(Ride rideOne, Ride rideTwo) {
	return -1 * ((rideOne.startX + rideOne.startY) - (rideTwo.startX + rideTwo.startY));
    }

}
