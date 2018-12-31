package primary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;

public class Ajax<V> implements Graph<V> {

    public class VNode<T> {
        private T id;
        private int inDegree;

        private VNode(T id, int inDegree) {
            this.id = id;
            this.inDegree = inDegree;
        }

        private VNode(T id) {
            this(id, 0);
        }

        public String toString() {
            return id.toString();
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof VNode)) {
                return false;
            }
            @SuppressWarnings("unchecked")
            VNode other = (VNode) obj;
            return this.id.equals(other.id) && this.inDegree == other.inDegree;
        }
    }

    private class TopologicalSorter {
        private int visitedVertices;
        private Map<VNode<V>, Integer> degreeMap;
        private List<V> ordering;
        
        private TopologicalSorter() {
        	visitedVertices = 0;
        	ordering = new ArrayList<>();
        	buildDegreeMap();
        }

        private void buildDegreeMap() {
            degreeMap = new HashMap<>();
            for (VNode<V> vertex : adjList.keySet()) {
                degreeMap.put(vertex, vertex.inDegree);
            }
        }

        private void decreaseKey(VNode<V> vertex) {
            if (vertex == null) {
                throw new IllegalArgumentException();
            }
            degreeMap.replace(vertex, degreeMap.get(vertex) - 1);
        }

        private List<V> sort(VNode<V> vertex) {
            if (vertex == null) {
                throw new IllegalArgumentException();
            }         
            if (degreeMap.get(vertex) > 0) {
                return null;
            } 
            else {
                topoSort(vertex);
                if (visitedVertices != vertices) {
                    return null;
                }
                visitedVertices = 0;
            }
            return ordering;
        }
        
        private void topoSort(VNode<V> vertex) {
            if (degreeMap.get(vertex) == 0) {
            	ordering.add(vertex.id);
                visitedVertices++;
                for (VNode<V> neighbor : adjList.get(vertex)) {
                    if (visitedVertices < vertices) {
                        decreaseKey(neighbor);
                        topoSort(neighbor);
                    }
                }
            }
        }
    }

    private VNode<V> startVertex;

    private int vertices;

    private Map<VNode<V>, List<VNode<V>>> adjList;

    public Ajax() {
        this.clear();
    }

    public void addVertex(V v) {
        addVertex(new VNode<>(v));
    }

    private void addVertex(VNode<V> vertex) {
        if (vertex == null) {
            throw new IllegalArgumentException();
        }
        if (adjList.containsKey(vertex)) {
            throw new IllegalStateException();
        }
        adjList.put(vertex, new LinkedList<>());
        startVertex = (startVertex == null || vertex.inDegree < startVertex.inDegree) ? vertex : startVertex;
        vertices++;
    }

    public void addEdge(V src, V dest) {
        VNode<V> source = getVertex(src);
        VNode<V> destination = getVertex(dest);
        if (source == null) {
            source = new VNode<>(src);
        }
        if (destination == null) {
            destination = new VNode<>(dest);
        }
        addEdge(source, destination);
    }

    private void addEdge(VNode<V> source, VNode<V> destination) {
        if (source == null || destination == null) {
            throw new IllegalArgumentException();
        }
        if (!adjList.containsKey(source)) {
            addVertex(source);
        }
        if (!adjList.containsKey(destination)) {
            addVertex(destination);
        }
        adjList.get(source).add(destination);
        destination.inDegree++;
    }

    public void removeVertex(V v) {
        VNode<V> target = getVertex(v);
        List<VNode<V>> connectedVertices = adjList.get(target);
        adjList.remove(target);
        for (VNode<V> vertex : adjList.keySet()) {
            List<VNode<V>> neighbors = adjList.get(vertex);
            neighbors.removeIf(neighbor -> (neighbor.equals(target)));
        }
        for (VNode<V> vertex : connectedVertices) {
            vertex.inDegree--;
        }
        vertices--;
    }

    public void removeEdge(V src, V dest) {
        VNode<V> source = getVertex(src);
        VNode<V> destination = getVertex(dest);
        if (source != null && destination != null) {
            List<VNode<V>> neighbors = adjList.get(source);
            neighbors.remove(destination);
            destination.inDegree--;
        }
    }

    public void clear() {
        startVertex = null;
        vertices = 0;
        adjList = new HashMap<>();
    }

    public void clearEdges() {
        for (VNode<V> vertex : adjList.keySet()) {
            adjList.get(vertex).clear();
        }
    }

    public boolean containsVertex(V id) {
        return getVertex(id) != null;
    }

    public boolean containsVertex(VNode<V> v) {
        if (v == null) {
            throw new IllegalArgumentException();
        }
        return this.adjList.keySet().contains(v);
    }

    public boolean containsEdge(V src, V dest) {
        return containsEdge(getVertex(src), getVertex(dest));
    }

    private boolean containsEdge(VNode<V> source, VNode<V> destination) {
        if (source == null || destination == null) {
            return false;
        }
        return this.adjList.get(source).contains(destination);
    }
    
    private VNode<V> getVertex(V id) {
    	if (startVertex != null && startVertex.id.equals(id)) { 
          	return startVertex;
        }
    	for (VNode<V> vertex : adjList.keySet()) {
    		if (vertex.id.equals(id)) { 
    			return vertex;
    		}
    	}
    	return null;
    }

    public int vertexCount() {
    	return this.vertices;
    }

    public int edgeCount() {
        int edges = 0;
        for (VNode vertex : adjList.keySet()) {
            edges += adjList.get(vertex).size();
        }
        return edges;
    }

    public boolean isEmpty() {
        return this.adjList.isEmpty();
    }

    public Set<V> neighbors(V v) {
        return neighbors(getVertex(v));
    }

    private Set<V> neighbors(VNode<V> v) {
        if (v == null) {
            throw new IllegalArgumentException();
        }
        Set<V> vertices = new HashSet<>();
        for (VNode<V> vertex : adjList.get(v)) {
            vertices.add(vertex.id);
        }
        return vertices;
    }

    public Set<V> inverseNeighbors(V v) {
        return inverseNeighbors(getVertex(v));
    }

    private Set<V> inverseNeighbors(VNode<V> v) {
        if (v == null) {
            throw new IllegalArgumentException();
        }
        Set<V> vertices = new HashSet<>();
        for (VNode<V> vertex : adjList.keySet()) {
            if (!v.equals(vertex)) {
                for (VNode<V> other : adjList.get(vertex)) {
                    if (other.equals(v)) {
                        vertices.add(vertex.id);
                    }
                }
            }
        }
        return vertices;
    }

    public Set<V> vertices() {
        Set<V> vertices = new HashSet<>();
        for (VNode<V> vertex : adjList.keySet()) {
            vertices.add(vertex.id);
        }
        return vertices;
    }

    public int outDegree(V v) {
        return outDegree(getVertex(v));
    }

    private int outDegree(VNode<V> v) {
        if (v == null) {
            return 0;
        }
        return this.adjList.get(v).size();
    }

    public int inDegree(V v) {
        return inDegree(getVertex(v));
    }

    private int inDegree(VNode<V> v) {
        if (v == null) {
            return 0;
        }
        return v.inDegree;
    }

    public List<V> topologicalSort(V v) {
        if (!containsVertex(v)) {
            throw new IllegalStateException("Specified start vertex does not exist");
        }
        return new TopologicalSorter().sort(getVertex(v));
    }

    public VNode<V> generateVertex(V id, int inDegree) {
        return new VNode<>(id, inDegree);
    }
}


