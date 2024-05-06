import java.util.*;

public abstract class Algorithm {

    // Memeriksa apakah dua kata berbeda satu karakter
    static boolean isOneCharDiff(String word1, String word2) {
        int diffCount = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                diffCount++;
                if (diffCount > 1) return false;
            }
        }
        return true;
    }

    

    Object[] searchRoute(Node startNode, Node endNode, Set<String> dictionary, PriorityQueue<Node> queue) {
        Set<String> visited = new HashSet<>();
        startNode.cost = 0;
        int count = 0;
        long startTime = System.currentTimeMillis();


        while (!queue.isEmpty()) {
            count++;
            Node current = queue.poll();
            visited.add(current.word);

            if (current.word.equals(endNode.word)) {
                List<Node> path = new ArrayList<>();
                while (current != null) {
                    path.add(current);
                    current = current.parent;
                }
                Collections.reverse(path);
                long endTime = System.currentTimeMillis();
                long duration = endTime - startTime;
                return new Object[]{path, count, duration};
            }

            generateAdjacentWords(current, endNode, dictionary, visited, queue);
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        return new Object[]{null, count, duration};
    }

    // void generateAdjacentWords(Node current, Node target, Set<String> dictionary, Set<String> visited, PriorityQueue<Node> queue) {
    //     for(String word: dictionary){
    //         if (!visited.contains(word) && isOneCharDiff(current.word, word)) {
    //             Node adjacent = new Node(word);
    //             int newCost = calculateCost(word, current, target);
    //             adjacent.cost = newCost;
    //             adjacent.parent = current;
    //             queue.add(adjacent);
    //         }
    //     }
    // }
    void generateAdjacentWords(Node current, Node target, Set<String> dictionary, Set<String> visited, PriorityQueue<Node> queue) {
        int wordLength = current.word.length();
        

        for (int pos = 0; pos < wordLength; ++pos) {
            char origChar = current.word.charAt(pos);

            // Replace the current character with every possible lowercase alphabet character
            for (char c = 'a'; c <= 'z'; ++c) {
                if (c == origChar) continue; // Skip if it's the same character
                
                StringBuilder newWordBuilder = new StringBuilder(current.word);
                newWordBuilder.setCharAt(pos, c);
                String newWord = newWordBuilder.toString();

                // If the new word is in the dictionary and has not been visited
                if (dictionary.contains(newWord) && !visited.contains(newWord)) {
                    Node adjacent = new Node(newWord);
                    int newCost = calculateCost(newWord, current, target);
                    adjacent.cost = newCost;
                    adjacent.parent = current;
                    queue.add(adjacent);
                    visited.add(newWord);
                }
            }

            // Restore the original character at the current position
            current.word = current.word.substring(0, pos) + origChar + current.word.substring(pos + 1);
        }
    }


    static Integer OneCharDiff(String word1, String word2) {
        int diffCount = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                diffCount++;
            }
        }
        return diffCount;
    }

    abstract int calculateCost(String word, Node current, Node target);
}
