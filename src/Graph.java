
import java.util.*;

public class Graph {
    private final List<Vertex> vertexList;
    private final boolean[][] addMat;

    private int size;

    public Graph(int maxVertexCount) {
        this.vertexList = new ArrayList<>(maxVertexCount);
        this.addMat = new boolean[maxVertexCount][maxVertexCount];
    }
    public void addVertex(String label){
        vertexList.add(new Vertex(label));
    }
    public int getSize(){
        return vertexList.size();
    }
    private void addEdge(String start , String finish){
        int startIndex = indexOf(start);
        int finishIndex = indexOf(finish);
        if(startIndex == -1 || finishIndex == -1){
            throw new IllegalArgumentException("invalid label for edge");
        }
        addMat[startIndex][finishIndex]= true;
        addMat[finishIndex][startIndex] = true;

    }
    public void addEdges (String start , String finish , String... other){
        addEdge(start,finish);
        for (String another : other) {
            addEdge(start,another);
        }
    }

    private int indexOf(String vertexLabel) {
        for (int i = 0; i <vertexList.size() ; i++) {
            if(vertexLabel.equals(vertexList.get(i).getLable())){
                return i;
            }
        }
        return -1;
    }
    private void displayVertex(Vertex vertex){
        System.out.println(vertex);
    }
    public void display (){
        for (int i = 0; i <getSize() ; i++) {
            System.out.print(vertexList.get(i));
            for (int j = 0; j < getSize(); j++) {
                if (addMat[i][j]){
                    System.out.print(" -> " + vertexList.get(j));
                }
            }
            System.out.println();
        }
    }
    public void bfs(String startLabel,String finishLabel) {//обход в ширину
        int startIndex = indexOf(startLabel);
        int finishIndex = indexOf(finishLabel);
        if (startIndex == -1 || finishIndex == -1) {
            throw new IllegalArgumentException("Invalid start label");
        }
        Queue<Vertex> queue = new LinkedList<>();
        Vertex vertex = vertexList.get(startIndex);
        visitVertex(vertex, queue);
        while (!queue.peek().getLable().equals(finishLabel)) {
            vertex = getNearVisitedVertex(queue.peek());
            if (vertex != null) {
                visitVertex(vertex, queue);
            } else {
                queue.poll();
            }
        }
        Stack<Vertex> bestWay = new Stack<>();
        vertex = vertexList.get(finishIndex);
        while (vertex!= null){
            bestWay.add(vertex);
            vertex = vertex.getPrevious();
        }
        System.out.print("Best way from " + startLabel +" to " + finishLabel);
        while (!bestWay.isEmpty()){
            System.out.print(" -> " +bestWay.pop());
        }
        queue.clear();
        resetVertexState();
    }
    public void dfs(String startLabel){ // обход элементов в глубину
        int startIndex = indexOf(startLabel);
        if(startIndex == -1 ){
            throw new IllegalArgumentException("Invalid start label");
        }
        Stack<Vertex> stack = new Stack<>();
        Vertex vertex = vertexList.get(startIndex);
        visitVertex(vertex, stack);
        while (!stack.isEmpty()){
            vertex = getNearVisitedVertex(stack.peek());
            if(vertex != null){
                visitVertex(vertex,stack);
            }else {
                stack.pop();
            }
        }
        resetVertexState();
    }

    private void resetVertexState() {
        for (Vertex vertex : vertexList) {
            vertex.setVisited(false);
        }
    }

    private Vertex getNearVisitedVertex(Vertex peek) {
        int peekIndex = vertexList.indexOf(peek);
        for (int i = 0; i < getSize(); i++) {
            if(addMat[peekIndex][i] && !vertexList.get(i).getVisited()){
                vertexList.get(i).setPrevious(peek);
                return vertexList.get(i);
            }
        }
        return null;
    }

    private void visitVertex(Vertex vertex, Stack<Vertex> stack) {
        displayVertex(vertex);
        stack.push(vertex);
        vertex.setVisited(true);
    }

    private void visitVertex(Vertex vertex, Queue<Vertex> queue) {
        displayVertex(vertex);
        queue.offer(vertex);
        vertex.setVisited(true);

    }

}
