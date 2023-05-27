package com.learning.dfs;

import com.learning.graph.UndirectedGraphBuilder;
import com.learning.graph.Vertex;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class MinimumSpanningTree {

    public String mst(UndirectedGraphBuilder<String> undirectedGraphBuilder, Vertex<String> start) {
        StringBuffer ans = new StringBuffer();

        var graph = undirectedGraphBuilder.graph();
        Stack<Vertex> stack = new Stack<>();
        Set<Vertex<String>> visited = new HashSet<>();

        stack.add(start);
        visited.add(start);

        while (!stack.isEmpty()) {
            var v = stack.pop();

            ans.append(v.getLabel());

            graph.get(v)
                    .stream()
                    .filter(vrtx -> !visited.contains(vrtx))
                    .forEach(vrtx -> {
                        visited.add(vrtx);
                        stack.add(vrtx);
                    });
        }

        return ans.toString();
    }


    public static void main(String[] args) {
        var graphBuilder = new UndirectedGraphBuilder<String>();

        var bv = graphBuilder.addVertex("B");
        var cv = graphBuilder.addVertex("C");
        var dv = graphBuilder.addVertex("D");
        var ev = graphBuilder.addVertex("E");
        var av = graphBuilder.addVertex("A");



        graphBuilder.addEdge(av, cv);
        graphBuilder.addEdge(av, bv);
        graphBuilder.addEdge(bv, ev);
        graphBuilder.addEdge(bv, dv);
        graphBuilder.addEdge(cv, dv);

        graphBuilder.addEdge(av, dv);
        graphBuilder.addEdge(av, ev);

        graphBuilder.addEdge(dv, ev);
        graphBuilder.addEdge(bv, cv);
        graphBuilder.addEdge(cv, ev);


        var obj = new MinimumSpanningTree();
        System.out.println(obj.mst(graphBuilder, av));
    }
}
