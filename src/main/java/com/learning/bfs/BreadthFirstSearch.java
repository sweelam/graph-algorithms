package com.learning.bfs;

import com.learning.graph.UndirectedGraphBuilder;
import com.learning.graph.Vertex;

import java.util.*;


/**
 * Find shortest Path
 **/
public class BreadthFirstSearch {

    public void bfs(UndirectedGraphBuilder undirectedGraphBuilder, Vertex<String> start) {
        var graph = undirectedGraphBuilder.graph();
        Queue<Vertex<String>> q = new ArrayDeque<>();

        q.add(start);
        start.visited = true;

        while (!q.isEmpty()) {
            var node = q.poll();

            System.out.print(node.getLabel() + "\t");

            for (Vertex<String> neighbour : (List<Vertex<String>>) graph.get(node)) {
                if (!neighbour.visited) {
                    q.add(neighbour);
                    neighbour.visited = true;
                }
            }
        }
    }

    public void bfsRecursively(UndirectedGraphBuilder undirectedGraphBuilder, Vertex<String> start) {
        Queue<Vertex<String>> qu = new LinkedList<>();
        var graph = undirectedGraphBuilder.graph();

        start.visited = true;
        qu.add(start);
        bfsRecursively(graph, qu);
    }

    public void bfsRecursively(Map<Vertex, List<Vertex>> graph, Queue<Vertex<String>> q) {

        if (q.isEmpty()) {
            return;
        }

        Vertex v = q.poll();

        System.out.print(v.getLabel() + "\t");

        for (Vertex neighbour : graph.get(v)) {
            if (!neighbour.visited) {
                neighbour.visited = true;
                q.add(neighbour);
            }
        }

        bfsRecursively(graph, q);
    }

    public static void main(String[] args) {
        var graphBuilder = new UndirectedGraphBuilder();

        Vertex av = getVertex(graphBuilder);

        BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch();

        System.out.println("BFS Iteratively");
        breadthFirstSearch.bfs(graphBuilder, av);

        System.out.println("\n========================");

        System.out.println("BFS Recursively");
        graphBuilder = new UndirectedGraphBuilder();
        av = getVertex(graphBuilder);
        breadthFirstSearch.bfsRecursively(graphBuilder, av);
    }

    private static Vertex getVertex(UndirectedGraphBuilder undirectedGraphBuilder) {
        var av = undirectedGraphBuilder.addVertex("A");
        var bv = undirectedGraphBuilder.addVertex("B");
        var cv = undirectedGraphBuilder.addVertex("C");
        var dv = undirectedGraphBuilder.addVertex("D");
        var ev = undirectedGraphBuilder.addVertex("E");

        undirectedGraphBuilder.addEdge(av, dv);
        undirectedGraphBuilder.addEdge(av, cv);
        undirectedGraphBuilder.addEdge(bv, cv);
        undirectedGraphBuilder.addEdge(bv, ev);
        undirectedGraphBuilder.addEdge(dv, ev);
        return av;
    }

}
