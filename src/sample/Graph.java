package sample;

import java.util.*;

class Graph {
    int[] dist;
    Set<Integer> visited;
    PriorityQueue<Node> priorityQueue;
    int vertices;
    List<List<Node>> adjacencyList;

    public Graph(int vertices) {
        this.vertices = vertices;
        dist = new int[vertices];
        visited = new HashSet<>();
        priorityQueue = new PriorityQueue<>(vertices, new Node());
    }

    public int getVertices() {
        return vertices;
    }

    public void setVertices(int vertices) {
        this.vertices = vertices;
    }

    public void dijkstra(List<List<Node>> adjacencyList, int sourceVertex) {
        this.adjacencyList = adjacencyList;

        for (int i = 0; i < vertices; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        priorityQueue.add(new Node(sourceVertex, 0));

        dist[sourceVertex] = 0;

        while (visited.size() != vertices - sourceVertex) {

            int removed = priorityQueue.remove().node;


            visited.add(removed);
            neighborOfVisitedNode(removed);
        }
    }

    private void neighborOfVisitedNode(int removed) {
        int edgeDistance = -1;
        int newDistance = -1;


        for (int i = 0; i < adjacencyList.get(removed).size(); i++) {
            Node vertex = adjacencyList.get(removed).get(i);


            if (!visited.contains(vertex.node)) {
                edgeDistance = vertex.cost;
                newDistance = dist[removed] + edgeDistance;


                if (newDistance < dist[vertex.node]) {
                    dist[vertex.node] = newDistance;
                }

                priorityQueue.add(new Node(vertex.node, dist[vertex.node]));
            }
        }
    }


}

