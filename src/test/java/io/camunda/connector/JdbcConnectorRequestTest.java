package io.camunda.connector;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import io.camunda.connector.impl.ConnectorInputException;
import io.camunda.connector.test.outbound.OutboundConnectorContextBuilder;
import org.junit.jupiter.api.Test;

public class JdbcConnectorRequestTest {

  public static JdbcConnectorRequest mockInput() {
    var input = new JdbcConnectorRequest();

    var command = new CommandConfig();
    command.setCommandType("select");
    command.setSelectSql("SELECT * from DUAL");
    input.setCommand(command);

    var jdbc = new JdbcConfig();
    jdbc.setDriverName("h2");
    jdbc.setDriverSubProtocol("mem");
    jdbc.setHost("localhost");
    jdbc.setPort("");
    jdbc.setDbName("camunda");
    input.setJdbc(jdbc);

    var auth = new Authentication();
    auth.setPassword("secrets.JDBC_PASSWORD");
    auth.setUserName("testuser");
    input.setAuthentication(auth);

    return input;

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