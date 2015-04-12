package com.tecsup.jdbc.dao;

import com.tecsup.jdbc.helper.ConexionBD;
import com.tecsup.jdbc.modelo.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO extends BaseDAO {

    public List<Categoria> getByNombre(String nombre) {

        String query = "select id, nombre, descripcion"
                + " from categoria where nombre like ?";

        List<Categoria> list = new ArrayList();

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionBD.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setString(1, "%" + nombre + "%");

            rs = stmt.executeQuery();

            while (rs.next()) {
                Categoria vo = new Categoria();
                vo.setId(rs.getLong("id"));
                vo.setNombre(rs.getString("nombre"));
                vo.setDescripcion(rs.getString("descripcion"));

                list.add(vo);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            this.cerrarResultSet(rs);
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return list;
    }

    public Categoria get(Long id) {
        String query = "select id, nombre, descripcion "
                + " from categoria where id = ?";

        Categoria vo = new Categoria();

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionBD.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setLong(1, id);

            rs = stmt.executeQuery();

            if (rs.next()) {
                vo.setId(rs.getLong(1));
                vo.setNombre(rs.getString(2));
                vo.setDescripcion(rs.getString(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.cerrarResultSet(rs);
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return vo;
    }

    public List<Categoria> list() {
        String query = "select id,nombre,descripcion from categoria order by nombre";

        List<Categoria> lista = new ArrayList<Categoria>();

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionBD.obtenerConexion();
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Categoria vo = new Categoria();
                vo.setId(rs.getLong("id"));
                vo.setNombre(rs.getString("nombre"));
                vo.setDescripcion(rs.getString("descripcion"));
                lista.add(vo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.cerrarResultSet(rs);
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }

        return lista;

    }

    public void guardar(Categoria categoria) {
        String query = "insert into categoria(nombre,descripcion) values (?,?)";

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionBD.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setString(1, categoria.getNombre());
            stmt.setString(2, categoria.getDescripcion());

            int i = stmt.executeUpdate();
            if (i != 1) {
                throw new SQLException("No se pudo insertar");
            }

            long id = 0;
            query = "select last_insert_id()";
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            if (rs.next()) {
                id = rs.getLong(1);
            }
            categoria.setId(id);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.cerrarResultSet(rs);
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
    }

    public void actualizar(Categoria categoria) {

        String query = "update categoria set nombre=?,descripcion=? "
                + " where id = ?";

        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConexionBD.obtenerConexion();

            stmt = con.prepareStatement(query);
            stmt.setString(1, categoria.getNombre());
            stmt.setString(2, categoria.getDescripcion());
            stmt.setLong(3, categoria.getId());

            int i = stmt.executeUpdate();
            if (i != 1) {
                throw new SQLException("No se pudo actualizar");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
    }

    public void eliminar(Long id) {

        String query = "delete from categoria "
                + " where id = ?";

        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConexionBD.obtenerConexion();

            stmt = con.prepareStatement(query);
            stmt.setLong(1, id);

            int i = stmt.executeUpdate();
            if (i != 1) {
                throw new SQLException("No se pudo eliminar");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
    }
}
