package com.epam.esm.dao.mapper;

import com.epam.esm.domain.GiftCertificate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public enum  GiftCertificateMapper implements StatementMapper<GiftCertificate> {
    INSTANCE;
    public static GiftCertificateMapper getInstance() {
        return INSTANCE;
    }
    @Override
    public PreparedStatement statementSet(String query, Connection connection, GiftCertificate entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,entity.getName());
        preparedStatement.setString(2,entity.getDescription());
        preparedStatement.setFloat(3,entity.getPrice());
        preparedStatement.setTimestamp(4, Timestamp.valueOf(entity.getCreateDate()));
        preparedStatement.setTimestamp(5,Timestamp.valueOf(entity.getLastUpdateDate()));
        preparedStatement.setInt(6,entity.getDuration());
        return preparedStatement;
    }
}
