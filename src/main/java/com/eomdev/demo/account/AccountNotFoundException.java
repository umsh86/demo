package com.eomdev.demo.account;

public class AccountNotFoundException extends RuntimeException {

  private String id;

  public AccountNotFoundException(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }


}
