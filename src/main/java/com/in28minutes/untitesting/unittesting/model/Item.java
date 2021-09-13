package com.in28minutes.untitesting.unittesting.model;

import javax.persistence.*;

@Entity
@Table(name = "Item") //insert into item(id, name, price, quantity) values(1001, 'Item1', 10, 20);
public class Item {

    @Id
    @Column(name = "ID", nullable = false, updatable = false)
    private int id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "PRICE")
    private int price;
    @Column(name = "QUANTITY")
    private int quantity;

    @Transient // makes sure that this field is not set to the database
    private int value;

    public Item(int id, String name, int price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    protected Item() {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String toString() {
        return String.format("Item[%d, %s, %d, %d]", id, name, price, quantity);
    }
}