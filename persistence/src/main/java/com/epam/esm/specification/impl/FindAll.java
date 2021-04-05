package com.epam.esm.specification.impl;

import com.epam.esm.specification.SqlSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *   find all entities in corresponding database table
 *
 * @author Yurchyk Uladzislau on 2021-03-30.
 * @version 0.0.1
 */
public class FindAll implements SqlSpecification {


    @Override
    public String toSQL() {
        return "";
    }

    @Override
    public PreparedStatement toPreparedStatement(Connection connection, String sql) throws SQLException {
        return connection.prepareStatement(sql + toSQL());
    }
}