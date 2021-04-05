package com.epam.esm.dao.reader;

import com.epam.esm.domain.CertificatesTags;
import com.epam.esm.domain.Tag;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public enum  CertificatesTagsReaderImpl implements AbstractResultSetReader<CertificatesTags> {
    INSTANCE;

    public static CertificatesTagsReaderImpl getInstance() {return INSTANCE;}
    @Override
    public CertificatesTags oneObjectRead(ResultSet resultSet) throws SQLException {
        return CertificatesTags.Builder.newInstance()
                .setCertificateId(resultSet.getInt("cert_id"))
                .setTagId(resultSet.getInt("tag_id"))
                .build();
    }

    @Override
    public List<CertificatesTags> manyObjectsRead(ResultSet resultSet) throws SQLException {
        List<CertificatesTags> tags = new ArrayList<>();
        while(resultSet.next()) {
            CertificatesTags certificatesTags = CertificatesTags.Builder.newInstance()
                    .setCertificateId(resultSet.getInt("cert_id"))
                    .setTagId(resultSet.getInt("tag_id"))
                    .build();
            tags.add(certificatesTags);
        }
        return tags;
    }
}
