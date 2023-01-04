package io.camunda.connector;

import static org.assertj.core.api.Assertions.assertThat;

import io.camunda.connector.params.CommandParams;
import io.camunda.connector.params.JDBCParams;
import io.camunda.connector.test.outbound.OutboundConnectorContextBuilder;
import org.junit.jupiter.api.Test;

public class JdbcConnectorRequestTest {

  public static JdbcConnectorRequest mockInput() {
    var input = new JdbcConnectorRequest();

    var jdbc = new JDBCParams();
    jdbc.setPassword("secrets.JDBC_PASSWORD");
    jdbc.setUserName("testuser");
    input.setJdbc(jdbc);

    var command = new CommandParams();
    command.setCommandType("selectOne");
    command.setSql("SELECT * from DUAL");

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