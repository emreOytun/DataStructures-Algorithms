import lecturenotes.Graph;

public class FindMostDistantCities {
    public static void findMostDistantCities(Graph graph) {
        double[] distance = null;

        double maxDistance = -1;
        int source = -1;
        int destination = -1;
        for (int i = 0; i < graph.getNumV(); ++i) {
            distance = DijkstraAlgorithm.dijkstra(graph, i);
            for (int j = 0; j < graph.getNumV(); ++j) {
                if (distance[j] > maxDistance) {
                    source = i;
                    destination = j;
                    maxDistance = distance[j];
                }
            }
        }
        String result = "Largest distance: " + maxDistance + " From: " + source + " To: " + destination;
        System.out.println(result);
    }
}