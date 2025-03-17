package model;

//数据实体类
public class OrderClient {
    private int clientID = 0;
    private String clientName = "";
    private String address = "";

    //region getter和setter方法

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

    //endregion

    @Override
    public String toString() {
        return "OrderClient [clientID=" + clientID + ", clientName=" + clientName + ", address=" + address + "]";
    }
}
