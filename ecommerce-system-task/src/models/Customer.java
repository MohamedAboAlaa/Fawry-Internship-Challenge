package models;

/**
 * Customer class with balance management
 */
public class Customer {
    private String name;
    private double balance;
    
    public Customer(String name, double balance){
        this.name = name;
        this.balance = balance;
    }
    
    public String getName(){
        return name;
    }
    
    public double getBalance(){
        return balance;
    }
    
    public void setBalance(double balance){
        this.balance = balance;
    }
    
    public boolean canAfford(double amount){
        return balance >= amount;
    }
    
    public void deductBalance(double amount){
        if (canAfford(amount)) {
            balance -= amount;
        }
    }
    
    @Override
    public String toString(){
        return String.format("Customer: %s (Balance: %.2f)", name, balance);
    }
}