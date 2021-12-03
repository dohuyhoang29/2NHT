package com.helper;

import com.model.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AccountDatabaseHelper {
  public static Account getAccountByUsername(String user) {
    String query = "SELECT * FROM account WHERE username = ?";
    try (Connection cnt = DatabaseHelper.getConnetion();
        PreparedStatement preStm = cnt.prepareStatement(query)) {
      preStm.setString(1, user);
      ResultSet rs = preStm.executeQuery();
      if (rs.next()) {
        Integer id = rs.getInt("id");
        String username = rs.getString("username");
        String email = rs.getString("email");
        String password = rs.getString("password");
        String status = rs.getString("status");
        String type = rs.getString("type");
        String address = rs.getString("address");
        String phone = rs.getString("phone_number");
        return new Account(id, username, email, password, status, type, address, phone);
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return null;
  }

  public static Account getAccountById(Integer id) {
    String query = "SELECT * FROM account WHERE id = ?";
    try (Connection cnt = DatabaseHelper.getConnetion();
        PreparedStatement preStm = cnt.prepareStatement(query)) {
      preStm.setInt(1, id);
      ResultSet rs = preStm.executeQuery();
      if (rs.next()) {
        Integer Id = rs.getInt("id");
        String username = rs.getString("username");
        String email = rs.getString("email");
        String password = rs.getString("password");
        String status = rs.getString("status");
        String type = rs.getString("type");
        String address = rs.getString("address");
        String phone = rs.getString("phone_number");
        return new Account(Id, username, email, password, status, type, address, phone);
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return null;
  }

  public static ObservableList<Account> getAllAccount() {
    ObservableList<Account> list = FXCollections.observableArrayList();
    String query = "SELECT * FROM account";

    try (Connection cnt = DatabaseHelper.getConnetion();
        PreparedStatement preStm = cnt.prepareStatement(query);
        ResultSet rs = preStm.executeQuery()) {
      while (rs.next()) {
        Integer id = rs.getInt("id");
        String username = rs.getString("username");
        String email = rs.getString("email");
        String password = rs.getString("password");
        String status = rs.getString("status");
        String type = rs.getString("type");
        String address = rs.getString("address");
        String phone = rs.getString("phone_number");
        list.add(new Account(id, username, email, password, status, type, address, phone));
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
      return null;
    }
    return list;
  }

  public static boolean insertAccount(String username, String email, String password, String type, String address, String phone) {
    String query = "INSERT INTO account (username, email, `password`, status, type, address, phone_number) VALUES (?,?,?,?,?,?,?)";
    try (Connection cnt = DatabaseHelper.getConnetion();
        PreparedStatement preStm = cnt.prepareStatement(query)) {
      preStm.setString(1, username);
      preStm.setString(2, email);
      preStm.setString(3, password);
      preStm.setString(4, Account.UNLOCK);
      preStm.setString(4, type);
      preStm.setString(5, address);
      preStm.setString(6, phone);
      if (preStm.executeUpdate() > 0) {
        return true;
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

    return false;
  }

  public static boolean editAccount(String email, String password,String type, String address, String phone, Integer id) {
    String query = "UPDATE account SET  email = ?, `password` = ?, type = ?, address = ?, phone_number = ? WHERE id = ?";
    try (Connection cnt = DatabaseHelper.getConnetion();
        PreparedStatement preStm = cnt.prepareStatement(query)) {
      preStm.setString(1, email);
      preStm.setString(2, password);
      preStm.setString(3, type);
      preStm.setString(4, address);
      preStm.setString(5, phone);
      preStm.setInt(6, id);
      if (preStm.executeUpdate() > 0) {
        return true;
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

    return false;
  }

  public static boolean deleteAccount(Integer id) {
    String query = "DELETE FROM account WHERE id = ?";
    try (Connection cnt = DatabaseHelper.getConnetion();
        PreparedStatement preStm = cnt.prepareStatement(query)) {
      preStm.setInt(1, id);
      if (preStm.executeUpdate() > 0) {
        return true;
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return false;
  }

  public static boolean changePassword (String password, Integer id) {
    String query = "UPDATE account SET  `password` = ? WHERE id = ?;";
    try (Connection cnt = DatabaseHelper.getConnetion();
        PreparedStatement preStm = cnt.prepareStatement(query)) {

      preStm.setString(1, password);
      preStm.setInt(2, id);
      if (preStm.executeUpdate() > 0) {
        return true;
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

    return false;
  }

  public static ObservableList<Account> searchAccount(String user) {
    ObservableList<Account> list = FXCollections.observableArrayList();
    String query = "SELECT * FROM account WHERE username LIKE ? OR email LIKE ? OR phone_number LIKE ? OR address LIKE ?";
    try (Connection cnt = DatabaseHelper.getConnetion();
        PreparedStatement preStm = cnt.prepareStatement(query)) {
      preStm.setString(1, "%" + user + "%");
      preStm.setString(2, "%" + user + "%");
      preStm.setString(3, "%" + user + "%");
      preStm.setString(4, "%" + user + "%");
      ResultSet rs = preStm.executeQuery();
      while (rs.next()) {
        Integer id = rs.getInt("id");
        String username = rs.getString("username");
        String email = rs.getString("email");
        String password = rs.getString("password");
        String status = rs.getString("status");
        String type = rs.getString("type");
        String address = rs.getString("address");
        String phone = rs.getString("phone_number");
        list.add(new Account(id, username, email, password, status, type, address, phone));
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
      return null;
    }
    return list;
  }

  public static boolean lockAccount (Integer id) {
    String query = "UPDATE account SET status = ? WHERE id = ?";

    try (Connection cnt = DatabaseHelper.getConnetion();
        PreparedStatement preStm = cnt.prepareStatement(query)) {
      preStm.setString(1, Account.LOCK);
      preStm.setInt(2, id);

      if (preStm.executeUpdate() > 0) {
        return true;
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return false;
  }

  public static boolean unLockAccount (Integer id) {
    String query = "UPDATE account SET status = ? WHERE id = ?";

    try (Connection cnt = DatabaseHelper.getConnetion();
         PreparedStatement preStm = cnt.prepareStatement(query)) {
      preStm.setString(1, Account.UNLOCK);
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
