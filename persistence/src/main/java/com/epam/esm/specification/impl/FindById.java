package com.epam.esm.specification.impl;

import com.epam.esm.specification.SqlSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FindById implements SqlSpecification {

    private int id;

    public FindById(int id) {
        this.id = id;
    }

    @Override
    public String toSQL() {
        return "WHERE id = ?";
    }

    @Override
    public PreparedStatement toPreparedStatement(Connection connection, String sql) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql + toSQL());
        preparedStatement.setInt(1,id);
        return preparedStatement;
    }
}
