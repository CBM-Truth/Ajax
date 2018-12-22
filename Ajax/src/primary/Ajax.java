package primary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ajax {
	
	public class VNode { 
		private String id;
		private int inDegree;
		
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
	
	public class TopologicalSortPrinter { 
		private int printedNodes;
		//private List<VNode> unknownNeighbors;
		
		public Map<VNode, Integer> buildDegreeMap() { 
			Map<VNode, Integer> degreeMap = new HashMap<>();
			for (VNode vertex : adjList.keySet()) {
				int inDegree = 0;
				for (VNode key : adjList.keySet()) { 
					if (!key.equals(vertex)) { 
						for (VNode neighbor : adjList.get(key)) { 
							if (neighbor.equals(vertex)) { 
								inDegree++;
							}
						}
					}
				}
				degreeMap.put(vertex, inDegree);
			}
			return degreeMap;
		}
		
		public void decreaseKey(VNode vertex, Map<VNode, Integer> degreeMap) { 
			degreeMap.replace(vertex, degreeMap.get(vertex) - 1);
		}
		
		public void topoSortPrint(VNode vertex) {
			if (vertex == null) { 
				throw new IllegalArgumentException();
			}
			Map<VNode, Integer> degreeMap = buildDegreeMap();
			if (degreeMap.get(vertex) > 0) { 
				System.out.printf("No topological ordering starting from vertex %s\n", vertex.id);
			} else { 
				topoSortPrint(vertex, degreeMap);
				System.out.println();
				if (printedNodes != vertices) { 
					System.out.println("Could not visit every vertex, this is not a valid ordering");
				}
				printedNodes = 0;
			}
		}
		
		private void topoSortPrint(VNode vertex, Map<VNode, Integer> degreeMap) {
			if (degreeMap.get(vertex) == 0) {
				System.out.printf("%s ", vertex.id);
				printedNodes++; 
				for (VNode neighbor : adjList.get(vertex)) { 
					if (printedNodes < vertices) {
						decreaseKey(neighbor, degreeMap);
						topoSortPrint(neighbor, degreeMap);
					}
				} 
			}
		}
		
		/*
		 * 		private void topoSortPrint(VNode vertex, Map<VNode, Integer> degreeMap) {
			if (degreeMap.get(vertex) == 0) {
				System.out.printf("%s ", vertex.id);
				printedNodes++;
				unknownNeighbors.addAll(adjList.get(vertex));
				for (VNode neighbor : unknownNeighbors) { 
					if (printedNodes < vertices) {
						decreaseKey(neighbor, degreeMap);
						topoSortPrint(neighbor, degreeMap);
					}
				} 
			}
		}
	}
		 */
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
	
	public void addVertex(VNode vertex) { 
		if (adjList.containsKey(vertex)) { 
			throw new IllegalStateException();
		}
		adjList.put(vertex, new ArrayList<>());
		startVertex = (startVertex == null || vertex.inDegree < startVertex.inDegree) ? vertex : startVertex;
		vertices++;
	}
	
	public void addEdge(VNode source, VNode destination) {
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
		VNode source = new VNode(src);
		VNode destination = new VNode(dest);
		for (VNode vertex : adjList.keySet()) { 
			if (vertex.id.equals(src)) { 
				source = vertex;
				break;
			}
		}
		for (VNode vertex : adjList.keySet()) { 
			if (vertex.id.equals(dest)) { 
				destination = vertex;
				break;
			}
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
	
	public void topoSortPrint() {
		topoSortPrint(startVertex);
	}
	
	public void topoSortPrint(String v) {
		if (!contains(v)) { 
			throw new IllegalStateException("Specified start vertex does not exist");
		}
		VNode vertex = null;
		for (VNode vert : adjList.keySet()) { 
			if (vert.id.equals(v)) { 
				vertex = vert;
			}
		}
		topoSortPrint(vertex);
	}
	
	public void topoSortPrint(VNode vertex) {
		new TopologicalSortPrinter().topoSortPrint(vertex);
	}
	
}
