package nusfoodreviews;

import admin.AdminVerification;
import canteens.Canteen;
import command.Command;
import exceptions.NusfrException;
import parser.Parser;
import storage.ReadFiles;
import storage.Storage;
import stores.Store;
import ui.Ui;
import checkuser.UserChecker;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * This is the main class of the application.
 * This is the starting point for the application as well.
 */
public class NusFoodReviews {
    private ArrayList<Canteen> canteens; // todo: add a canteen manager
    private Ui ui;
    private Storage storage;
    private Parser parser;
    private boolean isExit = false;
    private static int userIndex = -1;   // userIndex=0 for Public, userIndex=1 for admin
    private static int canteenIndex = -1;
    private static int storeIndex = -1;

    /**
     * This is the constructor for the main class.
     * @param reader takes in the value from the data file.
     * @throws IOException will be thrown if there is an error while reading from the data file.
     */
    public NusFoodReviews(BufferedReader reader) throws IOException {
        ui = new Ui();
        parser = new Parser(this, ui);
        storage = new ReadFiles(reader);
        canteens = storage.execute();
    }

    /**
     * Main entry-point for the java.nusfoodreviews.NusFoodReviews application.
     */
    public static void main(String[] args) throws NusfrException, IOException {
        InputStream inputStream = NusFoodReviews.class.getClassLoader().getResourceAsStream("storage.txt");
        InputStreamReader streamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(streamReader);
        new NusFoodReviews(reader).run();
    }

    /**
     * This is the first method to be called. Based on the user's input, the user will enter either public user mode or
     * the admin user mode.
     * @throws NusfrException will be thrown whenever the user inputs illegal characters
     */
    public void run() throws NusfrException {
        ui.showLogo();
        while (true) {
            if (userIndex == -1) {
                assert (canteenIndex == -1);
                assert (storeIndex == -1);
                userIndex = chooseUser();
            } else if (userIndex == 1) {
                runAdmin();
            } else if (userIndex == 0) {
                runPublicUser();
            } else {
                System.exit(1);
            }
        }
    }


    /**
     * This method will display the appropriate message depending on the user input and which mode he/she has chosen.
     * @return an integer 0 or 1.
     * @throws NusfrException will be thrown if the user input has illegal characters
     */
    public int chooseUser() throws NusfrException {
        ui.showLoginPage();
        boolean isPublicUser = UserChecker.checkUserType(ui);
        if (isPublicUser) {
            ui.userShowWelcome();
            return 0;
        } else {
            ui.adminShowWelcome();
            AdminVerification.verifyInputPassword();
            ui.showAdminVerified();
            return 1;
        }
    }

    /**
     * This is the main public user method.
     */
    public void runPublicUser() {
        try {
            if (canteenIndex < 0) {
                setCanteenIndex();
            } else if (storeIndex < 0) {
                setStoreIndex();
            } else {
                Canteen canteen = canteens.get(canteenIndex);
                Store store = canteen.getStore(storeIndex);
                ui.showStoreOptions(canteen.getCanteenName(),
                        store.getStoreName());
                String line = ui.readCommand();
                Command c = parser.parse(line, store, canteen);
                c.execute(canteens, ui);
            }
        } catch (NusfrException | IOException e) {
            ui.showError(e.getMessage());
        }
    }

    public void runAdmin() {
        ui.showAdminOptions();
        try {
            String line = ui.readCommand();
            Command c = parser.parseAdminCommand(line);
            c.execute(canteens, ui);
        } catch (NusfrException | IOException e) {
            ui.showError(e.getMessage());
        }
    }

    public static void resetAllIndex() {
        userIndex = -1;
        canteenIndex = -1;
        storeIndex = -1;
    }

    public static void resetCanteenStoreIndex() {
        canteenIndex = -1;
        storeIndex = -1;
    }

    public static void resetStoreIndex() {
        storeIndex = -1;
    }

    public int getCanteenIndex() {
        return canteenIndex;
    }

    public int getUserIndex() {
        return userIndex;
    }

    public void setCanteenIndex() throws NusfrException {
        ui.showDisplaySelectCanteens(canteens, "view");
        String line = ui.readCommand();

        if (line.equals("exit")) {
            ui.showGoodbye();
            System.exit(0);
        } else if (line.equals("cancel")) {
            canteenIndex = -1;
            return;
        } else if (line.equals("login")) {
            resetAllIndex();
            return;
        }

        if (canteens.size() > 0) {
            canteenIndex = parser.parseInt(line, 1, canteens.size()) - 1;
        }
    }

    public int getStoreIndex() {
        return storeIndex;
    }


    public void setStoreIndex() throws NusfrException {
        Canteen canteen = canteens.get(canteenIndex);
        ui.showDisplaySelectStores(canteen);
        if (canteen.getNumStores() < 1) {
            resetCanteenStoreIndex();
            return;
        }
        String line = ui.readCommand();
        if (line.equals("exit")) {
            ui.showGoodbye();
            System.exit(0);
        } else if (line.equals("cancel")) {
            resetCanteenStoreIndex();
            return;
        } else if (line.equals("login")) {
            resetAllIndex();
            return;
        } else if (line.equals("home")) {
            resetCanteenStoreIndex();
            return;
        }
        storeIndex = parser.parseInt(line, 1, canteen.getNumStores()) - 1;
    }

    public ArrayList<Canteen> getCanteens() {
        return canteens;
    }
}
