package services;

import interfaces.Shippable;
import java.util.List;

/**
 * ShippingService that accepts items implementing Shippable interface
 * Calculates shipping fees and prints shipment details
 */
public class ShippingService {
    private static final double SHIPPING_RATE_PER_KG = 20.0; // to match expected output
    private static final double BASE_SHIPPING_FEE = 10.0;
    
    public static double calculateShippingFee(List<Shippable> shippableItems){
        if(shippableItems.isEmpty()){
            return 0.0;
        }
        double totalWeight = shippableItems.stream().mapToDouble(Shippable::getWeight).sum();
        return BASE_SHIPPING_FEE + (totalWeight * SHIPPING_RATE_PER_KG);
    }
    
    public static void processShipment(List<Shippable> shippableItems, java.util.Map<String, Integer> itemQuantities){
        if(shippableItems.isEmpty()){
            return;
        }
        
        System.out.println("----------------------");
        System.out.println("** Shipment notice **");
        
        double totalWeight = 0.0;
        for(Shippable item : shippableItems){
            int quantity = itemQuantities.getOrDefault(item.getName(), 1);
            double itemWeight = item.getWeight() * quantity;
            totalWeight += itemWeight;
            System.out.printf("%dx %s %.0fg%n", quantity, item.getName(), item.getWeight() * 1000);
        }
        System.out.printf("Total package weight %.1fkg%n", totalWeight);
    }
}