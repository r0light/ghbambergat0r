package model;

import java.util.ArrayList;
import java.util.List;

public class Vehicle {

    public Ride currentRide;
    public List<Integer> stopsX = new ArrayList<>();

    public int getLastStopX() {
	return stopsX.get(stopsX.size() - 1);
    }

    public List<Integer> stopsY = new ArrayList<>();

    public int getLastStopY() {
	return stopsY.get(stopsY.size() - 1);
    }

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
	int timeAtStart = calculateTimeAtStart(newRide);
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

    public int diffTime(Ride newRide) {
	return calculateTimeAtStart(newRide) - newRide.earliestStart;
    }

    private int calculateTimeAtStart(Ride newRide) {
	int timeAtStart = time + Math.abs(newRide.startX - positionX) + Math.abs(newRide.startY - positionY);
	return timeAtStart;
    }

    public void nextStop(int newX, int newY) {
	if (newX == getLastStopX() && newY == getLastStopY()) {
	    // no need to add a new stop
	} else {
	    // add intermediate stops
	    while (newX != getLastStopX()) {
		int nextX = getLastStopX();
		int nextY = getLastStopY();

		if (newX < getLastStopX()) {
		    nextX -= 1;
		} else {
		    nextX += 1;
		}

		stopsX.add(nextX);
		stopsY.add(nextY);
	    }
	}
    }

    public void tick() {
	positionX = stopsX.get(0);
	positionY = stopsY.get(0);
	stopsX = stopsX.subList(1, stopsX.size() - 1);
	stopsY = stopsY.subList(1, stopsY.size() - 1);
    }
}
