package lecturenotes;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ListGraph extends AbstractGraph {
    private List<Edge>[] edges; // Indexes == Vertices
    
    public ListGraph(int numV, boolean directed) {
        super(numV, directed);
        edges = new List[numV];
        for (int i = 0; i < numV; ++i) {
            edges[i] = new LinkedList<Edge>();
        }
    }

    /** Determine whether an edge exists.
    @param source The source vertex
    @param dest The destination vertex
    @return true if there is an edge from source to dest
    */
    @Override
    public boolean isEdge(int source, int dest) {
        if (source < 0 || source >= getNumV()) {
            throw new IndexOutOfBoundsException();
        }
        return edges[source].contains(new Edge(source, dest));
    }

    @Override
    public void insert(Edge edge) {
        edges[edge.getSource()].add(edge);
        if (!isDirected()) {    // If the graph is undirected, then add a new edge to the destination vertex too.
            edges[edge.getDest()].add(new Edge(edge.getDest(), edge.getSource(), edge.getWeight()));
        }
    }

    @Override
    public Iterator<Edge> edgeIterator(int source) {
        if (source < 0 || source >= getNumV()) {
            throw new IndexOutOfBoundsException();
        }
        return edges[source].iterator();
    }

    /** Get the edge between two vertices. If an
    edge does not exist, an Edge with a weight
    of Double.POSITIVE_INFINITY is returned.
    @param source The source
    @param dest The destination
    @return the edge between these two vertices
    */
    @Override
    public Edge getEdge(int source, int dest) {
        Edge target = new Edge(source, dest, Double.POSITIVE_INFINITY);
        for (Edge edge : edges[source]) {
            if (edge.equals(target)) {
                return edge;
            }
        }
        return target;
    }


}
