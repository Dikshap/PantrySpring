package main.java.pantry;
import java.time.LocalDate;

public class Client {
  public static void main(String[] args) {
    System.out.println("Spring Food Pantry");
    Inventory inventory = Inventory.getInstance();
    inventory.display_inventory();

    Provider provider1 = new Provider("Org1", "organization");
    Item apple1 = new Item().setCode("110022").setCost(3).setDateReceived(LocalDate.of(2020, 04, 01)).setExpiryDate(LocalDate.of(2020, 05, 01)).setName("Apple").setQty(12);
    Item apple2 = new Item().setCode("110022").setCost(5).setDateReceived(LocalDate.of(2020,04,06)).setExpiryDate(LocalDate.of(2020,05,10)).setName("Apple").setQty(12);
    provider1.add_item(apple1.getCode(), apple1.getName(), apple1.getCost(), apple1.PLU(), apple1.days_until_exp(), apple1.getQty());
    provider1.add_item(apple2.getCode(), apple2.getName(), apple2.getCost(), apple2.PLU(), apple2.days_until_exp(), apple2.getQty());
    Transaction trans1 = new Transaction(provider1.get_donated_sold(), "Purchase", provider1);
    trans1.display_transaction();

    inventory.display_inventory();

/*
    Provider provider2 = new Provider("Org2", "organiztion");
    Item apple2 = new Item().setCode("110022").setCost(5).setDateReceived(LocalDate.of(2020,04,06)).setExpiryDate(LocalDate.of(2020,05,10)).setName("Apple").setQty(12);
*/
    Provider provider = new Provider("Hunger Fighters", "organization");
    Provider provider2 = new Provider("Annie Bidwell", "community member");

    System.out.println("Provider: " + provider.get_provider_info());
    provider.add_item("94011", "Bananas", 0.99, true, 14, 10.5);
    provider.add_item("3424", "Carrots", 0.75, true, 21, 5.75);
    provider.add_item("3424", "Carrots", 0.00, true, 21, 4.25);
    provider.show_items();

    System.out.println("Provider2: " + provider2.get_provider_info());
    provider2.add_item("894455000322", "Almond Butter", 0.0, false, 180, 5.0);
    provider2.show_items();
  }
}
