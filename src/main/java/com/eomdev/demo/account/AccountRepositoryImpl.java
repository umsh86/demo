package com.eomdev.demo.account;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccountRepositoryImpl implements AccountRepositoryCustom {

  @Override
  public void someCustomMethod() {
    log.info("some custom method..");
  }
}
