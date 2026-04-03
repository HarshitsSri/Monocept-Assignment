package com.monocept.InventorySystem.strategy;
import com.monocept.InventorySystem.model.Product;

public interface ValuationStrategy {
    double calculate(Product p);
    
    String getStrategyName();
}
