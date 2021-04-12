package parser;

import canteens.Canteen;
import command.AddCanteenCommand;
import command.AddMenuCommand;
import command.AddReviewCommand;
import command.AddStoreCommand;
import command.Command;
import command.DeleteCanteenCommand;
import command.DeleteMenuCommand;
import command.DeleteReviewCommand;
import command.DeleteStoreCommand;
import command.DisplayCanteensCommand;
import command.DisplayMenusCommand;
import command.ExitCommand;
import command.HelpCommand;
import command.HomeCommand;
import command.LoginCommand;
import command.ReadReviewsCommand;
import command.ResetStoreCommand;
import command.DisplayStoreCommand;
import exceptions.DukeExceptions;
import nusfoodreviews.NusFoodReviews;
import storage.Storage;
import stores.Store;
import ui.Ui;


public class Parser {

    private NusFoodReviews nusFoodReviews;
    private Ui ui;
    private String savePath = Storage.DEFAULT_STORAGE_FILEPATH;

    public Parser(NusFoodReviews nusFoodReviews, Ui ui) {
        this.nusFoodReviews = nusFoodReviews;
        this.ui = ui;
    }

    public int parseInt(String line, int inclusiveMin, int inclusiveMax) throws DukeExceptions {
        int parsedInt;

        try {
            parsedInt = Integer.parseInt(line);
            if (parsedInt < inclusiveMin || parsedInt > inclusiveMax) {
                String exceptionMessage;
                if (inclusiveMin == inclusiveMax) {
                    exceptionMessage = "Please enter a valid index!";
                } else {
                    exceptionMessage = "Please enter a valid index in the range of " + inclusiveMin
                            + " and " + inclusiveMax + "!";
                }
                throw new DukeExceptions(exceptionMessage);
            }
        } catch (NumberFormatException e) {
            throw new DukeExceptions("Please enter a valid integer index!");
        }
        return parsedInt;
    }

    public Command parse(String line, Store store, Canteen canteen) throws DukeExceptions {
        Command newCommand;
        if (line.equals("home")) {
            newCommand = new HomeCommand(nusFoodReviews);
        } else if (line.equals("list")) {
            newCommand = new ResetStoreCommand(nusFoodReviews);
        } else if (line.equals("menu")) {
            newCommand = new DisplayMenusCommand(store);
        } else if (line.equals("add")) { //add review
            newCommand = new AddReviewCommand(store,canteen);
        } else if (line.equals("exit")) {
            newCommand = new ExitCommand();
        } else if (line.equals("reviews")) {
            newCommand = new ReadReviewsCommand(store);
        } else if (line.equals("help")) {
            newCommand = new HelpCommand();
        } else if (line.equals("login")) {
            newCommand = new LoginCommand(nusFoodReviews);
        } else {
            throw new DukeExceptions("Please enter a valid command!");
        }
        return newCommand;
    }

    //parse admin commands only
    public Command parseAdminCommand(String line) throws DukeExceptions {
        Command newCommand;

        switch (line) {
        case "1":
            newCommand = new DisplayCanteensCommand();
            break;
        case "2":
            newCommand = new AddCanteenCommand(savePath);
            break;
        case "3":
            newCommand = new AddStoreCommand(nusFoodReviews);
            break;
        case "4":
            newCommand = new AddMenuCommand(nusFoodReviews);
            break;
        case "5":
            newCommand = new DeleteCanteenCommand(this, savePath);
            break;
        case "6":
            newCommand = new DeleteStoreCommand(nusFoodReviews, this);
            break;
        case "7":
            newCommand = new DeleteReviewCommand(nusFoodReviews, this);
            break;
        case "8":
            newCommand = new DeleteMenuCommand(nusFoodReviews, this);
            break;
        case "9":
            newCommand = new DisplayStoreCommand(nusFoodReviews);
            break;
        case "0":
            newCommand = new ExitCommand();
            break;
        case "login":
            newCommand = new LoginCommand(nusFoodReviews);
            break;
        default:
            throw new DukeExceptions("Please enter a valid index!");
        }
        assert true;
        return newCommand;
    }
}
