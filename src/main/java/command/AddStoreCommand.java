package command;

import canteens.Canteen;
import exceptions.NusfrException;
import nusfoodreviews.NusFoodReviews;
import storage.Storage;
import storage.WriteToFile;
import stores.Store;
import ui.Ui;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Adds a new store to an existing canteen's ArrayList of Stores.
 * Checks for edge cases: no canteens, duplicate store names.
 * Allows user to backtrack with 'cancel' keyword.
 */
public class AddStoreCommand extends Command {

    private NusFoodReviews nusFoodReviews;

    /**
     * Constructor for AddStoreCommand. Reference to main program is passed to set and access currentCanteenIndex.
     * @param nusFoodReviews Reference to main program.
     */
    public AddStoreCommand(NusFoodReviews nusFoodReviews) {
        this.nusFoodReviews = nusFoodReviews;
    }

    /**
     * Implements abstract method execute() in Command class.
     * Checks for edge cases before adding store; will not add store if there are no canteens/storeName already exists.
     * Allows user to backtrack with 'cancel' keyword.
     * Adds store using the Canteen class method addStore(), where the actual Store object is instantiated.
     * Writes new store to storage.
     *
     * @param canteens Most updated ArrayList of canteens passed in from the main program.
     * @param ui Ui object passed in from the main program.
     * @throws IOException If writing to file has exception.
     * @throws NusfrException If setting canteen index has exception.
     */
    @Override
    public void execute(ArrayList<Canteen> canteens, Ui ui) throws IOException, NusfrException {

        if (canteens.size() == 0) {
            System.out.println(Ui.LINESPACING);
            System.out.println("There is no canteen yet. Please add a canteen");
            System.out.println(Ui.LINESPACING);
            return;
        }

        //get canteen where user wants to add store in
        nusFoodReviews.setCanteenIndex();
        int currentCanteenIndex = nusFoodReviews.getCanteenIndex();
        if (currentCanteenIndex == -1) {
            ui.showStoreNotAdded();
            return;
        }
        Canteen currentCanteen = canteens.get(currentCanteenIndex);
        boolean isNameValid;
        String storeName;

        //display what canteens are in the store
        ui.showDisplayStores(canteens.get(currentCanteenIndex));
        ui.showAddStore();

        //get store name from user
        do {
            isNameValid = true;
            storeName = ui.readCommand();
            if (storeName.equals("cancel")) {
                ui.showStoreNotAdded();
                return;
            } else {
                for (Store store : currentCanteen.getStores()) {
                    if (store.getStoreName().equals(storeName)) {
                        isNameValid = false;
                        ui.showInvalidStorePrompt(store.getStoreName());
                        break;
                    }
                }
            }
        } while (!isNameValid);

        //add store to canteen
        currentCanteen.addStore(storeName);
        ui.printStoreAdded(storeName, currentCanteen.getCanteenName());
        WriteToFile.saveStore(new FileWriter(Storage.DEFAULT_STORAGE_FILEPATH,true),
                canteens.get(currentCanteenIndex).getCanteenName(),storeName);
    }

}
