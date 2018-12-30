package drivers;

import primary.Ajax;
import primary.Graph;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Driver {

    public static void main(String[] args) {
        Ajax<String> graph = new Ajax<>();
        graph.addEdge("7", "5");
        graph.addEdge("7", "6");
        graph.addEdge("5", "2");
        graph.addEdge("5", "4");
        graph.addEdge("6", "4");
        graph.addEdge("6", "3");
        graph.addEdge("2", "1");
        graph.addEdge("3", "1");
        graph.addEdge("1", "0");
        Set<String> neighbors = graph.neighbors("5");
        Set<String> inverseNeighbors = graph.inverseNeighbors("5");
        Set<String> vertices = graph.vertices();
        System.out.println(vertices);
        System.out.println(neighbors);
        System.out.println(inverseNeighbors);
        System.out.println(graph.topologicalSort("7"));
        graph.clear();
        int a = graph.vertexCount();
        int b = graph.edgeCount();
        if (graph.isEmpty()) {
            a -= b;
        }
        if (graph.containsVertex("4")) {
            System.out.println("Graph contains 4");
        }
        if (graph.containsEdge("a", "b")) {
            System.out.println("Graph contains a->b");
        }
        graph.addVertex("aaa");
        graph.removeVertex("aaa");
        graph.clearEdges();
        graph.removeEdge("a", "b");
        int d = graph.outDegree("a");
        int e = graph.inDegree("a");
        System.out.println(d);
        System.out.println(e);
        if (graph.containsVertex(graph.generateVertex("a", 0))) {
            System.out.println("Returned true");
        }
        Graph<String> g = new Ajax<>();
        g.clearEdges();

        double ab = 13253 >> 10;
        double bc = 51 << 64;
        System.out.printf("%.10f\n%.10f\n", ab, bc);

    }
}


