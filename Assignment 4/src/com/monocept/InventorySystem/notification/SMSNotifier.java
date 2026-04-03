package com.monocept.InventorySystem.notification;

public class SMSNotifier implements Notifier {
    public void send(String message) {
        System.out.println("[SMS] " + message);
    }
}
