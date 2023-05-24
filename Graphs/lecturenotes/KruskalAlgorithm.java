package lecturenotes;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class KruskalAlgorithm {

    private static class Subset {
        private int parent, rank;
        public Subset(int parent, int rank) {
            this.parent = parent;
            this.rank = rank;
        }
    }  

    // Comparator class to compare the edges according to their weights.
    private static class EdgeComparator implements Comparator<Edge> {
        public int compare(Edge edge1, Edge edge2) {
            if (edge1.getWeight() == edge2.getWeight()) return 0;
            if (edge1.getWeight() < edge2.getWeight()) return -1;
            return 1;
        }
    }
    
    public static Edge[] kruskals(Graph graph) {
        // Use TreeSet to sort the edges. 
        // edge.equals() == True if source-destination pair's are equal, order doesn't matter since the graph is undirected graph.
        TreeSet<Edge> treeSet = new TreeSet<>(new EdgeComparator());
        int numV = graph.getNumV();
        for (int i = 0; i < numV; ++i) {    // Initialize TreeSet with all edges.
            Iterator<Edge> edgeIterator = graph.edgeIterator(i);
            while (edgeIterator.hasNext()) {
                Edge edge = edgeIterator.next();
                int v1 = edge.getSource();
                int v2 = edge.getDest();
                treeSet.add(new Edge(Math.min(v1, v2), Math.max(v1, v2), edge.getWeight())); // ONEMLI**: Undirected graph icin ayni edge'i 2 kere eklememek uzere bu sekilde edge ekliyırum.
            }
        }

        Edge[] result = new Edge[numV-1];
        Subset[] subsets = new Subset[numV];
        for (int i = 0; i < numV; ++i) {
            subsets[i] = new Subset(i, 0);
        }

        int addedEdges = 0;
        int i = 0;
        while (addedEdges < numV - 1) {
            Edge nextEdge = treeSet.first(); // Get the next smallest edge.
            int x = findRoot(subsets, nextEdge.getSource());
            int y = findRoot(subsets, nextEdge.getDest());
        
            if (x != y) {   // Eger ki ayni set icinde degillerse (cycle kontrolu yapmis oluyoruz burda), o zaman bunların setlerini birlestir.
                result[addedEdges] = nextEdge;
                ++addedEdges;
                union(subsets, x, y);
            }
            ++i;
        }
        return result;
    }

    // PATH COMPRESSION
    // Finds the root of a given node(index).
    // It checks if the current node(index) is the root, return if it does.
    // Otherwise, calls the next recursive method with the parent of this, and assigns the returned parent to this node's parent for path compression.
    private static int findRoot(Subset[] subsets, int i) {
        if (subsets[i].parent == i) {
            return i;
        }
        subsets[i].parent = findRoot(subsets, subsets[i].parent); // PATH COMPRESSION ICIN YAPILIR, BOYLECE BIR SONRAKI FINDROOT CAGRISINDA AYNI NODE ICIN O(1) TIME'DA RESULT VERIR.
        return subsets[i].parent;
    }

    // UNION BY RANK
    private static void union(Subset[] subsets, int x, int y) {
        int rootX = findRoot(subsets, x);
        int rootY = findRoot(subsets, y);

        if (subsets[rootY].rank < subsets[rootX].rank) {
            subsets[rootY].parent = rootX;
        }
        else if (subsets[rootX].rank < subsets[rootY].rank) {
            subsets[rootX].parent = rootY;
        }
        else {
            subsets[rootY].parent = rootX;
            ++subsets[rootX].rank; // ONEMLI**: Eger ki rank'ler esitse, birini digerine eklersin. Ekledigin node'un ranki artar.
        }
    }
}
