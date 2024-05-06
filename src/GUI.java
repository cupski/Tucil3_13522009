import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class GUI {
    private JTextField startWordField;
    private JTextField endWordField;
    private JComboBox<String> algorithmComboBox;
    private JTextArea outputTextArea;

    public void run() {
        JFrame frame = new JFrame("Welcome to Word Ladder by Cupski");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null); 

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        inputPanel.add(new JLabel("Start Word:"), gbc);
        gbc.gridy++;
        startWordField = new JTextField(15);
        inputPanel.add(startWordField, gbc);

        gbc.gridy++;
        inputPanel.add(new JLabel("End Word:"), gbc);
        gbc.gridy++;
        endWordField = new JTextField(15);
        inputPanel.add(endWordField, gbc);

        gbc.gridy++;
        inputPanel.add(new JLabel("Algorithm:"), gbc);
        gbc.gridy++;
        algorithmComboBox = new JComboBox<>(new String[]{"Uniform Cost Search (UCS)", "Greedy Best First Search (GBFS)", "A* Search (AStar)"});
        inputPanel.add(algorithmComboBox, gbc);

        gbc.gridy++;
        gbc.gridwidth = 2;
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchButtonClicked();
            }
        });
        inputPanel.add(searchButton, gbc);

        JPanel outputPanel = new JPanel(new BorderLayout());
        outputPanel.setBorder(BorderFactory.createTitledBorder("Result"));

        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        outputPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(outputPanel, BorderLayout.CENTER);

        frame.setContentPane(mainPanel);

        frame.setVisible(true);
    }

    private void searchButtonClicked() {
        String startWord = startWordField.getText().trim().toLowerCase();
        String endWord = endWordField.getText().trim().toLowerCase();
        int algorithmChoice = algorithmComboBox.getSelectedIndex() + 1;

        Set<String> dictionary = Menu.readDictionaryFromFile("src/word.txt");

        // Validate input
        if (startWord.isEmpty() || endWord.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Start word or end word cannot be empty.");
            return;
        }
        else if (!dictionary.contains(startWord) || !dictionary.contains(endWord)) {
            JOptionPane.showMessageDialog(null, "Start word or end word not found in dictionary.");
            return;
        }
        else if (startWord.length() != endWord.length()) {
            JOptionPane.showMessageDialog(null, "Make Sure both words have the same length");
            return;
        }

        // Run the search algorithm
        Node startNode = new Node(startWord);
        Node endNode = new Node(endWord);
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(n -> n.cost));
        queue.add(startNode);
        Object[] route;
        switch (algorithmChoice) {
            case 1:
                Algorithm ucs = new UCS();
                route = ucs.searchRoute(startNode, endNode, dictionary, queue);
                break;
            case 2:
                Algorithm gbfs = new GBFS();
                route = gbfs.searchRoute(startNode, endNode, dictionary, queue);
                break;
            case 3:
                Algorithm aStar = new AStar();
                route = aStar.searchRoute(startNode, endNode, dictionary, queue);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Invalid algorithm choice. Please try again.");
                return;
        }

        // Display the result
        displayResult(route);
    }

    private void displayResult(Object[] route) {
        @SuppressWarnings("unchecked")
        java.util.List<Node> path = (java.util.List<Node>) route[0];
        int depth = (int) route[1];
        long duration = (long) route[2];

        StringBuilder result = new StringBuilder();
        result.append(path != null ? "Path Found!!!\n" : "Path Not Found :(\n");
        if (path != null) {
            result.append("Route: \n");
            for (int i = 0; i < path.size(); i++) {
                result.append(i + 1).append(". ").append(path.get(i).word).append("\n");
            }
        }
        result.append("\nNode Visited: ").append(depth);
        result.append("\nExecution time: ").append(duration).append(" milliseconds");

        outputTextArea.setText(result.toString());
    }
}
