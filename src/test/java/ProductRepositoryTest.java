import com.example.dflipflop.Entities.Product;
import com.example.dflipflop.Repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {ProductRepository.class,})
public class ProductRepositoryTest {

    @DataJpaTest
    public class ProductRepositoryTests {
        @Autowired
        private TestEntityManager entityManager;

        @Autowired
        private ProductRepository repository;

        @Test
        public void testSaveNewProduct() {
            entityManager.persist(new Product("Banane", "Test", (float) 12, " "));

          /*  Product product = repository.findByName("iPhone 10");

            assertThat(product.getName()).isEqualTo("iPhone 10");*/
        }
    }
}

