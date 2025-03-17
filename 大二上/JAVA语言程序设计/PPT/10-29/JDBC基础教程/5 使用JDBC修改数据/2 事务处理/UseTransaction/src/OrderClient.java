import java.util.Random;

public class OrderClient {
    private int clientID;
    private String clientName;
    private String address;

    public OrderClient(String clientName, String address) {
        this.clientName = clientName;
        this.address = address;
    }

    public static OrderClient getInstance(){
        int ranValue=new Random().nextInt(100);
        return new OrderClient("client"+ranValue,
                "address of client"+ranValue);

    }

    @Override
    public String toString() {
        return "OrderClient{" +
                "clientName='" + clientName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
