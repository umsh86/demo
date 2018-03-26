package com.eomdev.demo.account;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AccountJpaTest {

  @Autowired
  private MockMvc mvc;

  @Test
  public void testCreateAccount() throws Exception {
    AccountDto.Create create = new AccountDto.Create();
    create.setEmail("test1@gmail.com");
    create.setName("테스터1");
    create.setPassword("111111");

    String jsonString = this.jsonStringFromObject(create);

    mvc.perform(MockMvcRequestBuilders.post("/accounts")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonString)
    )
    .andDo(print())
    .andExpect(status().isCreated())
    .andExpect(jsonPath("$.id").isNotEmpty());

  }

  private String jsonStringFromObject(Object object) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.writeValueAsString(object);
  }

}
