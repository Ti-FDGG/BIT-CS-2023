import model.Client;
import model.ClientDTO;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DtoDemo {
    public static void main(String[] args) {
        //创建示例集合
        var clients = Client.createExampleClients(10);
        clients.forEach(System.out::println);

        System.out.println("Client集合转换为ClientDTO集合");
        //定义转换函数
        Function<Client, ClientDTO> toDTO =
                client -> new ClientDTO(client.getId(), client.getName());
        //完成转换工作
        List<ClientDTO> dtos = clients.stream()
                .map(toDTO)
                .collect(Collectors.toList());
        dtos.forEach(System.out::println);
      }
}
