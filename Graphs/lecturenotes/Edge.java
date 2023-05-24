package lecturenotes;

public class Edge {
    private int source;
    private int dest;
    private double weight;

    public Edge(int source, int dest) {
        this.source = source;
        this.dest = dest;
        this.weight = 1.0;
    }

    public Edge(int source, int dest, double weight) {
        this(source, dest);
        this.weight = weight;
    }

    public int getDest() {
        return dest;
    }

    public int getSource() {
        return source;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (obj.getClass() != Edge.class) return false;
        Edge oth = (Edge) obj;
        if (source == oth.source && dest == oth.dest) return true;
        return false;
    }

    @Override
    public int hashCode() {
        return 17*source + 23*dest;
    }

    @Override
    public String toString() {
        return "" + dest + "-" + source + " (" + weight + ")";
    }
}
