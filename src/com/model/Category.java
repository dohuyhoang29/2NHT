package com.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Category {
  public static final String MAC = "MacBook";
  public static final String IPHONE = "iPhone";
  public static final String IPAD = "iPad";

  ObjectProperty<Integer> id;
  StringProperty name;
  StringProperty description;

  public Category () {}

  public Category (Integer id, String name, String description) {
    this.id = new SimpleObjectProperty<>(id);
    this.name = new SimpleStringProperty(name);
    this.description = new SimpleStringProperty(description);
  }

  public Integer getId() {
    return id.get();
  }

  public ObjectProperty<Integer> getIdProperty() {
    return id;
  }

  public void setId(Integer id) {
    this.id.set(id);
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

  public String getDescription() {
    return description.get();
  }

  public StringProperty getDescriptionProperty() {
    return description;
  }

  public void setDescription(String description) {
    this.description.set(description);
  }
}
