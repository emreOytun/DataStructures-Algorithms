package lecturenotes;

public abstract class AbstractGraph implements Graph {
    private int numV;
    private boolean directed;

    public AbstractGraph(int numV, boolean directed) {
        this.numV = numV;
        this.directed = directed;
    }

    @Override
    public int getNumV() {
        return numV;
    }

    @Override
    public boolean isDirected() {
        return directed;
    }
}
