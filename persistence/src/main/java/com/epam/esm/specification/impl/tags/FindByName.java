package com.epam.esm.specification.impl.tags;

import com.epam.esm.specification.SqlSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FindByName implements SqlSpecification {

    private String name;

    public FindByName(String name) {
        this.name = name;
    }

    @Override
    public String toSQL() {
        return " WHERE name = ? ";
    }

    @Override
    public PreparedStatement toPreparedStatement(Connection connection, String sql) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql + toSQL());
        preparedStatement.setString(1,name);
        return preparedStatement;
    }
}
