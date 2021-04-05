package com.epam.esm.specification.impl.giftCertificate;

import com.epam.esm.dao.mapper.GiftCertificateMapper;
import com.epam.esm.dao.mapper.TagMapper;
import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.specification.SqlSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateGiftCertificateById implements SqlSpecification {

    private GiftCertificate giftCertificate;

    public UpdateGiftCertificateById(GiftCertificate giftCertificate) {
        this.giftCertificate = giftCertificate;
    }

    @Override
    public String toSQL() {
        return " WHERE id = ? ";
    }

    @Override
    public PreparedStatement toPreparedStatement(Connection connection, String sql) throws SQLException {
        PreparedStatement preparedStatement = GiftCertificateMapper.getInstance()
                .statementSet(sql+toSQL(),connection,giftCertificate);
        connection.prepareStatement(sql + toSQL());
        preparedStatement.setInt(7,giftCertificate.getId());
        return preparedStatement;
    }
}
