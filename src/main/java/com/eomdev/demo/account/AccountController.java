package com.eomdev.demo.account;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
@Slf4j
public class AccountController {

  @Autowired
  private AccountService accountService;

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private ModelMapper modelMapper;

  @GetMapping
  public ResponseEntity getAccounts() {
    List<Account> accounts = accountService.findByAll();

    List<AccountDto.Response> responses = new ArrayList<>();
    responses.addAll(modelMapper.map(accounts, new TypeToken<List<AccountDto.Response>>(){}.getType()));

    return new ResponseEntity(responses, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity createAccount(@RequestBody @Valid AccountDto.Create create,
      BindingResult result) {

    if (result.hasErrors()) {
      ErrorResponse errorResponse = new ErrorResponse();
      errorResponse.setMessage("잘못된 요청입니다.");
      errorResponse.setCode("bad.request");
      return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
    }

    Account newAccount = accountService.createAccount(create);
    return new ResponseEntity<>(modelMapper.map(newAccount, AccountDto.Response.class), HttpStatus.CREATED);
  }

  @GetMapping("/{accountId}")
  public ResponseEntity getAccountByName(@PathVariable String accountId) {
    Optional<Account> account = accountRepository.findById(accountId);
    return new ResponseEntity<>(modelMapper.map(account.get(), AccountDto.Response.class), HttpStatus.OK);
  }


}
