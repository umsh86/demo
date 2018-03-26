package com.eomdev.demo.account;

public class AccountDuplicatedEmailException extends RuntimeException {

  private String username;

  public AccountDuplicatedEmailException(String message, String username) {
    super(message);
    this.username = username;
  }

  public String getUsername() {
    return username;
  }

}