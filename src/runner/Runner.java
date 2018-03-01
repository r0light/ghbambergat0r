package runner;

import java.util.concurrent.Semaphore;

import algo.SwitchSolver;
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
	// SimpleSolver solver = new SimpleSolver(semaphore);
	SwitchSolver solver = new SwitchSolver(semaphore);
	solver.setProblem(this.problem);
	solver.compute();
    }

}
