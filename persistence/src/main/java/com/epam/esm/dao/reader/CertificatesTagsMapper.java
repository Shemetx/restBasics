package com.epam.esm.dao.reader;

import com.epam.esm.domain.CertificatesTags;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Gets entity as ResultSet form db and read it to CertificateTags entity
 */
@Component
public class CertificatesTagsMapper implements RowMapper<CertificatesTags> {

    @Override
    public CertificatesTags mapRow(ResultSet rs, int rowNum) throws SQLException {
        return CertificatesTags.Builder.newInstance()
                .setCertificateId(rs.getInt("cert_id"))
                .setTagId(rs.getInt("tag_id"))
                .build();
    }
}
