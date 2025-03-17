package model;

public class ClientDTO {
    private int clientId;
    private String name;

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClientDTO(int clientId, String name) {
        this.clientId = clientId;
        this.name = name;
    }

    @Override
    public String toString() {
        return "ClientDTO{" +
                "clientId=" + clientId +
                ", name='" + name + '\'' +
                '}';
    }
}
