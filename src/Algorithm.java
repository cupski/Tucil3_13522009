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

    void generateAdjacentWords(Node current, Node target, Set<String> dictionary, Set<String> visited, PriorityQueue<Node> queue) {
        int wordLength = current.word.length();
        

        for (int pos = 0; pos < wordLength; ++pos) {
            char origChar = current.word.charAt(pos);

            // Tukar current karakter dengan semua kemungkinan huruf alfabet
            for (char c = 'a'; c <= 'z'; ++c) {
                if (c == origChar) continue; // skip jika karakternya sama
                
                StringBuilder newWordBuilder = new StringBuilder(current.word);
                newWordBuilder.setCharAt(pos, c);
                String newWord = newWordBuilder.toString();

                // pastikan kata baru ada di dictionary dan belum dikunjungi
                if (dictionary.contains(newWord) && !visited.contains(newWord)) {
                    Node adjacent = new Node(newWord);
                    int newCost = calculateCost(newWord, current, target);
                    adjacent.cost = newCost;
                    adjacent.parent = current;
                    queue.add(adjacent);
                    visited.add(newWord);
                }
            }

            //Kembalikan Kata Sebelumnya
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
