package com.jinxuliang.javafxusejdbc.repo;

import com.jinxuliang.javafxusejdbc.entity.OrderClient;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//Repository设计模式，封装CRUD功能
public class OrderClientRepository {
    //这里将数据库放到C:\database文件夹下
    private static String connectionStr = "jdbc:sqlite:C:\\database\\client.db";

    //提取所有记录
    public List<OrderClient> getAllClients() {
        var clients = new ArrayList<OrderClient>();
        String sql = "select * from OrderClient";
        try (var conn = DriverManager.getConnection(connectionStr);
             var statement = conn.createStatement();
             var rs = statement.executeQuery(sql);
        ) {
            OrderClient client = null;
            while (rs.next()) {
                var id = rs.getInt("id");
                var name = rs.getString("name");
                var address = rs.getString("address");
                client = new OrderClient(id, name, address);
                clients.add(client);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clients;
    }

    //插入或更新记录
    public  void insertOrUpdateClients(OrderClient client) {
        String sql = "";
        boolean isAddNew = true;
        if (client.getId() > 0) {
            //修改
            isAddNew = false;
            sql = String.format("update OrderClient set name='%s',address='%s' where id=%d",
                    client.getName(), client.getAddress(), client.getId());
        } else {
            //新增
            sql = String.format("insert into OrderClient(name,address) values ('%s','%s')",
                    client.getName(), client.getAddress());
        }
        try (var conn = DriverManager.getConnection(connectionStr);
             var statement = conn.createStatement();
        ) {
            statement.execute(sql);
            if (isAddNew) {
                sql = "select last_insert_rowid() as newId from OrderClient";
                var rs = statement.executeQuery(sql);
                if (rs.next()) {
                    var newId = rs.getInt("newId");
                    client.setId(newId);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //删除记录
    public void deleteClient(int clientId) {
        String sql = String.format("delete from OrderClient where id=%d", clientId);
        try (var conn = DriverManager.getConnection(connectionStr);
             var statement = conn.createStatement();
        ) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
