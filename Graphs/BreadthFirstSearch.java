import java.util.Queue;
import java.util.LinkedList;

public class BreadthFirstSearch {
    
    public void traverse(Vertex root) {
        Queue<Vertex> queue = new LinkedList<>();
        root.setVisited(true);
        queue.offer(root);

        while (!queue.isEmpty()) {
            Vertex curVertex = queue.poll();
            System.out.println("Current vertex: " + curVertex);

            for (Vertex v : curVertex.getAdjacencyList()) {
                if (!v.isVisited()) {
                    v.setVisited(true);
                    queue.offer(v);
                }
            }
        }
    }

    public static void main(String[] args) {
        BreadthFirstSearch bfs = new BreadthFirstSearch();

        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex d = new Vertex("D");
        Vertex e = new Vertex("E");
        Vertex f = new Vertex("F");
        Vertex g = new Vertex("G");
        Vertex h = new Vertex("H");

        a.addNeighbor(b);
        a.addNeighbor(f);
        a.addNeighbor(g);

        b.addNeighbor(a);
        b.addNeighbor(c);
        b.addNeighbor(d);

        c.addNeighbor(b);

        d.addNeighbor(b);
        d.addNeighbor(e);

        f.addNeighbor(a);

        g.addNeighbor(a);
        g.addNeighbor(h);

        h.addNeighbor(g);

        bfs.traverse(a);
    }

}