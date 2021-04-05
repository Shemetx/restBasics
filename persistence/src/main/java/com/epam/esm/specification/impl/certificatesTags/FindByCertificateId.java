package com.epam.esm.specification.impl.certificatesTags;

import com.epam.esm.specification.SqlSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FindByCertificateId implements SqlSpecification {

    private int id;

    public FindByCertificateId(int id) {
        this.id = id;
    }

    @Override
    public String toSQL() {
        return " WHERE cert_id = ? ";
    }

    @Override
    public PreparedStatement toPreparedStatement(Connection connection, String sql) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql + toSQL());
        preparedStatement.setInt(1,id);
        return preparedStatement;
    }
}
