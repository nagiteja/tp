package command;

import canteens.Canteen;
import stores.Store;
import ui.Ui;
import menus.Menu;

import java.util.ArrayList;

public class DisplayMenusCommand extends Command {

    private Store store;

    public DisplayMenusCommand(Store store) {
        this.store = store;
        assert store != null : "store must be defined";
    }

    public void execute(ArrayList<Canteen> canteens, Ui ui) {
        ArrayList<Menu> menus = store.getMenus();
        ui.showDisplayMenu(store.getStoreName(), menus);
    }
}
