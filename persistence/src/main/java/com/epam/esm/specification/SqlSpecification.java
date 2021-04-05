package com.epam.esm.specification;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *   interface is used to specify data from database
 *
 * @author Yurchyk Uladzislau on 2021-01-15.
 * @version 0.0.1
 */
public interface SqlSpecification {
    String toSQL();
    PreparedStatement toPreparedStatement(Connection connection,String sql) throws SQLException;
}