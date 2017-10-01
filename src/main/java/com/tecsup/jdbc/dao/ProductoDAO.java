package com.tecsup.jdbc.dao;

import com.tecsup.jdbc.helper.C3P0DataSource;
import com.tecsup.jdbc.helper.DAOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class ProductoDAO extends BaseDAO {

    public int obtenerTotal() throws DAOException {
        System.out.println("obtenerTotal()");
        Connection cons = null;
        CallableStatement callstmt = null;
        int resultado = -1;
        try {
            String call = "{CALL sp_productos_total(?)}";
            cons = C3P0DataSource.getInstance().getConnection();
            callstmt = (CallableStatement) cons.prepareCall(call);
            callstmt.registerOutParameter(1, Types.INTEGER);
            callstmt.execute();
            resultado = callstmt.getInt(1);
        } catch (SQLException e) {
            throw new DAOException(e.toString());
        } catch (Exception e) {
            throw new DAOException(e.toString());
        } finally {
            cerrarCallable(callstmt);
            cerrarConexion(cons);
        }
        return resultado;
    }

}
