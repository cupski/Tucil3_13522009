import java.util.*;

public class Node {
        String word;
        List<Node> neighbors;
        int cost;
        Node parent;

        public Node(String word) {
            this.word = word;
            this.neighbors = new ArrayList<>();
            this.cost = 0;
            this.parent = null;
        }
}

