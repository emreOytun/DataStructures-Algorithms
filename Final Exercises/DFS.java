
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import lecturenotes.Edge;
import lecturenotes.Graph;

public class DFS {
    
    private int[] parent;
    private boolean[] visited;
    private int[] finishOrder;
    private int finishIdx;

    public int[] dfs(Graph graph) {
        // Parent, visited ve finishOrder arraylerini initialize et.
        int numV = graph.getNumV();
        parent = new int[numV];
        visited = new boolean[numV];
        finishOrder = new int[numV];

        for (int i = 0; i < numV; ++i) {
            parent[i] = -1;
            visited[i] = false;
        }

        // Herbir visited olmayan node icin dfs_rec cagir.
        for (int i = 0; i < numV; ++i) {
            if (!visited[i]) {
                dfs_rec(graph, i);
            }
        } 
        return parent;
    }

    public void dfs_rec(Graph graph, int node) {
        // Node'u visited olarak isaretle.
        visited[node] = true;

        // Edge'ler uzerinde gez, komsularini bul. Visited olmayanlar icin dfs_rec cagir.
        Iterator<Edge> it = graph.edgeIterator(node);
        while (it.hasNext()) {
            int neighbor = it.next().getDest();
            if (!visited[neighbor]) {
                parent[neighbor] = node;
                dfs_rec(graph, neighbor);
            }
        }
        // En son finishOrder'a ekle.
        finishOrder[finishIdx++] = node;
    }


    private HashMap<Node, Node> parent = new HashMap<>();
    private HashSet<Node> visited = new HashSet<>();
    private ArrayList<Node> finishOrder = new ArrayList<>();

    public void dfs(Graph graph) {
        // Parent'i initialize et. Visited ve finishOrder'i ayrica init etmeye gerek yok.
        int numV = graph.getNumV();
        for (Node node : edges.keySet()) {
            parent.put(node, null);
        }

        for (Node node: edges.keySet()) {
            if (!visited.contains(node)) {
                dfs_rec(graph, node);
            }
        }
    }

    public void dfs_rec(Graph graph, Node node) {
        visited.add(node);

        Iterator<Edge> it = edges.get(node);
        while (it.hasNext()) {
            Node neighbor = it.next().getDest();
            if (!visited.contains(neighbor)) {
                parent.put(neighbor, node);
                dfs_rec(graph, neighbor);
            }
        }
        finishOrder.add(node);
    }
}
