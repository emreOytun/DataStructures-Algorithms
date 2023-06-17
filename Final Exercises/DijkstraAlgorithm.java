import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.HashMap;

import lecturenotes.Edge;
import lecturenotes.Graph;

public class DijkstraAlgorithm {
    
    // O(V^2)
    public static double[] dijkstra(Graph graph, int start) {
        // Parent map, distance map ve remainingSet'i initiaalzie et.
        int numV = graph.getNumV();
        int[] parent = new int[numV];
        double[] distance = new double[numV];
        HashSet<Integer> remainingV = new HashSet<>();
        
        for (int i = 0; i < numV; ++i) {
            parent[i] = start;
            distance[i] = Double.MAX_VALUE;    // Baslangic distance'larini sonsuz olarka initialize et.
            remainingV.add(Integer.valueOf(i));
        }

        // Baslangic vertex'inin distance'i 0 olacak.
        distance[start] = 0;
        while (!remainingV.isEmpty()) {
            // Kalanlar arasindan min distance'a sahip vertex'i bul.
            int min_node = -1;
            double min = Double.MAX_VALUE;
            for (Integer node : remainingV) {
                if (distance[node] <= min) {
                    min_node = node;
                }
            }
            remainingV.remove(min_node); // Bu vertex'i visited olarak isaretle.

            Iterator<Edge> it = graph.edgeIterator(min_node);
            while (it.hasNext()) {
                Edge edge = it.next();
                int neighbor = edge.getDest();
                if (remainingV.contains(neighbor)) {
                    double newDistance = min + edge.getWeight();
                    if (newDistance < distance[neighbor]) {
                        parent[neighbor] = min_node;
                        distance[neighbor] = newDistance;
                    }
                }
            }
        }
        return distance;
    }
    
    // This is the implementation of the dijkstra algorithm with a Node class and using a priority queue.
    // O(VlogV)
    public static HashMap<Node, Double> dijkstra2(Graph graph, Node startNode, Node endNode) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        HashMap<Node, Double> distanceMap = new HashMap();
        HashMap<Node, Node> parentMap = new HashMap<>();
        HashSet<Node> visitedSet = new HashSet<>();

        for (Node node : edges.keySet()) {
            distanceMap.put(node, Double.POSITIVE_INFINITY);
        }

        startNode.setDistance(0);
        distanceMap.put(startNode, 0);
        pq.add(startNode);
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            visitedSet.add(node);

            Iterator<Edge> it = edges.get(node).iterator();
            while (it.hasNext()) {
                Edge edge = it.next();
                Node neighbor = edge.getDest();
                if (!visitedSet.contains(neighbor)) {
                    double newDistance = distanceMap.get(node) + edge.getWeight();
                    if (newDistance < distanceMap.get(neighbor)) {
                        parentMap.put(neighbor, node);
                        distanceMap.put(neighbor, newDistance);
                        
                        neighbor.setDistance(newDistance);
                        pq.remove(neighbor);
                        pq.add(neighbor);
                    }
                }
            }
        }
        return distanceMap;
    }
    
}
