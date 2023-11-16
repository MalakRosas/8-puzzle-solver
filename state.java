/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8.puzzle;
import java.util.ArrayList;
public class state {
     String stateString;
     Integer[][] puzzleBoard;//takes the puzzle as a matrix
     Integer depth;
     ArrayList<String> neighbors = new ArrayList<>(4);
     state previousState;
        public state(String inputState, state previousState) {
        this.stateString = inputState;
        int x=0;
        puzzleBoard = new Integer[3][3];
        for (int i = 0 ; i < 3 ; i++) {
        for (int j = 0 ; j < 3 ; j++) {
        puzzleBoard[i][j] = Integer.parseInt(String.valueOf(inputState.charAt(x)));
              x++;}}
        //Save previous state 
        this.previousState = previousState;
        if (previousState == null)
            this.depth = 0;
        else
            this.depth = previousState.getDepth() + 1;
    }
    // Generate the string that represents the goal state
    public String GoalState() {
        StringBuilder s = new StringBuilder();
        for (int i = 0 ; i < 9 ; i++) {
            s.append(i);
        }
        return s.toString();
    }
    //Generate possible neighbors for current state 
    public void findNeighbors() {
    int blankRow=0, blankColumn=0;    //
    boolean found;          // to indicate finding the empty tile
    found = false;
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++)
            if (puzzleBoard[i][j] == 0) {
                blankRow=i;
                blankColumn=j;
                found = true;
                break;
            }
        if (found)
            break;
    }
        neighbors.add(0, getNeighbour("up", blankRow, blankColumn));
        neighbors.add(1, getNeighbour("down", blankRow, blankColumn));
        neighbors.add(2, getNeighbour("left", blankRow, blankColumn));
        neighbors.add(3, getNeighbour("right", blankRow, blankColumn));
}
    String getNeighbour(String position, int row, int column) {//row and column of blank tile
    String neighbor = null; 
    // Check the specified direction and corresponding conditions
    if ("up".equals(position) && (row - 1) >= 0) {
        swap(row, column, row - 1, column);
        neighbor = this.toString(); //this new state after swapping
        swap(row, column, row - 1, column);
    } else if ("down".equals(position) && (row + 1) < 3) {
        swap(row, column, row + 1, column);//swap again to return the puzzle to the same state to check its other neighbours
        neighbor = this.toString();
        swap(row, column, row + 1, column);
    } else if ("left".equals(position) && (column - 1) >= 0) {
        swap(row, column, row, column - 1);
        neighbor = this.toString();
        swap(row, column, row, column - 1);
    } else if ("right".equals(position) && (column + 1) < 3) {
        swap(row, column, row, column + 1);
        neighbor = this.toString();
        swap(row, column, row, column + 1);
    }

    return neighbor;
}
void swap(int blankRow, int blankColumn, int x2, int y2) {
    int temp = puzzleBoard[blankRow][blankColumn];
    puzzleBoard[blankRow][blankColumn] = puzzleBoard[x2][y2];
    puzzleBoard[x2][y2] = temp;
}
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++) {
                s.append(puzzleBoard[i][j].toString());
            }
        }
        return s.toString();
    } 
    public String getStateString() {
        return this.stateString;
    }
    public Integer[][] getPuzzleBoard() {
        return puzzleBoard;
    }
    public Integer getDepth() {
        return depth;
    }
    public ArrayList<String> getNeighbors() {
        return neighbors;
    }
    state getPreviousState() {
        return previousState;
    }
}