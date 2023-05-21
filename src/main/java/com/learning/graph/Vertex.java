package com.learning.graph;

public class Vertex <T> {
    private T label;
    public boolean visited = false;

    public Vertex(T label) {
        this.label = label;
    }

    public T getLabel() {
        return label;
    }
}
