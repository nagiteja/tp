package command;

import canteens.Canteen;
import ui.Ui;

import java.util.ArrayList;

/**
 * This class inherits from the Command class.
 * This class showcases the list of all the canteens present in the application.
 */

public class DisplayCanteensCommand extends Command {
    /**
     * Implements abstract method execute() in Command class.
     * @param canteens canteens object passed in from the main program.
     * @param ui Ui object passed in from the main program.
     */
    @Override
    public void execute(ArrayList<Canteen> canteens, Ui ui) {
        ui.showDisplayCanteens(canteens);
    }

}
