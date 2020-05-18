public class Trener {
    public static void main(String[] args) {

        testBfs();
    }

    private static void testBfs() {
        Graph graph = new Graph(10);
        graph.addVertex("Москва");
        graph.addVertex("Тула");
        graph.addVertex("Рязань");
        graph.addVertex("Калуга");
        graph.addVertex("Липецк");
        graph.addVertex("Тамбов");
        graph.addVertex("Орел");
        graph.addVertex("Воронеж");
        graph.addVertex("Саратов");
        graph.addVertex("Курск");



        graph.addEdges("Москва", "Тула", "Рязань", "Калуга");
        graph.addEdges("Липецк", "Тула","Воронеж");
        graph.addEdges("Тамбов", "Рязань", "Саратов");
        graph.addEdges("Орел", "Калуга", "Курск");
        graph.addEdges("Воронеж", "Липецк", "Саратов", "Курск");


        graph.bfs("Москва","Воронеж");
    }
    public void findShortWay(Graph graph){

    }


    }
