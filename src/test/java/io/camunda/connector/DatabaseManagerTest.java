package io.camunda.connector;

import io.camunda.connector.db.DatabaseManager;
import io.camunda.connector.params.JDBCParams;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = LocalConnectorRuntime.class)
public class DatabaseManagerTest {

  private JDBCParams jdbcParams;

  private JDBCParams getJdbcParams() {
    if(jdbcParams == null) {
      jdbcParams = new JDBCParams();
      // These settings must match definitions inside `/src/test/resources/application.properties`
      jdbcParams.setJdbcUrl("jdbc:h2:mem:camunda");
      jdbcParams.setUserName("sa");
      jdbcParams.setPassword("password");
    }
    return jdbcParams;
  }

  @Test
  public void selectOneTest() {
    DatabaseManager databaseManager = new DatabaseManager(getJdbcParams());
    Map<String, Object> user1 =  databaseManager.selectOne("SELECT * from USERS where email = 'user1@email.com'", null);
    assertNotNull(user1);
    assertEquals("user1@email.com", user1.get("EMAIL"));

  }

  @Test
  public void selectListTest() {
    DatabaseManager databaseManager = new DatabaseManager(getJdbcParams());
    List<Map<String, Object>> users =  databaseManager.selectList("SELECT * from USERS", null);
    assertNotNull(users);
    assertEquals(11, users.size());
    assertEquals("user1@email.com", users.get(0).get("EMAIL"));
  }

  @Test
  public void selectMapTest() {
    DatabaseManager databaseManager = new DatabaseManager(getJdbcParams());
    Map<Object, List<Map<String, Object>>> users =  databaseManager.selectMap("SELECT * from USERS", null, "ID");
    assertNotNull(users);
    assertEquals("user1@email.com", users.get(1).get(0).get("EMAIL"));
    assertEquals("user2@email.com", users.get(2).get(0).get("EMAIL"));
  }

  @Test
  public void insertUpdateDeleteTest() {
    DatabaseManager databaseManager = new DatabaseManager(getJdbcParams());

    // INSERT
    String sql = "INSERT INTO users (id, username, password, email, first_name, last_name, created_at)\n" +
        "VALUES (12, 'user12', 'password12', 'user12@email.com', 'James', 'Madison', '2021-01-01 12:00:00');";
    int resultCount =  databaseManager.update(sql, null);
    assertEquals(1, resultCount);

    // UPDATE
    sql = "UPDATE USERS SET UPDATED_AT='2023-01-01 12:00:00'";
    resultCount =  databaseManager.update(sql, null);
    assertEquals(12, resultCount);

    // DELETE
    sql = "DELETE FROM USERS WHERE ID=12";
    resultCount =  databaseManager.update(sql, null);
    assertEquals(1, resultCount);
  }

}