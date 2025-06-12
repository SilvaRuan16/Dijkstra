package capital.service;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class GraphService {

    private int totalVertices;
    private List<List<Edge>> adjacencyList;

    class Edge {
        int destination;
        double weight;

        Edge(int destination, double weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    public void initializeGraph(int totalVertices) {
        this.totalVertices = totalVertices;
        adjacencyList = new ArrayList<>();
        for (int i = 0; i < totalVertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination, double weight) {
        adjacencyList.get(source).add(new Edge(destination, weight));
        adjacencyList.get(destination).add(new Edge(source, weight));
    }

    public Map<String, Object> findShortestPathDijkstra(int source, int destination, String[] capitalNames) {
        double[] distances = new double[totalVertices];
        int[] previous = new int[totalVertices];
        Arrays.fill(distances, Double.POSITIVE_INFINITY);
        Arrays.fill(previous, -1);
        distances[source] = 0;

        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(
                Comparator.comparingDouble(e -> e.weight)
        );
        priorityQueue.offer(new Edge(source, 0));

        while (!priorityQueue.isEmpty()) {
            Edge current = priorityQueue.poll();
            int currentVertex = current.destination;

            for (Edge neighbor : adjacencyList.get(currentVertex)) {
                int neighborVertex = neighbor.destination;
                double edgeWeight = neighbor.weight;

                if (distances[currentVertex] + edgeWeight < distances[neighborVertex]) {
                    distances[neighborVertex] = distances[currentVertex] + edgeWeight;
                    previous[neighborVertex] = currentVertex;
                    priorityQueue.offer(new Edge(neighborVertex, distances[neighborVertex]));
                }
            }
        }

        List<Integer> path = new ArrayList<>();
        for (int at = destination; at != -1; at = previous[at]) {
            path.add(at);
        }
        Collections.reverse(path);

        Map<String, Object> result = new HashMap<>();

        if (distances[destination] == Double.POSITIVE_INFINITY) {
            result.put("message", "No path between " + capitalNames[source] + " and " + capitalNames[destination]);
            result.put("path", Collections.emptyList());
            return result;
        }

        List<String> pathNames = new ArrayList<>();
        for (int id : path) {
            pathNames.add(capitalNames[id]);
        }

        result.put("distance", distances[destination]);
        result.put("path", pathNames);
        return result;
    }
}
