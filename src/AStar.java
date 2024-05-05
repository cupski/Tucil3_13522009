import java.util.*;

public class AStar extends Algorithm {
    @Override
    List<Node> searchRoute(Node startNode, Node endNode, Set<String> dictionary, PriorityQueue<Node> queue) {
        Set<String> visited = new HashSet<>();
        startNode.cost = 0;

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            visited.add(current.word);

            if (current.word.equals(endNode.word)) {
                List<Node> path = new ArrayList<>();
                while (current != null) {
                    path.add(current);
                    current = current.parent;
                }
                Collections.reverse(path);
                return path;
            }

            generateAdjacentWords(current, endNode, dictionary, visited, queue);
        }

        return null;
    }

    @Override
    int calculateCost(String word, Node current, Node target) {
        return current.cost + 1 + Math.abs(word.compareTo(target.word));
    }
}
