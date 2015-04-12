
import com.tecsup.jdbc.dao.CategoriaDAO;
import com.tecsup.jdbc.modelo.Categoria;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *  
 * El objetivo de está clase es verificar el funcionamiento de la clase 
 * CategoriaDAO. Se utiliza la libreria JUNIT el cual provee las
 * herramientas para pruebas unitarias (cada método que ejecutamos dentro
 * de esta clase).
 * 
 * Las pruebas retornan como resultado Verdadero o Falso, si la prueba logra 
 * completar todas las pruebas significa que la clase verificada es correcta.
 * 
 * Las pruebas las diseña el programador. En este caso he creado diversas
 * condiciones que como mínimo deben ser superadas para aprobar que la
 * clase CategoriaDAO esta bien diseñada.
 * 
 * Para ejecutar la clase Click Derecho sobre esta y luego TEST FILE o
 * simplemente "Control + F6"
 * 
 * 
 */

public class CategoriaTest {

    CategoriaDAO categoríaDAO = new CategoriaDAO();

    /**
     * Verifico que la lista no sea NULL
     * Luego imprimo los nombres de las categorías
     */
    
    @Test
    public void list() {
        try {
            List<Categoria> categorías = categoríaDAO.list();
            
            Assert.assertNotNull(categorías);

            for (Categoria categoría : categorías) {
                System.out.println(categoría.getNombre());
            }

        } catch (Exception e) {
            Assert.fail("Fallo al listar");
        }
    }

    /**
     * Verifico que el listado no sea NULL
     * luego imprimo el listado de categorías
     */
    @Test
    public void getByNombre() {

        try {
            List<Categoria> categorías = categoríaDAO.getByNombre("ciencias");

            Assert.assertNotNull(categorías);
            for (Categoria categoría : categorías) {
                System.out.println(categoría.getId() + " " + categoría.getNombre());
            }

        } catch (Exception e) {
            Assert.fail("Fallo al listar");
        }
    }
    
    /**
     * 
     * Guardo la categoría, luego verifico que el ID no sea cero. (El ID nunca
     * puede ser cero en la base de datos)
     * 
     */

    @Test
    public void guardar() {

        try {
            Categoria categoría = new Categoria();
            categoría.setNombre("Historia");
            categoría.setDescripcion("categoría de prueba.");

            categoríaDAO.guardar(categoría);

            Assert.assertTrue(0 != categoría.getId());
            System.out.println(categoría.getId() + " " + categoría.getNombre());

        } catch (Exception e) {
            Assert.fail("Fallo al guardar");
        }

    }

    /**
     * 
     * Actualizo los datos de la categoría con ID 1
     * 
     */
    
    @Test
    public void actualizar() {

        try {
            Categoria categoría = new Categoria();
            categoría.setId(1l);
            categoría.setNombre("Ciencia Ficción");
            categoría.setDescripcion("Ciencia Ficción :D");

            categoríaDAO.actualizar(categoría);

            System.out.println(categoría.getId() + " " + categoría.getNombre());

        } catch (Exception e) {
            Assert.fail("Fallo al guardar");
        }

    }

    /**
     * Elimino la categoría con ID: 4 (para que esto funcione esa categoría 
     * debería existir en la base de datos)
     * 
     * 
     * Para no ejecutar un TEST simplemente quitamos el @Test.
     * Añadir @Test para ejecutar este método
     * 
     */
    
    public void eliminar() {

        try {
            categoríaDAO.eliminar(4l);

        } catch (Exception e) {
            Assert.fail("Fallo al guardar");
        }

    }

}
