package command;

import canteens.Canteen;
import exceptions.NusfrException;
import reviews.Review;
import storage.Storage;
import storage.WriteToFile;
import stores.Store;
import ui.Ui;

import java.io.FileWriter;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static ui.Ui.LINESPACING;

public class AddReviewCommand extends Command {
    private Store store;
    private Canteen canteen;

    public AddReviewCommand(Store store, Canteen canteen) {

        this.store = store;
        this.canteen = canteen;
    }

    @Override
    public void execute(ArrayList<Canteen> canteens, Ui ui) throws NusfrException {
        try {
            getReviewFromUser(ui);
        } catch (NumberFormatException | IOException e) {
            throw new NusfrException("Review not added. Please input your review in proper format!");
        }
    }


    public void getReviewFromUser(Ui ui) throws NumberFormatException, IOException, NusfrException {
        String description;
        String line;
        double rating = 0.0;
        ui.enterReview();
        line = ui.readCommand();
        if (line.equals("cancel")) {
            ui.reviewNotAdded();
            return;
        } else if (line.contains("/")) {
            ui.reviewNotAdded();
            return;
        } else if (line.contains("\n")) {
            ui.reviewNotAdded();
            return;
        } else if (line.equals("")) {
            System.out.println("Input cannot be empty");
            ui.reviewNotAdded();
            return;
        } else if (line.contains("<")) {
            ui.reviewNotAdded();
            return;
        } else if (line.contains(">")) {
            ui.reviewNotAdded();
            return;
        } else if (line.contains("\\")) {
            ui.reviewNotAdded();
            return;
        } else {
            description = line;
        }
        ui.enterRating();
        line = ui.readCommand();
        if (line.equals("cancel")) {
            ui.reviewNotAdded();
            return;
        } else {
            rating = Double.parseDouble(line);
            if (rating < 1.0 || rating > 5.0) {
                System.out.println("Please enter valid rating");
                System.out.println(LINESPACING);
                return;
            }
        }
        store.addReview(new Review(description, rating));
        ui.reviewAdded();
        Date dateTime = new Date();
        Format formatter = new SimpleDateFormat("yyy-MM-dd");
        WriteToFile.saveReview(new FileWriter(Storage.DEFAULT_STORAGE_FILEPATH,true),canteen,store,description,line,
                formatter.format(dateTime));
    }
}
