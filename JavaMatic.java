import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;

public class JavaMatic {

    public static void main(String[] args) {
    	JavaMachine machine = new JavaMachine();
    	String[] ingredients = { "Coffee", "Decaf Coffee", "Sugar", "Cream", "Steamed Milk",
    	                         "Foamed Milk", "Espresso", "Cocoa", "Whipped Cream" };
    	for (int ingrt = 0; ingrt < ingredients.length; ingrt++) {
    		machine.init_item(ingredients[ingrt], 10);
    	}
    	machine.print_inventory();

    	// add ingredients to machine
    	HashMap<String, Double> prices = new HashMap<String, Double>();
    	prices.put("Coffee", 0.75);
    	prices.put("Decaf Coffee", 0.75);
    	prices.put("Sugar", 0.25);
    	prices.put("Cream", 0.25);
    	prices.put("Steamed Milk", 0.35);
    	prices.put("Foamed Milk", 0.35);
    	prices.put("Espresso", 1.10);
    	prices.put("Cocoa", 0.90);
    	prices.put("Whipped Cream", 1.00);
    	machine.init_prices(prices);

    	// add drinks to machine
    	Drink coffee = new Drink("Coffee");
    	String[] ingrs = { "Coffee", "Sugar", "Cream" };
    	Integer[] counts = { 3, 1, 1 };
    	coffee.init_ingredients(ingrs, counts);
    	machine.add_drink(coffee);

    	String[] ingrs1 = { "Decaf Coffee", "Sugar", "Cream" };
    	Integer[] counts1 = { 3, 1, 1 };
    	Drink decaf_coffee = new Drink("Decaf Coffee");
    	decaf_coffee.init_ingredients(ingrs1, counts1);
    	machine.add_drink(decaf_coffee);

    	String[] ingrs2 = { "Espresso", "Steamed Milk" };
    	Integer[] counts2 = { 2, 1 };
    	Drink latte = new Drink("Caffe Latte");
    	latte.init_ingredients(ingrs2, counts2);
    	machine.add_drink(latte);

    	String[] ingrs3 = { "Espresso" };
    	Integer[] counts3 = { 3 };
    	Drink americano = new Drink("Caffe Americano");
    	americano.init_ingredients(ingrs3, counts3);
    	machine.add_drink(americano);

    	String[] ingrs4 = { "Espresso", "Cocoa", "Steamed Milk", "Whipped Cream" };
    	Integer[] counts4 = { 1, 1, 1, 1 };
    	Drink mocha = new Drink("Caffe Mocha");
    	mocha.init_ingredients(ingrs4, counts4);
    	machine.add_drink(mocha);

    	String[] ingrs5 = { "Espresso", "Steamed Milk", "Foamed Milk" };
    	Integer[]counts5 = { 2, 1, 1 };
    	Drink cappuccino = new Drink("Cappuccino");
    	cappuccino.init_ingredients(ingrs5, counts5);
    	machine.add_drink(cappuccino);

        machine.print_menu();

        startup(machine, ingredients);
        return;
    }

    public static void startup(JavaMachine machine, String[] ingredients) {
    	while(true) {
    		Scanner stream = new Scanner(System.in);
    		String input = stream.next();
    		if (input.equals("r") || input.equals("R")) {
    			for (int ingrt = 0; ingrt < ingredients.length; ingrt++) {
		    		machine.init_item(ingredients[ingrt], 10);
		    	}
		    	machine.init_stock();
    			machine.print_inventory();
    			machine.print_menu();
    		} else if (input.equals("q") || input.equals("Q")) {
    			break;
    		} else {
    			try {
    				Integer drink_choice = Integer.parseInt(input);
    				if (drink_choice > 6 || drink_choice < 1) {
    					System.out.println("Invalid selection: " + input);
    				}
    				machine.order_drink(drink_choice);
    				machine.print_inventory();
    				machine.print_menu();
    			} catch (NumberFormatException e) {
				    System.out.println("Invalid selection: " + input);
				}
    		}
    	}
    }
}

