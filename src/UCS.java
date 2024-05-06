public class UCS extends Algorithm {
    @Override
    int calculateCost(String word, Node current, Node target) {
        return current.cost + 1;
    }
}