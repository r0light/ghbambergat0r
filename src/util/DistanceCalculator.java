package util;

public class DistanceCalculator {

    public static int distance(int startX, int startY, int endX, int endY) {
	return Math.abs(startX - startY) + Math.abs(endX - endY);
    }

}
