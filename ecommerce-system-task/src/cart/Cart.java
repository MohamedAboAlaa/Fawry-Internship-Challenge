package cart;

import models.Product;
import java.util.HashMap;
import java.util.Map;

/**
 * Shopping cart to manage products and quantities
 */
public class Cart {
    private Map<Product, Integer> items;
    
    public Cart(){
        this.items = new HashMap<>();
    }
    
    public void add(Product product, int quantity){
        if(product == null){
            throw new IllegalArgumentException("Product cannot be null");
        }
        
        if(quantity <= 0){
            throw new IllegalArgumentException("Quantity must be positive");
        }
        
        if(!product.isAvailable(quantity)){
            throw new IllegalArgumentException("Not enough stock for " + product.getName() + ". Available: " + product.getQuantity() + ", Requested: " + quantity);
        }
        
        items.put(product, items.getOrDefault(product, 0) + quantity);
    }
    
    public void remove(Product product){
        items.remove(product);
    }
    
    public void clear(){
        items.clear();
    }
    
    public boolean isEmpty(){
        return items.isEmpty();
    }
    
    public Map<Product, Integer> getItems(){
        return new HashMap<>(items);
    }
    
    public double getSubtotal(){
        return items.entrySet().stream()
                   .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                   .sum();
    }
    
    public int getTotalItemCount(){
        return items.values().stream().mapToInt(Integer::intValue).sum();
    }
    
    @Override
    public String toString(){
        if(isEmpty()){
            return "Cart is empty";
        }
        StringBuilder sb = new StringBuilder("Cart contents:\n");
        for(Map.Entry<Product, Integer> entry : items.entrySet()){
            sb.append(String.format("- %dx %s\n", entry.getValue(), entry.getKey().getName()));
        }
        return sb.toString();
    }
}