package command;

import canteens.Canteen;
import exceptions.NusfrException;
import nusfoodreviews.NusFoodReviews;
import ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This class will just display all the stores in the given canteen.
 * Checks for all the edge cases.
 */
public class DisplayStoreCommand extends Command {

    private final NusFoodReviews nusFoodReviews;

    public DisplayStoreCommand(NusFoodReviews nusFoodReviews) {
        this.nusFoodReviews = nusFoodReviews;
    }

    /**
     * Implements abstract method execute() in Command class.
     * Checks for all the edge cases before displaying all the stores
     * @param canteens Most updated ArrayList of canteens passed in from the main program.
     * @param ui Ui object passed in from the main program.
     * @throws IOException If writing to file has exception.
     * @throws NusfrException If user input contains -1. Invalid input.
     */
    @Override
    public void execute(ArrayList<Canteen> canteens, Ui ui) throws IOException, NusfrException {

        if (canteens.size() == 0) {
            ui.noCanteenToViewStore();
            return;
        }

        nusFoodReviews.setCanteenIndex();
        int currentCanteenIndex = nusFoodReviews.getCanteenIndex();
        if (currentCanteenIndex == -1) {
            ui.showStoreNotAdded();
            return;
        }
        ui.showDisplayStores(canteens.get(currentCanteenIndex));

    }

}
