package checkuser;

import exceptions.NusfrException;
import ui.Ui;

public class UserChecker {

    public static boolean checkUserType(Ui ui) throws NusfrException {
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
