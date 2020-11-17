package com.xxqin;

import java.util.List;

/**
 * @author qinxue
 * @date 2020/11/12 17:50
 */
public class DataBaseConfig {

    private String connectionUrl;

    private String driver;

    private String username;

    private String password;

    private List<String> tables;

    private String parentPackage;

    private Boolean genEntity;

    private String targetEntityPackage;

    private Boolean genMapper;

    private String targetMapperPackage;

    private String targetMapperXml;

    private String author;

    public String getConnectionUrl() {
        return connectionUrl;
    }

    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getTables() {
        return tables;
    }

    public void setTables(List<String> tables) {
        this.tables = tables;
    }

    public String getParentPackage() {
        return parentPackage;
    }

    public void setParentPackage(String parentPackage) {
        this.parentPackage = parentPackage;
    }

    public String getTargetEntityPackage() {
        return targetEntityPackage;
    }

    public void setTargetEntityPackage(String targetEntityPackage) {
        this.targetEntityPackage = targetEntityPackage;
    }

    public String getTargetMapperPackage() {
        return targetMapperPackage;
    }

    public void setTargetMapperPackage(String targetMapperPackage) {
        this.targetMapperPackage = targetMapperPackage;
    }

    public String getTargetMapperXml() {
        return targetMapperXml;
    }

    public void setTargetMapperXml(String targetMapperXml) {
        this.targetMapperXml = targetMapperXml;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Boolean getGenEntity() {
        return genEntity;
    }

    public void setGenEntity(Boolean genEntity) {
        this.genEntity = genEntity;
    }

    public Boolean getGenMapper() {
        return genMapper;
    }

    public void setGenMapper(Boolean genMapper) {
        this.genMapper = genMapper;
    }

    @Override
    public String toString() {
        return "DataBaseConfig{" +
                "connectionUrl='" + connectionUrl + '\'' +
                ", driver='" + driver + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", tables=" + tables +
                ", parentPackage='" + parentPackage + '\'' +
                ", genEntity=" + genEntity +
                ", targetEntityPackage='" + targetEntityPackage + '\'' +
                ", genMapper=" + genMapper +
                ", targetMapperPackage='" + targetMapperPackage + '\'' +
                ", targetMapperXml='" + targetMapperXml + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

}
