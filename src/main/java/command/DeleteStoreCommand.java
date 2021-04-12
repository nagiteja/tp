package command;

import canteens.Canteen;
import exceptions.NusfrException;
import nusfoodreviews.NusFoodReviews;
import parser.Parser;
import storage.Storage;
import storage.UpdateFile;
import ui.Ui;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DeleteStoreCommand extends Command {

    private Parser parser;
    private NusFoodReviews nusFoodReviews;

    public DeleteStoreCommand(NusFoodReviews nusFoodReviews, Parser parser) {
        this.nusFoodReviews = nusFoodReviews;
        this.parser = parser;
    }

    /**
     * Implements abstract method execute() in Command class.
     * Checks if canteen is empty; will not delete store if there are no canteens
     * Allows user to backtrack with 'cancel' keyword.
     * Deletes a store from a canteen
     *
     * @param canteens ArrayList of canteens.
     * @param ui       Ui object passed in from the main program.
     * @throws IOException     if updating file has exception.
     * @throws NusfrException if index is out of bounds or admin input is unexpected.
     */
    @Override
    public void execute(ArrayList<Canteen> canteens, Ui ui) throws IOException, NusfrException {
        if (canteens.size() > 0) {
            nusFoodReviews.setCanteenIndex();
            int currentCanteenIndex = nusFoodReviews.getCanteenIndex();
            Canteen currentCanteen = canteens.get(currentCanteenIndex);
            ui.showDisplaySelectStores(currentCanteen);
            String line = ui.readCommand();

            if (canteens.size() == 0) {
                System.out.println(Ui.LINESPACING);
                System.out.println("There are no canteens for you to delete any stores.");
                System.out.println(Ui.LINESPACING);
                return;
            }

            nusFoodReviews.setCanteenIndex();
            currentCanteenIndex = nusFoodReviews.getCanteenIndex();

            if (canteens.get(currentCanteenIndex).getStores().size() == 0) {
                throw new NusfrException("There are current no stores in the canteen");
            }

            ui.showDisplaySelectStores(canteens.get(currentCanteenIndex));
            line = ui.readCommand();
            if (line.equals("cancel")) {
                ui.showStoreNotDeleted();
                return;
            }

            int storeIndex = parser.parseInt(line, 1,
                    canteens.get(currentCanteenIndex).getNumStores()) - 1;

            currentCanteen = canteens.get(currentCanteenIndex);
            String storeName = currentCanteen.getStore(storeIndex).getStoreName();
            currentCanteen.deleteStore(storeIndex);
            ui.showDeleteStore(storeName);
            UpdateFile.deleteAndUpdateFile(new FileWriter(Storage.DEFAULT_STORAGE_FILEPATH), canteens);
        }

    }
}
