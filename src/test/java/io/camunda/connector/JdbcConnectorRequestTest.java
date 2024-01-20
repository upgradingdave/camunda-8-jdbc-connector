/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH
 * under one or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information regarding copyright
 * ownership. Camunda licenses this file to you under the Apache License,
 * Version 2.0; you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
    var context =
        OutboundConnectorContextBuilder.create().secret("JDBC_PASSWORD", "password value").build();

    context.replaceSecrets(input);

    // then
    assertThat(input).extracting("jdbc").extracting("userName").isEqualTo("testuser");

    assertThat(input).extracting("jdbc").extracting("password").isEqualTo("password value");
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
