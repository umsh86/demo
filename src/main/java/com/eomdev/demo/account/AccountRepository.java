package com.eomdev.demo.account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String>, AccountRepositoryCustom {

  Account findByEmail(String email);
  Account findByName(String name);

}