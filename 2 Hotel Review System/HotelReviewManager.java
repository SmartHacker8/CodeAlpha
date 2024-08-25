// HotelReviewManager.java
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HotelReviewManager {
    private List<Hotel> hotels;
    private List<Review> reviews;

    public HotelReviewManager() {
        hotels = new ArrayList<>();
        reviews = new ArrayList<>();
    }

    public void addHotel(Hotel hotel) {
        hotels.add(hotel);
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public List<Review> getReviewsForHotel(Hotel hotel) {
        return reviews.stream()
                .filter(review -> review.getComment().contains(hotel.getName()))
                .collect(Collectors.toList());
    }

    public List<Review> sortReviewsByRating(boolean ascending) {
        return reviews.stream()
                .sorted((r1, r2) -> ascending ? r1.getRating() - r2.getRating() : r2.getRating() - r1.getRating())
                .collect(Collectors.toList());
    }

    public List<Review> filterReviewsByKeyword(String keyword) {
        return reviews.stream()
                .filter(review -> review.getComment().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Hotel> getHotels() {
        return hotels;
    }
}
