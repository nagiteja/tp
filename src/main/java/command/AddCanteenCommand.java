package command;

import canteens.Canteen;
import exceptions.NusfrException;
import storage.WriteToFile;
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
    public void execute(ArrayList<Canteen> canteens, Ui ui) throws IOException, NusfrException {
        String canteenName;
        boolean isNameValid;
        ui.showAddCanteen();
        String line;

        do {
            isNameValid = true;
            line = ui.readCommand();
            if (line.equals("cancel")) {
                ui.showCanteenNotAdded();
                return;
            } else {
                for (Canteen canteen : canteens) {
                    if (canteen.getCanteenName().equals(line)) {
                        isNameValid = false;
                        ui.showInvalidCanteenPrompt(canteen.getCanteenName());
                        break;
                    }
                }
            }
        } while (!isNameValid);

        canteenName = line;
        canteens.add(new Canteen(canteenName));
        ui.showAddCanteenSuccess(canteenName);
        WriteToFile.saveCanteen(new FileWriter(savePath,true),canteenName);
    }
}
