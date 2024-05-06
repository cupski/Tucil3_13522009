public class GBFS extends Algorithm {

    @Override
    int calculateCost(String word, Node current, Node target) {
        return OneCharDiff(word, target.word);
    }
}
