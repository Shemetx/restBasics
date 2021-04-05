package com.epam.esm.specification.impl.giftCertificate;

import com.epam.esm.specification.SqlSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FindByPartOfDescription implements SqlSpecification {
    private String description;

    public FindByPartOfDescription(String description) {
        this.description = description;
    }

    @Override
    public String toSQL() {
        return " WHERE locate(?,description) ";
    }

    @Override
    public PreparedStatement toPreparedStatement(Connection connection, String sql) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql + toSQL());
        preparedStatement.setString(1,description);
        return preparedStatement;
    }
}
