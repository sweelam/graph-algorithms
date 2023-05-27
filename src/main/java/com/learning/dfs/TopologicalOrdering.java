package com.learning.dfs;

import com.learning.graph.IGraph;
import com.learning.graph.Vertex;
import com.learning.graph.DirectedGraphBuilder;

import java.util.*;

/**
 * Find Prerequisites
 */
public class TopologicalOrdering {
    public Stack<Vertex> topSort(IGraph<String> graphBuilder) {
        var graph = graphBuilder.graph();
        Stack<Vertex> topOrder = new Stack<>();

        for (Vertex<String> v : graph.keySet()) {
            if (!v.visited) {
                dfs(graphBuilder, v, topOrder);
            }
        }


        return topOrder;
    }

    public void dfs(IGraph<String> iGraph, Vertex<String> v, Stack<Vertex> topOrder) {

        v.visited = true;

        for (Vertex vertex : iGraph.graph().get(v)) {
            if (!vertex.visited) {
                vertex.visited = true;
                dfs(iGraph, vertex, topOrder);
            }
        }

        topOrder.push(v);
    }

    Stack<Vertex> stack;
    public boolean findPrerequisitesCourses(IGraph<String> iGraph, Vertex<String> startCourse, Vertex<String> target) {
        startCourse.visited = true;

        if (startCourse.getLabel().equals(target.getLabel())) {
            return true;
        }

        stack.push(startCourse);

        for (Vertex vertex : iGraph.graph().get(startCourse)) {
            if (!vertex.visited) {
                vertex.visited = true;
                if (findPrerequisitesCourses(iGraph, vertex, target)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        var graph = new DirectedGraphBuilder<String>();

        Vertex<String> introToCs = graph.addVertex("Intro to CS");
        Vertex<String> cLang = graph.addVertex("C Lang");
        Vertex<String> maths = graph.addVertex("Maths");
        Vertex<String> algorithms = graph.addVertex("Algorithms");
        Vertex<String> dataStructure = graph.addVertex("Data Structure");
        Vertex<String> database = graph.addVertex("Database");
        Vertex<String> pl2 = graph.addVertex("PL2");


        graph.addEdge(introToCs, cLang);
        graph.addEdge(introToCs, maths);
        graph.addEdge(cLang, dataStructure);
        graph.addEdge(maths, algorithms);
        graph.addEdge(dataStructure, database);
        graph.addEdge(algorithms, pl2);
        graph.addEdge(database, pl2);


        TopologicalOrdering topologicalOrdering = new TopologicalOrdering();


        topologicalOrdering.stack =  new Stack<>();
        topologicalOrdering.findPrerequisitesCourses(graph, introToCs, database);
        System.out.println(topologicalOrdering.stack.toString());

        topologicalOrdering.stack =  new Stack<>();
        topologicalOrdering.findPrerequisitesCourses(graph, introToCs, pl2);
        System.out.println(topologicalOrdering.stack.toString());

        Stack<Vertex> topSort = topologicalOrdering.topSort(graph);
        while (!topSort.isEmpty()) {
            System.out.print(topSort.pop() + "\t");
        }
    }

}
