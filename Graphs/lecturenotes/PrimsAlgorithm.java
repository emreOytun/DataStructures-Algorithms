package lecturenotes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class PrimsAlgorithm {

    // Comparator class to compare the edges according to their weights.
    private static class EdgeComparator implements Comparator<Edge> {
        public int compare(Edge edge1, Edge edge2) {
            if (edge1.getWeight() == edge2.getWeight()) return 0;
            if (edge1.getWeight() < edge2.getWeight()) return -1;
            return 1;
        }
    }

    public static ArrayList<Edge> primsAlgorithm(Graph graph, int start) {
        ArrayList<Edge> result = new ArrayList<>();
        int numV = graph.getNumV();
        
        // remainingV set'ini initialize et.
        Set<Integer> remainingV = new HashSet<>(numV);
        for (int i = 0; i < numV; ++i) {
            remainingV.add(i);
        }
        remainingV.remove(start);

        Queue<Edge> pq = new PriorityQueue<>(numV, new EdgeComparator());
        int current = start;
        while (remainingV.size() != 0) {

            // Edge'ler uzerinden iterate et, dest vertex halen islenmekte ise(unvisited ise) bu edge'i pq'a ekle.
            Iterator<Edge> it = graph.edgeIterator(current);
            while (it.hasNext()) {
                Edge edge = it.next();
                int dest = edge.getDest();
                if (remainingV.contains(dest)) {
                    pq.add(edge);
                }
            }

            // Dest vertex'i unvisited olan min edge'i bul.
            int dest = -1;
            Edge edge = null;
            do {
                edge = pq.poll();
                dest = edge.getDest();
            } while (!remainingV.contains(dest));
            
            result.add(edge);
            current = dest;
            remainingV.remove(dest);
        }
        return result;
    }

}