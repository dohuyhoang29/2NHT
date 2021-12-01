package com.helper;

import com.model.Account;
import com.model.Product;

public class ProjectManager {
  private static ProjectManager projectManager;
  private static Product product = new Product();
  private static Account account = new Account();

  private ProjectManager() {}

  public static ProjectManager getInstance() {
    if (projectManager == null) {
      projectManager = new ProjectManager();
    }
    return projectManager;
  }

  public ProjectManager getProjectManager() {
    return projectManager;
  }

  public void setProjectManager(ProjectManager projectManager) {
    ProjectManager.projectManager = projectManager;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    ProjectManager.product = product;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    ProjectManager.account = account;
  }
}
