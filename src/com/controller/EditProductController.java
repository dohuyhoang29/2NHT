package com.controller;

import com.helper.CategoryDatabaseHelper;
import com.helper.ProductDatabaseHelper;
import com.helper.ProjectManager;
import com.helper.ValidationManager;
import com.model.Category;
import com.model.Product;
import com.view.Navigator;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class EditProductController implements Initializable {

  @FXML
  private ImageView changeLanguage;

  @FXML
  private Label username;

  @FXML
  private HBox dashboard;

  @FXML
  private ImageView imgdashboard;

  @FXML
  private Label lbdashboard;

  @FXML
  private HBox addProduct;

  @FXML
  private HBox productsList;

  @FXML
  private VBox changeLanguageContainer;

  @FXML
  private VBox basicInfo;

  @FXML
  private TextField txtProductCode;

  @FXML
  private Label errProductCode;

  @FXML
  private TextField txtProductName;

  @FXML
  private Label errProductName;

  @FXML
  private ChoiceBox<String> cbCategory;

  @FXML
  private Label errProductCategory;

  @FXML
  private TextField txtImportPrice;

  @FXML
  private Label errImportPrice;

  @FXML
  private TextField txtPrice;

  @FXML
  private Label errPrice;

  @FXML
  private TextField txtHardDrive;

  @FXML
  private Label errHardDrive;

  @FXML
  private TextField txtOrigin;

  @FXML
  private Label errOrigin;

  @FXML
  private TextField txtWarrantyPeriod;

  @FXML
  private Label errWarrantyPeriod;

  @FXML
  private Button btnNextBasicInfo;

  @FXML
  private VBox productImages;

  @FXML
  private HBox demoImg;

  @FXML
  private ImageView imgPreview;

  @FXML
  private VBox clickUpload;

  @FXML
  private Label errImgSrc;

  @FXML
  private Button btnPreviousProductImg;

  @FXML
  private Button btnNextProductImg;

  @FXML
  private VBox productData;

  @FXML
  private TextField txtCpu;

  @FXML
  private TextField txtGpu;

  @FXML
  private TextField txtRam;

  @FXML
  private TextField txtScreen;

  @FXML
  private TextField txtRearCamera;

  @FXML
  private TextField txtSelfieCamera;

  @FXML
  private TextField txtDimensions;

  @FXML
  private TextField txtBatteryCapacity;

  @FXML
  private TextField txtSim;

  @FXML
  private TextField txtOperatingSystem;

  @FXML
  private TextField txtWeight;

  @FXML
  private Button btnPreviousProductData;

  @FXML
  private Button btnSave;

  int count;

  private File imgSrc;
  final FileChooser fileChooser = new FileChooser();
  String path = Paths.get(".").toAbsolutePath().normalize() + "/src/com/images/";
  Product product;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    username.setText(ProjectManager.getInstance().getAccount().getUsername());
    List<Category> listCate = CategoryDatabaseHelper.getAllCategories();
    for (Category c : listCate) {
      cbCategory.getItems().add(c.getName());
    }
    cbCategory.setValue("Laptop");
  }

  public void setData(Product product) {
    demoImg.setVisible(true);
    clickUpload.setVisible(false);
    this.product = product;
    Image image = new Image(getClass().getResourceAsStream("/com/images/" + product.getImgSrc()));
    imgSrc = new File(path + product.getImgSrc());

    imgPreview.setImage(image);
    txtProductCode.setText(product.getProductCode());
    txtProductName.setText(product.getProductName());
    cbCategory.setValue(product.getCategoryName());
    txtWarrantyPeriod.setText(product.getWarrantyPeriod());
    txtImportPrice.setText(product.getImportPrice().toString());
    txtPrice.setText(product.getPrice().toString());
    txtHardDrive.setText(product.getHardDrive());
    txtOrigin.setText(product.getOrigin());
    txtSim.setText(product.getSim());
    txtWeight.setText(product.getWeight());
    txtDimensions.setText(product.getDimensions());
    txtScreen.setText(product.getScreen());
    txtCpu.setText(product.getCpu());
    txtGpu.setText(product.getGpu());
    txtRam.setText(product.getRam());
    txtOperatingSystem.setText(product.getOperatingSystem());
    txtRearCamera.setText(product.getRearCamera());
    txtSelfieCamera.setText(product.getSelfieCamera());
    txtBatteryCapacity.setText(product.getBatteryCapacity());
  }

  //Actions
  @FXML
  private void clickSave() throws IOException {
    Category category = CategoryDatabaseHelper.getCategoryByName(cbCategory.getValue());
    String imgName;
    if (imgSrc == null) {
      imgName = product.getImgSrc();
    } else {
      imgName = imgSrc.getName();
    }

    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setContentText("Are you sure you want to do it?");

    Optional<ButtonType> option = alert.showAndWait();
    if (option.get() == ButtonType.OK) {
      ProductDatabaseHelper.editProduct(category.getId(), txtProductCode.getText(),
          txtProductName.getText(), txtWarrantyPeriod.getText(),
          Integer.parseInt(txtImportPrice.getText()), Integer.parseInt(txtPrice.getText()),
          txtHardDrive.getText(), txtOrigin.getText(), imgName, txtScreen.getText(), txtCpu.getText(),
          txtGpu.getText(), txtRam.getText(), txtOperatingSystem.getText(), txtRearCamera.getText(),
          txtSelfieCamera.getText(), txtBatteryCapacity.getText(), txtSim.getText(),
          txtDimensions.getText(), txtWeight.getText(), product.getId());

      if (imgSrc != null) {
        Files.copy(imgSrc.toPath(), (new File(path + imgSrc.getName())).toPath(),
            StandardCopyOption.REPLACE_EXISTING);
      }
      Navigator.getInstance().goToProductsList();
    }
  }

  @FXML
  void deleteImage (MouseEvent event) {
    demoImg.setVisible(false);
    clickUpload.setVisible(true);
  }

  @FXML
  private void showChangeLanguageMousePressed(MouseEvent mouseEvent) {
    count++;
    changeLanguageContainer.setVisible(count % 2 != 0);
  }

  @FXML
  void clickChooseImage(MouseEvent mouseEvent) {
    imgSrc = fileChooser.showOpenDialog(Navigator.getInstance().getStage());

    if (imgSrc != null) {
      demoImg.setVisible(true);
      clickUpload.setVisible(false);
      Image image = new Image("file:///" + imgSrc.getAbsolutePath());
      imgPreview.setImage(image);
    }
  }

  @FXML
  private void setBtnNextBasicInfo(MouseEvent mouseEvent) {
    ValidationManager check = ValidationManager.getInstance();

    if(basicInfo.isVisible()) {
      int count = 0;
      if(txtProductCode.getText().isEmpty()) {
        errProductCode.setText("Product's code is required");
        count++;
      }else errProductCode.setText("");

      if(txtProductName.getText().isEmpty()) {
        errProductName.setText("Product's name is required");
        count++;
      }else errProductName.setText("");

      if(cbCategory.getValue() == null) {
        errProductCategory.setText("Product's category is required");
        count++;
      }else errProductCategory.setText("");

      if(txtImportPrice.getText().isEmpty()) {
        errImportPrice.setText("Product's import price is required");
        count++;
      }else if(!check.isPositiveNumber(txtImportPrice.getText())) {
        errImportPrice.setText("Product's import price must be a positive number");
        count++;
      }else errImportPrice.setText("");

      if(txtPrice.getText().isEmpty()) {
        errPrice.setText("Product's price is required");
        count++;
      }else if(!check.isPositiveNumber(txtPrice.getText())) {
        errPrice.setText("Product's price must be a positive number");
        count++;
      }else errPrice.setText("");

      if(txtHardDrive.getText().isEmpty()) {
        errHardDrive.setText("Hard drive is required");
        count++;
      }
      else errHardDrive.setText("");

      if(txtOrigin.getText().isEmpty()) {
        errOrigin.setText("Origin is required");
        count++;
      }else errOrigin.setText("");

      if(txtWarrantyPeriod.getText().isEmpty()) {
        errWarrantyPeriod.setText("Warranty Period date is required");
        count++;
      }else errWarrantyPeriod.setText("");

      if(count == 0)
        productImages.setVisible(true);
    }
  }

  @FXML
  private void setBtnNextProductImg(MouseEvent mouseEvent) {
    if(productImages.isVisible()) {
      int count = 0;
      if (imgSrc == null) {
        errImgSrc.setText("No photos selected");
        count++;
      } else errImgSrc.setText("");

      if(count == 0) productData.setVisible(true);
    }
  }

  @FXML
  private void setBtnPreviousProductImg(MouseEvent mouseEvent) {
    productImages.setVisible(false);
    basicInfo.setVisible(true);
  }

  @FXML
  private void setBtnPreviousProductData(MouseEvent mouseEvent) {
    productData.setVisible(false);
    productImages.setVisible(true);
  }
  //Dieu huong

  @FXML
  private void goToDashboard(MouseEvent mouseEvent) throws IOException {
    Navigator.getInstance().goToDashboard();
  }

  @FXML
  private void goToProductList(MouseEvent mouseEvent) throws IOException {
    Navigator.getInstance().goToProductsList();
  }

  @FXML
  private void goToInsertProduct(MouseEvent mouseEvent) throws IOException {
    Navigator.getInstance().goToInsertProduct();
  }

  @FXML
  private void goToAccountList(MouseEvent mouseEvent) throws IOException {
    Navigator.getInstance().goToAccountList();
  }

  @FXML
  private void goToCategoryList(MouseEvent mouseEvent) throws IOException {
    Navigator.getInstance().goToCategoryList();
  }

  @FXML
  private void goToOrder(MouseEvent mouseEvent) throws IOException {
    Navigator.getInstance().goToOrder();
  }

  @FXML
  private void logout(MouseEvent mouseEvent) throws IOException {
    Navigator.getInstance().goToLogin();
  }
}
