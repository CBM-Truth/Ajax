package primary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ajax {

    private class VNode {
        public String id;
        public int inDegree;

        public VNode(String id, int inDegree) {
            this.id = id;
            this.inDegree = inDegree;
        }

        public VNode(String id) {
            this(id, 0);
        }

        public String toString() {
            return id;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof VNode)) {
                return false;
            }
            VNode other = (VNode) obj;
            return this.id.equals(other.id) && this.inDegree == other.inDegree;
        }
    }

    private class TopologicalSorter {
        public int visitedVertices;
        public Map<VNode, Integer> degreeMap;
        public List<String> ordering;
        
        public TopologicalSorter() {
        	visitedVertices = 0;
        	ordering = new ArrayList<>();
        	buildDegreeMap();
        }

        public void buildDegreeMap() {
            degreeMap = new HashMap<>();
            for (VNode vertex: adjList.keySet()) {
                degreeMap.put(vertex, vertex.inDegree);
            }
        }

        public void decreaseKey(VNode vertex) {
            degreeMap.replace(vertex, degreeMap.get(vertex) - 1);
        }

        public List<String> sort(VNode vertex) {
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
        
        private void topoSort(VNode vertex) { 
            if (degreeMap.get(vertex) == 0) {
            	ordering.add(vertex.id);
                visitedVertices++;
                for (VNode neighbor : adjList.get(vertex)) {
                    if (visitedVertices < vertices) {
                        decreaseKey(neighbor);
                        topoSort(neighbor);
                    }
                }
            }
        }
        
        public void print(VNode vertex)  {
            if (vertex == null) {
                throw new IllegalArgumentException();
            }         
            if (degreeMap.get(vertex) > 0) {
                System.out.printf("No topological ordering starting from vertex %s\n", vertex.id);
            } 
            else {
                topoPrint(vertex);
                System.out.println();
                if (visitedVertices != vertices) {
                    System.out.println("Could not visit every vertex, this is not a valid ordering");
                }
                visitedVertices = 0;
            }   
        }

        private void topoPrint(VNode vertex) {
            if (degreeMap.get(vertex) == 0) {
                System.out.printf("%s ", vertex.id);
                visitedVertices++;
                for (VNode neighbor : adjList.get(vertex)) {
                    if (visitedVertices < vertices) {
                        decreaseKey(neighbor);
                        topoPrint(neighbor);
                    }
                }
            }
        }
    }

    private VNode startVertex;

    private int vertices;

    private Map<VNode, List<VNode>> adjList;

    public Ajax() {
        adjList = new HashMap<VNode, List<VNode>>();
    }

    public Map<VNode, List<VNode>> getAdjList() {
        return this.adjList;
    }

    private void addVertex(VNode vertex) {
        if (adjList.containsKey(vertex)) {
            throw new IllegalStateException();
        }
        adjList.put(vertex, new ArrayList<>());
        startVertex = (startVertex == null || vertex.inDegree < startVertex.inDegree) ? vertex : startVertex;
        vertices++;
    }
    
    public void addVertex(String v) { 
    	addVertex(new VNode(v));
    }

    private void addEdge(VNode source, VNode destination) {
        if (!adjList.containsKey(source)) {
            addVertex(source);
        }
        if (!adjList.containsKey(destination)) {
            addVertex(destination);
        }
        adjList.get(source).add(destination);
        destination.inDegree++;
    }

    public void addEdge(String src, String dest) {
    	VNode source = getVertex(src);
    	VNode destination = getVertex(dest);
    	if (source == null) { 
    		source = new VNode(src);
    	}
    	if (destination == null) { 
    		destination = new VNode(dest);
    	}
        addEdge(source, destination);
    }

    public boolean contains(String id) {
        for (VNode vertex : adjList.keySet()) {
            if (id.equals(vertex.id)) {
                return true;
            }
        }
        return false;
    }
    
    private VNode getVertex(String id) {
    	if (startVertex != null && startVertex.id.equals(id)) { 
          	return startVertex;
        }
    	for (VNode vertex : adjList.keySet()) { 
    		if (vertex.id.equals(id)) { 
    			return vertex;
    		}
    	}
    	return null;
    }

    public int size() { 
    	return this.vertices;
    }
    
    public int getNumEdges() {
    	int edges = 0;
    	for (VNode vertex : adjList.keySet()) { 
    		edges += adjList.get(vertex).size();
    	}
    	return edges;
    }

    public void topoPrint() {
        topoPrint(startVertex.id);
    }

    public List<String> topoSort(String v) {
        if (!contains(v)) {
            throw new IllegalStateException("Specified start vertex does not exist");
        }
        return new TopologicalSorter().sort(getVertex(v));
    }
    
    public void topoPrint(String v) {
        if (!contains(v)) {
            throw new IllegalStateException("Specified start vertex does not exist");
        }	
    	new TopologicalSorter().print(getVertex(v));
    }
}

