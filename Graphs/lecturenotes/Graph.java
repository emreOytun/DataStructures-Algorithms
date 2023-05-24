package lecturenotes;

import java.util.Iterator;

public interface Graph {
    void insert(Edge edge);
    boolean isEdge(int source, int dest);
    Edge getEdge(int source, int dest);
    Iterator<Edge> edgeIterator(int source);

    int getNumV();
    boolean isDirected();
}
