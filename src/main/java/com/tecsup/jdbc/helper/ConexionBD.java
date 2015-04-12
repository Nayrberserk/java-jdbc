package com.tecsup.jdbc.helper;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBD {

    public static Connection obtenerConexion() {

        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/market", "root", "mysql");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }

}
