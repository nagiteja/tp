package command;

import canteens.Canteen;
import exceptions.NusfrException;
import storage.WriteToFile;
import ui.Ui;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Adds a new canteen to the existing ArrayList of Canteens.
 * Checks for edge cases: duplicate canteen names.
 * Allows user to backtrack with 'cancel' keyword.
 */
public class AddCanteenCommand extends Command {

    private String savePath;

    public AddCanteenCommand(String savePath) {
        this.savePath = savePath;
    }

    /**
     * Implements abstract method execute() in Command class.
     * Checks for edge cases before adding canteen; will not add canteen if canteenName already exists.
     * Allows user to backtrack with 'cancel' keyword.
     * Instantiates new Canteen object and adds it to the ArrayList of canteens.
     * Writes new canteen to storage.
     *
     * @param canteens Most updated ArrayList of canteens passed in from the main program.
     * @param ui Ui object passed in from the main program.
     * @throws IOException If writing to file has exception.
     * @throws NusfrException If user input contains illegal characters.
     */
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
