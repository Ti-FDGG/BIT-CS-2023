package com.jinxuliang;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
//       insertCLOB();
//        readFromCLOB();
        insertBLOB();
        readFromBLOB();
    }

    //插入CLOB类型的数据
    private static void insertCLOB() throws SQLException {
        String sql = "insert into CLOB (info,TextContent) values (?,?)";
        try (var connection =
                     ConnectionManager.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             FileReader reader = new FileReader(
                     "./src/com/jinxuliang/Main.java");
        ) {
            statement.setString(1, "Main.java");
            statement.setCharacterStream(2, reader);
            statement.execute();
            System.out.println("插入完成");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //读入CLOB类型的数据
    private static void readFromCLOB() {
        String sql = "select * from CLOB";
        try (var connection =
                     ConnectionManager.getInstance().getConnection();
             Statement statement = connection.createStatement();
        ) {
            var rs = statement.executeQuery(sql);
            while (rs.next()) {
                processResultSet(rs);
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void processResultSet(ResultSet rs)
            throws SQLException, IOException {
        Clob textConent = rs.getClob("TextContent");
        try (Reader reader = textConent.getCharacterStream();
             BufferedReader bufferedReader = new BufferedReader(reader);) {
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            System.out.printf("%d,%s,%s", rs.getInt("Id"),
                    rs.getString("Info"), sb.toString());
        }
    }

    //插入BLOB类型的数据
    private static void insertBLOB() throws SQLException {
        String sql = "insert into BLOB (FileName,MIME,FileData) values (?,?,?)";
        try (var connection =
                     ConnectionManager.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, "群山");
            statement.setString(2, "images/jpeg");
            statement.setBinaryStream(3,
                    Files.newInputStream(Paths.get("./pics/mountain.jpg")));
            statement.execute();
            System.out.println("插入完成");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //读入BLOB类型的数据
    private static void readFromBLOB() {
        String sql = "select * from BLOB";
        try (var connection =
                     ConnectionManager.getInstance().getConnection();
             Statement statement = connection.createStatement();
        ) {
            var rs = statement.executeQuery(sql);
            int fileCount = 0;
            while (rs.next()) {
                processBlobResultSet(rs, fileCount);
                fileCount++;
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void processBlobResultSet(ResultSet rs, int fileCount)
            throws SQLException, IOException {
        Blob fileDataBlob = rs.getBlob("FileData");
        var fileData = fileDataBlob.getBytes(1,
                (int) fileDataBlob.length());
        String writeFileName = "./pics/image" + fileCount + ".jpg";
        Files.write(Paths.get(writeFileName),
                fileData);
        System.out.printf("%d,%s,%s,%s", rs.getInt("Id"),
                rs.getString("FileName"),
                rs.getString("MIME"),
                "写入" + fileData.length + "字节到文件" + writeFileName);
    }
}
