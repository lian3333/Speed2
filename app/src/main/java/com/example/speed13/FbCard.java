package com.example.speed13;

public class FbCard {
    private int value;
    private int color;

    public FbCard() {
    }

    public FbCard(int value, int color) {
        this.value = value;
        this.color = color;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
