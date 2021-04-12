package ui;

import canteens.Canteen;
import exceptions.NusfrExceptions;
import menus.Menu;
import reviews.Review;
import stores.Store;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the main class that interacts with the user through the command line interface.
 */
public class Ui {
    public static final String LINESPACING = "====================================================";
    private static Scanner userInputScanner;
    private static String line;

    /**
     * Constructor of class. Creates a scanner to collect user input
     */
    public Ui() {
        userInputScanner = new Scanner(System.in);
    }

    public void showLogo() {
        System.out.println(" _    _   _    _   ______     ______   _____   _____   ____   \n"
                + "|  \\ | | | |  | | |  ____|   |  ____| |  _  | |  _  | |  _ \\  \n"
                + "| |\\\\| | | |  | | | |____    | |___   | | | | | | | | | | | |\n"
                + "| | \\  | | |__| |  ____| |   |  ___|  | |_| | | |_| | | |_| |  \n"
                + "|_|  \\_| |______| |______|   |__|     |_____| |_____| |____/\n"
                + " _____   _____  __      __  _   _____   _       _\n"
                + "|  _  \\ | ____| \\ \\    / / | | | ____| | |  _  | |\n"
                + "| |_|_/ | |___   \\ \\  / /  | | | |___  | | | | | |\n"
                + "| |\\ \\  | |___    \\ \\/ /   | | | |___  | \\_| |_/ |\n"
                + "|_| \\_\\ |_____|    \\__/    |_| |_____| |_________| \n"
                + "Welcome to NUS FOOD REVIEW");
    }

    public String readCommand() throws NusfrExceptions {
        try {
            line = userInputScanner.nextLine();
            if (line.contains("/")) {
                throw new NusfrExceptions("Input cannot contain Delimiters");
            } else if (line.contains("<")) {
                throw new NusfrExceptions("Input cannot contain Delimiters");
            } else if (line.contains(">")) {
                throw new NusfrExceptions("Input cannot contain Delimiters");
            } else if (line.contains("\\")) {
                throw new NusfrExceptions("Input cannot contain Delimiters");
            }
        } catch (NullPointerException e) {
            System.out.println("Input cannot be empty.");
        }
        return line;
    }

    public void userShowWelcome() {
        System.out.println(LINESPACING);
        System.out.println("Welcome to our amazing canteen review application!!");
    }

    public void adminShowWelcome() {
        System.out.println(LINESPACING);
        System.out.println("Welcome Admin! ");
        System.out.println("Please enter your password: ");
        System.out.println(LINESPACING);
    }

    public void adminPasswordReenter() {
        System.out.println("Re-enter your password: ");
        System.out.println(LINESPACING);
    }

    public void showAdminVerified() {
        System.out.println(LINESPACING);
        System.out.println("Successfully verified!");
        System.out.println(LINESPACING);
    }

    public void showAdminOptions() {
        System.out.println("Please enter the index of the task you would like to choose : "
                + "(Enter 'login' to go back to login page)");
        System.out.println("1. View canteens");
        System.out.println("2. Add canteen");
        System.out.println("3. Add a store in a canteen");
        System.out.println("4. Add Menu to a store");
        System.out.println("5. Delete canteen");
        System.out.println("6. Delete a store in a canteen");
        System.out.println("7. Delete reviews in a store");
        System.out.println("8. Delete menu in a store");
        System.out.println("9. View stores in a canteen");
        System.out.println("0. Exit");
        System.out.println(LINESPACING);
    }

    public void printStoreAdded(String storeName, String canteenName) {
        System.out.println(LINESPACING);
        System.out.println("Got it ! Successfully added " + storeName + " to " + canteenName + ".");
        System.out.println(LINESPACING);
    }


    public void chooseStore() {
        System.out.println("Please select store to add menu to. (Enter 'cancel' to go back)");
        System.out.println(LINESPACING);
    }

    public void chooseDeleteStore() {
        System.out.println("Please select store to delete menu from. (Enter 'cancel' to go back)");
        System.out.println(LINESPACING);
    }

    public void enterMenuName() {
        System.out.println(LINESPACING);
        System.out.println("Please enter name of menu. (Enter 'cancel' to go back)");
        System.out.println(LINESPACING);
    }

    public void enterMenuPrice() {
        System.out.println(LINESPACING);
        System.out.println("Please enter price of menu. (Enter 'cancel' to go back)");
        System.out.println(LINESPACING);
    }

    public void showLoginPage() {
        System.out.println(LINESPACING);
        System.out.println("Enter 1 for Public User");
        System.out.println("Enter 2 for Admin");
        System.out.println(LINESPACING);
    }

    public void showGoodbye() {
        System.out.println(LINESPACING);
        System.out.println("Thank you for using our application! See you again!");
        System.out.println(LINESPACING);
    }

    public void showError(String errorMessage) {
        System.out.println(LINESPACING);
        System.out.println(errorMessage);
        System.out.println(LINESPACING);
    }

