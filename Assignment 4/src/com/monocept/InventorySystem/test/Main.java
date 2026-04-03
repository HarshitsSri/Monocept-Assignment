package com.monocept.InventorySystem.test;

import com.monocept.InventorySystem.exception.InventoryException;
import com.monocept.InventorySystem.model.Product;
import com.monocept.InventorySystem.notification.*;
import com.monocept.InventorySystem.service.*;
import com.monocept.InventorySystem.strategy.*;
import com.monocept.InventorySystem.util.InputValidator;
import java.util.*;

public class Main {

	public static void main(String[] args) throws InventoryException {

		Scanner scanner = new Scanner(System.in);

		List<Notifier> notifiers = Arrays.asList(new EmailNotifier(), new SMSNotifier());

		InventoryService service = new InventoryService(notifiers, new ReorderService(), new FIFOValuation());
		System.out.println("     Inventory Management System     ");
		while (true) {
			try {
				System.out.println("\n1.Add 2.Delete 3.Show 4.AddStock 5.RemoveStock 6.Value 7.Change 8.Exit");

				int choice = InputValidator.getInt(scanner, "Choice: ");

				switch (choice) {

				case 1:
					int id = InputValidator.getInt(scanner, "ID: ");
					String name = InputValidator.getString(scanner, "Name: ");
					int qty = InputValidator.getInt(scanner, "Qty: ");
					double price = InputValidator.getInt(scanner, "Price: ");
					int reorder = InputValidator.getInt(scanner, "Reorder Level: ");

					service.addProduct(new Product(id, name, qty, price, reorder));
					break;

				case 2:
					service.deleteProduct(InputValidator.getInt(scanner, "ID: "));
					break;

				case 3:
					service.showAllProducts();
					break;

				case 4:
					service.addStock(InputValidator.getInt(scanner, "ID: "), InputValidator.getInt(scanner, "Qty: "));
					break;

				case 5:
					service.removeStock(InputValidator.getInt(scanner, "ID: "),
							InputValidator.getInt(scanner, "Qty: "));
					break;

				case 6:
					service.calculateTotalValue();
					break;

				case 7:
					System.out.println("1.FIFO 2.LIFO");
					int t = InputValidator.getInt(scanner, "Choice: ");

					if (t == 1)
						service.changeStrategy(new FIFOValuation());
					else if(t==2)
						service.changeStrategy(new LIFOValuation());
					else
						System.out.println("Invalid choice");
					break;

				case 8:
					return;
				default:
					System.out.println("Invalid choice");
				}
				
					
			} catch(IllegalArgumentException e) {
				System.out.println("Input Error: "+e.getMessage());
			}
			catch (InventoryException e) {

				System.out.println(" ERROR: " + e.getMessage());

			} catch (Exception e) {

				System.out.println("Something went wrong. Please try again.");
			}
		}
	}
}
