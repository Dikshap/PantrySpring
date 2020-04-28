package main.java.pantry;

import java.util.UUID;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.ArrayList;

public class Provider {
  private final UUID _uuid;
  private final String _name;
  private final ProviderType _type;
  private final HashMap<String, ArrayList<Item>> donated_sold = new HashMap<>();

  // Constructor
  // create a provider with random UUID, given name, and given type
  // (organization or community member)
  Provider(String name, String type) {
    _uuid = UUID.randomUUID();
    _name = name;
    if(type.equals("organization")){
      _type = ProviderType.ORGANIZATION;
    }
    else{
      _type = ProviderType.COMMUNITYMEMBER;
    }
  }

  // Return the UUID, name, and type of this Provider
  public ArrayList<String> get_provider_info(){
    ArrayList<String> provider = new ArrayList<>();
    provider.add(_uuid.toString());
    provider.add(_name);
    provider.add(_type.name());
    return provider;
  }

  // Create an Item (code, name, cost (0 if free), PLU? (true if PLU, false if
  // UPC), date received, date item expires, quantity of the item being
  // provided at this time, and a generated id)
  // If the code doesn't already exist, add it as a key to donated_sold
  // Then add the item to the list of items with that code
  public void add_item(String code, String name, double cost, boolean plu,
    int days_until_exp, double qty){
    Item _item = new Item();
    _item.code = code;
    _item.name = name;
    _item.cost = cost;
    _item.plu = plu;
    _item.date_received = LocalDate.now();
    _item.exp_date = _item.date_received.plus(days_until_exp, ChronoUnit.DAYS);
    _item.qty = qty;
    _item.id = UUID.randomUUID();

    //see if we already have an item list for current code (key)
    ArrayList<Item> item_list = donated_sold.computeIfAbsent(_item.code, k -> new ArrayList<>());
    //if not create one and put it in the map
    item_list.add(_item);
  }

  public void show_items() {
    NumberFormat formatter = NumberFormat.getCurrencyInstance();
    String type;

    for(HashMap.Entry<String, ArrayList<Item>> entry:donated_sold.entrySet()) {
      int count = 1;
      System.out.println("Item Key " + entry.getKey() + ":");
      for(Item it : entry.getValue()) {
        type = it.plu ? "PLU":"UPC";
        System.out.println("\t" + count + ".");
        System.out.println("\tItem code: " + it.code);
        System.out.println("\tItem name: " + it.name);
        System.out.println("\tItem cost: " + formatter.format(it.cost));
        System.out.println("\tItem PLU? " + type);
        System.out.println("\tItem date received: " + it.date_received);
        System.out.println("\tItem expiration date: " + it.exp_date);
        if(it.plu){
          System.out.println("\tQuantity: " + it.qty + " lbs");
        }
        else{
          System.out.println("\tQuantity: " + it.qty + " units");
        }
        count++;
      }
    }
  }

  public HashMap<String, ArrayList<Item>> get_donated_sold(){
    return donated_sold;
  }
}