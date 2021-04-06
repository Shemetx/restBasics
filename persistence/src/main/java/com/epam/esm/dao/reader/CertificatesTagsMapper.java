package com.epam.esm.dao.reader;

import com.epam.esm.domain.CertificatesTags;
import com.epam.esm.domain.Tag;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
