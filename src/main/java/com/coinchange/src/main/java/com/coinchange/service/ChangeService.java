package com.coinchange.service;

import com.coinchange.model.ChangeResponse;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class ChangeService {
    private final Map<Double, Integer> coinInventory = new LinkedHashMap<>();

    public ChangeService() {
        coinInventory.put(0.25, 100);
        coinInventory.put(0.10, 100);
        coinInventory.put(0.05, 100);
        coinInventory.put(0.01, 100);
    }

    public synchronized ChangeResponse getChange(int bill) {
        if (bill <= 0 || !isValidBill(bill)) {
            return new ChangeResponse("Invalid bill provided.", null);
        }

        double amount = bill;
        Map<Double, Integer> tempInventory = new LinkedHashMap<>(coinInventory);
        Map<String, Integer> changeToGive = new LinkedHashMap<>();

        for (Map.Entry<Double, Integer> entry : tempInventory.entrySet()) {
            double coin = entry.getKey();
            int available = entry.getValue();

            int needed = (int) (amount / coin);
            int use = Math.min(needed, available);

            if (use > 0) {
                changeToGive.put(String.format("%.2f", coin), use);
                amount -= coin * use;
                amount = Math.round(amount * 100.0) / 100.0;
                tempInventory.put(coin, available - use);
            }
        }

        if (amount > 0) {
            return new ChangeResponse("Not enough coins to make change.", null);
        } else {
            coinInventory.clear();
            coinInventory.putAll(tempInventory);
            return new ChangeResponse("Change provided successfully.", changeToGive);
        }
    }

    private boolean isValidBill(int bill) {
        return bill == 1 || bill == 2 || bill == 5 || bill == 10 || bill == 20 || bill == 50 || bill == 100;
    }
}