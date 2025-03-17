import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    //引用本机SQL Server数据库服务器，并打开MyDB数据库
    private final static String MsSqlServer =
            "jdbc:sqlserver://localhost:1433;database=MyDB;";
    private final static String userName = "test";
    private final static String password = "abcdefg";

    public static void main(String[] args) throws SQLException {
        callStoreProcedure();
    }

    //调用存储过程
    private static void callStoreProcedure() throws SQLException {
        try (var connection =
                     DriverManager.getConnection(MsSqlServer,
                             userName, password)){
         //尝试调用SQL Server数据库中的findByName这一存储过程
        String sql = "{call findByName(?)}";
        CallableStatement callableStatement =
                connection.prepareCall(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
        //设置存储过程需要的输入型参数
        callableStatement.setString(1, "张");
        //执行查询，得到结果集
        ResultSet rs = callableStatement.executeQuery();
        //将结果集转换为Java对象集合
        var clients = getClients(rs);
        //显示对象集合中的数据
        clients.forEach(System.out::println);
        }
    }

    public static List<OrderClient> getClients(ResultSet rs) throws SQLException {
        var clients = new ArrayList<OrderClient>();
        while (rs.next()) {
            clients.add(getClientFromResultSet(rs));
        }
        return clients;
    }
    public static OrderClient getClientFromResultSet(ResultSet rs) throws SQLException {
        var client = new OrderClient();
        client.setClientID(rs.getInt("ClientID"));
        client.setClientName(rs.getString("ClientName"));
        client.setAddress(rs.getString("Address"));
        return client;
    }
}
