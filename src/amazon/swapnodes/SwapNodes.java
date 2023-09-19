package amazon.swapnodes;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class SwapNodes {
    public static void main(String[] args) {


        List<Integer> queries = new ArrayList<>();
        queries.add(1);
        queries.add(1);

        swapNodes(getIndexes2(), queries);
    }

    static List<List<Integer>> getIndexes1() {
        List<List<Integer>> indexes = new ArrayList<>();
        indexes.add(Arrays.asList(2, 3));
        indexes.add(Arrays.asList(4, -1));
        indexes.add(Arrays.asList(5, -1));
        indexes.add(Arrays.asList(6, -1));
        indexes.add(Arrays.asList(7, 8));
        indexes.add(Arrays.asList(-1, 9));
        indexes.add(Arrays.asList(-1, -1));
        indexes.add(Arrays.asList(10, 11));
        indexes.add(Arrays.asList(-1, -1));
        indexes.add(Arrays.asList(-1, -1));
        indexes.add(Arrays.asList(-1, -1));

        return indexes;
    }

    static List<List<Integer>> getIndexes2() {
        List<List<Integer>> indexes = new ArrayList<>();
        indexes.add(Arrays.asList(2, 3));
        indexes.add(Arrays.asList(-1, -1));
        indexes.add(Arrays.asList(-1, -1));

        return indexes;
    }

    public static List<List<Integer>> swapNodes(List<List<Integer>> indexes, List<Integer> queries) {
        // Write your code here
        List<List<Integer>> inOrderLists = new ArrayList<>();

        for (Integer query : queries) {
            // balancing list index since indexing starts from 0
            int rightNodeLevelIndex = 0;
            int leftNodeLevelIndex = 0;
            if(query>1) {
                rightNodeLevelIndex = (query - 1) + query;
                leftNodeLevelIndex = rightNodeLevelIndex-1;

                --rightNodeLevelIndex;
                --leftNodeLevelIndex;

                //swapping leftNode in current node level
                int leftNodeLeftValue = indexes.get(leftNodeLevelIndex).get(0);
                indexes.get(leftNodeLevelIndex).set(0, indexes.get(leftNodeLevelIndex).get(1));
                indexes.get(leftNodeLevelIndex).set(1, leftNodeLeftValue);

                //swapping rightNode in current node level
                int rightNodeLeftValue = indexes.get(rightNodeLevelIndex).get(0);
                indexes.get(rightNodeLevelIndex).set(0, indexes.get(rightNodeLevelIndex).get(1));
                indexes.get(rightNodeLevelIndex).set(1, rightNodeLeftValue);
            } else {
                int nodeLevelIndex = query-1;
                int leftValue = indexes.get(nodeLevelIndex).get(0);
                indexes.get(nodeLevelIndex).set(0, indexes.get(nodeLevelIndex).get(1));
                indexes.get(nodeLevelIndex).set(1, leftValue);
            }

            // print in-order
            List<Integer> inOrderList = new ArrayList<>();
            inOrder(createTree(indexes), inOrderList);
            inOrderLists.add(inOrderList);

            System.out.println();
        }

        return inOrderLists;
    }

    static void inOrder(Node node, List<Integer> list) {
        if (node == null)
            return;

        // traverse the left child
        inOrder(node.left, list);

        // traverse the root node
        System.out.print(node.data + " ");
        list.add(node.data);

        // traverse the right child
        inOrder(node.right, list);
    }

    static Node createTree(List<List<Integer>> indexes) {

        Node rootNode = new Node(null, null, 1);
        LinkedList<Node> nodeQueue = new LinkedList<>();
        nodeQueue.add(rootNode);

        int nodesProcessed = 0;

        while(!nodeQueue.isEmpty()) {

            Node currentNode = nodeQueue.poll();
            List<Integer> currentIndex = indexes.get(nodesProcessed);

            Node leftNode = currentIndex.get(0) == -1? null : new Node(null, null, currentIndex.get(0));
            Node rightNode = currentIndex.get(1) == -1? null : new Node(null, null, currentIndex.get(1));

            currentNode.setLeft(leftNode);
            currentNode.setRight(rightNode);

            if(leftNode != null) nodeQueue.add(leftNode);
            if(rightNode != null) nodeQueue.add(rightNode);

            nodesProcessed++;
        }

        return rootNode;
    }

    static class Node {
        public Node left;
        public Node right;
        public int data;

        public Node(Node left, Node right, int data) {
            this.left = left;
            this.right = right;
            this.data = data;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}
/*
public static void mains(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> indexes = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                indexes.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int queriesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> queries = IntStream.range(0, queriesCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        List<List<Integer>> result = swapNodes(indexes, queries);

        result.stream()
                .map(
                        r -> r.stream()
                                .map(Object::toString)
                                .collect(joining(" "))
                )
                .map(r -> r + "\n")
                .collect(toList());

        bufferedReader.close();
        //bufferedWriter.close();
    }
 */