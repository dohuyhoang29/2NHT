package com.helper;

import com.Main;
import com.model.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CategoryDatabaseHelper {
  public static ObservableList<Category> getAllCategories() {
    ObservableList<Category> list = FXCollections.observableArrayList();
    String query = "SELECT * FROM categories";
    try (Connection cnt = DatabaseHelper.getConnetion();
        PreparedStatement preStm = cnt.prepareStatement(query);
        ResultSet rs = preStm.executeQuery()) {
        while (rs.next()) {
          Integer id = rs.getInt("id");
          String name = rs.getString("name");
          String description = rs.getString("description");
          String status = rs.getString("status");
          list.add(new Category(id, name, description, status));
        }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
      return null;
    }
    return list;
  }

  public static Category getCategoryByName(String name) {
    String query = "SELECT * FROM categories WHERE name = ?";
    try (Connection cnt = DatabaseHelper.getConnetion();
        PreparedStatement preStm = cnt.prepareStatement(query)) {
      preStm.setString(1, name);

      ResultSet rs = preStm.executeQuery();
      if(rs.next()) {
        Integer id = rs.getInt("id");
        String Name = rs.getString("name");
        String description = rs.getString("description");
        String status = rs.getString("status");
        return new Category(id, Name, description, status);
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return null;
  }

  public static boolean insertCategory(String name, String description) {
    String query = "INSERT INTO categories(name, description, status) VALUES (?,?,?);";
    try (Connection cnt = DatabaseHelper.getConnetion();
        PreparedStatement preStm = cnt.prepareStatement(query)) {
      preStm.setString(1, name);
      preStm.setString(2, description);
      preStm.setString(3, Main.UNLOCK);
      if (preStm.executeUpdate() > 0) {
        return true;
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return false;
  }

  public static boolean editCategory(String name, String description, Integer id) {
    String query = "UPDATE categories SET name = ?, description = ? WHERE id = ?";
    try (Connection cnt = DatabaseHelper.getConnetion();
        PreparedStatement preStm = cnt.prepareStatement(query)) {
      preStm.setString(1, name);
      preStm.setString(2, description);
      preStm.setInt(3, id);
      if (preStm.executeUpdate() > 0) {
        return true;
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return false;
  }

  public static boolean lockCategory(Integer id) {
    String query = "UPDATE categories SET status = ? WHERE id = ?";
    try (Connection cnt = DatabaseHelper.getConnetion();
        PreparedStatement preStm = cnt.prepareStatement(query)) {
      preStm.setString(1, Main.LOCK);
      preStm.setInt(2, id);
      if (preStm.executeUpdate() > 0) {
        return true;
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return false;
  }

  public static boolean unLockCategory(Integer id) {
    String query = "UPDATE categories SET status = ? WHERE id = ?";
    try (Connection cnt = DatabaseHelper.getConnetion();
         PreparedStatement preStm = cnt.prepareStatement(query)) {
      preStm.setString(1, Main.UNLOCK);
      preStm.setInt(2, id);
      if (preStm.executeUpdate() > 0) {
        return true;
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return false;
  }
}
