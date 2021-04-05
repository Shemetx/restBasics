package com.epam.esm.dao.impl;

import com.epam.esm.dao.BaseDao;
import com.epam.esm.dao.mapper.StatementMapper;
import com.epam.esm.dao.mapper.TagMapper;
import com.epam.esm.dao.reader.TagReaderImpl;
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
public class TagsDaoImpl implements BaseDao<Tag> {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();


    private static final String INSERT_TAG = "INSERT INTO tag(name) " +
            "VALUES(?);";
    private static final String DELETE_TAG = "DELETE FROM tag ";
    private static final String SELECT_TAG = "SELECT * FROM tag ";
    private static final String UPDATE_TAG = "UPDATE tag SET " +
            " name = ? ";

    @Override
    public <S extends Tag> S add(S entity) {
        StatementMapper<Tag> setter = TagMapper.getInstance();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = setter.statementSet
                     (INSERT_TAG, connection, entity);) {
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return entity;
    }

    @Override
    public void delete(Tag entity, SqlSpecification specification) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = specification.toPreparedStatement(connection, DELETE_TAG);
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
             PreparedStatement preparedStatement = specification.toPreparedStatement(connection, UPDATE_TAG);
        ) {
            i = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return i;
    }

    @Override
    public Optional<Tag> query(SqlSpecification specification) {
        Optional<Tag> user = Optional.empty();
        try (Connection connection = connectionPool.getConnection();) {
            user = (Optional<Tag>) QueryHelper.query(connection, specification, SELECT_TAG, TagReaderImpl.getInstance());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public List<Tag> queryAll(SqlSpecification specification) {
        List<Tag> userList = null;
        try (Connection connection = connectionPool.getConnection();) {
            userList = (List<Tag>) QueryHelper.queryAll(connection, specification, SELECT_TAG, TagReaderImpl.getInstance());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }
}
