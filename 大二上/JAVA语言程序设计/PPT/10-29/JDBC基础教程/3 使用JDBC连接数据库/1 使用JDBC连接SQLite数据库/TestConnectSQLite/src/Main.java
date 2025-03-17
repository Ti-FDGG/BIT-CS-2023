import java.sql.*;

public class Main {
    public static void main(String[] args) {
        testConnection();
        //testConnectionSimple();
    }

    private static void testConnection() {
        Connection connection = null;
        try {
            // 创建数据库连接
            connection = DriverManager.getConnection(
                    "jdbc:sqlite:./dbs/test.db ");
            // 准备创建操作与存取的数据库命令
            Statement statement = connection.createStatement();
            // 查询表中保存的数据
            ResultSet rs = statement.executeQuery("select * from test");
            while (rs.next()) {
                // 输出查询到的数据
                System.out.println("------------------");
                System.out.println("id = " + rs.getInt("id"));
                System.out.println("value = " + rs.getString("value"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    private static void testConnectionSimple() {
        String dbUrl = "jdbc:sqlite:./dbs/test.db";
        //使用try-with-resources简化JDBC连接数据库代码
        try (Connection connection = DriverManager.getConnection(dbUrl);
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("select * from test");
        ) {
            while (rs.next()) {
                // 输出查询到的数据
                System.out.println("------------------");
                System.out.println("id = " + rs.getInt("id"));
                System.out.println("value = " + rs.getString("value"));
            }
        } catch (SQLException e) {
            System.err.println("数据库连接失败：" + e.getMessage());
        }
    }
}
