package com.epam.esm.domain;


import com.epam.esm.util.PropertyReaderUtil;

public enum ApplicationProperties {

    INSTANCE;

    private final String url;
    private final String dbName;
    private final String user;
    private final String password;
    private final Integer availableConnections;

    public ApplicationProperties getInstance() {
        return INSTANCE;
    }

    ApplicationProperties() {
        this.url = PropertyReaderUtil.url();
        this.dbName = PropertyReaderUtil.dbName();
        this.user = PropertyReaderUtil.user();
        this.password = PropertyReaderUtil.password();
        this.availableConnections = Integer.parseInt(PropertyReaderUtil.availableConnections());
    }

    public String getUrl() {
        return url;
    }

    public String getDbName() {
        return dbName;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public Integer getAvailableConnections() {
        return availableConnections;
    }

}