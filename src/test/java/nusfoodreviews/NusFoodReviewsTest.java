package nusfoodreviews;

import static org.junit.jupiter.api.Assertions.assertTrue;
import exceptions.NusfrException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

class NusFoodReviewsTest {
    private NusFoodReviews nusFoodReviews = null;

    @AfterEach
    public void tearDown() {
        nusFoodReviews = null;
    }

    public void initializeNusFoodReviews(String input) throws IOException {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        InputStream inputStream = NusFoodReviews.class.getClassLoader().getResourceAsStream("storage.txt");
        InputStreamReader streamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(streamReader);
        nusFoodReviews = new NusFoodReviews(reader);
    }

    @Test
    public void resetAllIndexes_null_negatives() throws IOException {
        initializeNusFoodReviews("");
        nusFoodReviews.resetAllIndex();
        assertTrue(nusFoodReviews.getUserIndex() == -1);
        assertTrue(nusFoodReviews.getCanteenIndex() == -1);
        assertTrue(nusFoodReviews.getStoreIndex() == -1);
    }

    @Test
    public void resetCanteenStoreIndex_null_negatives() throws IOException {
        initializeNusFoodReviews("");
        nusFoodReviews.resetCanteenStoreIndex();
        assertTrue(nusFoodReviews.getCanteenIndex() == -1);
        assertTrue(nusFoodReviews.getStoreIndex() == -1);
    }

    @Test
    void chooseUser_one_publicUser() throws IOException, NusfrException {
        initializeNusFoodReviews("1\r\n");

        int output = nusFoodReviews.chooseUser();
        assertTrue(output == 0);
    }
}
