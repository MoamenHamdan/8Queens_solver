package eightQueens;

import java.util.ArrayList;

public class Node {
	private final int[][] state;
	private Node parent;
	private int cost;
	private Action action;

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}
	public Node(int[][] state, int cost) {
		this.state = state;
		this.cost = cost;
	}

	public Node(int[][] state, Node parent, int cost) {
		super();
		this.state = state;
		this.parent = parent;
		this.cost = cost;
	}

	public Node(int[][] state, Node parent, Action action, int Cost) {

		this.state = state;
		this.parent = parent;
		this.action = action;
		this.cost = Cost;
	}

	

	public Node() {
		this.state = null;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int[][] getState() {
		return state;
	}

	public ArrayList<Node> getPathFromRoot() {

		ArrayList<Node> path = new ArrayList<Node>();
		Node current = this;
		while (current.parent != null) {
			path.add(0, current);
			current = current.getParent();
		}

		path.add(0, current);
		return path;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("+---+---+---+---+---+---+---+---+\n");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                sb.append("| ");
                // Print 'Q' for Queen and '.' for empty space
                sb.append(state[i][j] == 1 ? "Q " : ". ");
            }
            sb.append("|\n");
            sb.append("+---+---+---+---+---+---+---+---+\n");
        }
        sb.append("Cost: ").append(cost).append("\n");
        return sb.toString();
    }
}
