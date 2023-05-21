package com.learning.graph;

import java.util.*;

public class GraphBuilder<T> {
    private Map<Vertex<T>, List<Vertex>> graph;

    public GraphBuilder() {
        graph = new HashMap<>();
    }

    public Vertex<T> addVertex(T label) {
        Vertex<T> v = new Vertex(label);
        graph.putIfAbsent(v, new ArrayList<>());
        return v;
    }

    public void addEdge(T label1, T label2) {
        graph.get(new Vertex(label1)).add(new Vertex(label2));
        graph.get(new Vertex(label2)).add(new Vertex(label1));
    }

    public void addEdge(Vertex v1, Vertex v2) {
        graph.get(v1).add(v2);
        graph.get(v2).add(v1);
    }


    public Map<Vertex<T>, List<Vertex>> graph() {
        return graph;
    } 
}


