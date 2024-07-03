import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class WordCounter extends JFrame implements ActionListener {
    private JTextArea textArea;
    private JButton countButton;
    private JLabel resultLabel;

    public WordCounter() {
        // Set up the frame
        setTitle("Word Counter");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create text area
        textArea = new JTextArea();
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Create button
        countButton = new JButton("Count Words");
        countButton.addActionListener(this);
        add(countButton, BorderLayout.SOUTH);

        // Create result label
        resultLabel = new JLabel("Word Count: 0");
        add(resultLabel, BorderLayout.NORTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Get text from text area
        String text = textArea.getText();
        // Split text into words
        String[] words = text.split("\\s+");
        // Count words
        int wordCount = words.length;
        // Update label
        resultLabel.setText("Word Count: " + wordCount);
    }

    public static void main(String[] args) {
        // Create and display the form
        SwingUtilities.invokeLater(() -> {
            new WordCounter().setVisible(true);
        });
    }
}
