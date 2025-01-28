package com.tap.model;

import java.util.Map;
import java.util.HashMap;

public class Cart {
    private Map<Integer, CartItem> items;

    public Cart() {
        items = new HashMap<>();
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void addItem(CartItem item) {
        if (items.containsKey(item.getItemId())) {
            // Update quantity if item already exists
            CartItem existingItem = items.get(item.getItemId());
            existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
        } else {
            items.put(item.getItemId(), item);
        }
    }

    public void updateItem(int itemId, int quantity) {
        if (items.containsKey(itemId)) {
            CartItem item = items.get(itemId);
            item.setQuantity(quantity);
        }
    }

    public void removeItem(int itemId) {
        items.remove(itemId);
    }

    // Add the clear method
    public void clear() {
        items.clear(); // Clears all items in the cart
    }
}
