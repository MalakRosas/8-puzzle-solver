/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8.puzzle;

import java.util.Scanner;
public class Puzzle {
    public static void calcRunTime(Runnable codeBlock) {
    long startTime = System.nanoTime();
    codeBlock.run();
    long endTime = System.nanoTime();
    long runtime = endTime - startTime;
    long runtimeInMillis = runtime / 1_000_000;
    System.out.println("Runtime: " + runtimeInMillis + " milliseconds");
}
public static void main(String[] args) {
    calcRunTime(() -> {
        Scanner input=new Scanner(System.in);
        System.out.println("Enter the initial state : ");
        String initial=input.nextLine();
        int[][]arr=new int[3][3];
        int x=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++)
        arr[i][j]=initial.charAt(x);
            x++;
        }
            System.out.println("Enter the algorithm you want : ");
        String alg=input.nextLine();
        state initialState = new state(initial, null);
        state goalState = new state("012345678", null);
        search result;
        if(alg.equals("BFS".toLowerCase()))
        {
            result = BFS.bfs(initialState, goalState);
            System.out.println(result.toString());
        }
        else if(alg.equals("DFS".toLowerCase())){
            result = DFS.dfs(initialState, goalState);
            System.out.println(result.toString());
        }
        else if(alg.equals("Astar".toLowerCase())){
                Heuristic h1 = new Euclidean(); 
                Heuristic h2 = new Manhattan(); 
                search r1,r2;
                r1 = Astar.Astar(initialState, goalState, h1);
                r2 = Astar.Astar(initialState, goalState, h2);
                System.out.println("Euclidean:\n"+r1.toString());
                System.out.println("Manhattan:\n"+r2.toString());
            }
        //125340678
    });
}
}