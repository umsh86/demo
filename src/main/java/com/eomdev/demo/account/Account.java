package com.eomdev.demo.account;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Account {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(updatable = false)
  private String id;

  @NotBlank
  private String email;

  @NotBlank
  private String password;

  @NotBlank
  private String name;

  private LocalDateTime joined;
  private LocalDateTime updated;

}
