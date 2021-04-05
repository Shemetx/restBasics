package com.epam.esm.dao.mapper;

import com.epam.esm.domain.Tag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public enum TagMapper implements StatementMapper<Tag>{
    INSTANCE;
    public static TagMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public PreparedStatement statementSet(String query, Connection connection, Tag entity) throws SQLException {
       PreparedStatement preparedStatement = connection.prepareStatement(query);
       preparedStatement.setString(1,entity.getName());
        return preparedStatement;
    }
}
