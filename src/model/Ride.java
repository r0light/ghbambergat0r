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

    public int getStartX() {
	return startX;
    }

    public void setStartX(int startX) {
	this.startX = startX;
    }

    public int getStartY() {
	return startY;
    }

    public void setStartY(int startY) {
	this.startY = startY;
    }

    public int getEndX() {
	return endX;
    }

    public void setEndX(int endX) {
	this.endX = endX;
    }

    public int getEndY() {
	return endY;
    }

    public void setEndY(int endY) {
	this.endY = endY;
    }

    public int getEarliestStart() {
	return earliestStart;
    }

    public void setEarliestStart(int earliestStart) {
	this.earliestStart = earliestStart;
    }

    public int getLatestFinish() {
	return latestFinish;
    }

    public void setLatestFinish(int latestFinish) {
	this.latestFinish = latestFinish;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

}
