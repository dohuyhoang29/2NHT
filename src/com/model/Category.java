package com.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Category {
  public static final String MAC = "MacBook";
  public static final String IPHONE = "iPhone";
  public static final String IPAD = "iPad";

  private ObjectProperty<Integer> id;
  private StringProperty name;
  private StringProperty description;
  private StringProperty status;

  public Category () {}

  public Category (Integer id, String name, String description, String status) {
    this.id = new SimpleObjectProperty<>(id);
    this.name = new SimpleStringProperty(name);
    this.description = new SimpleStringProperty(description);
    this.status = new SimpleStringProperty(status);
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

  public String getStatus() {
    return status.get();
  }

  public StringProperty getStatusProperty() {
    return status;
  }

  public void setStatus(String status) {
    this.status.set(status);
  }
}
