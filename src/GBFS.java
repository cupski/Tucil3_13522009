public class GBFS extends Algorithm {
    @Override
    int calculateCost(String word, Node current, Node target) {
        return Math.abs(word.compareTo(target.word));
    }
}
