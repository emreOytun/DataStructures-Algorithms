package lecturenotes;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch {

    public static int[] breadthFirstSearch(Graph graph, int start) {
        // Initialize an empty linked list for queue.
        Queue<Integer> queue = new LinkedList<>();
        
        // Initialize the result array
        int[] result = new int[graph.getNumV()];
        for (int i = 0; i < result.length; ++i) {
            result[i] = -1;
        }

        // Initialize an array for the vertices that indicates if a vertex is visited before or not.
        // It could be an HashSet if vertices have name.
        boolean[] visited = new boolean[graph.getNumV()];
        
        // Mark the start vertex as visited.
        visited[start] = true;
        queue.offer(start);
        
        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();
            Iterator<Edge> it = graph.edgeIterator(currentVertex);
            while (it.hasNext()) {
                Edge edge = it.next();
                int neighborVertex = edge.getDest();
                if (!visited[neighborVertex]) {
                    visited[neighborVertex] = true;
                    queue.offer(neighborVertex);
                    result[neighborVertex] = currentVertex; // Insert the edge (current,neighbor) into the tree.
                }
            }
        }
        return result;
    }

}