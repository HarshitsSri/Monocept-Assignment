package com.monocept.InventorySystem.notification;


public class EmailNotifier implements Notifier {
    public void send(String message) {
        System.out.println("[EMAIL] " + message);
    }
}
