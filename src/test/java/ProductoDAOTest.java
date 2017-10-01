
import com.tecsup.jdbc.dao.ProductoDAO;
import com.tecsup.jdbc.helper.DAOException;
import org.junit.Assert;
import org.junit.Test;

public class ProductoDAOTest {

    @Test
    public void obtenerTotalTest() {
        ProductoDAO dao = new ProductoDAO();
        try {
            int total = dao.obtenerTotal();
            System.out.println("Total --> " + total);
            Assert.assertTrue(total > 0);
        } catch (DAOException e) {
            Assert.fail("falló");
        }
    }

}
