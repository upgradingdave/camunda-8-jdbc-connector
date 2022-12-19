package io.camunda.connector.db;

import io.camunda.connector.JdbcConnectorRequest;
import io.camunda.connector.api.error.ConnectorException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class Database {

  public abstract Connection getConnection(JdbcConnectorRequest request);
  public List<Object> convertResult(ResultSet resultSet) throws SQLException {
    final List<Object> result = new ArrayList<>();
    while (resultSet.next()) {
      final List<Object> row = new ArrayList<>();
      int colCount = resultSet.getMetaData().getColumnCount();
      for (int i = 0; i < colCount; i++) {
        row.add(resultSet.getObject(i+1));
      }
      result.add(row);
    }
    return result;
  }

  public List<Object> select(Connection connection, final String query) throws ConnectorException, SQLException {
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery(query);
    return convertResult(resultSet);
  }

  public Integer insert(Connection connection, final String insert) throws ConnectorException, SQLException {
    Statement statement = connection.createStatement();
    return statement.executeUpdate(insert);
  }

}
