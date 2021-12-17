package com.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Dashboard {
  StringProperty name;
  ObjectProperty<Integer> price;
  ObjectProperty<Integer> quantity;

  public Dashboard (String name, Integer price, Integer quantity) {
    this.name = new SimpleStringProperty(name);
    this.price = new SimpleObjectProperty<>(price);
    this.quantity = new SimpleObjectProperty<>(quantity);
  }

  public String getName() {
    return name.get();
  }

  public StringProperty getNameProperty() {
    return name;
  }

  public void setName(String name) {
    this.name.set(name);
  }

  public Integer getPrice() {
    return price.get();
  }

  public ObjectProperty<Integer> getPriceProperty() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price.set(price);
  }

  public Integer getQuantity() {
    return quantity.get();
  }

  public ObjectProperty<Integer> getQuantityProperty() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity.set(quantity);
  }
}
