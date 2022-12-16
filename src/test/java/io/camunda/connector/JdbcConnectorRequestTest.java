package io.camunda.connector;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import io.camunda.connector.params.AuthenticationParams;
import io.camunda.connector.params.CommandParams;
import io.camunda.connector.params.H2Params;
import io.camunda.connector.params.JDBCParams;
import io.camunda.connector.test.outbound.OutboundConnectorContextBuilder;
import org.junit.jupiter.api.Test;

public class JdbcConnectorRequestTest {

  public static JdbcConnectorRequest mockInput() {
    var input = new JdbcConnectorRequest();

    var command = new CommandParams();
    command.setCommandType("select");
    command.setSelectSql("SELECT * from DUAL");
    input.setCommand(command);

    var jdbc = new JDBCParams();
    jdbc.setDriverName("h2");

    input.setJdbc(jdbc);

    var h2 = new H2Params();
    h2.setConnectionMode("mem");
    h2.setHost("localhost");
    h2.setPort("");
    h2.setDbName("camunda");

    var auth = new AuthenticationParams();
    auth.setPassword("secrets.JDBC_PASSWORD");
    auth.setUserName("testuser");
    input.setAuthentication(auth);

    return input;

  }

  @Test
  void shouldCreateContext() {
    // given
    var input = mockInput();

    // when
    var context = OutboundConnectorContextBuilder.create()
      .secret("JDBC_PASSWORD", "password value")
      .build();
    // then
    assertThat(input)
      .extracting("jdbc")
      .extracting("driverName")
      .isEqualTo("h2");
  }

  /*@Test
  void shouldReplaceTokenSecretWhenReplaceSecrets() {
    // given
    var input = mockInput();

    var context = OutboundConnectorContextBuilder.create()
        .secret("JDBC_PASSWORD", "password value")
        .build();
    // when
    context.replaceSecrets(input);
    // then
    assertThat(input)
        .extracting("authentication")
        .extracting("password")
        .isEqualTo("password value");
  }*/

  /*@Test
  void shouldFailWhenValidate_NoAuthentication() {
    // given
    var input = mockInput();
    var context = OutboundConnectorContextBuilder.create().build();
    // when
    assertThatThrownBy(() -> context.validate(input))
      // then
      .isInstanceOf(ConnectorInputException.class)
      .hasMessageContaining("authentication");
  }*/


}