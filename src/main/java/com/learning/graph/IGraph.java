package com.learning.graph;

import java.util.List;
import java.util.Map;

 public interface IGraph<T> {
     Vertex<T> addVertex(T label);

     void addEdge(T label1, T label2);

     void addEdge(Vertex v1, Vertex v2);


     Map<Vertex<T>, List<Vertex>> graph();
}
