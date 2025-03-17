package helper;

import model.OrderClient;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderClientRepositoryHelper {

    //从ResultSet当前记录中读取数据，创建一个OrderClient对象，返回给外界
    public static OrderClient getOrderClientFromResultSet(ResultSet rs)
            throws SQLException {
        if (rs == null) {
            return null;
        }
        OrderClient client = new OrderClient();
        client.setClientID(rs.getInt("ClientID"));
        client.setClientName(rs.getString("ClientName"));
        client.setAddress(rs.getString("Address"));
        return client;
    }

    public static void printOrderClient(OrderClient client) {
        if (client == null) {
            return;
        }
        System.out.println("---------------------");
        System.out.println("ClientId=" + client.getClientID());
        System.out.println("ClientName=" + client.getClientName());
        System.out.println("Address=" + client.getAddress());
    }
}
