package com.monocept.InventorySystem.strategy;
import com.monocept.InventorySystem.model.Product;

public class FIFOValuation implements ValuationStrategy {
    public double calculate(Product p) {
        return p.getQuantity() * p.getPrice();
    }
    
    public String getStrategyName() {
        return "FIFO";
    }
}
