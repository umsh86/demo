package com.eomdev.demo.account;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class AccountDto {

  @Data
  public static class Create {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String name;
  }

  @Data
  public static class Update {
    @NotBlank
    private String password;

    @NotBlank
    private String name;
  }


  @Data
  public static class Response {
    private String id;
    private String email;
    private String name;
    private LocalDateTime joined;
    private LocalDateTime updated;
  }

}
