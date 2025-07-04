package models;

/**
 * Base Product class with name, price, and quantity
 */
public class Product{
    protected String name;
    protected double price;
    protected int quantity;
    
    public Product(String name, double price, int quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    
    public String getName(){
        return name;
    }
    
    public double getPrice(){
        return price;
    }
    
    public int getQuantity(){
        return quantity;
    }
    
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    
    public boolean isAvailable(int requestedQuantity){
        return quantity >= requestedQuantity;
    }
    
    public void reduceQuantity(int amount){
        if (amount <= quantity) {
            quantity -= amount;
        }
    }
    
    public boolean isExpired(){
        return false; // Base products don't expire
    }
    
    public boolean requiresShipping(){
        return false; // Base products don't require shipping
    }
    
    @Override
    public String toString(){
        return String.format("%s (Price: %.2f, Quantity: %d)", name, price, quantity);
    }
}