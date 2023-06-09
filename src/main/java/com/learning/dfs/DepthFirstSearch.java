package com.learning.dfs;

import com.learning.graph.IGraph;
import com.learning.graph.UndirectedGraphBuilder;
import com.learning.graph.Vertex;

import java.util.*;

public class DepthFirstSearch {

    public void dfs(IGraph<String> undirectedGraphBuilder, Vertex<String> start) {

        var graph = undirectedGraphBuilder.graph();
        Set<Vertex> visited = new HashSet<>();

        Stack<Vertex> stack = new Stack<>();
        stack.add(start);
        visited.add(start);

        while (!stack.isEmpty()) {
            var current = stack.pop();

            System.out.print(current.getLabel() + "\t");

            for (Vertex<String> neighbour : graph.get(current)) {
                if (!visited.contains(neighbour)) {
                    stack.add(neighbour);
                    visited.add(neighbour);
                }
            }
        }
    }

    public void dfsRecursively(IGraph<String> undirectedGraphBuilder, Set<Vertex> visited, Vertex<String> start) {
        dfsRecursively(undirectedGraphBuilder.graph(), visited, start);
    }

    public void dfsRecursively(Map<Vertex<String>, List<Vertex>> graph, Set<Vertex> visited, Vertex<String> start) {
        System.out.print(start.getLabel() + "\t");

        visited.add(start);

        graph.get(start)
                .stream()
                .filter(neighbour -> !visited.contains(neighbour))
                .forEach(neighbour -> dfsRecursively(graph, visited, neighbour));
    }

    public static void main(String[] args) {
        var graphBuilder = new UndirectedGraphBuilder<String>();

        var av = graphBuilder.addVertex("A");
        var bv = graphBuilder.addVertex("B");
        var cv = graphBuilder.addVertex("C");
        var dv = graphBuilder.addVertex("D");
        var ev = graphBuilder.addVertex("E");

        graphBuilder.addEdge(av, cv);
        graphBuilder.addEdge(av, dv);
        graphBuilder.addEdge(bv, cv);
        graphBuilder.addEdge(bv, ev);
        graphBuilder.addEdge(dv, ev);

        var depthFirstSearch = new DepthFirstSearch();


        System.out.println("DFS Iteratively");
        depthFirstSearch.dfs(graphBuilder, av);

        System.out.println("\n========================");

        System.out.println("DFS Recursively");
        depthFirstSearch.dfsRecursively(graphBuilder, new HashSet<>(),av);
    }
}
