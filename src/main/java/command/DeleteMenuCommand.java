package command;

import canteens.Canteen;
import exceptions.NusfrException;
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

public class DeleteMenuCommand extends Command {
    private NusFoodReviews nusFoodReviews;
    private Parser parser;

    public DeleteMenuCommand(NusFoodReviews nusFoodReviews, Parser parser) {
        this.nusFoodReviews = nusFoodReviews;
        this.parser = parser;
    }

    public void execute(ArrayList<Canteen> canteens, Ui ui) throws IOException, NusfrException {

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

