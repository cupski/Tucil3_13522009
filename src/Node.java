import java.util.*;

public class Node {
        String word;
        List<Node> neighbors;
        int cost;
        Node parent;

        public Node(String word) {
            this.word = word;
            this.neighbors = new ArrayList<>();
            this.cost = Integer.MAX_VALUE; // Set biaya awal ke nilai maksimum
            this.parent = null;
        }
}

