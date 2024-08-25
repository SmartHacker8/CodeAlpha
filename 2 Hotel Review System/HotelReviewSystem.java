// HotelReviewSystem.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HotelReviewSystem extends JFrame {
    private HotelReviewManager reviewManager;
    private JTextArea reviewDisplay;
    private JComboBox<Hotel> hotelComboBox;
    private JTextField usernameField, commentField, filterField;
    private JSpinner ratingSpinner;

    public HotelReviewSystem() {
        reviewManager = new HotelReviewManager();
        setTitle("Hotel Review System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Add some hotels
        reviewManager.addHotel(new Hotel("Grand Hotel", "New York"));
        reviewManager.addHotel(new Hotel("Beach Resort", "Miami"));
        reviewManager.addHotel(new Hotel("Mountain Retreat", "Denver"));

        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        hotelComboBox = new JComboBox<>(reviewManager.getHotels().toArray(new Hotel[0]));
        topPanel.add(hotelComboBox);

        JPanel formPanel = new JPanel(new GridLayout(3, 2));
        formPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        formPanel.add(usernameField);

        formPanel.add(new JLabel("Rating:"));
        ratingSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));
        formPanel.add(ratingSpinner);

        formPanel.add(new JLabel("Comment:"));
        commentField = new JTextField();
        formPanel.add(commentField);

        topPanel.add(formPanel);
        add(topPanel, BorderLayout.NORTH);

        reviewDisplay = new JTextArea();
        reviewDisplay.setEditable(false);
        add(new JScrollPane(reviewDisplay), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        JButton addReviewButton = new JButton("Add Review");
        bottomPanel.add(addReviewButton);

        filterField = new JTextField(10);
        bottomPanel.add(filterField);
        JButton filterButton = new JButton("Filter by Keyword");
        bottomPanel.add(filterButton);

        JButton sortAscButton = new JButton("Sort by Rating (Asc)");
        bottomPanel.add(sortAscButton);
        JButton sortDescButton = new JButton("Sort by Rating (Desc)");
        bottomPanel.add(sortDescButton);

        add(bottomPanel, BorderLayout.SOUTH);

        // Action listeners
        addReviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String comment = commentField.getText();
                int rating = (int) ratingSpinner.getValue();
                Hotel hotel = (Hotel) hotelComboBox.getSelectedItem();

                Review review = new Review(username, comment + " [" + hotel.getName() + "]", rating);
                reviewManager.addReview(review);
                updateReviewDisplay();
            }
        });

        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = filterField.getText();
                displayFilteredReviews(keyword);
            }
        });

        sortAscButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displaySortedReviews(true);
            }
        });

        sortDescButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displaySortedReviews(false);
            }
        });
    }

    private void updateReviewDisplay() {
        Hotel hotel = (Hotel) hotelComboBox.getSelectedItem();
        StringBuilder displayText = new StringBuilder();
        for (Review review : reviewManager.getReviewsForHotel(hotel)) {
            displayText.append(review.toString()).append("\n\n");
        }
        reviewDisplay.setText(displayText.toString());
    }

    private void displayFilteredReviews(String keyword) {
        StringBuilder displayText = new StringBuilder();
        for (Review review : reviewManager.filterReviewsByKeyword(keyword)) {
            displayText.append(review.toString()).append("\n\n");
        }
        reviewDisplay.setText(displayText.toString());
    }

    private void displaySortedReviews(boolean ascending) {
        StringBuilder displayText = new StringBuilder();
        for (Review review : reviewManager.sortReviewsByRating(ascending)) {
            displayText.append(review.toString()).append("\n\n");
        }
        reviewDisplay.setText(displayText.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HotelReviewSystem().setVisible(true);
            }
        });
    }
}
