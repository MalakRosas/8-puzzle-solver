/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8.puzzle;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
class minumum_entry implements Comparable<minumum_entry> { // ordering the nodes according to their cost and ensuring that the state with the lowest cost is dequeued first.
     String state;
     Integer cost;
     state parentState;
     public minumum_entry(String state,state parentState ,Integer cost) {
        this.state = state;
        this.cost = cost;
        this.parentState = parentState;
    }
    @Override
    public int compareTo(minumum_entry other) {
        return this.getCost().compareTo(other.getCost());// Comparison method to determine priority in the priority queue
    }
    public String getState() {
        return state;
    }
    public Integer getCost() {
        return cost;
    }
    public state getParentState() {
        return parentState;
    }
}
 class HashPriorityQueue<Type> extends PriorityQueue<Type> {
     Set<String> set = new HashSet<>();// check for duplicates when adding elements to the priority queue.
}
public class Astar {
    public static search Astar(state initialState, state goalState, Heuristic h) {//h may be euclidean or manhattan
        HashPriorityQueue<minumum_entry> pq = new HashPriorityQueue<>(); //Initialize a HashPriorityQueue to store frontiers
        Set<String> explored = new HashSet<>(); //Set of explored nodes to avoid duplications 
        state currentState = initialState;                                                     
        search result = new search();  //To keep track of search results
        boolean hasNeighbors=false;// Flag to indicate whether current state has neighbors 
        int cost = h.distance(initialState.getStateString());// calculate cost according to distance 
        minumum_entry entry = new minumum_entry(initialState.getStateString(),null, cost);
        pq.add(entry);
            while (!pq.isEmpty()) {
                entry = pq.remove(); //removes the entry with the minimum cost from the pq
                currentState = new state(entry.getState(), entry.getParentState());//updating current state of the puzzle
                explored.add(currentState.getStateString());//to avoid duplication of visited nodes
                //Intitialize root node that takes the initial state of the puzzle as a string that does not have a parent
                if (goalState.getStateString().equals(currentState.getStateString())) {
                    result.updateExpandedNodes();
                    result.setFound(true);
                    break;
                }
                result.updateExpandedNodes();//Increment expanded nodes by one
                currentState.findNeighbors();//Get all neighbours of the current state
                for (String neighbor :currentState.getNeighbors()) {
                    if (neighbor != null){
                    cost = h.distance(neighbor);//calculate cost for neigbour
                    entry = new minumum_entry(neighbor,currentState, cost);//compare the new neighbour with the previous one
                    /* It checks if the priority queue already contains an entry for this neighbor. 
                    If it doesn't or if the new entry has a lower cost than the existing entry, it adds the new
                    entry to the frontier. This ensures that the frontier always contains the entry with the minimum cost*/
                    if (!pq.contains(entry)&&!explored.contains(neighbor)) {
                        pq.add(entry);
                        hasNeighbors = true;
                    } else if (pq.contains(entry)) {
                        /*If the priority queue already contains an element with the same state 
                        as the new entry, adding the new entry will trigger the compareTo method 
                        to compare the costs of the two entries.If the cost of the new entry is lower than
                        the cost of the existing entry, the new entry will be placed at the front of the priority queue.*/
                        pq.add(entry);// update the key of the existing entry 
                        hasNeighbors = true;
                    }
                    }
                }
                if (hasNeighbors)
                    result.updateMaxDepth(currentState.getDepth() + 1);
            }
             result.setSearchAlgorithm("Astar search (" + h.getClass().getSimpleName() + ")");
         result.findPathToGoal(currentState);
        result.updateDepth();
        result.updateCost();
        return result;
    }
}
