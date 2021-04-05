package com.epam.esm.dao.impl;

import com.epam.esm.dao.BaseDao;
import com.epam.esm.dao.mapper.GiftCertificateMapper;
import com.epam.esm.dao.mapper.StatementMapper;
import com.epam.esm.dao.mapper.TagMapper;
import com.epam.esm.dao.reader.GiftCertificateReaderImpl;
import com.epam.esm.dao.reader.TagReaderImpl;
import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.domain.Tag;
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
public class GiftCertificateDaoImpl implements BaseDao<GiftCertificate> {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String INSERT_GIFT_CERTIFICATE = "INSERT INTO gift_certificate" +
            "(name,description,price,create_date,last_update_date,duration) " +
            "VALUES(?,?,?,?,?,?);";
    private static final String DELETE_GIFT_CERTIFICATE = "DELETE FROM gift_certificate ";
    private static final String UPDATE_GIFT_CERTIFICATE = "UPDATE gift_certificate SET " +
            " name = ?, description = ?, price = ?, create_date = ?, last_update_date = ?, duration = ? ";
    private static final String SELECT_GIFT_CERTIFICATE = "SELECT * FROM gift_certificate ";

    @Override
    public <S extends GiftCertificate> S add(S entity) {
        StatementMapper<GiftCertificate> setter = GiftCertificateMapper.getInstance();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = setter.statementSet
                     (INSERT_GIFT_CERTIFICATE, connection, entity);) {
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return entity;
    }

    @Override
    public void delete(GiftCertificate entity, SqlSpecification specification) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = specification.toPreparedStatement(connection, DELETE_GIFT_CERTIFICATE);
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
             PreparedStatement preparedStatement = specification.toPreparedStatement(connection, UPDATE_GIFT_CERTIFICATE);
        ) {
            i = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return i;
    }

    @Override
    public Optional<GiftCertificate> query(SqlSpecification specification) {
        Optional<GiftCertificate> user = Optional.empty();
        try (Connection connection = connectionPool.getConnection();) {
            user = (Optional<GiftCertificate>) QueryHelper.query(connection, specification, SELECT_GIFT_CERTIFICATE, GiftCertificateReaderImpl.getInstance());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public List<GiftCertificate> queryAll(SqlSpecification specification) {
        List<GiftCertificate> userList = null;
        try (Connection connection = connectionPool.getConnection();) {
            userList = (List<GiftCertificate>) QueryHelper.queryAll(connection, specification, SELECT_GIFT_CERTIFICATE, GiftCertificateReaderImpl.getInstance());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }
}
