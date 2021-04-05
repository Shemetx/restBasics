package com.epam.esm.specification.impl.certificatesTags;

import com.epam.esm.specification.SqlSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FindByTagId implements SqlSpecification {
    private int id;

    public FindByTagId(int id) {
        this.id = id;
    }

    @Override
    public String toSQL() {
        return " WHERE tag_id = ? ";
    }

    @Override
    public PreparedStatement toPreparedStatement(Connection connection, String sql) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql + toSQL());
        preparedStatement.setInt(1,id);
        return preparedStatement;
    }
}
