package algo;

import model.Problem;

public class SimpleSolver implements Solver {

    public Problem problem;

    @Override
    public void compute() {
	if (problem != null) {

	}

    }

    @Override
    public void setProblem(Problem problem) {
	this.problem = problem;
    }
}
