package lecturenotes;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class DepthFirstSearch {
    private int discoverIndex;
    private int[] discoveryOrder;
    private int finishIndex;
    private int[] finishOrder;
    private Graph graph;
    private int[] parent;
    private boolean[] visited;
    private Set<Integer> finished; // USED FOR CYCLE DETECTION

    public DepthFirstSearch(Graph graph) {
        this.graph = graph;
        int n = graph.getNumV();
        visited = new boolean[n];
        discoveryOrder = new int[n];
        finishOrder = new int[n];
        parent = new int[n];
        finished = new HashSet<>(); // USED FOR CYCLE DETECTION

        // Initialize parent as -1.
        for (int i = 0; i < n; ++i) {
            parent[i] = -1;
        }

        // There could be seperate graphs in a graph, so check for every node if it is non-visited after dfs.
        for (int i = 0; i < n; ++i) {
            if (!visited[i]) {
                dfs(i);
            }
        }
    }

    public void dfs(int current) {
        visited[current] = true; // Set current node as visited.
        discoveryOrder[discoverIndex++] = current;  // While traversing, first put the current vertex/node to the discoveryOrder.
    
        Iterator<Edge> itr = graph.edgeIterator(current);
        while (itr.hasNext()) {
            int neighbor = itr.next().getDest();
            if (visited[neighbor] && !finished.contains(neighbor)) {  // If node is visited before, but not finished totally. Then this indicates a cycle. // USED FOR CYCLE DETECTION
                System.out.println("CYCLE DETECTED!");
            }
            else if (!visited[neighbor]) {
                parent[neighbor] = current;
                dfs(neighbor);
            }
        }
        finishOrder[finishIndex++] = current; // Just before the recursive call for the current vertex/node is finished, put it into the finishOrder.
        finished.add(current); // Add current node to the finished set // USED FOR CYCLE DETECTION
    }
}
