package command;

import canteens.Canteen;
import exceptions.NusfrException;
import org.junit.jupiter.api.Test;
import ui.Ui;

import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCanteenCommandTest {

    @Test
    public void execute_validCanteenName_success() throws IOException, NusfrException {
        String canteenName = "Valid Canteen Name";

        ByteArrayInputStream in = new ByteArrayInputStream(canteenName.getBytes());
        System.setIn(in);

        ArrayList<Canteen> canteens = new ArrayList<>();
        Ui ui = new Ui();

        AddCanteenCommand c = new AddCanteenCommand("dataTest/addTest.txt");
        c.execute(canteens, ui);
        assertEquals(canteens.get(0).getCanteenName(), canteenName);
        FileWriter fw = new FileWriter("dataTest/addTest.txt"); // clears all entries
    }

}
