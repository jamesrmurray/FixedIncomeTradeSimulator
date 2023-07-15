package org.jmtrading;

public class Trade {
    private String bondType;
    private int quantity;
    private double price;
    private String direction;
    private boolean processed;

    public Trade(String bondType, int quantity, double price, String direction) {
        this.bondType = bondType;
        this.quantity = quantity;
        this.price = price;
        this.direction = direction;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public String getBondType() {
        return bondType;
    }

    public void setBondType(String bondType) {
        this.bondType = bondType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    // Additional getters and setters for other fields can be added if needed

    // Other methods and logic can be added here
}
