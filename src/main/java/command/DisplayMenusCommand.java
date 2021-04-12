package command;

import canteens.Canteen;
import exceptions.DukeExceptions;
import stores.Store;
import ui.Ui;
import menus.Menu;

import java.util.ArrayList;

/**
 * Displays all menus of a selected store.
 */
public class DisplayMenusCommand extends Command {

    private Store store;

    /**
     * Relevant Store object is passed into the constructor of the command.
     * @param store Store object which user wishes to see the menu of.
     */
    public DisplayMenusCommand(Store store) {
        this.store = store;
        assert store != null : "store must be defined";
    }

    /**
     * Implements abstract method execute() in Command class.
     * Gets menus by getter method and display menus with relevant method in Ui class.
     *
     * @param canteens Most updated ArrayList of canteens passed in from the main program.
     * @param ui Ui object passed in from the main program.
     */
    public void execute(ArrayList<Canteen> canteens, Ui ui) {
        ArrayList<Menu> menus = store.getMenus();
        ui.showDisplayMenu(store.getStoreName(), menus);
    }
}
