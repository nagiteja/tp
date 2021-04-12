package command;

import canteens.Canteen;
import ui.Ui;
import java.util.ArrayList;

/**
 * This class showcases the Help command whenever the user inputs the keyword "help".
 */

public class HelpCommand extends Command {
    /**
     * Implements abstract method execute() in Command class.
     * @param canteens canteens object passed in from the main program.
     * @param ui Ui object passed in from the main program.
     */
    @Override
    public void execute(ArrayList<Canteen> canteens, Ui ui) {
        ui.showHelpCommand();
    }
}
