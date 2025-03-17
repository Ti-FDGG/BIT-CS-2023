package repository;

import helper.OrderClientRepositoryHelper;
import model.OrderClient;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderClientRepository
        implements IOrderClientRepository, Closeable {

    private Connection connection = null;
    private final String connectionStr = "jdbc:sqlite:test.db";

    public OrderClientRepository() throws SQLException {
        // 创建数据库连接
        connection = DriverManager.getConnection(connectionStr);
    }

    @Override
    public List<OrderClient> getClients(String firstName) {
        List<OrderClient> clients = new ArrayList<>();
        String sql = "select * from OrderClient where ClientName Like ? ";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + firstName + "%");
            // 查询表中保存的数据
            ResultSet rs = statement.executeQuery();
            // 遍历结果集，填充OrderClient集合
            while (rs.next()) {
                clients.add(OrderClientRepositoryHelper.getOrderClientFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return clients;
    }

    @Override
    public int addClient(OrderClient client) {
        if (client != null) {
            Statement statement;
            try {
                statement = connection.createStatement();

                String sql = "insert into OrderClient(ClientName,Address)" +
                        "values('" + client.getClientName() + "','" +
                        client.getAddress() + "')";
                int rowAffected = statement.executeUpdate(sql);
                System.out.println("rowAffected=" + rowAffected);
                sql = "select last_insert_rowid()";
                ResultSet rs = statement.executeQuery(sql);
                rs.next();
                int rowId = rs.getInt(1);
                System.out.println("rowId=" + rowId);
                return rowId;
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            }
        }
        return 0;
    }
    @Override
    public int updateClient(OrderClient client) {
        // TODO Auto-generated method stub
        return 0;
    }
    @Override
    public int deleteClient(int clientID) {
        // TODO Auto-generated method stub
        return 0;
    }
    public OrderClient getClient(int id) throws SQLException {

        String sql = "select * from OrderClient where ClientID=?";
        // 准备创建操作与存取的数据库命令
        PreparedStatement statement = connection.prepareStatement(sql);
        //设置参数值，第一个参数指的是SQL命令中的参数位置（从1开始，顺序递增），第二个参数是SQL参数的具体值
        statement.setInt(1, id);
        // 查询表中保存的数据
        ResultSet rs = statement.executeQuery();
        rs.next();
        return OrderClientRepositoryHelper.getOrderClientFromResultSet(rs);

    }
    @Override
    public void close() throws IOException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
