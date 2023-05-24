import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private String name;
    private boolean visited;
    private List<Vertex> adjacencyList = new ArrayList<>();

    public Vertex(String name) {
        this.name = name;
        this.adjacencyList = new ArrayList<>();
    }

    public List<Vertex> getAdjacencyList() {
        return adjacencyList;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isVisited() {
        return visited;
    }

    public void addNeighbor(Vertex vertex) {
        adjacencyList.add(vertex);
    }

    public void showNeighbors() {
        for (Vertex v : adjacencyList) {
            System.out.println(v);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
