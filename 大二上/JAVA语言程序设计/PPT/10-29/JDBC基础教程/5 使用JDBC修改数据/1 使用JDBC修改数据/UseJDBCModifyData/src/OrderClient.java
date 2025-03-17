public class OrderClient {
    private int clientID;
    private String clientName;
    private String address;

    @Override
    public String toString() {
        return "OrderClient{" +
                "clientID=" + clientID +
                ", clientName='" + clientName + '\'' +
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
