package com.epam.esm.util;


import com.epam.esm.dao.reader.AbstractResultSetReader;
import com.epam.esm.domain.BaseEntity;
import com.epam.esm.specification.SqlSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 *  Util class that helps dao layer execute queries
 *
 * @author Yurchyk Uladzislau on 2021-01-15.
 * @version 0.0.1
 */
public class QueryHelper {

    public static Optional<? extends BaseEntity> query(Connection connection, SqlSpecification specification,
                                                       String sqlQuery,
                                                       AbstractResultSetReader<? extends BaseEntity> reader) throws SQLException {
        Optional<BaseEntity> entity;
        PreparedStatement preparedStatement = specification.toPreparedStatement(connection, sqlQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            entity = Optional.of(reader.oneObjectRead(resultSet));
            return entity;
        } else {
            return Optional.empty();
        }
    }

    public static List<? extends BaseEntity> queryAll(Connection connection, SqlSpecification specification,
                                                      String sqlQuery,
                                                      AbstractResultSetReader<? extends BaseEntity> reader) throws SQLException {
        List<? extends BaseEntity> entities;
        PreparedStatement preparedStatement = specification.toPreparedStatement(connection, sqlQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        entities = reader.manyObjectsRead(resultSet);
        return entities;
    }

}