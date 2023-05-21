package com.learning.dfs;

public class MazeProblem {

    /**
     * way out of a maze.
     * <p>
     * [
     * [1,1,1,1,1],
     * [1,2,0,1,1],
     * [1,1,0,1,1],
     * [1,1,0,0,0],
     * [1,1,1,1,3],
     * ]
     * So we have a map like this
     * integer 1 represents walls
     * integer 2 is the starting point
     * integer 3 is the destination (so this is what we are looking for)
     * integer 0 represents the states we can consider (so the solution contains one or more 0 states)
     * <p>
     * So the solution should be something like this (S represents the states in the solution set):
     * <p>
     * [
     * [-,-,-,-,-],
     * [-,2,S,-,-],
     * [-,-,S,-,-],
     * [-,-,S,S,S],
     * [-,-,-,-,3],
     * ]
     */

    private boolean[][] visited;

    public boolean canEscape(int[][] maze, int[] startPosition) {
        visited = new boolean[maze.length][maze.length];

        if (dfs(maze, startPosition[0], startPosition[1]))
            return true;

        return false;
    }

    private boolean dfs(int[][] maze, int row, int col) {
        if (row == maze.length - 1 && col == maze.length - 1 && maze[row][col] == 3) {
            return true;
        }

        if (isFeasible(maze, row, col)) {
            visited[row][col] = true;

            // right
            if (dfs(maze, row, col++)) {
                return true;
            }

            // left
            if (dfs(maze, row, col--)) {
                return true;
            }

            // down
            if (dfs(maze, row++, col)) {
                return true;
            }

            // up
            if (dfs(maze, row--, col)) {
                return true;
            }

            visited[row][col] = false;
            return false;
        }

        return false;
    }

    private boolean isFeasible(int[][] maze, int row, int col) {
        return (row >= 0 && row <= maze.length - 1)
                && (col >= 0 && col <= maze.length - 1)
                && (maze[row][col] == 0)
                && !visited[row][col];
    }

    public static void main(String[] args) {
        int[][] maze = {
                {1, 1, 1, 2, 1},
                {1, 1, 0, 0, 1},
                {1, 1, 0, 1, 1},
                {1, 1, 0, 0, 0},
                {1, 1, 1, 1, 3},
        };

        var mazeSolver = new MazeProblem();

        System.out.println(
                mazeSolver.canEscape(maze, new int[]{1, 2}) ?
                        "Can escape!" : "Can't escape!"
        );
    }

}
