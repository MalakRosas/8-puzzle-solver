/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8.puzzle;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
 class HashStack<Type> extends Stack<Type> {
}
public class DFS {
        public static search dfs(state initialState, state goalState) {
        HashStack<frontier> st = new HashStack<>();////Initialize a stack which implements class HashStack to store frontiers
        Set<String> explored = new HashSet<>();// Set of explored nodes to avoid duplication 
        state currentState = initialState;    
        search result = new search(); //To keep track of search results
        boolean hasNeighbors=false;// Flag to indicate whether current state has neighbors 
        frontier entry = new frontier(initialState.getStateString(), null);
        st.push(entry);
            while (!st.isEmpty()) {
                entry = st.pop();
                currentState = new state(entry.getState(), entry.getParentState());
                explored.add(currentState.getStateString());//to avoid duplication of visited nodes
                if (goalState.getStateString().equals(currentState.getStateString())) {
                    result.updateExpandedNodes();
                    result.setFound(true);
                    break;
                }
                result.updateExpandedNodes();//Increment expanded nodes by one
                currentState.findNeighbors();////Get all neighbours of the current state
                for (int i = 3; i >= 0; i--) {//loop from the end of arraylist to the start as stack is LIFO
                    String neighbor = currentState.getNeighbors().get(i);
                    if (neighbor != null){
                    entry = new frontier(neighbor, currentState);
                    if (!st.contains(entry) && !explored.contains(neighbor)) {
                        st.push(entry);
                        hasNeighbors = true;
                    }
                    }
                }
               if (hasNeighbors)
                    result.updateMaxDepth(currentState.getDepth() + 1);
            }
             result.setSearchAlgorithm("DFS");
         result.findPathToGoal(currentState);
        result.updateDepth();
        result.updateCost();
        return result;
    }
}