    public void showDisplaySelectStores(Canteen canteen) throws NusfrExceptions {
        if (canteen.getNumStores() <= 0) {
            throw new NusfrExceptions("There are currently no stores in " + canteen.getCanteenName() + "!");
        } else {
            ArrayList<Store> stores = canteen.getStores();
            System.out.println("Here's a list of the stores in the canteen: " + canteen.getCanteenName());
            System.out.println("Please select one of the following stores:"
                    + " (Enter 'login' to go back to login page)");
            for (int i = 0; i < stores.size(); i++) {
                System.out.print((i + 1) + ". ");
                stores.get(i).displayStore();
            }
        }
        System.out.println(Ui.LINESPACING);
    }

    public void showDisplayStores(Canteen canteen) {
        System.out.println(LINESPACING);
        if (canteen.getNumStores() <= 0) {
            System.out.println("There are currently no stores in " + canteen.getCanteenName() + "!");
        } else {
            ArrayList<Store> stores = canteen.getStores();
            System.out.println("Here's a list of the stores in the canteen: " + canteen.getCanteenName());
            for (int i = 0; i < stores.size(); i++) {
                System.out.print((i + 1) + ". ");
                stores.get(i).displayStore();
            }
        }
        System.out.println(Ui.LINESPACING);
    }

    public void showEmptyCanteen() {
        System.out.println(LINESPACING);
        System.out.println("There are no stores in this canteen!");
        System.out.println(LINESPACING);
    }

    public void showHelpCommand() {
        System.out.println(Ui.LINESPACING);
        System.out.println("Enter 'menu' to view sample menu");
        System.out.println("Enter 'reviews' to show reviews of that particular store");
        System.out.println("Enter 'add' to add a new review");
        System.out.println("Enter 'home' to select a new canteen");
        System.out.println("Enter 'list' to select a new store");
        System.out.println("Enter 'login' to re-access the app as a public/admin user");
        System.out.println("Enter 'exit' to exit the application");
        System.out.println(Ui.LINESPACING);
    }

    public void showDisplayMenu(String storeName, ArrayList<Menu> menus) {
        System.out.println(LINESPACING);
        int count = 1;
        if (menus.size() == 0) {
            System.out.println("This store has no menus yet!");
            return;
        }
        System.out.println("Here are the menus of the " + storeName + ":");
        for (Menu menuItem: menus) {
            System.out.println(count++ + ")" + menuItem.toString());
        }
        System.out.println(LINESPACING);
    }

    public void showNoMenuToDelete() {
        System.out.println(LINESPACING);
        System.out.println("There is no menus to delete!");
        System.out.println(LINESPACING);
    }

    public void showDisplaySelectCanteens(ArrayList<Canteen> canteens, String action) {
        System.out.println(LINESPACING);
        if (canteens.size() <= 0) {
            System.out.println("There are no canteens yet! Log in as admin to add a new canteen.");
            System.out.println("Enter 'login' to switch to Admin.");
            System.out.println(LINESPACING);
            return;
        }
        if (action.equals("delete")) {
            System.out.println("Select one of the following NUS canteens to " + action
                    + ": (Enter 'cancel' to go back)");
            for (int i = 0; i < canteens.size(); i++) {
                System.out.println(i + 1 + ". " + canteens.get(i).getCanteenName());
            }
            System.out.println(LINESPACING);
        } else {
            System.out.println("Select one of the following NUS canteens to " + action
                    + ": (Enter 'login' to go back to login page)");
            for (int i = 0; i < canteens.size(); i++) {
                System.out.println(i + 1 + ". " + canteens.get(i).getCanteenName());
            }
            System.out.println(LINESPACING);
        }
    }

    public void showDisplayCanteens(ArrayList<Canteen> canteens) {
        System.out.println(LINESPACING);
        if (canteens.size() == 0) {
            System.out.println("There are no canteens yet!");
        } else {
            System.out.println("Here is a list of NUS canteens:");
            for (int i = 0; i < canteens.size(); i++) {
                System.out.println(i + 1 + ". " + canteens.get(i).getCanteenName());
            }
        }
        System.out.println(LINESPACING);
    }

    public void showCanteenDeleted(Canteen canteen, int numRemainingCanteens) {
        System.out.println("The canteen '" + canteen.getCanteenName() + "' has been deleted.");
        System.out.println("You now have " + numRemainingCanteens + " canteens left.");
        System.out.println(LINESPACING);
    }

    public void showDeleteStore(String storeName) {
        System.out.println(LINESPACING);
        System.out.println("Store, " + "(" + storeName + ")" + " has been deleted.");
        System.out.println(LINESPACING);
    }

    public void showReviews(String storeName, ArrayList<Review> reviews,double averageRating) {
        System.out.println(LINESPACING);
        System.out.println("Here are the reviews of the " + storeName + ":");
        System.out.println("Recommended: " + Math.round(averageRating * 100.0) / 100.0 + "/5.0");
        System.out.println(LINESPACING);
        int count = 1;
        for (Review review: reviews) {
            System.out.println(count++ + ") " + review.toString());
            System.out.println("Customer rating: " + review.getRating());
            System.out.println(review.getDate());
            System.out.println(LINESPACING);
        }

    }

