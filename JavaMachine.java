import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class JavaMachine {
    private HashMap<String, Integer> Inventory;
    private HashMap<String, Double> Ingr_Prices;
    private HashMap<String, Double> Drink_Prices;
    private ArrayList<Drink> Drinks;
    private ArrayList<String> Unavailable;
    private Boolean Sorted;

    public JavaMachine() {
    	Inventory = new HashMap<String, Integer>();
    	Ingr_Prices = new HashMap<String, Double>();
        Drink_Prices = new HashMap<String, Double>();
        Drinks = new ArrayList<>();
        Unavailable = new ArrayList<>();
        Sorted = false;
    }

    public void init_item(String name, Integer count) {
    	Inventory.put(name, count);
    }

    public void init_prices(HashMap<String, Double> prices) {
        for (HashMap.Entry<String, Double> entry : prices.entrySet()) {
            String ingr = entry.getKey();
            Double price = entry.getValue();
            Ingr_Prices.put(ingr, price);
        }
    }

    public void init_stock() {
        Unavailable = new ArrayList<>();
    }

    public void add_drink(Drink drink) {
        Drinks.add(drink);
        Double calc_price = 0.0;
        for (HashMap.Entry<String, Integer> entry : drink.get_ingredients().entrySet()) {
            String ingr = entry.getKey();
            Integer count = entry.getValue();
            calc_price += Ingr_Prices.get(ingr) * count;
        }
        Drink_Prices.put(drink.get_name(), calc_price);
    }

    public void print_inventory() {
        String out = "Inventory:\n" +
                     "Coffee," + Inventory.get("Coffee") + "\n" +
                     "Decaf Coffee," + Inventory.get("Decaf Coffee") + "\n" +
                     "Sugar," + Inventory.get("Sugar") + "\n" +
                     "Cream," + Inventory.get("Cream") + "\n" +
                     "Steamed Milk," + Inventory.get("Steamed Milk") + "\n" +
                     "Foamed Milk," + Inventory.get("Foamed Milk") + "\n" +
                     "Espresso," + Inventory.get("Espresso") + "\n" +
                     "Cocoa," + Inventory.get("Cocoa") + "\n" +
                     "Whipped Cream," + Inventory.get("Whipped Cream");
        System.out.println(out);
    }

    public void print_menu() {
        String out = "Menu:\n";
        if (!Sorted) {
            sort_drinks();
        }
        for (int drink = 0; drink < Drinks.size(); drink++) {
            Drink current = Drinks.get(drink);
            out += Integer.toString(current.get_id()) + ',' +
                   current.get_name() + ',' +
                   String.format("$%.2f,", Drink_Prices.get(current.get_name())) +
                   check_avail(current);
            if (drink < Drinks.size() - 1) {
                out += "\n";
            }
        }
        System.out.println(out);
    }

    public void order_drink(Integer menu_id) {
        for (int drink = 0; drink < Drinks.size(); drink++) {
            Drink curr = Drinks.get(drink);
            if (curr.get_id() == menu_id) {
                if (check_avail(curr).equals("true")) {
                    System.out.println("Dispensing: " + curr.get_name());
                    // subtract ingredients from inventory
                    for (HashMap.Entry<String, Integer> entry : curr.get_ingredients().entrySet()) {
                        String ingr = entry.getKey();
                        Integer amount = entry.getValue();
                        Integer old = Inventory.get(ingr);
                        Inventory.put(ingr, old - amount);
                    }
                } else {
                    System.out.println("Out of stock: " + curr.get_name());
                }
            }
        }
    }

    // private methods
    private String check_avail(Drink drink) {
        if (Unavailable.contains(drink.get_name())) {
            return "false";
        }
        for (HashMap.Entry<String, Integer> entry : drink.get_ingredients().entrySet()) {
            String ingr = entry.getKey();
            Integer amount = entry.getValue();
            if (Inventory.get(ingr) < amount) {
                Unavailable.add(drink.get_name());
                return "false";
            }
        }
        return "true";
    }

    private void sort_drinks() {
        // sort drinks alphabetically and set drink id
         Collections.sort(Drinks, new Comparator<Drink>() {
            @Override
            public int compare(Drink d1, Drink d2) {
                return d1.get_name().compareTo(d2.get_name());
            }
        });

        for (int drink = 0; drink < Drinks.size(); drink++) {
            Drink current = Drinks.get(drink);
            current.set_id(drink + 1);            
        }
        Sorted = true;
    }
}
