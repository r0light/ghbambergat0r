package util;

import model.Ride;

public class DistanceCalculator {

    public static int distance(int startX, int startY, int endX, int endY) {
	return Math.abs(startX - startY) + Math.abs(endX - endY);
    }

    public static int distance(Ride r) {
	return distance(r.startX, r.startY, r.endX, r.endY);
    }

}
