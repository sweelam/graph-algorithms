package com.learning.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DirectedGraphBuilder<T> implements IGraph<T> {
    private Map<Vertex<T>, List<Vertex>> graph;

    public DirectedGraphBuilder() {
        graph = new HashMap<>();
    }

    public Vertex<T> addVertex(T label) {
        Vertex<T> v = new Vertex(label);
        graph.putIfAbsent(v, new ArrayList<>());
        return v;
    }

    public void addEdge(T label1, T label2) {
        graph.get(new Vertex(label1)).add(new Vertex(label2));
    }

    public void addEdge(Vertex v1, Vertex v2) {
        graph.get(v1).add(v2);
    }


    public Map<Vertex<T>, List<Vertex>> graph() {
        return graph;
    }
}
