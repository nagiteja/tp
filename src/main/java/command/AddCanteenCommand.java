package command;

import canteens.Canteen;
import exceptions.DukeExceptions;
import storage.Storage;
import ui.Ui;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class AddCanteenCommand extends Command {

    private String savePath;

    public AddCanteenCommand(String savePath) {
        this.savePath = savePath;
    }

    @Override
    public void execute(ArrayList<Canteen> canteens, Ui ui) throws IOException, DukeExceptions {
        String canteenName;
        ui.showAddCanteen();
        String line = ui.readCommand();
        if (line.equals("cancel")) {
            ui.showCanteenNotAdded();
            return;
        } else {
            canteenName = line;
        }
        canteens.add(new Canteen(canteenName));
        ui.showAddCanteenSuccess(canteenName);
        Storage.saveCanteen(new FileWriter(savePath,true),canteenName);
    }
}
