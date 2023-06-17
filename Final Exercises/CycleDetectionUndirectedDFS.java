import java.util.Iterator;

import lecturenotes.Edge;
import lecturenotes.Graph;

public class CycleDetectionUndirectedDFS {
    private boolean[] visited;
    
    public boolean cycleDetection(Graph graph) {
        int numV = graph.getNumV();
        visited = new boolean[numV];

        for (int i = 0; i < numV; ++i) {
            if (!visited[i]) {
                boolean result = dfs(graph, i, i);
                if (result) return true;
            }
        }
        return false;
    }

    private boolean dfs(Graph graph, int parent, int node) {
        visited[node] = true;

        Iterator<Edge> it = graph.edgeIterator(node);
        while (it.hasNext()) {
            int neighbor = it.next().getDest();
            if (!visited[neighbor]) {
                boolean result = dfs(graph, node, neighbor);
                if (result) return true;
            }
            else if (parent != neighbor) {
                return true;
            }
        }
        return false;
    }
}
