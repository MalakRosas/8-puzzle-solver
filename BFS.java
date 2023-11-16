/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8.puzzle;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
 class HashQueue<Type> extends LinkedList<Type> {//Define a queue with unique values 
      Set<String> set = new HashSet<>();
}
public class BFS {
    public static search bfs(state initialState, state goalState) {
        HashQueue<frontier> queue = new HashQueue<>();//Initialize a queue which implements class HashQueue to store frontiers
        Set<String> explored = new HashSet<>();// Set of explored nodes to avoid duplication 
        state currentState = initialState;
        search result = new search(); //To keep track of search results
        boolean hasNeighbors=false;  // Flag to indicate whether current state has neighbors 
        //Intitialize root node that takes the initial state of the puzzle as a string that does not have a parent
        frontier entry = new frontier(initialState.getStateString(), null);//initialize a frontier of initial state that have no parents
        queue.add(entry);
            while (!queue.isEmpty()) {
                entry = queue.remove();//check frontier and remove it from the queue
                currentState = new state(entry.getState(), entry.getParentState());//updating current state of the puzzle
                explored.add(currentState.getStateString());//to avoid duplication of visited nodes
                if (goalState.getStateString().equals(currentState.getStateString())) {
                    System.out.println("Goal state reached!");
                    result.updateExpandedNodes();
                    result.setFound(true);
                    break;
                }
                result.updateExpandedNodes();//Increment expanded nodes by one
                currentState.findNeighbors();//Get all neighbours of the current state
                for (String neighbor :currentState.getNeighbors()) {//getNeighbours return arraylist of all neighbours of current node
                    if (neighbor != null){
                    entry = new frontier(neighbor, currentState);//updating current state and set the previous state as it's parent
                   if (!explored.contains(neighbor)) {//Add the new frontier node to the queue if it doesn't exist
                        queue.add(entry);
                        hasNeighbors = true;
                    }
                    }
                }
                if (hasNeighbors)
                   result.updateMaxDepth(currentState.getDepth() + 1);
            }
        result.setSearchAlgorithm("BFS");
        result.findPathToGoal(currentState);
        result.updateDepth();
        result.updateCost();
        return result;
    }
}