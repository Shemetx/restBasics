package com.epam.esm.dao.reader;

import com.epam.esm.domain.Tag;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public enum  TagReaderImpl implements AbstractResultSetReader<Tag> {
    INSTANCE;
    public static TagReaderImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Tag oneObjectRead(ResultSet resultSet) throws SQLException {
        return Tag.Builder.newInstance()
                .setId(resultSet.getInt("id"))
                .setName(resultSet.getString("name"))
                .build();

    }

    @Override
    public List<Tag> manyObjectsRead(ResultSet resultSet) throws SQLException {
       List<Tag> tags = new ArrayList<>();
       while(resultSet.next()) {
           Tag tag = Tag.Builder.newInstance()
                   .setId(resultSet.getInt("id"))
                   .setName(resultSet.getString("name"))
                   .build();
           tags.add(tag);
       }
        return tags;
    }
}
