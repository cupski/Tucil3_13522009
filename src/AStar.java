public class AStar extends Algorithm {
    @Override
    int calculateCost(String word, Node current, Node target) {
        return current.cost + 1 + Math.abs(word.compareTo(target.word));
    }
}
