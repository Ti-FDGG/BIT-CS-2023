import helper.OrderClientRepositoryHelper;
import model.OrderClient;
import repository.IOrderClientRepository;
import repository.OrderClientRepository;

import java.sql.SQLException;
import java.util.List;

public class OrderClientRepositoryTest {

    public static void main(String[] args) throws SQLException {
        IOrderClientRepository repository = new OrderClientRepository();
        //查找姓“李”的客户
        List<OrderClient> clients = repository.getClients("李");
        //输出查找到的结果
        for (OrderClient orderClient : clients) {
            OrderClientRepositoryHelper.printOrderClient(orderClient);
        }
    }
}
