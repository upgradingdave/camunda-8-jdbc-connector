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

public class JdbcConnectorFunctionTest {

  /*@Test
  void shouldReturnReceivedMessageWhenExecute() throws Exception {
    // given
    var input = JdbcConnectorRequestTest.mockInput();

    var function = new JdbcConnectorFunction();
    var context = OutboundConnectorContextBuilder.create()
      .variables(input)
      .build();
    // when
    var result = function.execute(context);
    // then
    assertThat(result)
      .isInstanceOf(JdbcConnectorResult.class)
      .extracting("myProperty")
      .isEqualTo("Message received: Hello World!");
  /*}

  /* @Test
  void shouldThrowWithErrorCodeWhenMessageStartsWithFail() {
    // given
    var input = JdbcConnectorRequestTest.mockInput();

    var function = new JdbcConnectorFunction();
    var context = OutboundConnectorContextBuilder.create()
        .variables(input)
        .build();
    // when
    var result = catchThrowable(() -> function.execute(context));
    // then
    assertThat(result)
        .isInstanceOf(ConnectorException.class)
        .hasMessageContaining("started with 'fail'")
        .extracting("errorCode").isEqualTo("FAIL");
  }*/
}
