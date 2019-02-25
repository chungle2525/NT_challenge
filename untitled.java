import java.util.HashMap;

public class JavaMachine {
    public HashMap<String, Integer> Ingredients;
    public HashMap<String, Double> Ingr_Prices;
    public HashMap<String, Double> Drink_Prices;

    public JavaMachine() {
    	Ingredients = new HashMap<String, Integer>();
    	Ingr_Prices = new HashMap<String, Double>();
        Drink_Prices = new HashMap<String, Double>();
    }

    public void init_ingredients(String[] names, Integer[] counts,
    	                         HashMap<String, Double> prices) {
        Ingredients = new HashMap<String, Integer>();
    	Double calc_price = 0.0;
    	for (int ingredient = 0; ingredient < names.length; ingredient++) {
	        Ingredients.put(names[ingredient], counts[ingredient]);
	        calc_price += (prices.get(names[ingredient]) * counts[ingredient]);
	    }
	    Price = calc_price;
    }

    public void add_ingredient(String name, Integer count) {
    	Ingredients.put(name, count);
    }

    public HashMap<String, Integer> get_ingredients() {
        return Ingredients;
    }
}
