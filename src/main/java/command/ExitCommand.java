package command;

import canteens.Canteen;
import ui.Ui;

import java.util.ArrayList;
import java.util.Map;

/**
 * Represents an executor that will exit the application.
 */
public class ExitCommand extends Command {

    public ExitCommand() {
        this.exit = true;
    }

    /**
     * Implements abstract method execute() in Command class.
     * @param canteens canteens object passed in from the main program.
     * @param ui Ui object passed in from the main program.
     */
    @Override
    public void execute(ArrayList<Canteen> canteens, Ui ui) {
        ui.showGoodbye();
        System.exit(0);
    }

}
