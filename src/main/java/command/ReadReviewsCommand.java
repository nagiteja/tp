package command;

import canteens.Canteen;
import reviews.Review;
import stores.Store;
import ui.Ui;

import java.util.ArrayList;

import static ui.Ui.LINESPACING;


/**
 * User function.
 * Prints the reviews of a selected store.
 * Checks whether reviews of the store exists else print message to inform users.
 */
public class ReadReviewsCommand extends Command {
    public Store store;


    /**
     * Constructor of this class. Initializes necessary objects to interact with.
     * @param store Store object passed in.
     */
    public ReadReviewsCommand(Store store) {
        this.store = store;
    }

    /**
     * Implements abstract method execute() in Command class.
     * Checks if reviews size is more than 0 else will print error message
     * Displays reviews to users
     * @param canteens ArrayList of canteens.
     * @param ui       Ui object passed in from the main program.
     */
    @Override
    public void execute(ArrayList<Canteen> canteens, Ui ui) {
        ArrayList<Review> reviews = store.getReviews();
        if (reviews.size() > 0) {
            double averageRating = store.getAverageRating();
            String storeName = store.getStoreName();
            ui.showReviews(storeName, reviews, averageRating);
        } else {
            System.out.println("There are no reviews yet!");
            System.out.println(LINESPACING);
        }

    }
}
