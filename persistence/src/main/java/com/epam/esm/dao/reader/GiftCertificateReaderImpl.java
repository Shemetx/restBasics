package com.epam.esm.dao.reader;

import com.epam.esm.domain.GiftCertificate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public enum GiftCertificateReaderImpl implements AbstractResultSetReader<GiftCertificate> {
    INSTANCE;

    public static GiftCertificateReaderImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public GiftCertificate oneObjectRead(ResultSet resultSet) throws SQLException {

        return GiftCertificate.Builder.newInstance()
                .setId(resultSet.getInt("id"))
                .setName(resultSet.getString("name"))
                .setDescription(resultSet.getString("description"))
                .setPrice(resultSet.getFloat("price"))
                .setDuration(resultSet.getInt("duration"))
                .setCreateDate(resultSet.getTimestamp("create_date").toLocalDateTime())
                .setLastUpdateDate(resultSet.getTimestamp("last_update_date").toLocalDateTime())
                .build();
        }

    @Override
    public List<GiftCertificate> manyObjectsRead(ResultSet resultSet) throws SQLException {
            List<GiftCertificate> list = new ArrayList<>();
            while(resultSet.next()) {
                GiftCertificate giftCertificate = GiftCertificate.Builder.newInstance()
                        .setId(resultSet.getInt("id"))
                        .setName(resultSet.getString("name"))
                        .setDescription(resultSet.getString("description"))
                        .setPrice(resultSet.getFloat("price"))
                        .setDuration(resultSet.getInt("duration"))
                        .setCreateDate(resultSet.getTimestamp("create_date").toLocalDateTime())
                        .setLastUpdateDate(resultSet.getTimestamp("last_update_date").toLocalDateTime())
                        .build();
                list.add(giftCertificate);
            }
        return list;
    }
}
