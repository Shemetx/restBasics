package com.epam.esm.dao.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementMapper<T> {
    PreparedStatement statementSet(String query, Connection connection, T entity) throws SQLException;
}
