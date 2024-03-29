/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH
 * under one or more contributor license agreements. Licensed under a proprietary license.
 * See the License.txt file for more information. You may not use this file
 * except in compliance with the proprietary license.
 */
package io.camunda.connector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.camunda.connector.db.DatabaseManager;
import io.camunda.connector.params.JDBCParams;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = LocalConnectorRuntime.class)
public class DatabaseManagerTest {

  @Value("${spring.datasource.url}")
  private String jdbcUrl;

  @Value("${spring.datasource.username}")
  private String dbUsername;

  @Value("${spring.datasource.password}")
  private String dbPassword;

  private JDBCParams jdbcParams;

  private JDBCParams getJdbcParams() {
    if (jdbcParams == null) {
      jdbcParams = new JDBCParams();

      jdbcParams.setJdbcUrl(jdbcUrl);
      jdbcParams.setUserName(dbUsername);
      jdbcParams.setPassword(dbPassword);
    }
    return jdbcParams;
  }

  @Test
  public void selectOneTest() {
    DatabaseManager databaseManager = new DatabaseManager(getJdbcParams());
    Map<Integer, Object> params = new HashMap<>();
    params.put(1, "user1@email.com");
    Map<String, Object> user1 =
        databaseManager.selectOne("SELECT * from USERS where email = ?", params);
    assertNotNull(user1);
    assertEquals("user1@email.com", user1.get("EMAIL"));
  }

  @Test
  public void selectListTest() {
    DatabaseManager databaseManager = new DatabaseManager(getJdbcParams());
    List<Map<String, Object>> users = databaseManager.selectList("SELECT * from USERS", null);
    assertNotNull(users);
    assertEquals(11, users.size());
    assertEquals("user1@email.com", users.get(0).get("EMAIL"));
  }

  @Test
  public void selectMapTest() {
    DatabaseManager databaseManager = new DatabaseManager(getJdbcParams());
    Map<Object, List<Map<String, Object>>> users =
        databaseManager.selectMap("SELECT * from USERS", null, "ID");
    assertNotNull(users);
    assertEquals("user1@email.com", users.get(1).get(0).get("EMAIL"));
    assertEquals("user2@email.com", users.get(2).get(0).get("EMAIL"));
  }

  @Test
  public void insertUpdateDeleteTest() {
    DatabaseManager databaseManager = new DatabaseManager(getJdbcParams());

    // INSERT
    /*
        String sql =
            "INSERT INTO users (id, username, password, email, first_name, last_name, created_at)\n"
                + "VALUES (12, 'user12', 'password12', 'user12@email.com', 'James', 'Madison', '2021-01-01 12:00:00');";
    */
    String sql =
        "INSERT INTO users (username, password, email, first_name, last_name, created_at)\n"
            + "VALUES ('user12', 'password12', 'user12@email.com', 'James', 'Madison', CURRENT_TIMESTAMP);";

    int resultCount = databaseManager.update(sql, null);
    assertEquals(1, resultCount);

    // UPDATE
    sql = "UPDATE USERS SET UPDATED_AT='2023-01-01 12:00:00'";
    resultCount = databaseManager.update(sql, null);
    assertEquals(12, resultCount);

    // DELETE
    sql = "DELETE FROM USERS WHERE EMAIL='user12@email.com'";
    resultCount = databaseManager.update(sql, null);
    assertEquals(1, resultCount);
  }
}
