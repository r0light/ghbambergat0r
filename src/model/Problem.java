package model;

public class Problem {

    public String name;
    public int rows;
    public int columns;

    public int noOfVehicles;
    public List<Vehicles> vehicles;

    public int noOfRides;
    public List<Ride> rides;

    public int bonus;
    public int steps;

    public Problem(String name, int rows, int columns, int noOfVehicles, int noOfRides, int bonus, int steps) {
	super();
	this.name = name;
	this.rows = rows;
	this.columns = columns;
	this.noOfVehicles = noOfVehicles;
	this.noOfRides = noOfRides;
	this.bonus = bonus;
	this.steps = steps;
    }

}
