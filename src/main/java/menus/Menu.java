package menus;

/**
 * This is a menu class which will basically store the name of the dish and the price of the dish.
 */
public class Menu {

    private String itemName;
    private double price;

    /**
     * This is the Menu class constructor.
     * @param itemName This is a string variable and contains the name of the dish.
     * @param price This is a double variable and will contain the price of the dish.
     */
    public Menu(String itemName, double price) {
        this.itemName = itemName;
        this.price = price;
    }

    /**
     * This will print out the name of the item and the price of the item.
     * @return a bit string containing the name of the dish nd the price next to it.
     */
    @Override
    public String toString() {
        return itemName + " ($" + String.valueOf(price) + ")";
    }

    /**
     * The is the getter method for the Item name.
     * @return a string containing the name of the dish
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * This is the getter method fro the price of the item.
     * @return a double variable containing the price of the item.
     */
    public double getPrice() {
        return price;
    }
}
