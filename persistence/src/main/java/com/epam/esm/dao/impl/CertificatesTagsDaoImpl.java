package com.epam.esm.dao.impl;

import com.epam.esm.dao.BaseDao;
import com.epam.esm.dao.mapper.CertificatesTagsMapper;
import com.epam.esm.dao.mapper.GiftCertificateMapper;
import com.epam.esm.dao.mapper.StatementMapper;
import com.epam.esm.dao.reader.CertificatesTagsReaderImpl;
import com.epam.esm.dao.reader.GiftCertificateReaderImpl;
import com.epam.esm.domain.CertificatesTags;
import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.pool.ConnectionPool;
import com.epam.esm.specification.SqlSpecification;
import com.epam.esm.util.QueryHelper;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class CertificatesTagsDaoImpl implements BaseDao<CertificatesTags> {

    private ConnectionPool connectionPool = ConnectionPool.getInstance();


    private static final String INSERT_CERTIFICATE_TAG = "INSERT INTO certificates_tags(cert_id,tag_id) " +
            "VALUES(?,?);";
    private static final String DELETE_CERTIFICATE_TAG = "DELETE FROM certificates_tags ";
    private static final String UPDATE_CERTIFICATE_TAG = "UPDATE certificates_tags SET " +
            " cert_id = ?, tag_id = ? ";
    private static final String SELECT_CERTIFICATE_TAG = "SELECT * FROM certificates_tags ";

    @Override
    public <S extends CertificatesTags> S add(S entity) {
        StatementMapper<CertificatesTags> setter = CertificatesTagsMapper.getInstance();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = setter.statementSet
                     (INSERT_CERTIFICATE_TAG, connection, entity);) {
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return entity;
    }

    @Override
    public void delete(CertificatesTags entity, SqlSpecification specification) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = specification.toPreparedStatement(connection, DELETE_CERTIFICATE_TAG);
        ) {
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public int update(SqlSpecification specification) {
        int i = 0;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = specification.toPreparedStatement(connection, UPDATE_CERTIFICATE_TAG);
        ) {
            i = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return i;
    }

    @Override
    public Optional<CertificatesTags> query(SqlSpecification specification) {
        Optional<CertificatesTags> certificatesTags = Optional.empty();
        try (Connection connection = connectionPool.getConnection();) {
            certificatesTags = (Optional<CertificatesTags>) QueryHelper.query(connection, specification, SELECT_CERTIFICATE_TAG, CertificatesTagsReaderImpl.getInstance());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return certificatesTags;
    }

    @Override
    public List<CertificatesTags> queryAll(SqlSpecification specification) {
        List<CertificatesTags> certificatesTags = null;
        try (Connection connection = connectionPool.getConnection();) {
            certificatesTags = (List<CertificatesTags>) QueryHelper.queryAll(connection, specification, SELECT_CERTIFICATE_TAG, CertificatesTagsReaderImpl.getInstance());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return certificatesTags;
    }
}
