package primary;

import java.util.Set;

public interface Graph<V> {

     /**
      * Creates a new node in the graph with the passed in value as data to be stored.
      *
      * @param v the reference object with which to compare.
      *
      * @throws java.lang.IllegalArgumentException  if the passed vertex is null.
      */
     void addVertex(V v);

     /**
      * Creates a new directed edge from the source vertex to the destination vertex.
      *
      * @param src vertex of type V that represents the source of the edge to be added.
      * @param dest vertex of type V that represents the destination of the edge to be added.
      *
      * @throws java.lang.IllegalArgumentException  if either passed vertex is null.
      */
     void addEdge(V src, V dest);

     /**
      * Removes the vertex referenced by the passed in value from the graph, also removes all subsequent edges
      * that originate from the vertex.
      *
      * @param v vertex to be removed from the graph.
      *
      * @throws java.lang.IllegalArgumentException  if the passed vertex is null.
      */
     void removeVertex(V v);

     /**
      * Removes the edge between the passed in vertices src and dest.
      *
      * @param src vertex of type V that represents the source of the edge to be removed.
      * @param dest vertex of type V that represents the destination of the edge to be removed.
      *
      * @throws java.lang.IllegalArgumentException  if either passed vertex is null.
      */
     void removeEdge(V src, V dest);

     /**
      * Removes all edges and vertices from the graph.
      */
     void clear();

     /**
      * Removes all edges from the graph.
      */
     void clearEdges();

     /**
      * Determines whether or not the passed in vertex is present within the graph.
      *
      * @param v vertex to be removed from the graph.
      *
      * @throws java.lang.IllegalArgumentException  if the passed vertex is null.
      * @return true if the graph contains this vertex; false otherwise.
      */
     boolean containsVertex(V v);

     /**
      * Determines whether or not the passed in vertex is present within the graph.
      *
      * @param src source of edge to be removed from the graph.
      * @param dest destination of edge to be removed from the graph.
      *
      * @throws java.lang.IllegalArgumentException  if either passed in vertex is null.
      * @return true if the graph contains this edge; false otherwise.
      */
     boolean containsEdge(V src, V dest);

     /**
      * Determines whether the graph currently contains any vertices, and subsequently edges as well.
      *
      * @return true if the graph does not contain any vertices; false otherwise.
      */
     boolean isEmpty();

     /**
      * Counts the number of vertices currently present within the graph.
      *
      * @return the number of vertices in the graph.
      */
     int vertexCount();

     /**
      * Counts the number of edges currently present within the graph.
      *
      * @return the number of edges in the graph.
      */
     int edgeCount();

     /**
      * Determines the out-degree of the passed in vertex.
      *
      * @param v the target vertex.
      *
      * @throws java.lang.IllegalArgumentException  if the passed in vertex is null.
      * @return the number of vertices that have directed edges pointing to this vertex,
      * or the out-degree.
      */
     int outDegree(V v);

     /**
      * Determines the in-degree of the passed in vertex.
      *
      * @param v the target vertex.
      *
      * @throws java.lang.IllegalArgumentException  if the passed in vertex is null.
      * @return the number of directed edges pointing to this vertex, or the out-degree.
      */
     int inDegree(V v);

     /**
      * Determines the neighbors, or the vertices that the passed in vertex points to.
      *
      * @param v the target vertex.
      *
      * @throws java.lang.IllegalArgumentException  if the target vertex is null.
      * @return the set containing the neighbors that the target vertex points to.
      */
     Set<V> neighbors(V v);

     /**
      * Determines the inverse neighbors, or the vertices that point to the passed in vertex.
      *
      * @param v the target vertex.
      *
      * @throws java.lang.IllegalArgumentException  if the target vertex is null.
      * @return the set containing the inverse neighbors that point to the target vertex.
      */
     Set<V> inverseNeighbors(V v);

     /**
      * Returns a set of the vertices currently present within the graph.
      *
      * @return set of vertices making up the graph.
      */
     Set<V> vertices();
}


