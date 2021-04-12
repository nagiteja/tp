package admin;


import exceptions.NusfrException;
import ui.Ui;

/**
 * This AdminVerification class is basically used to check if the the password input by the user is the right one or not.
 * If correct we can access the admin mode and its features.
 */

public class AdminVerification {

    private static String actualPassword = "Password";
    private static Ui ui = new Ui();
    private static boolean isVerified = false;
    private static String inputPassword = null;

    /**
     * This method is where you will check for the verification. This is a while loop and this will continue to
     * execute until the correct password is entered.
     */
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