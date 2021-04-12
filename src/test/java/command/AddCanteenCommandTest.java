package command;

import canteens.Canteen;
<<<<<<< HEAD
import exceptions.NusfrException;
=======
>>>>>>> e45e7ef336233609d91482da16e1f194478ba7d5
import org.junit.jupiter.api.Test;
import ui.Ui;

import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCanteenCommandTest {

    @Test
<<<<<<< HEAD
    public void execute_validCanteenName_success() throws IOException, NusfrException {
=======
    public void execute_validCanteenName_success() throws IOException, NusfrException {
>>>>>>> e45e7ef336233609d91482da16e1f194478ba7d5
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
