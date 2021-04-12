package checkuser;

import exceptions.NusfrExceptions;
import ui.Ui;

/**
 * This class is will check whether the user is a public user or an admin user.
 * It uses conditional statements to check and identify.
 */
public class UserChecker {
    /**
     * This method will check the whther the user is a public or an admin user. If 1 is inputted then it is user.
     * If 2 is inputted then it is admin.
     * @param ui This object is passed in to get the user input.
     * @return returns a boolean variable depending on the user input.
     * @throws NusfrExceptions will be thrown when the user input is not equal to 1 or 2 or exit.
     */
    public static boolean checkUserType(Ui ui) throws NusfrExceptions {
        while (true) {
            String input = ui.readCommand();
            if (input.equals("1")) {
                return true;
            } else if (input.equals("2")) {
                return false;
            } else if (input.equals("exit")) {
                ui.showGoodbye();
                System.exit(0);
            } else {
                ui.showError("Wrong input, enter either 1 or 2.");
            }
        }
    }
}
