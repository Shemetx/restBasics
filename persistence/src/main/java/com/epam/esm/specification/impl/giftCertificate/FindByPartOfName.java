package com.epam.esm.specification.impl.giftCertificate;

import com.epam.esm.specification.SqlSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FindByPartOfName implements SqlSpecification {
    private String name;

    public FindByPartOfName(String name) {
        this.name = name ;
    }

    @Override
    public String toSQL() {
        return " WHERE locate(?,name) ";
    }

    @Override
    public PreparedStatement toPreparedStatement(Connection connection, String sql) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql + toSQL());
        preparedStatement.setString(1,name);
        return preparedStatement;
    }
}
