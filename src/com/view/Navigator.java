package com.view;

import com.controller.CategoryController;
import com.controller.EditAccountController;
import com.controller.EditCategoryController;
import com.controller.EditProductController;
import com.controller.OrderDetailsController;
import com.controller.PurchaseOrderDetailController;
import com.controller.ScreenProductController;
import com.controller.SearchController;
import com.controller.ViewProductController;
import com.helper.TranslateManager;
import com.model.Account;
import com.model.Category;
import com.model.Order;
import com.model.OrderDetail;
import com.model.Product;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Navigator {
  private Navigator() {
  }
  static final String PROJECT_NAME = "2NHT";
  static final String INSERT_PRODUCT = "InsertProductUI.fxml";
  static final String DASHBOARD = "DashboardUI.fxml";
  static final String PRODUCTS_LIST = "ProductsListUI.fxml";
  static final String LOGIN = "LoginUI.fxml";
  static final String REGISTER = "RegisterUI.fxml";
  static final String ACCOUNT_LIST = "AccountListUI.fxml";
  static final String INSERT_ACCOUNT = "InsertAccountUI.fxml";
  static final String EDIT_ACCOUNT = "EditAccountUI.fxml";
  static final String CATEGORY_LIST = "CategoryListUI.fxml";
  static final String INSERT_CATEGORY = "InsertCategoryUI.fxml";
  static final String EDIT_CATEGORY = "EditCategoryUI.fxml";
  static final String VIEW_PRODUCT = "ViewProductUI.fxml";
  static final String EDIT_PRODUCT = "EditProductUI.fxml";
  static final String ORDER = "OrderUI.fxml";
  static final String ORDER_DETAILS = "OrderDetailsUI.fxml";
  static final String HOME = "HomeUI.fxml";
  static final String SCREEN_PRODUCT = "ScreenProductUI.fxml";
  static final String CART = "CartUI.fxml";
  static final String PAY = "PayUI.fxml";
  static final String CATEGORY = "CategoryUI.fxml";
  static final String PROFILE = "ProfileUI.fxml";
  static final String SEARCH = "SearchUI.fxml";
  static final String CHANGE_PASS = "ChangePasswordUI.fxml";
  static final String PURCHASE_ORDER = "PurchaseOrderUI.fxml";
  static final String PURCHASE_ORDER_DETAIL = "PurchaseOrderDetailUI.fxml";

  private FXMLLoader loader;
  private static Navigator navigator;

  private Stage stage;

  public static Navigator getInstance() {
    if (navigator == null) {
      navigator = new Navigator();
    }
    return navigator;
  }

  public void setStage(Stage stage) {
    this.stage = stage;
  }

  public Stage getStage() {
    return stage;
  }

  public void goToScene(String title, String url) throws IOException {
    loader = new FXMLLoader(getClass().getResource(url), TranslateManager.getRb());
    Parent root = loader.load();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/images/logo_non_text.png")));
    stage.setTitle(title);
    stage.centerOnScreen();
    if (!stage.isShowing()) {
      stage.show();
    }
  }

  public void goToInsertProduct() throws IOException {
    goToScene(PROJECT_NAME, INSERT_PRODUCT);
  }

  public void goToDashboard() throws IOException {
    goToScene(PROJECT_NAME, DASHBOARD);
  }

  public void goToProductsList() throws IOException {
    goToScene(PROJECT_NAME, PRODUCTS_LIST);
  }

  public void goToLogin() throws IOException {
    goToScene(PROJECT_NAME, LOGIN);
  }

  public void goToRegister() throws IOException {
    goToScene(PROJECT_NAME, REGISTER);
  }

  public void goToAccountList() throws IOException {
    goToScene(PROJECT_NAME, ACCOUNT_LIST);
  }

  public void goToInsertAccount() throws IOException {
    goToScene(PROJECT_NAME, INSERT_ACCOUNT);
  }

  public void goToEditAccount(Account account) throws IOException {
    goToScene(PROJECT_NAME, EDIT_ACCOUNT);
    EditAccountController editAccountController = loader.getController();
    editAccountController.setData(account);
  }

  public void goToCategoryList() throws IOException {
    goToScene(PROJECT_NAME, CATEGORY_LIST);
  }

  public void goToInsertCategory() throws IOException {
    goToScene(PROJECT_NAME, INSERT_CATEGORY);
  }

  public void goToEditCategory(Category category) throws IOException {
    goToScene(PROJECT_NAME, EDIT_CATEGORY);
    EditCategoryController controller = loader.getController();
    controller.setData(category);
  }

  public void goToViewProduct(Product product) throws IOException {
    goToScene(PROJECT_NAME, VIEW_PRODUCT);
    ViewProductController controller = loader.getController();
    controller.setData(product);
  }

  public void goToEditProduct(Product product) throws IOException {
    goToScene(PROJECT_NAME, EDIT_PRODUCT);
    EditProductController controller = loader.getController();
    controller.setData(product);
  }

  public void goToOrder() throws IOException {
    goToScene(PROJECT_NAME, ORDER);
  }

  public void goToOrderDetails(Order order) throws IOException {
    goToScene(PROJECT_NAME, ORDER_DETAILS);
    OrderDetailsController controller = loader.getController();
    controller.setData(order);
  }

  public void goToHome() throws IOException {
    goToScene(PROJECT_NAME, HOME);
  }

  public void goToScreenProduct(Product product) throws IOException {
    goToScene(PROJECT_NAME, SCREEN_PRODUCT);
    ScreenProductController controller = loader.getController();
    controller.setData(product);
  }

  public void goToCart () throws IOException {
    goToScene(PROJECT_NAME, CART);
  }

  public void goToPay () throws IOException {
    goToScene(PROJECT_NAME, PAY);
  }

  public void goToCategory (Category category) throws IOException {
    goToScene(PROJECT_NAME, CATEGORY);
    CategoryController controller = loader.getController();
    controller.setData(category);
  }

  public void goToProfile() throws IOException {
    goToScene(PROJECT_NAME, PROFILE);
  }

  public void goToSearch (String key) throws IOException {
    goToScene(PROJECT_NAME, SEARCH);
    SearchController controller = loader.getController();
    controller.setData(key);
  }

  public void goToSearchWithCategory (String name, String category) throws IOException {
    goToScene(PROJECT_NAME, SEARCH);
    SearchController controller = loader.getController();
    controller.setDataWithCategory(name, category);
  }

  public void goToChangePassword () throws IOException {
    goToScene(PROJECT_NAME, CHANGE_PASS);
  }

  public void goToPurchaseOrder () throws IOException {
    goToScene(PROJECT_NAME, PURCHASE_ORDER);
  }

  public void goToPurchaseOrderDetail (Order order) throws IOException {
    goToScene(PROJECT_NAME, PURCHASE_ORDER_DETAIL);
    PurchaseOrderDetailController controller = loader.getController();
    controller.setData(order);
  }
}
