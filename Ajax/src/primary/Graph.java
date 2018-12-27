package primary;

import java.util.Set;

public interface Graph<V> {

     void addVertex(V v);

     void addEdge(V src, V dest);

     void removeVertex(V v);

     void removeEdge(V src, V dest);

     void clear();

     void clearEdges();

     boolean containsVertex(V id);

     boolean containsEdge(V src, V dest);

     int vertexCount();

     int edgeCount();

     boolean isEmpty();

     Set<V> neighbors(V v);

     Set<V> inverseNeighbors(V v);

     Set<V> vertices();

     int outDegree(V v);

     int inDegree(V v);
}
