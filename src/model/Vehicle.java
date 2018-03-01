package model;

import java.util.List;

public class Vehicle {
    public int time;
    public int positionX;
    public int positionY;
    public List<Ride> rides;
    public int id;

    public Vehicle(int time, int positionX, int positionY, List<Ride> rides, int id) {
	this.time = time;
	this.positionX = positionX;
	this.positionY = positionY;
	this.rides = rides;
	this.id = id;
    }

    public void addRide(Ride newRide) {
	int timeAtStart = time + Math.abs(newRide.startX - positionX) + Math.abs(newRide.startY - positionY);
	if (timeAtStart < newRide.earliestStart) {
	    timeAtStart = newRide.earliestStart;
	}

	time = timeAtStart + Math.abs(newRide.endX - newRide.startX) + Math.abs(newRide.endY - newRide.startY);

	positionX = newRide.endX;
	positionY = newRide.endY;

	rides.add(newRide);
    }

    public boolean makesSense(Ride newRide) {
	int timeAtStart = time + Math.abs(newRide.startX - positionX) + Math.abs(newRide.startY - positionY);
	if (timeAtStart < newRide.earliestStart) {
	    timeAtStart = newRide.earliestStart;
	}
	int expectedEndTime = timeAtStart + Math.abs(newRide.endX - newRide.startX)
		+ Math.abs(newRide.endY - newRide.startY);
	if (expectedEndTime <= newRide.latestFinish) {
	    return true;
	}
	return false;
    }
}
