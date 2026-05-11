package solution;

import java.util.*;

public class SolutionGraph {
    public int numIslands(char[][] grid) {
        int len1 = grid.length - 1;
        int len2 = grid[0].length - 1;
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1')
                    dfs(i, j, len1, len2, grid);
                result++;
            }
        }
        return result;
    }

    private void dfs(int i, int j, int len1, int len2, char[][] grid) {
        if (i < 0 || j < 0 || i > len1 || j > len2 || grid[i][j] == '0')
            return;
        grid[i][j] = '0';
        dfs(i - 1, j, len1, len2, grid);
        dfs(i + 1, j, len1, len2, grid);
        dfs(i, j - 1, len1, len2, grid);
        dfs(i, j + 1, len1, len2, grid);
    }

    // DFS
    int[] visited;
    List<List<Integer>> adjList;
    List<Integer> topologicalOrder;

    public boolean dfsCanFinish(int numCourses, int[][] prerequisites) {
        adjList = new ArrayList<>();
        visited = new int[numCourses];
        topologicalOrder = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            adjList.get(prerequisite[1]).add(prerequisite[0]);
        }
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0 && !dfsCanFinishHelper(i)) {
                topologicalOrder = null;
                return false;
            }
        }
        Collections.reverse(topologicalOrder);
        System.out.println(topologicalOrder);
        return true;
    }

    private boolean dfsCanFinishHelper(int i) {
        visited[i] = 1;
        for (Integer neighbour : adjList.get(i)) {
            if (visited[neighbour] == 0) {
                if (dfsCanFinishHelper(neighbour)) {
                    return true;
                }
            } else if (visited[neighbour] == 1)
                return true;
        }
        visited[i] = 2;
        topologicalOrder.add(i);
        return false;
    }

    // BFS
    int[] inDegree;

    public boolean bfsCanFinish(int numCourses, int[][] prerequisites) {
        adjList = new ArrayList<>();
        inDegree = new int[numCourses];
        topologicalOrder = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            adjList.get(prerequisite[1]).add(prerequisite[0]);
            inDegree[prerequisite[0]]++;
        }
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0)
                queue.offer(i);
        }
        int count = 0;
        while (!queue.isEmpty()) {
            int i = queue.poll();
            count++;
            topologicalOrder.add(i);
            for (Integer neighbour : adjList.get(i)) {
                inDegree[neighbour]--;
                if (inDegree[neighbour] == 0)
                    queue.offer(neighbour);
            }
        }
        topologicalOrder = count == numCourses ? topologicalOrder : null;
        return count == numCourses;
    }

    int len1;
    int len2;
    int newCount;

    public int orangesRotting(int[][] grid) {
        newCount = 0;
        Queue<int[]> oldlist = new ArrayDeque<>();
        len1 = grid.length - 1;
        len2 = grid[0].length - 1;
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                if (grid[i][j] == 1)
                    newCount++;
                if (grid[i][j] == 2) {
                    grid[i][j] = 0;
                    oldlist.add(new int[]{i, j});
                }
            }
        }
        int minute = 0;
        while (!oldlist.isEmpty()) {
            if(newCount == 0)
                break;
            int n = oldlist.size();
            while (n > 0) {
                int[] tmp = oldlist.poll();
                int x = tmp[0], y = tmp[1];
                if (x - 1 >= 0 && grid[x - 1][y] == 1) {
                    oldlist.offer(new int[]{x - 1, y});
                    newCount--;
                    grid[x - 1][y] = 2;
                }
                if (x + 1 <= len1 && grid[x + 1][y] == 1) {
                    oldlist.offer(new int[]{x + 1, y});
                    newCount--;
                    grid[x + 1][y] = 2;
                }
                if (y + 1 <= len2 && grid[x][y + 1] == 1) {
                    oldlist.offer(new int[]{x, y + 1});
                    newCount--;
                    grid[x][y + 1] = 2;
                }
                if (y - 1 >= 0 && grid[x][y - 1] == 1) {
                    oldlist.offer(new int[]{x, y - 1});
                    newCount--;
                    grid[x][y - 1] = 2;
                }
                n--;
            }
            minute++;
        }
        if(newCount == 0)
            return minute;
        else
            return -1;
    }

//    private int dfs(int i, int j, int[][] grid) {
//        if (i < 0 || j < 0 || i > len1 || j > len2 || grid[i][j] == 0)
//            return 0;
//        grid[i][j] = 0;
//        newCount--;
//        int minute1 = dfs(i - 1, j, grid);
//        int minute2 =  dfs(i + 1, j, grid);
//        int minute3 =  dfs(i, j - 1, grid);
//        int minute4 =  dfs(i, j + 1, grid);
//        return Math.max(minute1, Math.max(minute2, Math.max(minute3, minute4))) + 1;
//    }


}



