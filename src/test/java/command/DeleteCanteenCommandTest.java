package command;

import canteens.Canteen;
import exceptions.NusfrExceptions;
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
    public void execute_deleteExistingCanteen_success() throws IOException, NusfrExceptions {
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
