package com.epam.esm.dao.reader;

import com.epam.esm.domain.Tag;
import org.omg.IOP.TAG_ALTERNATE_IIOP_ADDRESS;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class TagMapper implements RowMapper<Tag> {

    @Override
    public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Tag.Builder.newInstance()
                .setId(rs.getInt("id"))
                .setName(rs.getString("name"))
                .build();
    }
}
