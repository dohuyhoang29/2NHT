package com.helper;

import com.model.Cart;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDatabaseHelper {
  public static List<Cart> getAllCartByAccount(String account) {
    List<Cart> list = new ArrayList<>();
    String query = "SELECT c.id, c.quantity, a.id AS account_id, p.id AS product_id, p.name, p.img_src, p.hard_drive, p.price "
        + "FROM cart AS c "
        + "INNER JOIN account AS a ON c.account_id = a.id "
        + "INNER JOIN product AS p ON c.product_id = p.id WHERE a.username = ?;";

    try (Connection cnt = DatabaseHelper.getConnetion();
        PreparedStatement preStm = cnt.prepareStatement(query)) {
      preStm.setString(1, account);
      ResultSet rs = preStm.executeQuery();

      while (rs.next()) {
        Integer id = rs.getInt("id");
        Integer quantity = rs.getInt("quantity");
        Integer accountID = rs.getInt("account_id");
        Integer productID = rs.getInt("product_id");
        String imgSrc = rs.getString("img_src");
        String name = rs.getString("name");
        String hardDrive = rs.getString("hard_drive");
        Integer price = rs.getInt("price");

        list.add(new Cart(id, quantity, productID, accountID, imgSrc, name, hardDrive, price));
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
      return null;
    }
    return list;
  }

  public static boolean addToCart (Integer quantity, Integer accountID, Integer productID) {
    String query = "INSERT INTO cart (quantity, account_id, product_id) VALUES (?,?,?);";

    try (Connection cnt = DatabaseHelper.getConnetion();
        PreparedStatement preStm = cnt.prepareStatement(query)) {
      preStm.setInt(1, quantity);
      preStm.setInt(2, accountID);
      preStm.setInt(3, productID);

      if (preStm.executeUpdate() > 0) {
        return true;
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return false;
  }

  public static boolean changeQuantity (Integer quantity, Integer id) {
    String query = "UPDATE cart SET quantity = ? WHERE id = ?;";

    try (Connection cnt = DatabaseHelper.getConnetion();
        PreparedStatement preStm = cnt.prepareStatement(query)) {
      preStm.setInt(1, quantity);
      preStm.setInt(2, id);

      if (preStm.executeUpdate() > 0) {
        return true;
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return false;
  }

  public static boolean deleteCart (Integer id) {
    String query = "DELETE FROM cart WHERE id = ?;";

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
}
