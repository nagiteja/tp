package command;

import canteens.Canteen;
import exceptions.NusfrException;
import nusfoodreviews.NusFoodReviews;
import parser.Parser;
import reviews.Review;
import storage.Storage;
import storage.UpdateFile;
import stores.Store;
import ui.Ui;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static ui.Ui.LINESPACING;

/**
 * Admin function.
 * Deletes reviews that are inappropriate.
 */
public class DeleteReviewCommand extends Command {
    private NusFoodReviews nusFoodReviews;
    private Parser parser;

    /**
     * Constructor of this class. Initializes necessary objects to interact with.
     * @param nusFoodReviews reference to main class.
     * @param parser         reference to the parser class.
     */
    public DeleteReviewCommand(NusFoodReviews nusFoodReviews, Parser parser) {
        this.nusFoodReviews = nusFoodReviews;
        this.parser = parser;
    }
    /**
     * Implements abstract method execute() in Command class.
     * Checks if canteen is empty; will not delete reviews if there are no canteens
     * Allows user to backtrack with 'cancel' keyword.
     * Deletes a review from a store
     * @param canteens ArrayList of canteens.
     * @param ui       Ui object passed in from the main program.
     * @throws IOException    if updating file has exception.
     * @throws NusfrException if index is out of bounds or admin input is unexpected.
     */

    public void execute(ArrayList<Canteen> canteens, Ui ui) throws IOException, NusfrException {
        if (canteens.size() > 0) {
            nusFoodReviews.setCanteenIndex();
            int currentCanteenIndex = nusFoodReviews.getCanteenIndex();
            nusFoodReviews.setStoreIndex();
            int currentStoreIndex = nusFoodReviews.getStoreIndex();
            if (currentStoreIndex == -1) {
                return;
            }
            Canteen currentCanteen = canteens.get(currentCanteenIndex);
            Store store = currentCanteen.getStore(currentStoreIndex);
            ArrayList<Review> reviews = store.getReviews();
            double averageRating = store.getAverageRating();
            if (reviews.size() <= 0) {
                System.out.println(LINESPACING);
                System.out.println("There are currently no reviews in this store to delete!");
                System.out.println(LINESPACING);
                return;
            }
            String storeName = store.getStoreName();
            ui.showReviews(storeName, reviews, averageRating);
            ui.showDeleteReview();

            String line = ui.readCommand();
            if (line.equals("cancel")) {
                ui.showReviewNotDeleted();
                return;
            }
            int reviewIndex = parser.parseInt(line, 1, reviews.size()) - 1;
            store.deleteReview(reviewIndex);
            ui.reviewDeleted();
            UpdateFile.deleteAndUpdateFile(new FileWriter(Storage.DEFAULT_STORAGE_FILEPATH), canteens);
        } else {
            System.out.println(LINESPACING);
            System.out.println("There are no canteens for you to delete reviews for any stores!");
            System.out.println(LINESPACING);
        }


    }

}
