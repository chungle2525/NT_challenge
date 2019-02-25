import java.util.HashMap;

public class Drink {
    private Integer Menu_id;
	private String Name;
    private HashMap<String, Integer> Ingredients;

    public Drink(String name_in) {
        Menu_id = 0;
    	Name = name_in;
        Ingredients = new HashMap<String, Integer>();
    }

    public String get_name() {
        return Name;
    }

    public Integer get_id() {
        return Menu_id;
    }

    public void set_id(Integer id_in) {
        Menu_id = id_in;
    }

    public void init_ingredients(String[] names, Integer[] counts) {
    	for (int ingredient = 0; ingredient < names.length; ingredient++) {
	        Ingredients.put(names[ingredient], counts[ingredient]);
	    }
    }

    public HashMap<String, Integer> get_ingredients() {
        return Ingredients;
    }
}
