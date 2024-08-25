// Review.java
public class Review {
    private String username;
    private String comment;
    private int rating;

    public Review(String username, String comment, int rating) {
        this.username = username;
        this.comment = comment;
        this.rating = rating;
    }

    public String getUsername() {
        return username;
    }

    public String getComment() {
        return comment;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return username + ": " + rating + " stars\n" + comment;
    }
}
