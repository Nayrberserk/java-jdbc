package com.tecsup.jdbc.dao;

import com.tecsup.jdbc.helper.C3P0DataSource;
import com.tecsup.jdbc.helper.DAOException;
import com.tecsup.jdbc.modelo.DetallePedido;
import com.tecsup.jdbc.modelo.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PedidoDAO extends BaseDAO {

    public Pedido insertar(Pedido pedido) throws DAOException {
        System.out.println("PedidoDAO: insertar()");
        String query = "INSERT INTO pedido (id_usuario,fecha,estado,total) VALUES(?,?,?,?)";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = C3P0DataSource.getInstance().getConnection();
            con.setAutoCommit(false);
            stmt = con.prepareStatement(query);
            stmt.setInt(1, pedido.getIdUsuario());
            stmt.setString(2, pedido.getFecha());
            stmt.setString(3, pedido.getEstado());
            stmt.setDouble(4, pedido.getTotal());
            int i = stmt.executeUpdate();
            if (i != 1) {
                throw new SQLException("No se pudo insertar");
            }
            int idPedido = findLastInsertID(con, stmt, rs);
            pedido.setId(idPedido);
            this.insertDetalles(pedido, con, stmt, rs);
            con.commit();
        } catch (SQLException ex) {
            try {
                con.rollback();
            } catch (SQLException sqlex) {
                throw new DAOException(sqlex.getMessage());
            }
            System.err.println(ex.getMessage());
            throw new DAOException(ex.getMessage());
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException e) {
                throw new DAOException(e.getMessage());
            }
            this.cerrarResultSet(rs);
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return pedido;
    }

    private void insertDetalles(Pedido pedido,
            Connection con,
            PreparedStatement stmt,
            ResultSet rs) throws SQLException {
        String query = "INSERT INTO detalle_pedido(id_pedido, id_producto,precio,cantidad) VALUES( ?,  ?,  ?,  ?)";
        for (DetallePedido ped : pedido.getDetalles()) {
            stmt = con.prepareStatement(query);
            
            // stmt.setInt(1, 10000); // error generado
            stmt.setInt(1, pedido.getId());
            
            stmt.setInt(2, ped.getProducto().getId());
            stmt.setDouble(3, ped.getPrecio());
            stmt.setInt(4, ped.getCantidad());
            int u = stmt.executeUpdate();
            if (u != 1) {
                throw new SQLException("No se pudo insertar");
            }
            int idDetalle = findLastInsertID(con, stmt, rs);
            ped.setId(idDetalle);
        }
    }

    private int findLastInsertID(Connection con,
            PreparedStatement stmt,
            ResultSet rs) throws SQLException {
        int lastID = 0;
        String query = "SELECT LAST_INSERT_ID()";
        stmt = con.prepareStatement(query);
        rs = stmt.executeQuery();
        if (rs.next()) {
            lastID = rs.getInt(1);
        }
        return lastID;
    }

}
