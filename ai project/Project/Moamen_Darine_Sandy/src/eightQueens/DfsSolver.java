package eightQueens;

import java.util.*;

public class DfsSolver implements SolverStrategy{

	private ArrayList<Node> stack;
	private ArrayList<int[][] > visitedState;
	int maxStackSize;
	int stackSize;

	public DfsSolver() {
        stack = new ArrayList<>();
        visitedState = new ArrayList<>();
        maxStackSize = 1;
    }

	public void solve(EightQueensBoard p) {
		long startTime = System.currentTimeMillis(); 
		int searchCount = 0;
		int[][] initialState = p.getInitialState();
		Node initialNode = new Node(initialState, 0);
		pushNode(initialNode, 0);
		visitedState.add(initialState);
		boolean solutionFound = false;
		while (!(stack.isEmpty() || solutionFound)) {
			Node nodeToExplore = popNextNode();
			searchCount++;
			int[][] stateToExplore = nodeToExplore.getState();
			if (p.isGoalState(stateToExplore)) {
				solutionFound = true;
				ArrayList<Node> solution = nodeToExplore.getPathFromRoot();
				System.out.println(solution);
				break;
			} else {
				SuccessorFunction succFunc = p.getSuccessorFunction();
				ArrayList<Action> actions = succFunc.getPossibleApplicableActions(stateToExplore);
				int i = 0;
				for (Action action : actions) {
					int[][]  successorState = action.apply(stateToExplore);
					boolean found = false;
					for (int[][]  s : visitedState)
						if (p.equalState(successorState, s)) {
							found = true;
							break;
						}
					if (!found) {
						Node successorNode = new Node(successorState, nodeToExplore, action,
								nodeToExplore.getCost() + action.getCostPerAction());
						pushNode(successorNode, i++);
						visitedState.add(successorState);
					}
				}
			}
			if (checkQueueSize() > maxStackSize)
				maxStackSize = checkQueueSize();
		}
		System.out.println("The number of nodes examined in DFS is: " + searchCount);
		System.out.println("The maximum queue size: " + maxStackSize);
		long endTime = System.currentTimeMillis();
		long elapsedTime = endTime - startTime;
		System.out.println("Execution time in milliseconds: " + elapsedTime);
	}

	private void pushNode(Node node, int pos) {
		stack.add(pos, node);
	}

	private Node popNextNode() {
		return stack.remove(0);
	}

	private int checkQueueSize() {
		return stack.size();
	}
}

/**
 *
 * @author user
 */
