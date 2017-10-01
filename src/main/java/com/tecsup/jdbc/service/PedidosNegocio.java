package com.tecsup.jdbc.service;

import com.tecsup.jdbc.dao.PedidoDAO;
import com.tecsup.jdbc.helper.DAOException;
import com.tecsup.jdbc.modelo.Pedido;

public class PedidosNegocio {

    public void realizarPedido(Pedido pedido) throws DAOException {
        PedidoDAO dao = new PedidoDAO();
        try {
            dao.insertar(pedido);
        } catch (DAOException ex) {
            throw ex;
        }
    }

}
