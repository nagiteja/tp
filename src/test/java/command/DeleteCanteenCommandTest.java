package command;

import canteens.Canteen;
<<<<<<< HEAD
import exceptions.NusfrException;
=======
>>>>>>> e45e7ef336233609d91482da16e1f194478ba7d5
import nusfoodreviews.NusFoodReviews;
import org.junit.jupiter.api.Test;
import parser.Parser;
import ui.Ui;


import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCanteenCommandTest {

    @Test
<<<<<<< HEAD
    public void execute_deleteExistingCanteen_success() throws IOException, NusfrException {
=======
    public void execute_deleteExistingCanteen_success() throws IOException, NusfrException {
>>>>>>> e45e7ef336233609d91482da16e1f194478ba7d5
        FileWriter fw = new FileWriter("dataTest/deleteTest.txt");
        fw.write("canteen name");
        InputStream inputStream = NusFoodReviews.class.getClassLoader().getResourceAsStream("storage.txt");
        InputStreamReader streamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(streamReader);

        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);

        ArrayList<Canteen> canteens = new ArrayList<Canteen>();
        Ui ui = new Ui();
        NusFoodReviews nusFoodReviews = new NusFoodReviews(reader);
        Parser parser = new Parser(nusFoodReviews, ui);

        canteens.add(new Canteen("canteen name"));
        DeleteCanteenCommand c = new DeleteCanteenCommand(parser, "dataTest/deleteTest.txt");
        c.execute(canteens, ui);
        assertEquals(0, canteens.size());
    }
}
