import com.jinxuliang.javafxusejdbc.repo.OrderClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class OrderClientRepositoryTests {
    private static OrderClientRepository repository = null;

    @BeforeAll
    public static void init() {
        repository = new OrderClientRepository();
    }

    @Test
    public void testGetAllOrderClients() {
        var clients = repository.getAllClients();
        Assertions.assertTrue(clients.size() > 0);
        clients.forEach(System.out::println);
    }
}
