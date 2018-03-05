package model;

public class Ride {

    public int startX;
    public int startY;
    public int endX;
    public int endY;
    public int earliestStart;
    public int latestFinish;
    public int id;
    public boolean startedInTime;

    public Ride(int startX, int startY, int endX, int endY, int earliestStart, int latestFinish, int id) {
	this.startX = startX;
	this.startY = startY;
	this.endX = endX;
	this.endY = endY;
	this.earliestStart = earliestStart;
	this.latestFinish = latestFinish;
	this.id = id;
    }

    public int calculatePoints() {
	return Math.abs(this.endX - this.startX) + Math.abs(this.endY - this.startY);
    }

}
