package admin;

import exceptions.NusfrException;
import ui.Ui;

public class AdminVerification {

    private static String actualPassword = "Password";
    private static Ui ui = new Ui();
    private static boolean isVerified = false;
    private static String inputPassword = null;

    public static void verifyInputPassword() {
        while (!isVerified) {
            try {
                inputPassword = ui.readCommand();
                if (!(inputPassword.equals(actualPassword))) {
                    throw new NusfrException("Wrong Password!");
                }
            } catch (NusfrException e) {
                ui.showError(e.getMessage());
                ui.adminPasswordReenter();
            }
            if (inputPassword.equals(actualPassword)) {
                isVerified = true;
            }
        }
        isVerified = false;
    }
}