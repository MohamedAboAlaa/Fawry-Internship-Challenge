package interfaces;

/**
 * Interface for items that can be shipped
 * Contains only getName() and getWeight() methods as required
 */
public interface Shippable{
    String getName();
    double getWeight();
}