    public void showStoreOptions(String canteenName, String storeName) {
        System.out.println("You are now viewing the store: " + storeName + " in the canteen: " + canteenName);
        System.out.println("Please enter your command:");
        System.out.println("If you need help, enter 'help' to view all the commands");
        System.out.println("Enter 'login' if you want to go back to login page");
        System.out.println(LINESPACING);
    }

    public void enterReview() {
        System.out.println(LINESPACING);
        System.out.println("Please type your review: (Enter 'cancel' to go back)");
    }

    public void enterRating() {
        System.out.println(LINESPACING);
        System.out.println("Please give your rating from 1 to 5 (Enter 'cancel' to go back)");
    }

    public void reviewAdded() {
        System.out.println(LINESPACING);
        System.out.println("Review successfully added!");
        System.out.println(LINESPACING);
    }

    public void menuAdded(String menuName,String menuPrice) {
        System.out.println(LINESPACING);
        System.out.println("The menu " + "'" + menuName + "," + "$" + menuPrice + "'" + "successfully added!");
        System.out.println(LINESPACING);
    }

    public void reviewNotAdded() {
        System.out.println(LINESPACING);
        System.out.println("Cancelling.... Review not added");
        System.out.println(LINESPACING);
    }

    public void menuNotAdded() {
        System.out.println(LINESPACING);
        System.out.println("Cancelling.... Menu not added");
        System.out.println(LINESPACING);
    }

    public void showDeleteReview() {
        System.out.println("Please enter review number to be deleted (Enter 'cancel' to go back)");
        System.out.println(LINESPACING);
    }

    public void reviewDeleted() {
        System.out.println(LINESPACING);
        System.out.println("Review successfully deleted!");
        System.out.println(LINESPACING);
    }

    public void menuDeleted(String menuName) {
        System.out.println(LINESPACING);
        System.out.println("Menu, " + "(" + menuName + ")" + " has been deleted.");
        System.out.println(LINESPACING);
    }

    public void showNoCanteen() {
        System.out.println(Ui.LINESPACING);
        System.out.println("There are no canteens for you to view any stores yet.");
        System.out.println(Ui.LINESPACING);
    }

    public void chooseMenu() {
        System.out.println(LINESPACING);
        System.out.println("Enter menu to be deleted. (Enter 'cancel' to go back)");
        System.out.println(LINESPACING);
    }


    public void showAddCanteen() {
        System.out.println(LINESPACING);
        System.out.println("Please enter the name of the new canteen: (Enter 'cancel' to go back)");
        System.out.println(LINESPACING);
    }

    public void showAddStore() {
        System.out.println("Please enter the name of the new store: (Enter 'cancel' to go back)");
        System.out.println(LINESPACING);
    }

    public void showAddCanteenSuccess(String canteenName) {
        System.out.println("The canteen '" + canteenName + "' has been added!");
        System.out.println(LINESPACING);
    }

    public void showInvalidCanteenPrompt(String canteenName) {
        System.out.println("The canteen name '" + canteenName + "' is already taken! Please enter another name:");
    }

    public void showCanteenNotAdded() {
        System.out.println(LINESPACING);
        System.out.println("Cancelling.... Canteen not added");
        System.out.println(LINESPACING);
    }

    public void showInvalidStorePrompt(String storeName) {
        System.out.println("The store name '" + storeName + "' already exist! Please enter again:");
    }


    public void showStoreNotAdded() {
        System.out.println(LINESPACING);
        System.out.println("Cancelling.... Store not added");
        System.out.println(LINESPACING);
    }

    public void showCanteenNotDeleted() {
        System.out.println(LINESPACING);
        System.out.println("Cancelling.... Canteen not Deleted");
        System.out.println(LINESPACING);
    }

    public void showStoreNotDeleted() {
        System.out.println(LINESPACING);
        System.out.println("Cancelling.... Store not Deleted");
        System.out.println(LINESPACING);
    }

    public void showReviewNotDeleted() {
        System.out.println(LINESPACING);
        System.out.println("Cancelling.... Review not Deleted");
        System.out.println(LINESPACING);
    }

    public void showMenuNotDeleted() {
        System.out.println(LINESPACING);
        System.out.println("Cancelling.... Menu not Deleted");
        System.out.println(LINESPACING);
    }

    public static void showDirectoryCreated() {
        System.out.println(LINESPACING);
        System.out.println("Directory not found...New directory created.");
        System.out.println("New database file created...");
        System.out.println(LINESPACING);
    }

    public static void showDirectoryFound() {
        System.out.println(LINESPACING);
        System.out.println("Directory found!...");
        System.out.println("Data loaded successfully...");
        System.out.println(LINESPACING);
    }

}
