
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.w3c.dom.Node;

import lecturenotes.Edge;
import lecturenotes.Graph;

public class BFS {

    // Node'lar int olarak temsil edilirse.
    public static int[] bfs(Graph graph, int start) {
        Queue<Integer> queue = new LinkedList<>();

        // Parent array ve visited array'i initialize et.
        int numV = graph.getNumV();
        int[] parent = new int[numV];
        boolean[] visited = new boolean[numV];
        for (int i = 0; i < numV; ++i) {
            parent[i] = -1;
            visited[i] = false;
        }

        // Queue'ya start node'u koy ve visited olarak isaretle.
        queue.offer(start);
        parent[start] = start;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            visited[node] = true;
            
            // Komsular uzerinden gez
            Iterator<Edge> it = graph.edgeIterator(node);
            while (it.hasNext()) {
                // Visited olmayan komsuyu queue'ya ekle. Parent'i set et.
                int neighbor = it.next().getDest();
                if (visited[neighbor] == false) {
                    queue.offer(neighbor);
                    parent[neighbor] = node;
                }
            }
            
            // Adjacency matrix verdiyse komsular uzerinden gezme:
            /* for (int i = 0; i < numV; ++i) {
                if (matrix[node][i] != 0 && visited[i] == false) {
                    int neighbor = i;
                    if (visited[neighbor] == false) {
                        queue.offer(neighbor);
                        parent[neighbor] = node;
                    }
                }
            }  */
        }
        return parent;
    }

    // Node'lar Node olarak temsil edilirse.
    public static List<Node> bfs(Node start) {
        Queue<Node> queue = new LinkedList<>();

        HashMap<Node, Node> parent = new HashMap<>();
        HashSet<Node> visited = new HashSet<>();
        for (Node node : edges.keySet()) {
            parent.put(node, start);
        }          
        
        queue.offer(start);
        parent.put(start, start);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            visited.add(node);

            Iterator<Edge> it = edges.get(node).iterator();
            while (it.hasNext()) {
                Node dest = it.next().getDest();
                if (!visited.contains(node)) {
                    queue.offer(dest);
                    parent.put(dest, node);
                }
            }
        }
    }
}
