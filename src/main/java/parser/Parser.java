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
import exceptions.NusfrException;
import nusfoodreviews.NusFoodReviews;
import storage.Storage;
import stores.Store;
import ui.Ui;

/**
 * This is the parser class and basically this class will take in the input from the suer an will execute the
 * respective command based on the user input.
 */
public class Parser {

    private NusFoodReviews nusFoodReviews;
    private Ui ui;
    private String savePath = Storage.DEFAULT_STORAGE_FILEPATH;

    /**
     * This is the Parser's constructor.
     * @param nusFoodReviews This object is passed in from the main function
     * @param ui This object is passed in from the main function.
     */
    public Parser(NusFoodReviews nusFoodReviews, Ui ui) {
        this.nusFoodReviews = nusFoodReviews;
        this.ui = ui;
    }

    /**
     * This will take in the input of the user and will check if the number is valid or not.
     * Will check if it is within the bounds or not.
     * @param line String input taken in from the user input
     * @param inclusiveMin Passed into this method from the main class
     * @param inclusiveMax passed into this method from the main class
     * @return will return the correct parsed int.
     * @throws NusfrException will br thrown if the input has illegal characters.
     */
    public int parseInt(String line, int inclusiveMin, int inclusiveMax) throws NusfrException {
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
                throw new NusfrException(exceptionMessage);
            }
        } catch (NumberFormatException e) {
            throw new NusfrException("Please enter a valid integer index!");
        }
        return parsedInt;
    }


    public Command parse(String line, Store store, Canteen canteen) throws NusfrException {
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

            throw new NusfrException("Please enter a valid command!");
        }
        return newCommand;
    }

    //parse admin commands only

    public Command parseAdminCommand(String line) throws NusfrException {
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
            throw new NusfrException("Please enter a valid index!");
        }
        assert true;
        return newCommand;
    }
}
