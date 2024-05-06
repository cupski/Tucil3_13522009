import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import javax.swing.JOptionPane;

public class Menu {
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        Set<String> dictionary = readDictionaryFromFile("src/word.txt");

        if(getInput(scanner) == 2){
            GUI gui = new GUI();
            gui.run();
            return;
        }

        while (true) {
            int choice = getMenuChoice(scanner);
            if (choice == 4) {
                System.out.println("Exiting...");
                break;
            }

            String startWord = getStartWord(scanner);
            String endWord = getEndWord(scanner);

            while (startWord.isEmpty() || endWord.isEmpty()) {
                System.out.println("Start word or end word cannot be empty.");
                startWord = getStartWord(scanner);
                endWord = getEndWord(scanner);
            }

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

            System.out.println("\nSearching :) (Please Wait!!)");
            Object[] route = algorithm.searchRoute(startNode, endNode, dictionary, queue);
            
            @SuppressWarnings("unchecked")
            List<Node> path = (List<Node>) route[0];
            int depth = (int) route[1];
            long duration = (long) route[2];
            
            // Menampilkan jalur transformasi
            System.out.println("\n===== " + (path != null ? "Path Found!!!": "Path Not Found :(") + " ====="); 
            if (path != null) {
                System.out.print("Route: ");
                for (int i = 0; i < path.size(); i++) {
                    System.out.print(path.get(i).word);
                    if (i < path.size() - 1) {
                        System.out.print(" -> ");
                    }
                }
            }
            System.out.println("\nNode Visited: " + depth);
            System.out.println("Execution time: " + duration + " milliseconds");
            

            if(Continue(scanner) != 1){
                break;
            }
        }

        scanner.close();
    }

    private static Integer getInput(Scanner scanner) {
        System.out.println("===== Pilih Metode Input =====");
        System.out.println("1. CLI((Command Line Interface))");
        System.out.println("2. GUI (Graphical User Interface)");
        System.out.println("============================");

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

    private static int getMenuChoice(Scanner scanner) {
        System.out.println("\n===== Word Ladder Menu =====");
        System.out.println("1. Uniform Cost Search (UCS)");
        System.out.println("2. Greedy Best First Search (GBFS)");
        System.out.println("3. A* Search (AStar)");
        System.out.println("4. Exit");
        System.out.println("============================");

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
        return scanner.nextLine().trim().toLowerCase();
    }

    private static String getEndWord(Scanner scanner) {
        System.out.print("Enter the end word: ");
        return scanner.nextLine().trim().toLowerCase();
    }

    private static Integer Continue(Scanner scanner){
        System.out.println("\n====Pencarian Selesai!!!====");
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