package repository;

import model.OrderClient;

import java.sql.SQLException;
import java.util.List;

//定义与客户相关的CRUD功能
public interface IOrderClientRepository {
	//按照客户姓名进行模糊检索，返回符合条件的OrderClient集合
	List<OrderClient> getClients(String firstName);
	//添加一条客户记录
	int addClient(OrderClient client);
	//按照id查找指定的客户记录
	OrderClient getClient(int id) throws SQLException;
	//更新指定的客户记录
	int updateClient(OrderClient client);
	//删除指定id的客户记录
	int deleteClient(int clientID);
}
