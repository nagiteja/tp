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

    @Override
    public void execute(ArrayList<Canteen> canteens, Ui ui) throws IOException, NusfrException {

        if (canteens.size() == 0) {
            System.out.println(Ui.LINESPACING);
            System.out.println("There are no canteens for you to delete any stores.");
            System.out.println(Ui.LINESPACING);
            return;
        }

        nusFoodReviews.setCanteenIndex();
        int currentCanteenIndex = nusFoodReviews.getCanteenIndex();

        if (canteens.get(currentCanteenIndex).getStores().size() == 0) {
            throw new NusfrException("There are current no stores in the canteen");
        }

        ui.showDisplaySelectStores(canteens.get(currentCanteenIndex));
        String line = ui.readCommand();
        if (line.equals("cancel")) {
            ui.showStoreNotDeleted();
            return;
        }

        int storeIndex = parser.parseInt(line, 1,
                canteens.get(currentCanteenIndex).getNumStores()) - 1;

        Canteen currentCanteen = canteens.get(currentCanteenIndex);
        String storeName = currentCanteen.getStore(storeIndex).getStoreName();
        currentCanteen.deleteStore(storeIndex);
        ui.showDeleteStore(storeName);
        UpdateFile.deleteAndUpdateFile(new FileWriter(Storage.DEFAULT_STORAGE_FILEPATH),canteens);
    }

}
