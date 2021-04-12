package command;

import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelpCommandTest {
    @Test
    public void testHelpCommand(){
        String temp = "====================================================" + "\nEnter 'menu' to view sample menu"
                + "\nEnter 'reviews' to show reviews of that particular store" + "\nEnter 'add' to add a new review" +
                "\nEnter 'home' to select a new canteen" + "\nEnter 'list' to select a new store" + "\nEnter 'login' to re-access the app as a public/admin user"
                + "\nEnter 'exit' to exit the application" + "\n====================================================";
        assertEquals(temp, temp);

    }
}


