package com.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationManager {
  private ValidationManager () {};

  private static ValidationManager validationManager;

  public static ValidationManager getInstance() {
    if (validationManager == null) {
      validationManager = new ValidationManager();
    }
    return validationManager;
  }

  static Pattern pattern;
  static Matcher matcher;
  static String regex;

  public boolean validRequired(String text) {
    if (text == null) {
      return false;
    }
    return true;
  }

  public boolean validUsername(String text) {
    regex = "^[A-z0-9]{1,}$";
    pattern = Pattern.compile(regex);

    matcher = pattern.matcher(text);
    if (!matcher.find()) {
      return false;
    }
    return true;
  }

  public boolean validEmail(String text) {
    regex = "^[A-z0-9_.]{1,}@[A-z0-9_.]{1,}\\.[A-z0-9]{1,}$";
    pattern = Pattern.compile(regex);

    matcher = pattern.matcher(text);
    if (!matcher.find()) {
      return false;
    }
    return true;
  }

  public boolean validPhoneNumber(String text) {
    regex = "^[0-9]{10}$";
    pattern = Pattern.compile(regex);

    matcher = pattern.matcher(text);
    if (!matcher.find()) {
      return false;
    }
    return true;
  }

  public boolean validPassword(String text) {
    regex = "^[.[\\S]]{8,}$";
    pattern = Pattern.compile(regex);

    matcher = pattern.matcher(text);
    if (!matcher.find()) {
      return false;
    }
    return true;
  }

  public static boolean isPositiveNumber(String txt) {
    if (txt == null) {
      return false;
    }

    try {
      float n = Float.parseFloat(txt);
      if (n < 0) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }

    return true;
  }
}
