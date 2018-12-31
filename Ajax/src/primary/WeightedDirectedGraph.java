package primary;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WeightedDirectedGraph<V, E> {

    private class Edge {
        private V source;
        private V destination;
        private int weight;
        private E data;

        private Edge(V source, V destination, int weight, E data) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
            this.data = data;
        }
    }

    private Map<V, Set<Edge>> adjList;

    public WeightedDirectedGraph() {
        this.adjList = new HashMap<>();
    }

    public void addVertex(V v) {
        if (v == null) {
            throw new IllegalArgumentException();
        }
        adjList.put(v, new HashSet<>());
    }

    public void addEdge(V v1, V v2) {
        if (v1 == null || v2 == null) {
            throw new IllegalArgumentException();
        }
        addEdgeToMap(v1, v2, 0, null);
    }

    public void addEdge(V v1, V v2, int weight) {
        if (v1 == null || v2 == null) {
            throw new IllegalArgumentException();
        }
        addEdgeToMap(v1, v2, weight, null);
    }

    public void addEdge(V v1, V v2, int weight, E data) {
        if (v1 == null || v2 == null) {
            throw new IllegalArgumentException();
        }
        addEdgeToMap(v1, v2, weight, data);
    }

    public void removeVertex(V v) {
        if (v == null) {
            throw new IllegalArgumentException();
        }
        Set<Edge> paths = adjList.get(v);
        adjList.remove(v);
        for (V vertex : adjList.keySet()) {
            Set<Edge> neighbors = adjList.get(vertex);
            neighbors.removeIf(edge -> edge.destination.equals(v));
        }
    }

    public void removeEdge(V v1, V v2) {
        if (v1 == null || v2 == null) {
            throw new IllegalArgumentException();
        }
        adjList.get(v1).removeIf(edge -> edge.destination.equals(v2));
    }

    public Set<E> edges() {
        Set<E> edgeValues = new HashSet<>();
        for (Set<Edge> subset : adjList.values()) {
            for (Edge edge : subset) {
                edgeValues.add(edge.data);
            }
        }
        return edgeValues;
    }

    public Set<V> vertices() {
        return adjList.keySet();
    }

    public void clear() {
        this.adjList = new HashMap<>();
    }

    public void clearEdges() {
        for (Set<Edge> edges : adjList.values()) {
            edges.clear();
        }
    }

    public int inDegree(V v) {
        int inDegree = 0;
        for (Set<Edge> edges : adjList.values()) {
            for (Edge edge : edges) {
                if (edge.destination.equals(v)) {
                    inDegree++;
                }
            }
        }
        return inDegree;
    }

    public int outDegree(V v) {
        return adjList.get(v).size();
    }

    public int vertexCount() {
        return vertices().size();
    }

    public int edgeCount() {
        int edgeCount = 0;
        for (Set<Edge> edges : adjList.values()) {
            edgeCount += edges.size();
        }
        return edgeCount;
    }

    public boolean containsVertex(V v) {
        return adjList.keySet().contains(v);
    }

    public boolean containsEdge(V v1, V v2) {
        if (v1 == null || v2 == null) {
            throw new IllegalArgumentException();
        }
        for (Edge edge : adjList.get(v1)) {
            if (edge.destination.equals(v2)) {
                return true;
            }
        }
        return false;
    }

    private void addEdgeToMap(V v1, V v2, int weight, E data) {
        adjList.computeIfAbsent(v1, k -> new HashSet<>()).add(new Edge(v1, v2, weight, data));
    }






}


