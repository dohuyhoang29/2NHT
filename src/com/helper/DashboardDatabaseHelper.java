package com.helper;

import com.model.Dashboard;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DashboardDatabaseHelper {
  public static ObservableList<Dashboard> getPrice () {
    ObservableList<Dashboard> list = FXCollections.observableArrayList();
    String query = "SELECT SUM(od.quantity) AS quantity, c.name AS categoryName, SUM(p.price * od.quantity) AS total "
        + "FROM categories c, product p, order_detail od, `order` o "
        + "WHERE c.id = p.category_id AND p.id = od.product_id AND od.order_id = o.id "
        + "GROUP BY c.id ";

    try (Connection cnt = DatabaseHelper.getConnetion();
        PreparedStatement stm = cnt.prepareStatement(query);
        ResultSet rs = stm.executeQuery()) {
      while (rs.next()) {
        String name = rs.getString("categoryName");
        Integer quantity = rs.getInt("quantity");
        Integer price = rs.getInt("total");

        list.add(new Dashboard(name, price, quantity));
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
      return null;
    }
    return list;
  }

  public static ObservableList<Dashboard> getPriceWithFilter (LocalDate from, LocalDate to) {
    ObservableList<Dashboard> list = FXCollections.observableArrayList();
    String query = "SELECT SUM(od.quantity) AS quantity, c.name AS categoryName, SUM(p.price * od.quantity) AS total "
        + "FROM categories c, product p, order_detail od, `order` o "
        + "WHERE c.id = p.category_id AND p.id = od.product_id AND od.order_id = o.id AND o.create_date BETWEEN ? AND ? "
        + "GROUP BY c.id ";

//    if (from != null && to == null) {
//      query += "AND  o.create_date >= ? ";
//    }
//
//    if (from == null && to != null) {
//      query += "AND  o.create_date <= ? ";
//    }
//
//    if (from != null && to != null) {
//      query += "AND  o.create_date >= ? AND o.create_date <= ?";
//    }

    try (Connection cnt = DatabaseHelper.getConnetion();
        PreparedStatement stm = cnt.prepareStatement(query)) {
//      if (from != null && to == null) {
//        stm.setDate(1, Date.valueOf(from));
//      }
//
//      if (from == null && to != null) {
//        stm.setDate(1, Date.valueOf(to));
//      }
//
//      if (from != null && to != null) {
        stm.setDate(1, Date.valueOf(from));
        stm.setDate(2, Date.valueOf(to));
//      }
      ResultSet rs = stm.executeQuery();
      while (rs.next()) {
        String name = rs.getString("categoryName");
        Integer quantity = rs.getInt("quantity");
        Integer price = rs.getInt("total");

        list.add(new Dashboard(name, price, quantity));
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
      return null;
    }
    return list;
  }
}
