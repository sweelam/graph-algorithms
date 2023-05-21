package com.learning.bfs;

import com.learning.graph.GraphBuilder;
import com.learning.graph.Vertex;

import java.util.*;


/**
 * Find shortest Path
 **/
public class BreadthFirstSearch {

    public void bfs(GraphBuilder graphBuilder, Vertex<String> start) {
        var graph = graphBuilder.graph();
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

    public void bfsRecursively(GraphBuilder graphBuilder, Vertex<String> start) {
        Queue<Vertex<String>> qu = new LinkedList<>();
        var graph = graphBuilder.graph();

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
        var graphBuilder = new GraphBuilder();

        Vertex av = getVertex(graphBuilder);

        BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch();

        System.out.println("BFS Iteratively");
        breadthFirstSearch.bfs(graphBuilder, av);

        System.out.println("\n========================");

        System.out.println("BFS Recursively");
        graphBuilder = new GraphBuilder();
        av = getVertex(graphBuilder);
        breadthFirstSearch.bfsRecursively(graphBuilder, av);
    }

    private static Vertex getVertex(GraphBuilder graphBuilder) {
        var av = graphBuilder.addVertex("A");
        var bv = graphBuilder.addVertex("B");
        var cv = graphBuilder.addVertex("C");
        var dv = graphBuilder.addVertex("D");
        var ev = graphBuilder.addVertex("E");

        graphBuilder.addEdge(av, dv);
        graphBuilder.addEdge(av, cv);
        graphBuilder.addEdge(bv, cv);
        graphBuilder.addEdge(bv, ev);
        graphBuilder.addEdge(dv, ev);
        return av;
    }

}
