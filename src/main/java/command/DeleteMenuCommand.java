package command;

import canteens.Canteen;
import exceptions.NusfrExceptions;
import menus.Menu;
import nusfoodreviews.NusFoodReviews;
import parser.Parser;
import storage.Storage;
import storage.UpdateFile;
import stores.Store;
import ui.Ui;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class is only available for admin. this class will enable the admin to delete the menu of a particular store.
 * This is based on whether the menu is wrong or incorrect pricing.
 */
public class DeleteMenuCommand extends Command {
    private NusFoodReviews nusFoodReviews;
    private Parser parser;

    /**
     * This is a contructor which takes in the NUSFoodRevews and parser as arguments
     * @param nusFoodReviews This object is passed on into this method from the main program
     * @param parser This object is passed on into this method from the main program.
     */
    public DeleteMenuCommand(NusFoodReviews nusFoodReviews, Parser parser) {
        this.nusFoodReviews = nusFoodReviews;
        this.parser = parser;
    }

    /**
     *  Implements abstract method execute() in Command class.
     *  Allows user to backtrack with 'cancel' keyword.
     *  Deletes the menu of the particular store from storage
     *  Checks for edge cases before deleting any menu from storage.
     * @param canteens Most updated ArrayList of canteens passed in from the main program.
     * @param ui Ui object passed in from the main program.
     * @throws IOException If writing to a file gas an excpetion.
     * @throws NusfrExceptions If user input contains incorrect characters.
     */
    public void execute(ArrayList<Canteen> canteens, Ui ui) throws IOException, NusfrExceptions {

        if (canteens.size() == 0) {
            System.out.println(Ui.LINESPACING);
            System.out.println("There is no canteen for you to delete any menus from any stores.");
            System.out.println(Ui.LINESPACING);
            return;
        }

        nusFoodReviews.setCanteenIndex();
        int currentCanteenIndex = nusFoodReviews.getCanteenIndex();
        if (currentCanteenIndex == -1) {
            ui.showMenuNotDeleted();
            return;
        }
        Canteen canteen = canteens.get(currentCanteenIndex);
        if (canteen.getNumStores() < 1) {
            ui.showEmptyCanteen();
            return;
        }
        ui.showDisplayStores(canteens.get(currentCanteenIndex));
        ui.chooseDeleteStore();

        String line = ui.readCommand();
        if (line.equals("cancel")) {
            ui.showMenuNotDeleted();
            return;
        }
        int currentStoreIndex = Integer.parseInt(line) - 1;
        ArrayList<Menu> menus = canteens.get(currentCanteenIndex).getStore(currentStoreIndex).getMenus();
        if (menus.size() < 1) {
            ui.showNoMenuToDelete();
            return;
        }
        ui.showDisplayMenu(canteens.get(currentCanteenIndex).getStore(currentStoreIndex).getStoreName(),
                menus);

        ui.chooseMenu();
        line = ui.readCommand();
        if (line.equals("cancel")) {
            ui.showMenuNotDeleted();
            return;
        }
        int menuNumber = parser.parseInt(line,1,
                canteens.get(currentCanteenIndex).getStore(currentStoreIndex).getMenuCount()) - 1;

        Canteen currentCanteen = canteens.get(currentCanteenIndex);
        Store store = currentCanteen.getStore(currentStoreIndex);
        String menuName = store.getMenus().get(menuNumber).getItemName();
        store.deleteMenu(menuNumber);
        ui.menuDeleted(menuName);
        UpdateFile.deleteAndUpdateFile(new FileWriter(Storage.DEFAULT_STORAGE_FILEPATH),canteens);
    }

}

