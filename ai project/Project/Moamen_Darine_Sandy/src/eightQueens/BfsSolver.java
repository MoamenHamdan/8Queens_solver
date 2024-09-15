package eightQueens;

import java.util.*;

public class BfsSolver implements SolverStrategy {

    private ArrayList<Node> queue;
	private ArrayList<int[][]> visitedState;
    int maxQueueSize;
    int queueSize;

    public BfsSolver() {
        queue = new ArrayList<>();
		visitedState = new ArrayList<>();
        maxQueueSize = 1;
    }

    @Override
    public void solve(EightQueensBoard p) {
        int searchCount = 0;
        int[][] initialState = p.getInitialState();
        Node initialNode = new Node(initialState, 0);
        pushNode(initialNode);
		visitedState.add(initialState);
        boolean solutionFound = false;
        while (!(queue.isEmpty() || solutionFound)) {
            Node nodeToExplore = popNextNode();
            searchCount++;
            int[][] stateToExplore = nodeToExplore.getState();
			if (p.isGoalState(stateToExplore)) {
                solutionFound = true;
                // TODO do something else: display the goal path...
                ArrayList<Node> solution = nodeToExplore.getPathFromRoot();
                System.out.println(solution);
                break;
            } else {
                // else put successors, then loop
                SuccessorFunction succFunc = p.getSuccessorFunction();
                ArrayList<Action> actions = succFunc.getPossibleApplicableActions(stateToExplore);
                for (Action action : actions) {
                    int[][] successorState = action.apply(stateToExplore);
					boolean found = false;
					for(int[][] s : visitedState)
						if(p.equalState(successorState,s)) {
							found = true;
							break;
						}
                    if(!found) {
						Node successorNode = new Node(successorState, nodeToExplore, action, nodeToExplore.getCost() + action.getCostPerAction());
						pushNode(successorNode);
						visitedState.add(successorState);
					}
                }
            }
			if(checkQueueSize() > maxQueueSize)
                        maxQueueSize = checkQueueSize() ;
        }
		System.out.println("The number of nodes examined in BFS is: "+ searchCount);
		System.out.println("The maximum queue size: "+ maxQueueSize  );
    }

    private void pushNode(Node node) {
        queue.add(queue.size(),node);
    }

    private Node popNextNode() {
        return queue.remove(0);
    }
    
    private int checkQueueSize() {
		return queue.size();
    }
}

