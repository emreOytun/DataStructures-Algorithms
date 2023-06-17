import java.util.HashSet;
import java.util.Iterator;

import lecturenotes.Edge;
import lecturenotes.Graph;

public class CycleDetectionUndirected {
    public boolean cycleDetection(Graph graph) {
        // Initialize the parent array/map for union-find structure as all elements' parent are theirselves.
        // Initialize the edgeSet with the all edges but not include two edge such that a->b and b->a, just add one of them.
        int numV = graph.getNumV();
        HashSet<Edge> edgeSet = new HashSet<>();
        int parent[] = new int[numV];
        for (int i = 0; i < numV; ++i) {    // Traverse through all the vertexes to initialize the parent array and to traverse the edges.
            parent[i] = i;
            Iterator<Edge> it = graph.edgeIterator(i);
            while (it.hasNext()) {
                Edge edge = it.next();
                if (!edgeSet.contains(edge) && !edgeSet.contains(new Edge(edge.getDest(), edge.getSource()))) {
                    edgeSet.add(edge);
                }
            }
        }
 
        for (Edge edge : edgeSet) {
            int root1 = findSubset(parent, edge.getSource());
            int root2 = findSubset(parent, edge.getDest());
            if (root1 == root2) return true;
            union(parent, root1, root2);
        }
        return false;
    }

    // The find operation for union-find data structure.
    private int findSubset(int parent[], int node) {
        if (parent[node] == node) {
            return node;
        }
        parent[node] = findSubset(parent, parent[node]); // Path compression to minimize the find operation time.
        return parent[node];
    } 

    // This can be converted to union-by-rank to make it more efficent.
    private void union(int parent[], int node1, int node2) {
        int root1 = findSubset(parent, node1);
        int root2 = findSubset(parent, node2);
        parent[root2] = root1;
    }
}
