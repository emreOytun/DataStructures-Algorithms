import java.util.HashSet;
import java.util.Iterator;

import lecturenotes.Edge;
import lecturenotes.Graph;

public class CycleDetectionDirected {
    private HashSet<Integer> visited;
    private HashSet<Integer> finished;
    
    public boolean cycleDetection(Graph graph) {
        visited = new HashSet<>();
        finished = new HashSet<>();

        // Check if there is a cycle by running dfs on all unvisited nodes.
        int numV = graph.getNumV();
        for (int i = 0; i < numV; ++i) {
            if (!visited.contains(i)) {
                boolean isCycle = dfs(graph, 0);
                if (isCycle) return true;
            }
        }
        return false;
    }

    private boolean dfs(Graph graph, int node) {
        visited.add(node);
        
        Iterator<Edge> it = graph.edgeIterator(node);
        while (it.hasNext()) {
            int neighbor = it.next().getDest();
            if (visited.contains(neighbor) && !finished.contains(neighbor)) {
                return true; // Meaning there is a cycle.
            }
            if (!visited.contains(neighbor)) {
                boolean isCycle = dfs(graph, neighbor);
                if (isCycle) return true;
            }
        } 
        return false;
    }
}
