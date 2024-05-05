import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Menu {
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        Set<String> dictionary = readDictionaryFromFile("src/word.txt");

        while (true) {
            ListMenu();
            int choice = getChoice(scanner);

            if (choice == 4) {
                System.out.println("Exiting...");
                break;
            }

            String startWord = getStartWord(scanner);
            String endWord = getEndWord(scanner);

            while(startWord.length() != endWord.length()) {
                System.out.println("Make Sure both words have the same length :(");
                startWord = getStartWord(scanner);
                endWord = getEndWord(scanner);
            }

            while(!dictionary.contains(startWord) || !dictionary.contains(endWord)) {
                System.out.println("Start word or end word not found in dictionary. :(");
                startWord = getStartWord(scanner);
                endWord = getEndWord(scanner);
            }
            
            Node startNode = new Node(startWord);
            Node endNode = new Node(endWord);

            PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(n -> n.cost));
            queue.add(startNode);

            Algorithm algorithm;
            switch (choice) {
                case 1:
                    algorithm = new UCS();
                    break;
                case 2:
                    algorithm = new GBFS();
                    break;
                case 3:
                    algorithm = new AStar();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    continue;
            }

            System.out.println("Searching :) (Please Wait!!)");
            List<Node> path = algorithm.searchRoute(startNode, endNode, dictionary, queue);

            // Menampilkan jalur transformasi
            if (path != null) {
                System.out.println("Transformation path:");
                for (Node node : path) {
                    System.out.println(node.word);
                }
            } else {
                System.out.println("Transformation is not possible.");
            }

            if(Continue(scanner) != 1){
                break;
            }
        }

        scanner.close();
    }

    private static void ListMenu() {
        System.out.println("===== Word Ladder Menu =====");
        System.out.println("1. Uniform Cost Search (UCS)");
        System.out.println("2. Greedy Best First Search (GBFS)");
        System.out.println("3. A* Search (AStar)");
        System.out.println("4. Exit");
        System.out.println("============================");
    }

    private static int getChoice(Scanner scanner) {
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); 

        while(choice < 1 || choice > 4){
            System.out.println("Put the Right Input :)");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 
        }
        return choice;
    }

    private static String getStartWord(Scanner scanner) {
        System.out.print("Enter the start word: ");
        return scanner.nextLine().trim();
    }

    private static String getEndWord(Scanner scanner) {
        System.out.print("Enter the end word: ");
        return scanner.nextLine().trim();
    }

    private static Integer Continue(Scanner scanner){
        System.out.println("\n----------Pencarian Selesai!!!----------");
        System.out.println("1.Kembali ke Menu");
        System.out.println("2.Keluar");

        System.out.print("Pilihan : ");
        int input = scanner.nextInt();
        
        while (input < 1 || input > 2){
            System.out.println("\nMasukan Tidak Valid\n");
            System.out.print("Pilihan Menu : ");
            input = scanner.nextInt();
        }
        
        return input;
    }

    // Metode untuk membaca kamus dari file
    static Set<String> readDictionaryFromFile(String filePath) {
        Set<String> dictionary = new HashSet<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                dictionary.add(line.trim()); // Menambahkan kata ke dalam kamus
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error occurred while reading the dictionary file: " + e.getMessage());
        }
        return dictionary;
    }
}
