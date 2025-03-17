import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import model.OrderClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.OrderClientRepository;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class TestOrderClientRepository {

    private OrderClientRepository repo = null;
    private Random ran = new Random();

    //每个测试前，创建Repository实例
    @BeforeEach
    public void setUp() throws Exception {
        repo = new OrderClientRepository();
        ran = new Random();
    }
    //测试结束后，关闭Repository
    @AfterEach
    public void tearDown() throws Exception {
        repo.close();
    }

    @Test
    public void testGetClients() {
        //调用要被测试的代码
        List<OrderClient> clients = repo.getClients("t");
        for (OrderClient orderClient : clients) {
            System.out.println(orderClient);
        }
        //检查结果是否正确
        assertTrue(clients.size() > 0);
    }


    @Test
    public void testAddClient() throws SQLException {
        OrderClient client = new OrderClient();
        int ranValue = ran.nextInt(1000);
        client.setAddress("address" + ranValue);
        client.setClientName("clientName" + ranValue);
        int newId = repo.addClient(client);
        assertTrue(newId > 0);
        OrderClient clientFromDB = repo.getClient(newId);
        assertTrue(clientFromDB != null);
        System.out.println(clientFromDB);
    }

	@Test
	public void testUpdateClient() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteClient() {
		fail("Not yet implemented");
	}

}
