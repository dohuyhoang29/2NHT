package com.model;

import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Feedback  {
  ObjectProperty<Integer> id;
  StringProperty feedback;
  StringProperty username;
  StringProperty name;
  ObjectProperty<Integer> point;
  LocalDate date;
  StringProperty dateProperty;

  public Feedback (Integer id, String feedback, String username, String name, Integer point, LocalDate date) {
    this.id = new SimpleObjectProperty<>(id);
    this.feedback = new SimpleStringProperty(feedback);
    this.username = new SimpleStringProperty(username);
    this.name = new SimpleStringProperty(name);
    this.point = new SimpleObjectProperty<>(point);
    this.date = date;
    this.dateProperty = new SimpleStringProperty(date.toString());
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

  public String getFeedback() {
    return feedback.get();
  }

  public StringProperty getFeedbackProperty() {
    return feedback;
  }

  public void setFeedback(String feedback) {
    this.feedback.set(feedback);
  }

  public String getUsername() {
    return username.get();
  }

  public StringProperty getUsernameProperty() {
    return username;
  }

  public void setUsername(String username) {
    this.username.set(username);
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

  public Integer getPoint() {
    return point.get();
  }

  public ObjectProperty<Integer> getPointProperty() {
    return point;
  }

  public void setPoint(Integer point) {
    this.point.set(point);
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public String getDateProperty() {
    return dateProperty.get();
  }

  public StringProperty getDatePropertyProperty() {
    return dateProperty;
  }

  public void setDateProperty(String dateProperty) {
    this.dateProperty.set(dateProperty);
  }
}
