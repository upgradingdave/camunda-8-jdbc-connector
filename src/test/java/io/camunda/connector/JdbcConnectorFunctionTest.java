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