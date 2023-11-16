/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8.puzzle;
public class frontier {
      String state;
      state parentState;
    public frontier(String state, state parentState) {
        this.state = state;
        this.parentState = parentState;
    }
    public String getState() {
        return state;
    }
    public state getParentState() {
        return parentState;
    }
}
