package eightQueens;

import java.util.*;


public class AStarSolver implements SolverStrategy {

    private ArrayList<Node> pqueue;
    int maxPQueueSize;
    int pqueueSize;

    public AStarSolver() {
        pqueue = new ArrayList<>();
        maxPQueueSize = 1;
    }

    @Override
    public void solve(EightQueensBoard p) {
    	long startTime = System.currentTimeMillis(); 
        boolean solutionFound = false;
        int g = 0;
        int[][] initialState = p.getInitialState();
        Node initialNode = new Node(initialState, g);
        pushNode(initialNode);

        while (!(pqueue.isEmpty() || solutionFound)) // while the queue is not empty
        {
            Node nodeToExplore = popNextNode();
            int[][] stateToExplore = nodeToExplore.getState();
            if (p.isGoalState(stateToExplore)) {
                ArrayList<Node> solution = nodeToExplore.getPathFromRoot();
                System.out.println(solution);
                break;
            } else {
                SuccessorFunction succFunc = p.getSuccessorFunction();
                ArrayList<Action> actions = succFunc
                        .getPossibleApplicableActions(stateToExplore);
                Collections.shuffle(actions);

                ArrayList<Node> nodeSuccessors = new ArrayList<Node>();

                for (Action action : actions) {

                    int[][] successorState = action.apply(stateToExplore);
                    g = ((EightQueensBoard) p).calcHammingDistance(successorState);
                    Node successorNode = new Node(successorState, nodeToExplore, action, g);
                    nodeSuccessors.add(successorNode);
                    pushNode(successorNode);
                }
                
            }
 
            if(checkQueueSize() > maxPQueueSize)
                	maxPQueueSize = checkQueueSize() ;
        }
       
        System.out.println("AStar: The maximum pqueue size: " + maxPQueueSize);
        long endTime = System.currentTimeMillis();
		long elapsedTime = endTime - startTime;
		System.out.println("Execution time in milliseconds: " + elapsedTime);

    }

    private void pushNode(Node node) {
    	int i=0;
    	if(!pqueue.isEmpty()){
	    	Node current = pqueue.get(i);
	    	while(current.getCost()<node.getCost()&&i<pqueue.size()-1)
	    		current = pqueue.get(++i);
	    	pqueue.add(++i, node);
    	}
    	else
    	pqueue.add(i, node);
        
    }

    private Node popNextNode() {
        return pqueue.remove(pqueue.size() - 1);
    }

    private int checkQueueSize() {
        return pqueue.size();
    }

   
}
