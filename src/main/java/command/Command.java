package command;

import canteens.Canteen;
import exceptions.NusfrException;
import ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This is an abstract class command which has the major method "execute". This is used for inheritance purposes.
 */
public abstract class Command {
    protected boolean exit;
    private int targetIndex = -1;

    public Command() {
        exit = false;
    }


    public abstract void execute(ArrayList<Canteen> canteens, Ui ui) throws NusfrException, IOException;


}
