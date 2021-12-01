package com.helper;

import com.model.Order;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OrderDatabaseHelper {
  public static Integer insertOrder (Integer accountID, String name, Integer totalPrice, String address, String phone, String note) {
    String query = "INSERT INTO `order` (account_id, name, total_price, create_date, status, address, phone_number) VALUES (?,?,?,?,?,?,?);";
    Integer id = 0;
    try (Connection cnt = DatabaseHelper.getConnetion();
        PreparedStatement preStm = cnt.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
      preStm.setInt(1, accountID);
      preStm.setString(2, name);
      preStm.setInt(3, totalPrice);
      preStm.setDate(4, Date.valueOf(LocalDate.now()));
      preStm.setString(5, Order.TO_PAY);
      preStm.setString(6, address);
      preStm.setString(7, phone);
      preStm.setString(8, note);

      if (preStm.executeUpdate() > 0) {
        ResultSet rs = preStm.getGeneratedKeys();
        if (rs.next()) {
          id =rs.getInt(1);
        }
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return id;
  }

  public static ObservableList<Order> getAllOrder () {
    ObservableList<Order> list = FXCollections.observableArrayList();
    String query = "SELECT o.id, o.account_id, o.name, o.total_price, o.create_date, o.status, o.address, o.phone_number "
        + "FROM `order` AS o "
        + "INNER JOIN `account` AS a ON o.account_id = a.id;";

    try (Connection cnt = DatabaseHelper.getConnetion();
        PreparedStatement preStm = cnt.prepareStatement(query);
        ResultSet rs = preStm.executeQuery()) {
      while (rs.next()) {
        Integer id = rs.getInt("id");
        Integer accountID = rs.getInt("account_id");
        String name = rs.getString("name");
        Integer totalPrice = rs.getInt("total_price");
        LocalDate createDate = rs.getDate("create_date").toLocalDate();
        String status = rs.getString("status");
        String address = rs.getString("address");
        String phoneNumber = rs.getString("phone_number");

        list.add(new Order(id, accountID, name, totalPrice, createDate, status, address, phoneNumber));
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
      return null;
    }
    return list;
  }

  public static List<Order> searchOrder (String name, String status, LocalDate from, LocalDate to) {
    List<Order> list = new ArrayList<>();
    String query = "SELECT o.id, o.account_id, o.name, o.total_price, o.create_date, o.status, o.address, o.phone_number "
        + "FROM `order` AS o "
        + "INNER JOIN `account` AS a ON o.account_id = a.id WHERE 1 = 1 ";

    if (name != null) {
      query += " AND name = ? OR price = ? ";
    }

    if (status != null) {
      query += "AND status = ? ";
    }

    if (from != null) {
      query += "AND create_date >= ? ";
    }

    if (to != null) {
      query += "AND create_date <= ? ";
    }
    try (Connection cnt = DatabaseHelper.getConnetion();
        PreparedStatement preStm = cnt.prepareStatement(query)) {
      if (name != null) {

      }
      preStm.setString(1, name);

      ResultSet rs = preStm.executeQuery();
      while (rs.next()) {
        Integer id = rs.getInt("id");
        Integer accountID = rs.getInt("account_id");
        String Name = rs.getString("name");
        Integer totalPrice = rs.getInt("total_price");
        LocalDate createDate = rs.getDate("create_date").toLocalDate();
        String Status = rs.getString("status");
        String address = rs.getString("address");
        String phoneNumber = rs.getString("phone_number");

        list.add(new Order(id, accountID, Name, totalPrice, createDate, Status, address, phoneNumber));
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
      return null;
    }
    return list;
  }

  public static boolean changeStatus (String status, Integer id) {
    String query = "UPDATE `order` SET status = ? WHERE id = ?";

    try (Connection cnt = DatabaseHelper.getConnetion();
        PreparedStatement preStm = cnt.prepareStatement(query)) {
      preStm.setString(1, status);
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
