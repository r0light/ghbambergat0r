package runner;

import java.util.concurrent.Semaphore;

import algo.SimpleSolver;
import model.Problem;

public class Runner implements Runnable {

    private Problem problem;
    Semaphore semaphore;

    public Runner(Problem problem, Semaphore semaphore) {
	this.problem = problem;
	this.semaphore = semaphore;
    }

    @Override
    public void run() {
	SimpleSolver solver = new SimpleSolver(semaphore);
	solver.setProblem(this.problem);
	solver.compute();
    }

}
