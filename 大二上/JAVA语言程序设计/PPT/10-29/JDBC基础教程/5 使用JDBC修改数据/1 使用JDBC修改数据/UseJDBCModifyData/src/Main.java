import java.sql.*;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

public class Main {

    private static final String dbUrl = "jdbc:sqlite:test.db";
    //引用本机SQL Server数据库服务器，并打开MyDB数据库
    private final static String MsSqlServer =
            "jdbc:sqlserver://localhost:1433;database=MyDB;";
    private final static String userName = "test";
    private final static String password = "abcdefg";

    public static void main(String[] args) {
        testInsert();
        testUpdate();
        testDelete();
        testUpdateResultSet();
        testInsertBatch();
    }

    //测试数据的插入
    private static void testInsert() {
        try (Connection connection = DriverManager.getConnection(dbUrl);
             Statement statement = connection.createStatement()) {
            int ranValue = new Random().nextInt(100);
            //生成SQL命令
            String clientName = "client" + ranValue;
            String address = "address of client " + ranValue;
            //插入数据时，可以不指定自增字段的值，而由数据库自己生成
            String sql = "insert into OrderClient(ClientName,Address)" +
                    "values('" + clientName + "','" + address + "')";
            int rowAffected = statement.executeUpdate(sql);
            if (rowAffected > 0) {
                System.out.println("数据己经成功插入");
            } else {
                System.out.println("插入操作未成功");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    //测试更新数据
    private static void testUpdate() {
        try (Connection connection = DriverManager.getConnection(dbUrl);
             Statement statement = connection.createStatement()
        ) {
            // 更新数据的SQL命令
            String sql = "update OrderClient set ClientName='张三'" +
                    " where ClientName='李四'";
            int rowAffected = statement.executeUpdate(sql);
            if (rowAffected > 0) {
                System.out.println("数据己经成功更新:" + rowAffected);
            } else {
                System.out.println("更新操作未成功");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    //测试删除数据
    private static void testDelete() {
        try (Connection connection = DriverManager.getConnection(dbUrl);
             Statement statement = connection.createStatement()) {
            //删除数据
            String sql = "delete from OrderClient where ClientName like 'client%'";
            int rowAffected = statement.executeUpdate(sql);
            if (rowAffected > 0) {
                System.out.println("数据己经成功删除:" + rowAffected);
            } else {
                System.out.println("没有删除任何一条数据");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    //测试更新数据
    //注意：SQLite只支持TYPE_FORWARD_ONLY类型的cursor
    //因此，以下代码必须使用SQL Server

    private static void testUpdateResultSet() {
        try (Connection connection =
                     DriverManager.getConnection(MsSqlServer, userName, password);
             Statement statement = connection.createStatement(
                     ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);) {
            String sql = "select * from OrderClient";
            var rs = statement.executeQuery(sql);
            rs.last();
            System.out.println("最后一条记录内容：");
            System.out.println(getClientFromResultSet(rs));
            rs.updateString("address",
                    "address updated at " + LocalTime.now());
            rs.updateRow();
            System.out.println("更新完毕，重新提取记录");
            var rs2 = statement.executeQuery(sql);
            rs2.last();
            System.out.println("最后一条记录内容：");
            System.out.println(getClientFromResultSet(rs2));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private static OrderClient getClientFromResultSet(ResultSet rs) throws SQLException {
        var client = new OrderClient();
        client.setClientID(rs.getInt("ClientID"));
        client.setClientName(rs.getString("ClientName"));
        client.setAddress(rs.getString("Address"));
        return client;
    }

    //测试数据的插入
    private static void testInsertBatch() {
        try (Connection connection = DriverManager.getConnection(dbUrl);
             Statement statement = connection.createStatement()) {
            statement.addBatch("insert into OrderClient(ClientName,Address) values('张三','北京')");
            statement.addBatch("insert into OrderClient(ClientName,Address) values('李四','上海')");
            statement.addBatch("insert into OrderClient(ClientName,Address) values('王五','广州')");
            statement.addBatch("insert into OrderClient(ClientName,Address) values('赵六','深圳')");

            var result = statement.executeBatch();
            var arrString = Arrays.stream(result).mapToObj(String::valueOf)
                    .collect(Collectors.joining(","));
            System.out.println(arrString);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

}
