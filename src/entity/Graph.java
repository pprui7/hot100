package entity;

import java.util.*;

public class Graph {
    private Map<Integer, List<Integer>> adjList;
    private Set<Integer> vertices;
    private int edgeCount;
    Map<Integer, Integer> inDegree = new HashMap<>();

    public Graph(){
        this.adjList = new HashMap<>();
        this.vertices = new HashSet<>();
        edgeCount = 0;
    }
    public void buildFromEdges(int[][] edges) {
        if (edges == null) return;
        for (int[] edge : edges) {
            vertices.add(edge[0]);
            vertices.add(edge[1]);
            adjList.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
            adjList.computeIfAbsent(edge[0], k -> new ArrayList<>());
            edgeCount++;
        }
        for (Integer vertex : vertices){
            inDegree.put(vertex, 0);
        }
        for (Integer from : vertices) {
            for (Integer to : adjList.get(from)) {
                inDegree.put(to, inDegree.get(to) + 1);
            }
        }
    }



    public boolean hasCycle(){
        Queue<Integer> queue = new ArrayDeque<>();
        inDegree.forEach((key, value) -> {
            if(value == 0)
                queue.offer(key);
        });
        int visitedCount = 0;
        while(!queue.isEmpty()){
            Integer currentNode = queue.poll();
            visitedCount++;
            for (Integer to : adjList.get(currentNode)) {
                inDegree.put(to, inDegree.get(to) - 1);
                if(inDegree.get(to) == 0)
                    queue.offer(to);
            }
        }
        return visitedCount != vertices.size();
    }
}
