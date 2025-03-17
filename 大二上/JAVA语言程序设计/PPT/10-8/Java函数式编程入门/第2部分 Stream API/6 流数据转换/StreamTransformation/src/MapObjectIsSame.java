import model.Client;

import java.util.List;
import java.util.stream.Collectors;

public class MapObjectIsSame {
    public static void main(String[] args) {
        var clients = List.of(new Client(1, "Client1"),
                new Client(2, "Client2"),
                new Client(3, "Client3"));

        var newClients = clients.stream()
                //提取clients中的所有对象，将其名字改为大写
                .map(client -> {
                    client.setName(client.getName().toUpperCase());
                    return client;
                }).collect(Collectors.toList());
        //两个List，其实引用相同的对象
        System.out.println(newClients.get(0) == clients.get(0));//true
        //以下两句输出，完全一样
        System.out.println(clients);
        System.out.println(newClients);
    }
}
