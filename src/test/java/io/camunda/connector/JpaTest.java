package io.camunda.connector;

import io.camunda.connector.jpa.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = LocalConnectorRuntime.class)
public class JpaTest {

  @Autowired
  UserRepository userRepository;

  @Test
  public void findUserTest() {
    var user1 = userRepository.findById(Long.valueOf(1));
    assertNotNull(user1);
    assertEquals("user1@email.com", user1.get().getEmail());
  }
}
