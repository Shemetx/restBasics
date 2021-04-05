package com.epam.esm.specification.impl.tags;

import com.epam.esm.dao.mapper.GiftCertificateMapper;
import com.epam.esm.dao.mapper.TagMapper;
import com.epam.esm.domain.Tag;
import com.epam.esm.specification.SqlSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateTagById implements SqlSpecification {
    private Tag tag;

    public UpdateTagById(Tag tag) {
        this.tag = tag;
    }

    @Override
    public String toSQL() {
        return " WHERE id = ?";
    }

    @Override
    public PreparedStatement toPreparedStatement(Connection connection, String sql) throws SQLException {
        PreparedStatement preparedStatement = TagMapper.getInstance()
                .statementSet(sql+toSQL(),connection,tag);
        connection.prepareStatement(sql + toSQL());
        preparedStatement.setInt(2,tag.getId());
        return preparedStatement;
    }
}
