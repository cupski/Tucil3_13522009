import java.util.*;

public abstract class Algorithm {
    // Memeriksa apakah dua kata berbeda satu karakter
    static boolean isOneCharDiff(String word1, String word2) {
        if (word1.length() != word2.length()) return false;
        int diffCount = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                diffCount++;
                if (diffCount > 1) return false;
            }
        }
        return diffCount == 1;
    }

    

    abstract List<Node> searchRoute(Node startNode, Node endNode, Set<String> dictionary, PriorityQueue<Node> queue);

    abstract int calculateCost(String word, Node current, Node target);

    void generateAdjacentWords(Node current, Node target, Set<String> dictionary, Set<String> visited, PriorityQueue<Node> queue) {
        for(String word: dictionary){
            if (!visited.contains(word) && isOneCharDiff(current.word, word)) {
                Node adjacent = new Node(word);
                int newCost = calculateCost(word, current, target);
                if (newCost < adjacent.cost) {
                    adjacent.cost = newCost;
                    adjacent.parent = current;
                    queue.add(adjacent);
                }
            }
        }
    }
}