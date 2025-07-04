package models;

import java.time.LocalDate;

/**
 * Product that can expire (like Cheese and Biscuits)
 */
public class ExpirableProduct extends Product{
    private LocalDate expirationDate;
    public ExpirableProduct(String name, double price, int quantity, LocalDate expirationDate){
        super(name, price, quantity);
        this.expirationDate = expirationDate;
    }
    
    @Override
    public boolean isExpired(){
        return LocalDate.now().isAfter(expirationDate);
    }
    
    public LocalDate getExpirationDate(){
        return expirationDate;
    }
    
    @Override
    public String toString(){
        return String.format("%s (Price: %.2f, Quantity: %d, Expires: %s)", name, price, quantity, expirationDate.toString());
    }
}