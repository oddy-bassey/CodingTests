package amazon;

import java.util.LinkedList;

public class BST {
    public static void main(String[] args) {
        System.out.println("Height of the BST is: "+getHeight(getTree()));
        levelOrder(getTree());
    }

    static int getHeight(Node node) {
        if(node == null) {
            return -1;
        }
        int leftNodeHeight = getHeight(node.left);
        int rightNodeHeight = getHeight(node.right);

        return Math.max(leftNodeHeight, rightNodeHeight)+1;
    }

    static void levelOrder(Node root) {

        if(root == null) return;

        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            Node currentNode = queue.poll();

            System.out.print(currentNode.data+" ");
            if(currentNode.left != null) queue.add(currentNode.left);
            if(currentNode.right != null) queue.add(currentNode.right);
        }
    }

    static Node getTree() {
        Node node1 = new Node(null, null, 1);
        Node node2 = new Node(node1, null, 2);
        Node node4 = new Node(null, null, 4);
        Node node7 = new Node(null, null, 7);
        Node node6 = new Node(null, node7, 6);
        Node node5 = new Node(node4, node6, 5);

        return new Node(node2, node5, 3);
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
    }
}
