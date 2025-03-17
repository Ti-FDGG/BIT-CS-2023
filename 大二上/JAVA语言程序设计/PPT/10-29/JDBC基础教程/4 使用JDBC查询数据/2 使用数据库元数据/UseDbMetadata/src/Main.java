import java.sql.*;
import java.util.ArrayList;

public class Main {
    //引用本机SQL Server数据库服务器，并打开MyDB数据库
    private final static String MsSqlServer =
            "jdbc:sqlserver://localhost:1433;database=MyDB;";
    private final static String userName = "test";
    private final static String password = "abcdefg";

    public static void main(String[] args) throws SQLException {
        showDbMetadata();
        showTableMetadata();
        showResultSetMetadata();
    }

    //查询数据库元数据
    private static void showDbMetadata() throws SQLException {
        try (var connection =
                     DriverManager.getConnection(MsSqlServer, userName, password)) {
            var metaData = connection.getMetaData();
            System.out.println("数据库产品名称：" + metaData.getDatabaseProductName());
            System.out.println("数据库版本号：" + metaData.getDatabaseProductVersion());
            System.out.println("数据库驱动名：" + metaData.getDriverName());
            System.out.println("数据库驱动版本：" + metaData.getDriverVersion());
            System.out.println("当前登录用户：" + metaData.getUserName());
        }
    }

    //查询数据库表元数据
    private static void showTableMetadata() throws SQLException {
        try (var connection =
                     DriverManager.getConnection(MsSqlServer, userName, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            String[] tableTypes = {"TABLE"};
            ArrayList<String> tables = new ArrayList<>();
            //获取所有表名
            ResultSet rsTable = metaData.getTables(null, "%",
                    "%", tableTypes);
            while (rsTable.next()) {
                tables.add(rsTable.getString("TABLE_NAME"));
            }
            for (String tableName : tables) {
                //显示表中的字段信息
                showTableColumnInfo(metaData, tableName);
            }
        }
    }
    //显示指定表的字段信息
    private static void showTableColumnInfo(DatabaseMetaData metaData,
                                            String tableName) throws SQLException {
        System.out.println("\n===表：" + tableName + "===");
        //获取表中的所有字段
        ResultSet rs = metaData.getColumns(null, "%",
                tableName, "%");

        while (rs.next()) {
            StringBuffer buffer = new StringBuffer();
            buffer.append(rs.getString("COLUMN_NAME"));
            buffer.append(": ");
            buffer.append(rs.getString("TYPE_NAME"));
            System.out.println(buffer.toString());
        }
    }

    //查询结果集元数据
    private static void showResultSetMetadata() throws SQLException {
        try (var connection =
                     DriverManager.getConnection(MsSqlServer, userName, password);
             var statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("select * from OrderClient");
        ) {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            String format = "%-15s%-15s%-15s%-15s\n";
            System.out.format(format, "字段名", "字段类型", "是否可空", "是否是自增字段");
            for (int i = 1; i <= columnCount; i++) {
                System.out.format(format, metaData.getColumnName(i),
                        metaData.getColumnType(i),
                        metaData.isNullable(i),
                        metaData.isAutoIncrement(i));
            }
            System.out.println("\n字段总数：" + columnCount);
        }
    }

}
