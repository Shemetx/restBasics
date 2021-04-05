package com.epam.esm.dao.mapper;

import com.epam.esm.domain.CertificatesTags;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public enum  CertificatesTagsMapper implements StatementMapper<CertificatesTags> {
    INSTANCE;
    public static CertificatesTagsMapper getInstance() {
        return INSTANCE;
    }
    @Override
    public PreparedStatement statementSet(String query, Connection connection, CertificatesTags entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,entity.getCertificateId());
        preparedStatement.setInt(2,entity.getTagId());
        return preparedStatement;
    }
}
