import java.sql.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("使用JDBC查询数据示例");
        System.out.println("\n====测试数据库连接和数据的查询====\n");
        testConnectionAndQuery();
        System.out.println("\n====测试执行带参数的SQL命令====\n");
        testPrepareStatement(2);
    }
    //测试数据库连接和数据的查询
    private static void testConnectionAndQuery() {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:test.db");) {
            // 准备创建操作与存取的数据库命令
            Statement statement = connection.createStatement();
            // 查询表中保存的数据
            ResultSet rs = statement.executeQuery("select * from OrderClient");
            while (rs.next()) {
                // 输出查询到的数据
                System.out.println("------------------");
                System.out.println("ClientID = " + rs.getInt("ClientID"));
                System.out.println("ClientName = " + rs.getString("ClientName"));
                System.out.println("Address=" + rs.getString("Address"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    //测试执行带参数的SQL命令
    private static void testPrepareStatement(int ClientID) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:test.db")) {
            String sql = "select * from OrderClient where ClientID=?";
            // 准备创建操作与存取的数据库命令
            PreparedStatement statement = connection.prepareStatement(sql);
            //设置参数值，第一个参数指的是SQL命令中的参数位置（从1开始，顺序递增），第二个参数是SQL参数的具体值
            statement.setInt(1, ClientID);
            // 查询表中保存的数据
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                // 输出查询到的数据
                System.out.println("------------------");
                System.out.println("ClientID = " + rs.getInt("ClientID"));
                System.out.println("ClientName = " + rs.getString("ClientName"));
                System.out.println("Address=" + rs.getString("Address"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
