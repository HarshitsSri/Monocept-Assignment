package com.monocept.InventorySystem.service;
import com.monocept.InventorySystem.exception.InventoryException;
import com.monocept.InventorySystem.model.Product;
import com.monocept.InventorySystem.notification.*;
import com.monocept.InventorySystem.strategy.*;
import java.util.*;

public class InventoryService {

	private Map<Integer, Product> inventory = new HashMap<>();
	private List<Notifier> notifiers;
	private ReorderService reorderService;
	private ValuationStrategy strategy;

	public InventoryService(List<Notifier> notifiers, ReorderService reorderService, ValuationStrategy strategy) {
		this.notifiers = notifiers;
		this.reorderService = reorderService;
		this.strategy = strategy;
	}

	public void addProduct(Product p) throws InventoryException {
		if (inventory.containsKey(p.getId())) {
			throw new InventoryException("Product already exists with ID: " + p.getId());
		}
		inventory.put(p.getId(), p);
		System.out.println(" Product added.");
	}

	public void deleteProduct(int id) throws InventoryException {
		if (!inventory.containsKey(id)) {
			throw new InventoryException("Product not found.");
		}
		inventory.remove(id);
		System.out.println(" Product deleted.");
	}

	public void addStock(int id, int qty) throws InventoryException {
		Product p = inventory.get(id);

		if (p == null) {
			throw new InventoryException("Product not found.");
		}

		if (qty <= 0) {
			throw new InventoryException("Quantity must be greater than 0.");
		}

		p.addStock(qty);
		System.out.println("Stock added.");
	}

	public void removeStock(int id, int qty) throws InventoryException {
		Product p = inventory.get(id);

		if (p == null) {
			throw new InventoryException("Product not found.");
		}

		if (qty <= 0) {
			throw new InventoryException("Quantity must be greater than 0.");
		}

		if (qty > p.getQuantity()) {
			throw new InventoryException("Not enough stock.");
		}

		p.removeStock(qty);

		System.out.println("Stock updated: Removed " + qty + " units of '" + p.getName() + "'");
		System.out.println("Current stock: " + p.getQuantity());

		if (p.getQuantity() <= p.getReorderLevel()) {
			triggerReorder(p);
		}
	}

	private void triggerReorder(Product p) {
		System.out.println(" Reorder triggered...");

		reorderService.reorder(p);

		for (Notifier n : notifiers) {
			n.send("Low stock alert for " + p.getName());
		}
	}

	public void calculateTotalValue() {
		double total = 0;
		for (Product p : inventory.values()) {
			total += strategy.calculate(p);
		}

		System.out.println("Total inventory value: $" + total);
	}
	public void changeStrategy(ValuationStrategy newStrategy) {
	    this.strategy = newStrategy;
	    System.out.println(" Valuation strategy changed to: " + newStrategy.getStrategyName());
	}

	public void showAllProducts() {
		if (inventory.isEmpty()) {
			System.out.println("No products available.");
			return;
		}
		for (Product p : inventory.values()) {
			p.display();
		}
	}
}