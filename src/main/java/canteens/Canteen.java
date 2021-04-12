package canteens;

import stores.Store;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a canteen, with all its stores and functionality.
 */
public class Canteen {
    private String canteenName;
    private ArrayList<Store> stores;

    private static Logger logger = Logger.getLogger(Store.class.getName());

    /**
     * This is a constructor.
     * @param canteenName This will be set by the constructor and this will be then passed on to the logger
     */
    public Canteen(String canteenName) {
        this.canteenName = canteenName;
        stores = new ArrayList<>();
        logger.log(Level.FINER, "New Canteen object " + canteenName + " created");
    }

    /**
     * This method basically logs whenever a new store is created and this will also crate a new store object within the
     * respective canteen.
     * @param storeName This String argument will be passed on to the logger and will also be passed in while creating
     *                  a new store object.
     */
    public void addStore(String storeName) {
        logger.log(Level.FINER, "Creating new store in " + canteenName);
        Store newStore = new Store(storeName);
        stores.add(newStore);
    }

    /**
     * This method will delete stores.
     * @param storeIndex Based on the index provided the stores object will remove the respective store from the canteen
     *
     */
    public void deleteStore(int storeIndex) {
        stores.remove(storeIndex);
    }

    /**
     * This method will get us the number of stores within a canteen.
     * @return This will return a int which contains all the stores of a canteen.
     */
    public int getNumStores() {
        return stores.size();
    }

    /**
     * This method will give us a list of all the stores.
     * @return This will return a stores object arraylist
     */
    public ArrayList<Store> getStores() {
        return stores;
    }

    /**
     * This method will get us the store object based on the index inputted by the user.
     * @param index This index is inputted by the user
     * @return This returns a store object which is based on the index.
     */
    public Store getStore(int index) {

        return stores.get(index);
    }

    /**
     * This method is a getter method like before and we will get the name of the canteen.
     * @return Will return a string which is the name of the current canteen.
     */
    public String getCanteenName() {
        return canteenName;
    }
}
