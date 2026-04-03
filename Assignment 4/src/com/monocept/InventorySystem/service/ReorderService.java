package com.monocept.InventorySystem.service;
import com.monocept.InventorySystem.model.Product;

public class ReorderService {
    public void reorder(Product p) {
    	p.addStock(20);
        System.out.println("Reorder placed for 20 units of '" + p.getName() + "'");  
    }
}
