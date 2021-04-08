package com.epam.esm.dao.reader;

import com.epam.esm.domain.GiftCertificate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Gets entity as ResultSet form db and read it to GiftCertificate entity
 */
@Component
public class GiftCertificateMapper implements RowMapper<GiftCertificate> {

    @Override
    public GiftCertificate mapRow(ResultSet rs, int rowNum) throws SQLException {
        return GiftCertificate.Builder.newInstance()
                .setId(rs.getInt("id"))
                .setName(rs.getString("name"))
                .setDescription(rs.getString("description"))
                .setPrice(rs.getFloat("price"))
                .setDuration(rs.getInt("duration"))
                .setCreateDate(rs.getTimestamp("create_date").toLocalDateTime())
                .setLastUpdateDate(rs.getTimestamp("last_update_date").toLocalDateTime())
                .build();
    }
}
