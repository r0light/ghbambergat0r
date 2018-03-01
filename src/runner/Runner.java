package runner;

import algo.SimpleSolver;
import model.Problem;

public class Runner implements Runnable {

    private Problem problem;

    public Runner(Problem problem) {
	this.problem = problem;
    }

    @Override
    public void run() {
	SimpleSolver solver = new SimpleSolver();
	solver.setProblem(this.problem);
	solver.compute();
    }

}
