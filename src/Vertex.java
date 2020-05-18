import java.util.Objects;

public class Vertex {
    private final String lable;
    private boolean visited;

    public Vertex(String lable) {
        this.lable = lable;
    }

    public String getLable() {
        return lable;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "lable='" + lable + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return Objects.equals(lable, vertex.lable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lable);
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean getVisited() {
        return visited;
    }
}
