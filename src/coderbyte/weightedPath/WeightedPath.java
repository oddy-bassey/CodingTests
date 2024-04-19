package coderbyte.weightedPath;

import java.util.*;

public class WeightedPath {

    public static String weightedPath(String[] strArr) {
        // code goes here

        // step 1: Parse the input
        int numNodes = Integer.parseInt(strArr[0]);
        String[] nodes = Arrays.copyOfRange(strArr, 1, numNodes+1);
        Map<String, List<Edge>> graph = new HashMap<>();
        for(int i = numNodes+1; i<strArr.length; i++) {
            String[] parts = strArr[i].split("\\|");
            String node1 = parts[0];
            String node2 = parts[1];
            int weight = Integer.parseInt(parts[2]);
            graph.computeIfAbsent(node1, k -> new ArrayList<>()).add(new Edge(node2, weight));
            graph.computeIfAbsent(node2, k -> new ArrayList<>()).add(new Edge(node1, weight));
        }

        // Step 2: finding shortest path using Dijkstra's algorithm
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));
        for (String node: nodes) {
            distances.put(node, Integer.MAX_VALUE);
            previous.put(node, null);
        }
        distances.put(nodes[0], 0);
        priorityQueue.offer(new Node(nodes[0], 0));

        while (!priorityQueue.isEmpty()) {
            Node current = priorityQueue.poll();
            if (current.distance > distances.get(current.name)) continue;
            for (Edge neighbour : graph.getOrDefault(current.name, Collections.emptyList())) {
                int newDistance = distances.get(current.name) + neighbour.weight;
                if(newDistance < distances.get(neighbour.destination)) {
                    distances.put(neighbour.destination, newDistance);
                    previous.put(neighbour.destination, current.name);
                    priorityQueue.offer(new Node(neighbour.destination, newDistance));
                }
            }
        }

        // Step 3: reconstruct the shortest path
        List<String> path = new ArrayList<>();
        String current = nodes[numNodes - 1];
        while (current != null) {
            path.add(current);
            current = previous.get(current);
        }
        Collections.reverse(path);

        return path.get(0).equals(nodes[0]) ? String.join("-", path) : "-1";
    }

    static class Edge {
        String destination;
        int weight;

        public Edge(String destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    static class Node {
        String name;
        int distance;

        public Node(String name, int distance) {
            this.name = name;
            this.distance = distance;
        }
    }

    public static void main (String[] args) {
        String[] strArr = {"4", "A", "B", "C", "D", "A|B|1", "B|D|9", "B|C|3", "C|D|4"};
        System.out.println(weightedPath(strArr));
    }

}