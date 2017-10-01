
import com.tecsup.jdbc.helper.DAOException;
import com.tecsup.jdbc.modelo.DetallePedido;
import com.tecsup.jdbc.modelo.Pedido;
import com.tecsup.jdbc.modelo.Producto;
import com.tecsup.jdbc.service.PedidosNegocio;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;


public class PedidoNegocioTest {

   @Test
    public void realizarPedidoTest() {
        DetallePedido dp1 = new DetallePedido();
        dp1.setProducto(new Producto(1, "Papeles"));
        dp1.setPrecio(10d);
        dp1.setCantidad(1);
        DetallePedido dp2 = new DetallePedido();
        dp2.setProducto(new Producto(2, "Cinta"));
        dp2.setPrecio(20d);
        dp2.setCantidad(1);
        List<DetallePedido> detalles = new ArrayList<DetallePedido>();
        detalles.add(dp1);
        detalles.add(dp2);
        Pedido pedido = new Pedido();
        pedido.setDetalles(detalles);
        pedido.setIdUsuario(10);
        pedido.setFecha("2017-06-08");
        pedido.setTotal(400d);
        pedido.setEstado("prueba-error");
        PedidosNegocio negocio = new PedidosNegocio();
        try {
            negocio.realizarPedido(pedido);
        } catch (DAOException e) {
            Assert.fail("Falló la inserción");
} }

}
