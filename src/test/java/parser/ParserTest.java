package parser;

import command.Command;
import command.DisplayMenusCommand;
import command.ExitCommand;
import command.HomeCommand;
import command.ReadReviewsCommand;
import command.ResetStoreCommand;
import nusfoodreviews.NusFoodReviews;
import stores.Store;
import exceptions.NusfrExceptions;
import org.junit.jupiter.api.Test;
import canteens.Canteen;
import ui.Ui;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ParserTest {

    Canteen canteen = new Canteen("The Deck");
    Store store = new Store("Techno Edge");
    InputStream inputStream = NusFoodReviews.class.getClassLoader().getResourceAsStream("storage.txt");
    InputStreamReader streamReader = new InputStreamReader(inputStream);
    BufferedReader reader = new BufferedReader(streamReader);
    NusFoodReviews nusFoodReviews = new NusFoodReviews(reader);
    Ui ui = new Ui();

    ParserTest() throws IOException {
    }

    @Test
    public void parse_list_displayCommand() throws NusfrExceptions {
        Parser parser = new Parser(nusFoodReviews, ui);
        Command c = parser.parse("list",store, canteen);
        assertTrue(c instanceof ResetStoreCommand);
    }

    @Test
    public void parse_menu_success() throws NusfrExceptions {
        Parser parser = new Parser(nusFoodReviews, ui);
        Command c = parser.parse("menu",store, canteen);
        assertTrue(c instanceof DisplayMenusCommand);
    }

    @Test
    public void parse_ExceedStoreIndex_exceptionThrown() throws NusfrExceptions {
        Parser parser = new Parser(nusFoodReviews, ui);
        try {
            parser.parseInt("0",1, 1);
        } catch (Exception e) {
            assertEquals("Please enter a valid index!", e.getMessage());
        }
    }

    @Test
    public void parse_exit_displayCommand() throws NusfrExceptions {
        Parser parser = new Parser(nusFoodReviews, ui);
        Command c = parser.parse("exit",store, canteen);
        assertTrue(c instanceof ExitCommand);
    }

    @Test
    public void parse_home_displayCommand() throws NusfrExceptions {
        Parser parser = new Parser(nusFoodReviews, ui);
        Command c = parser.parse("home",store, canteen);
        assertTrue(c instanceof HomeCommand);
    }

    @Test
    public void parse_reviews_displayCommand() throws NusfrExceptions {
        Parser parser = new Parser(nusFoodReviews, ui);
        Command c = parser.parse("reviews",store, canteen);
        assertTrue(c instanceof ReadReviewsCommand);
    }

}