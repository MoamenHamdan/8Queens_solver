package eightQueens;

import java.util.*;

public class UcsSolver implements SolverStrategy {

	private ArrayList<Node> pqueue;
	private ArrayList<int[][]> expandedState;
	int maxPQueueSize;
	int pqueueSize;

	public UcsSolver() {
		pqueue = new ArrayList<>();
		expandedState = new ArrayList<>();
		maxPQueueSize = 1;
	}

	public void solve(EightQueensBoard p) {
		long startTime = System.currentTimeMillis();
		boolean solutionFound = false;
		int searchCount = 0;
		int g = 0;
		int[][] initialState = p.getInitialState();
		Node initialNode = new Node(initialState, g);
		pushNode(initialNode, 0);

		while (!(pqueue.isEmpty() || solutionFound)) {
			Node nodeToExplore = popNextNode();
			searchCount++;
			int[][] stateToExplore = nodeToExplore.getState();
			expandedState.add(stateToExplore);
			if (p.isGoalState(stateToExplore)) {
				ArrayList<Node> solution = nodeToExplore.getPathFromRoot();
				System.out.println(solution);
				break;
			} else {
				SuccessorFunction succFunc = p.getSuccessorFunction();
				ArrayList<Action> actions = succFunc.getPossibleApplicableActions(stateToExplore);
				for (Action action : actions) {
					int[][] successorState = action.apply(stateToExplore);
					boolean found = false;
					for (int[][] s : expandedState) {
						if (p.equalState(successorState, s)) {
							found = true;
							break;
						}
					}
					if (!found) {
						Node successorNode = new Node(successorState, nodeToExplore, action,
								nodeToExplore.getCost() + action.getCostPerAction());
						int pos = 0;
						if (!pqueue.isEmpty()) {
							Node current = pqueue.get(pos);
							while (current.getCost() <= successorNode.getCost() && pos < pqueue.size() - 1)
								current = pqueue.get(++pos);
							if (current.getCost() <= successorNode.getCost())
								pushNode(successorNode, pos + 1);
							else
								pushNode(successorNode, pos);
						} else
							pushNode(successorNode, pos);
					}
				}
			}
			if (checkQueueSize() > maxPQueueSize)
				maxPQueueSize = checkQueueSize();
		}
		System.out.println("The number of nodes examined in UCS is: " + searchCount);
		System.out.println("The maximum queue size: " + maxPQueueSize);
		long endTime = System.currentTimeMillis();
		long elapsedTime = endTime - startTime;
		System.out.println("Execution time in milliseconds: " + elapsedTime);
	}

	private void pushNode(Node node, int pos) {
		pqueue.add(pos, node);
	}

	private Node popNextNode() {
		return pqueue.remove(0);
	}

	private int checkQueueSize() {
		return pqueue.size();
	}

}
