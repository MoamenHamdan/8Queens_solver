package eightQueens;

import java.util.*;
public interface SuccessorFunction {
    ArrayList<Action> getPossibleApplicableActions(int[][] state);   
}
