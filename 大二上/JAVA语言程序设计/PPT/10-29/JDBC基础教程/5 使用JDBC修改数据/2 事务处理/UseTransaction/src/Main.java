import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    private static final String dbUrl = "jdbc:sqlite:test.db";

    public static void main(String[] args) {
        //testSucceedTransaction();
         testFailedTransaction();
    }

    static void testSucceedTransaction() {
        try (Connection connection = DriverManager.getConnection(dbUrl);
             Statement statement = connection.createStatement();
        ) {
            showCountOfRecord(statement); //显示记录数
            connection.setAutoCommit(false); //指明需手动执行事务
            try {
                deleteAllRecords(statement);  //删除全部数据
                //插入5条记录
                for (int i = 0; i < 5; i++) {
                    insert(OrderClient.getInstance(), statement);
                }
                connection.commit();
                System.out.println("操作结束");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                connection.rollback();
                System.out.println("回滚");
            } finally {
                //显示记录数
                showCountOfRecord(statement);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    static void testFailedTransaction() {
        try (Connection connection = DriverManager.getConnection(dbUrl);
             Statement statement = connection.createStatement();
        ) {
            showCountOfRecord(statement); //显示记录数
            connection.setAutoCommit(false); //指明需手动执行事务
            try {
                //删除全部数据
                deleteAllRecords(statement);
                //插入10条记录
                for (int i = 0; i < 10; i++) {
                    insert(OrderClient.getInstance(), statement);
                    if (i == 5) {
                        throw new RuntimeException("故意引发的异常");
                    }
                }
                connection.commit();
                System.out.println("操作结束");
            }

            catch (Exception e) {
                System.out.println(e.getMessage());
                connection.rollback();
                System.out.println("回滚");
            } finally {
                //显示记录数
                showCountOfRecord(statement);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    //删除所有记录
    private static void deleteAllRecords(Statement statement)
            throws SQLException {
        String sql = "delete from OrderClient";
        int rowAffected = statement.executeUpdate(sql);
        System.out.println("数据删除条数:" + rowAffected);
    }

    //插入一条记录
    private static void insert(OrderClient orderClient, Statement statement)
            throws SQLException {
        String sql = "insert into OrderClient(ClientName,Address) values('"
                + orderClient.getClientName() + "','"
                + orderClient.getAddress() + "')";
        int rowAffected = statement.executeUpdate(sql);
        if (rowAffected > 0) {
            System.out.println("数据己经成功插入:" + orderClient);
        } else {
            System.out.println("插入操作未成功");
        }
    }

    //显示数据库中的记录数
    private static void showCountOfRecord(Statement statement)
            throws SQLException {
        String sql = "select count(*) as count from OrderClient";
        var rs = statement.executeQuery(sql);
        rs.next();
        System.out.println("当前数据库中共有记录条数：" + rs.getInt("count"));
    }
}
