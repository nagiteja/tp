package stores;

import menus.Menu;
import reviews.Review;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Store {
    private String storeName;
    private ArrayList<Review> reviews;
    private ArrayList<Menu> menus;
    public double ratingSum = 0;
    public int menuCount = 0;
    public double averageRating;

    private static Logger logger = Logger.getLogger(Store.class.getName());

    public Store(String storeName) {
        this.storeName = storeName;
        menus = new ArrayList<>();
        reviews = new ArrayList<>();
        logger.log(Level.FINER, "New Store object " + storeName + " created");
    }

    public void displayStore() {
        System.out.println(storeName);
    }


    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public String getStoreName() {
        return storeName;
    }

    public ArrayList<Menu> getMenus() {
        return menus;
    }

    public double getAverageRating() {
        for (Review review : reviews) {
            ratingSum = ratingSum + review.getRating();
        }
        averageRating = ratingSum / reviews.size();
        return averageRating;
    }

    public void addReview(Review newReview) {
        reviews.add(newReview);
    }

    public void deleteReview(int reviewIndex) {
        reviews.remove(reviewIndex);
    }

    public void addMenu(Menu newMenu) {
        menus.add(newMenu);
        menuCount++;
    }

    public void deleteMenu(int menuIndex) {
        menus.remove(menuIndex);
    }

    public int getMenuCount() {
        return menuCount;
    }
}

