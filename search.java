/*
  * To change this template file, choose Tools | Templates
 * and open* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8.puzzle;
import java.util.ArrayList;
import java.util.Arrays;
public class search {
     int expanded, pathCost, maxDepth, searchDepth;
     boolean found;
     ArrayList<String> pathToGoal;
     String alg;
    public search() {
        pathToGoal = new ArrayList<>();
        expanded = 0;
        pathCost = 0;
        maxDepth = 0;
        searchDepth = 0;
        found = false;
    }
        public void setSearchAlgorithm(String searchAlgorithm) {
        this.alg = searchAlgorithm;
    }
          public String getSearchAlgorithm() {
        return alg;
    }
    public ArrayList<String> getPathToGoal() {
        return pathToGoal;
    }
    public int getNodesExpanded() {
        return expanded;
    }
    public int getPathCost() {
        return pathCost;
    }
    public int getSearchDepth() {
        return searchDepth;
    }
    public boolean isFound() {//to determine wether the goal is found or not
        return found;
    }
    public void setFound(boolean found) {
        this.found = found;
    }
public void findPathToGoal(state puzzleState) {
    int index;
    if (!this.found) { //if the goal is not found
        this.pathToGoal.add("Goal not found!");
        return;
    }
    ArrayList<state> statesList = new ArrayList<>();//Store all puzzle states from initial state to goal state
    // Iterate through the states from the last state to the first state and add them to the list
    while (puzzleState != null) {
        statesList.add(puzzleState);
        puzzleState = puzzleState.getPreviousState();
    }
    System.out.println(this.getSearchAlgorithm());
    System.out.println("Path to goal :");
    for (int i = statesList.size() - 1; i >= 0; i--) {//print in reverse order because we want the path from initial state to goal state
        state currentState = statesList.get(i);
        // Print the current state
        System.out.println("["+ currentState.getStateString()+']');
        // Check for null before accessing previous state
        if (i >=0 && currentState.getPreviousState() != null) {
            //index of position in arrayList to find how the tile move to become on this state from the ptevious state
            index = currentState.getPreviousState().getNeighbors().indexOf(currentState.getStateString());
        if (index == 0) 
        this.pathToGoal.add("Up");
        else if (index == 1) 
        this.pathToGoal.add("Down");
        else if (index == 2) 
        this.pathToGoal.add("Left");
        else if (index == 3) 
        this.pathToGoal.add("Right");
        }
    }
}
    public void updateExpandedNodes() {
        this.expanded++;
    }
    public void updateCost() {
        if (!this.found) {
            this.pathCost = this.expanded;
            return;
        }
        this.pathCost = pathToGoal.size();
    }
    public void updateMaxDepth(Integer depth) {
       if (this.maxDepth < depth)
            this.maxDepth = depth;
    }
    public void updateDepth() {
        if (!this.found) {
            this.searchDepth = this.maxDepth;
            return;
        }
        this.searchDepth = this.pathToGoal.size();
        if (this.maxDepth < this.searchDepth)
            this.maxDepth = this.searchDepth;
    }
    @Override
    public String toString() {
        return  "Directions: " + this.getPathToGoal().toString()+ "\n" +
                "Cost of path: " + this.getPathCost() + "\n" +
                "Expanded nodes: " + this.getNodesExpanded() + "\n" +
                "Search depth: " + this.getSearchDepth() + "\n" ;
    }
}