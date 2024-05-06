public class AStar extends Algorithm {
    @Override
    int calculateCost(String word, Node current, Node target) {
        return current.cost + 1 + OneCharDiff(word, target.word);
    }
}
