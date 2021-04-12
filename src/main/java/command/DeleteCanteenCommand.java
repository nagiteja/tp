package command;

import canteens.Canteen;
import exceptions.DukeExceptions;
import parser.Parser;
import storage.UpdateFile;
import ui.Ui;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import static ui.Ui.LINESPACING;

/**
 * Deletes and existing canteen from the ArrayList of canteens.
 * Checks for edge cases: no canteens.
 * Allows user to backtrack with 'cancel' keyword.
 */
public class DeleteCanteenCommand extends Command {

    private Parser parser;
    private String savePath;

    public DeleteCanteenCommand(Parser parser, String savePath) {
        this.parser = parser;
        this.savePath = savePath;
    }

    /**
     * Implements abstract method execute in Command class.
     * Checks for edge cases before attempting to delete canteen; returns if there are 0 canteens.
     * Allows user to backtrack with 'cancel' keyword.
     * Removes Canteen object from ArrayList.
     * Updates storage.
     *
     * @param canteens Most updated ArrayList of canteens passed in from the main program.
     * @param ui Ui object passed in from the main program.
     * @throws IOException If updating file has exception.
     * @throws DukeExceptions If user inputs illegal characters/alphabets/index out of range.
     */
    @Override
    public void execute(ArrayList<Canteen> canteens, Ui ui) throws IOException, DukeExceptions {
        int numCanteens = canteens.size();
        if (numCanteens > 0) {
            ui.showDisplaySelectCanteens(canteens, "delete");
            String line = ui.readCommand();
            if (line.equals("cancel")) {
                ui.showCanteenNotDeleted();
                return;
            }
            int canteenIndex = parser.parseInt(line, Math.min(1, numCanteens), numCanteens) - 1;

            assert canteenIndex >= 0 && canteenIndex < canteens.size() : "DeleteCanteenCommand canteenIndex invalid";
            Canteen removedCanteen = canteens.remove(canteenIndex);
            ui.showCanteenDeleted(removedCanteen, canteens.size());
            UpdateFile.deleteAndUpdateFile(new FileWriter(savePath), canteens);
        } else {
            System.out.println(LINESPACING);
            System.out.println("There are no canteens left!");
            System.out.println(LINESPACING);
        }

    }
}
