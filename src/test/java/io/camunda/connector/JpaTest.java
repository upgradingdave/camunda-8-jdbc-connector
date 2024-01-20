/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH
 * under one or more contributor license agreements. Licensed under a proprietary license.
 * See the License.txt file for more information. You may not use this file
 * except in compliance with the proprietary license.
 */
package io.camunda.connector;

import static org.junit.jupiter.api.Assertions.*;

import io.camunda.connector.jpa.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = LocalConnectorRuntime.class)
public class JpaTest {

  @Autowired UserRepository userRepository;

  @Test
  public void findUserTest() {
    var user1 = userRepository.findById(Long.valueOf(1));
    assertNotNull(user1);
    assertEquals("user1@email.com", user1.get().getEmail());
  }

  @Test
  public void selectCountTest() {
    var user1 = userRepository.findById(Long.valueOf(1));
    assertNotNull(user1);
    assertEquals("user1@email.com", user1.get().getEmail());
  }
}
