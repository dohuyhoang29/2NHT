package com.helper;


import com.Main;
import com.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductDatabaseHelper {

    public static ObservableList<Product> getAllProduct() {
        ObservableList<Product> list = FXCollections.observableArrayList();
        String query = "SELECT p.id, p.code, p.name AS 'product_name', p.status, p.warranty_period, p.import_price, p.price, p.hard_drive, p.origin, p.color, p.img_src, p.screen, p.cpu, p.gpu, p.ram, p.operating_system, p.rear_camera, p.selfie_camera, p.battery_capacity, p.sim, p.weight, p.dimensions, c.name AS 'category_name' FROM product AS p INNER JOIN categories AS c ON p.category_id=c.id;";

        try (Connection cnt = DatabaseHelper.getConnetion();
             PreparedStatement preStm = cnt.prepareStatement(query);
             ResultSet rs = preStm.executeQuery()) {
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String categoryId = rs.getString("category_name");
                String code = rs.getString("code");
                String name = rs.getString("product_name");
                String status = rs.getString("status");
                String warrantyPeriod = rs.getString("warranty_period");
                Integer importPrice = rs.getInt("import_price");
                Integer price = rs.getInt("price");
                String hardDrive = rs.getString("hard_drive");
                String origin = rs.getString("origin");
                String color = rs.getString("color");
                String imgSrc = rs.getString("img_src");
                String screen = rs.getString("screen");
                String cpu = rs.getString("cpu");
                String gpu = rs.getString("gpu");
                String ram = rs.getString("ram");
                String operatingSystem = rs.getString("operating_system");
                String rearCamera = rs.getString("rear_camera");
                String selfieCamera = rs.getString("selfie_camera");
                String batteryCapacity = rs.getString("battery_capacity");
                String sim = rs.getString("sim");
                String weight = rs.getString("weight");
                String dimensions = rs.getString("dimensions");

                list.add(
                        new Product(id, categoryId, code, name, status, warrantyPeriod, importPrice, price, hardDrive, origin
                                , color, imgSrc, screen, cpu, gpu, ram, operatingSystem, rearCamera,
                                selfieCamera, batteryCapacity, sim, weight, dimensions));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

        return list;
    }

    public static ObservableList<Product> getAllProductHome() {
        ObservableList<Product> list = FXCollections.observableArrayList();
        String query = "SELECT p.id, p.code, p.name AS 'product_name', p.status, p.warranty_period, p.import_price, p.price, p.hard_drive, p.origin, p.color, p.img_src, p.screen, p.cpu, p.gpu, p.ram, p.operating_system, p.rear_camera, p.selfie_camera, p.battery_capacity, p.sim, p.weight, p.dimensions, c.name AS 'category_name' FROM product AS p INNER JOIN categories AS c ON p.category_id=c.id;";

        try (Connection cnt = DatabaseHelper.getConnetion();
             PreparedStatement preStm = cnt.prepareStatement(query);
             ResultSet rs = preStm.executeQuery()) {
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String categoryId = rs.getString("category_name");
                String code = rs.getString("code");
                String name = rs.getString("product_name");
                String status = rs.getString("status");
                String warrantyPeriod = rs.getString("warranty_period");
                Integer importPrice = rs.getInt("import_price");
                Integer price = rs.getInt("price");
                String hardDrive = rs.getString("hard_drive");
                String origin = rs.getString("origin");
                String color = rs.getString("color");
                String imgSrc = rs.getString("img_src");
                String screen = rs.getString("screen");
                String cpu = rs.getString("cpu");
                String gpu = rs.getString("gpu");
                String ram = rs.getString("ram");
                String operatingSystem = rs.getString("operating_system");
                String rearCamera = rs.getString("rear_camera");
                String selfieCamera = rs.getString("selfie_camera");
                String batteryCapacity = rs.getString("battery_capacity");
                String sim = rs.getString("sim");
                String weight = rs.getString("weight");
                String dimensions = rs.getString("dimensions");

                if (status.equalsIgnoreCase(Main.UNLOCK)) {
                    list.add(
                            new Product(id, categoryId, code, name, status, warrantyPeriod, importPrice, price, hardDrive, origin
                                    , color, imgSrc, screen, cpu, gpu, ram, operatingSystem, rearCamera,
                                    selfieCamera, batteryCapacity, sim, weight, dimensions));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

        return list;
    }

    public static List<Product> getAllProductByname(String name) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT p.id, p.code, p.name AS 'product_name', p.status, p.warranty_period, p.import_price, p.price, p.hard_drive, p.origin, p.color, p.img_src, p.screen, p.cpu, p.gpu, p.ram, p.operating_system, p.rear_camera, p.selfie_camera, p.battery_capacity, p.sim, p.weight, p.dimensions, c.name AS 'category_name' FROM product AS p INNER JOIN categories AS c ON p.category_id=c.id WHERE p.name = ?;";

        try (Connection cnt = DatabaseHelper.getConnetion();
             PreparedStatement preStm = cnt.prepareStatement(query)) {
            preStm.setString(1, name);

            ResultSet rs = preStm.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String categoryId = rs.getString("category_name");
                String code = rs.getString("code");
                String Name = rs.getString("product_name");
                String status = rs.getString("status");
                String warrantyPeriod = rs.getString("warranty_period");
                Integer importPrice = rs.getInt("import_price");
                Integer price = rs.getInt("price");
                String hardDrive = rs.getString("hard_drive");
                String origin = rs.getString("origin");
                String color = rs.getString("color");
                String imgSrc = rs.getString("img_src");
                String screen = rs.getString("screen");
                String cpu = rs.getString("cpu");
                String gpu = rs.getString("gpu");
                String ram = rs.getString("ram");
                String operatingSystem = rs.getString("operating_system");
                String rearCamera = rs.getString("rear_camera");
                String selfieCamera = rs.getString("selfie_camera");
                String batteryCapacity = rs.getString("battery_capacity");
                String sim = rs.getString("sim");
                String weight = rs.getString("weight");
                String dimensions = rs.getString("dimensions");

                if (status.equalsIgnoreCase(Main.UNLOCK)) {
                    list.add(
                            new Product(id, categoryId, code, Name, status, warrantyPeriod, importPrice, price, hardDrive,
                                    origin, color, imgSrc, screen, cpu, gpu, ram, operatingSystem, rearCamera,
                                    selfieCamera, batteryCapacity, sim, weight, dimensions));
                }

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return list;
    }

    public static ObservableList<Product> getAllProductByCategory(String category) {
        ObservableList<Product> list = FXCollections.observableArrayList();
        String query = "SELECT p.id, p.code, p.name AS 'product_name', p.status, p.warranty_period, p.import_price, p.price, p.hard_drive, p.origin, p.color, p.img_src, p.screen, p.cpu, p.gpu, p.ram, p.operating_system, p.rear_camera, p.selfie_camera, p.battery_capacity, p.sim, p.weight, p.dimensions, c.name AS 'category_name' FROM product AS p INNER JOIN categories AS c ON p.category_id=c.id WHERE c.name = ?;";

        try (Connection cnt = DatabaseHelper.getConnetion();
             PreparedStatement preStm = cnt.prepareStatement(query)) {
            preStm.setString(1, category);

            ResultSet rs = preStm.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String categoryId = rs.getString("category_name");
                String code = rs.getString("code");
                String Name = rs.getString("product_name");
                String status = rs.getString("status");
                String warrantyPeriod = rs.getString("warranty_period");
                Integer importPrice = rs.getInt("import_price");
                Integer price = rs.getInt("price");
                String hardDrive = rs.getString("hard_drive");
                String origin = rs.getString("origin");
                String color = rs.getString("color");
                String imgSrc = rs.getString("img_src");
                String screen = rs.getString("screen");
                String cpu = rs.getString("cpu");
                String gpu = rs.getString("gpu");
                String ram = rs.getString("ram");
                String operatingSystem = rs.getString("operating_system");
                String rearCamera = rs.getString("rear_camera");
                String selfieCamera = rs.getString("selfie_camera");
                String batteryCapacity = rs.getString("battery_capacity");
                String sim = rs.getString("sim");
                String weight = rs.getString("weight");
                String dimensions = rs.getString("dimensions");

                if (status.equalsIgnoreCase(Main.UNLOCK)) {
                    list.add(
                            new Product(id, categoryId, code, Name, status, warrantyPeriod, importPrice, price, hardDrive,
                                    origin, color, imgSrc, screen, cpu, gpu, ram, operatingSystem, rearCamera,
                                    selfieCamera, batteryCapacity, sim, weight, dimensions));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return list;
    }

    public static ObservableList<Product> getAllProductByCategoryAndName(String category, String name) {
        ObservableList<Product> list = FXCollections.observableArrayList();
        String query = "SELECT p.id, p.code, p.name AS 'product_name', p.status, p.warranty_period, p.import_price, p.price, p.hard_drive, p.origin, "
                + "p.color, p.img_src, p.screen, p.cpu, p.gpu, p.ram, p.operating_system, p.rear_camera, p.selfie_camera, p.battery_capacity, p.sim, "
                + "p.weight, p.dimensions, c.name AS 'category_name' FROM product AS p INNER JOIN categories AS c ON p.category_id=c.id "
                + "WHERE 1 = 1";

        if (category != null) {
            query += " AND c.name = ? ";
        }

        if (name != null) {
            query += " AND p.name LIKE ? ";
        }

        try (Connection cnt = DatabaseHelper.getConnetion();
             PreparedStatement preStm = cnt.prepareStatement(query)) {
            if (category != null && name == null) {
                preStm.setString(1, category);
            }

            if (category == null && name != null) {
                preStm.setString(1, "%" + name + "%");
            }

            if (category != null && name != null) {
                preStm.setString(1, category);
                preStm.setString(2, "%" + name + "%");
            }

            ResultSet rs = preStm.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String categoryId = rs.getString("category_name");
                String code = rs.getString("code");
                String Name = rs.getString("product_name");
                String status = rs.getString("status");
                String warrantyPeriod = rs.getString("warranty_period");
                Integer importPrice = rs.getInt("import_price");
                Integer price = rs.getInt("price");
                String hardDrive = rs.getString("hard_drive");
                String origin = rs.getString("origin");
                String color = rs.getString("color");
                String imgSrc = rs.getString("img_src");
                String screen = rs.getString("screen");
                String cpu = rs.getString("cpu");
                String gpu = rs.getString("gpu");
                String ram = rs.getString("ram");
                String operatingSystem = rs.getString("operating_system");
                String rearCamera = rs.getString("rear_camera");
                String selfieCamera = rs.getString("selfie_camera");
                String batteryCapacity = rs.getString("battery_capacity");
                String sim = rs.getString("sim");
                String weight = rs.getString("weight");
                String dimensions = rs.getString("dimensions");

                if (status.equalsIgnoreCase(Main.UNLOCK)) {
                    list.add(
                            new Product(id, categoryId, code, Name, status, warrantyPeriod, importPrice, price, hardDrive,
                                    origin, color, imgSrc, screen, cpu, gpu, ram, operatingSystem, rearCamera,
                                    selfieCamera, batteryCapacity, sim, weight, dimensions));
                }

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return list;
    }

    public static boolean insertProduct(Integer categoryId, String code, String name,
                                        String warrantyPeriod,
                                        Integer importPrice, Integer price, String hardDrive, String origin, String imgSrc,
                                        String screen, String cpu, String gpu, String ram,
                                        String operatingSystem, String rearCamera, String selfieCamera, String batteryCapacity,
                                        String sim, String weight, String dimensions) {
        String query =
                "INSERT INTO product (category_id, code, name, status, warranty_period, import_price, price, hard_drive, origin, img_src, screen, cpu, gpu, ram, operating_system, rear_camera, selfie_camera, battery_capacity, sim, weight, dimensions) "
                        + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection cnt = DatabaseHelper.getConnetion();
             PreparedStatement preStm = cnt.prepareStatement(query)) {
            preStm.setInt(1, categoryId);
            preStm.setString(2, code);
            preStm.setString(3, name);
            preStm.setString(4, Main.UNLOCK);
            preStm.setString(5, warrantyPeriod);
            preStm.setInt(6, importPrice);
            preStm.setInt(7, price);
            preStm.setString(8, hardDrive);
            preStm.setString(9, origin);
            preStm.setString(10, imgSrc);
            preStm.setString(11, screen);
            preStm.setString(12, cpu);
            preStm.setString(13, gpu);
            preStm.setString(14, ram);
            preStm.setString(15, operatingSystem);
            preStm.setString(16, rearCamera);
            preStm.setString(17, selfieCamera);
            preStm.setString(18, batteryCapacity);
            preStm.setString(19, sim);
            preStm.setString(20, weight);
            preStm.setString(21, dimensions);

            if (preStm.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static boolean editProduct(Integer categoryId, String code, String name,
                                      String warrantyPeriod,
                                      Integer importPrice, Integer price, String hardDrive, String origin,
                                      String color, String imgSrc, String screen, String cpu, String gpu, String ram,
                                      String operatingSystem, String rearCamera, String selfieCamera, String batteryCapacity,
                                      String sim, String weight, String dimensions, Integer id) {
        String query = "UPDATE product SET category_id = ?, code = ?, name = ?, warranty_period = ?, import_price = ?, price = ?, hard_drive = ?, origin = ?, color = ?, img_src = ?, screen = ?, cpu = ?, gpu = ?, ram = ?, operating_system = ?, rear_camera = ?, selfie_camera = ?, battery_capacity = ?, sim = ?, weight = ?, dimensions = ? WHERE id = ?";

        try (Connection cnt = DatabaseHelper.getConnetion();
             PreparedStatement preStm = cnt.prepareStatement(query)) {
            preStm.setInt(1, categoryId);
            preStm.setString(2, code);
            preStm.setString(3, name);
            preStm.setString(4, warrantyPeriod);
            preStm.setInt(5, importPrice);
            preStm.setInt(6, price);
            preStm.setString(7, hardDrive);
            preStm.setString(8, origin);
            preStm.setString(9, color);
            preStm.setString(10, imgSrc);
            preStm.setString(11, screen);
            preStm.setString(12, cpu);
            preStm.setString(13, gpu);
            preStm.setString(14, ram);
            preStm.setString(15, operatingSystem);
            preStm.setString(16, rearCamera);
            preStm.setString(17, selfieCamera);
            preStm.setString(18, batteryCapacity);
            preStm.setString(19, sim);
            preStm.setString(20, weight);
            preStm.setString(21, dimensions);
            preStm.setInt(22, id);

            if (preStm.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static boolean lockProduct(Integer id) {
        String query = "UPDATE product SET status = ? WHERE id = ?";
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

    public static boolean unLockProduct(Integer id) {
        String query = "UPDATE product SET status = ? WHERE id = ?";
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

    public static List<Product> searchProduct(String key) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT p.id, p.code, p.name AS 'product_name', p.status, p.warranty_period, p.import_price, p.price, p.hard_drive, p.origin, p.color, p.img_src, p.screen, p.cpu, p.gpu, p.ram, p.operating_system, p.rear_camera, p.selfie_camera, p.battery_capacity, p.sim, p.weight, p.dimensions, c.name AS 'category_name' FROM product AS p INNER JOIN categories AS c ON p.category_id=c.id WHERE c.name LIKE ? OR p.name LIKE ?;";

        try (Connection cnt = DatabaseHelper.getConnetion();
             PreparedStatement preStm = cnt.prepareStatement(query)) {
            preStm.setString(1, "%" + key + "%");
            preStm.setString(2, "%" + key + "%");

            ResultSet rs = preStm.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String categoryId = rs.getString("category_name");
                String code = rs.getString("code");
                String Name = rs.getString("product_name");
                String status = rs.getString("status");
                String warrantyPeriod = rs.getString("warranty_period");
                Integer importPrice = rs.getInt("import_price");
                Integer price = rs.getInt("price");
                String hardDrive = rs.getString("hard_drive");
                String origin = rs.getString("origin");
                String color = rs.getString("color");
                String imgSrc = rs.getString("img_src");
                String screen = rs.getString("screen");
                String cpu = rs.getString("cpu");
                String gpu = rs.getString("gpu");
                String ram = rs.getString("ram");
                String operatingSystem = rs.getString("operating_system");
                String rearCamera = rs.getString("rear_camera");
                String selfieCamera = rs.getString("selfie_camera");
                String batteryCapacity = rs.getString("battery_capacity");
                String sim = rs.getString("sim");
                String weight = rs.getString("weight");
                String dimensions = rs.getString("dimensions");

                list.add(
                        new Product(id, categoryId, code, Name, status, warrantyPeriod, importPrice, price, hardDrive,
                                origin, color, imgSrc, screen, cpu, gpu, ram, operatingSystem, rearCamera,
                                selfieCamera, batteryCapacity, sim, weight, dimensions));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return list;
    }

    public static List<Product> searchProductByCategoryAndName(String name, String category) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT p.id, p.code, p.name AS 'product_name', p.status, p.warranty_period, p.import_price, p.price, p.hard_drive, p.origin, p.color, p.img_src, p.screen, p.cpu, p.gpu, p.ram, p.operating_system, p.rear_camera, p.selfie_camera, p.battery_capacity, p.sim, p.weight, p.dimensions, c.name AS 'category_name' FROM product AS p INNER JOIN categories AS c ON p.category_id=c.id WHERE c.name = ? AND p.name LIKE ?;";

        try (Connection cnt = DatabaseHelper.getConnetion();
             PreparedStatement preStm = cnt.prepareStatement(query)) {
            preStm.setString(1, category);
            preStm.setString(2, "%" + name + "%");

            ResultSet rs = preStm.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String categoryId = rs.getString("category_name");
                String code = rs.getString("code");
                String Name = rs.getString("product_name");
                String status = rs.getString("status");
                String warrantyPeriod = rs.getString("warranty_period");
                Integer importPrice = rs.getInt("import_price");
                Integer price = rs.getInt("price");
                String hardDrive = rs.getString("hard_drive");
                String origin = rs.getString("origin");
                String color = rs.getString("color");
                String imgSrc = rs.getString("img_src");
                String screen = rs.getString("screen");
                String cpu = rs.getString("cpu");
                String gpu = rs.getString("gpu");
                String ram = rs.getString("ram");
                String operatingSystem = rs.getString("operating_system");
                String rearCamera = rs.getString("rear_camera");
                String selfieCamera = rs.getString("selfie_camera");
                String batteryCapacity = rs.getString("battery_capacity");
                String sim = rs.getString("sim");
                String weight = rs.getString("weight");
                String dimensions = rs.getString("dimensions");

                list.add(
                        new Product(id, categoryId, code, Name, status, warrantyPeriod, importPrice, price, hardDrive,
                                origin, color, imgSrc, screen, cpu, gpu, ram, operatingSystem, rearCamera,
                                selfieCamera, batteryCapacity, sim, weight, dimensions));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return list;
    }
}
