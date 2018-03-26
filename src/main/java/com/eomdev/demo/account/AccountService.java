package com.eomdev.demo.account;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AccountService {

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private ModelMapper modelMapper;

  public Account createAccount(AccountDto.Create dto) {
    Account account = modelMapper.map(dto, Account.class);

    String email = dto.getEmail();
    if (existUser(email)) {
      log.error("account duplicated email exception. {}", email);
      throw new AccountDuplicatedEmailException("already registered email address", email);
    }
    account.setJoined(LocalDateTime.now());
    return accountRepository.save(account);
  }

  public boolean existUser(String email){
    Account account = findByEmail(email);
    return account != null ? true : false;
  }

  public Account findByEmail(String email){
    return accountRepository.findByEmail(email);
  }

  public Account getAccount(String id){
    Optional<Account> deletedAccount = accountRepository.findById(id);
    return deletedAccount.get();
  }

  public void deleteAccount(String id) {
    accountRepository.deleteById(id);
  }

  public Account updateAccount(String id, AccountDto.Update updateDto) {
    Account account = getAccount(id);
    if(account == null){
      throw new AccountNotFoundException(id);
    }
    modelMapper.map(updateDto, account);
    account.setUpdated(LocalDateTime.now());
    return accountRepository.save(account);
  }

  public Account findByName(String name) {
    return accountRepository.findByName(name);
  }

  public List<Account> findByAll() {
    return accountRepository.findAll();
  }


}
