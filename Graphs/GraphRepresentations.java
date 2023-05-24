public class GraphRepresentations {
    
    private static int[][] adjacencyMatrix = {
        {0,2,4,0},
        {0,0,0,3},
        {0,0,0,0},
        {0,0,0,0}
    };
    
    public static void main(String[] args) {
        // Adjacency Matrix:
        
        // Graph'in undirected olup olmadigini bulma: Matrix'in simetrik olup olmadigina bakilir.
        // Simetrik ise undirected, degilse directed graphtir.

        // find all edges / iterate through edges
        for (int i = 0; i < adjacencyMatrix.length; ++i) {
            for (int j = 0; j < adjacencyMatrix.length; ++j) {
                if (adjacencyMatrix[i][j] != 0) {
                    System.out.println("Edge with weight: " + adjacencyMatrix[i][j]);
                }
            }
        }

        // if we know the two vertices of an edge, then random access is O(1)
        // checking if there is a connection between two given vertices is in O(1)
        System.out.println(adjacencyMatrix[0][2]);

        // Adjacency List Representation:


    }
}