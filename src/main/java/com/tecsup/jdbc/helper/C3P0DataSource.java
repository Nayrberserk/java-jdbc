package com.tecsup.jdbc.helper;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class C3P0DataSource {

    private ComboPooledDataSource comboPoolDS;
    private static C3P0DataSource ds;

    private C3P0DataSource() {
        try {
            comboPoolDS = new ComboPooledDataSource();
            comboPoolDS.setDriverClass("com.mysql.jdbc.Driver");
            comboPoolDS.setJdbcUrl("jdbc:mysql://localhost:3306/tecsup");
            comboPoolDS.setUser("root");
            comboPoolDS.setPassword("mysql");
            comboPoolDS.setAcquireIncrement(3);
            comboPoolDS.setMinPoolSize(5);
            comboPoolDS.setMaxPoolSize(20);
            comboPoolDS.setMaxIdleTime(20);
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
        }
    }

    public static C3P0DataSource getInstance() {
        if (ds == null) {
            ds = new C3P0DataSource();
        }
        return ds;
    }

    public Connection getConnection() {
        Connection con = null;
        try {
            con = comboPoolDS.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

}
