import java.net.Inet4Address;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.List;

public class NetworkInterfaceInfo {
    public static void main(String[] args) throws SocketException {
        //列出本机所有的网络接口
        listAllNetworkInterface();
        System.out.println("\n------------------\n");
        //显示特定网卡的详细信息
        printNetworkInfo(NetworkInterface.getByName("eth7"));
    }

    private static void listAllNetworkInterface() throws SocketException {
        var networks = NetworkInterface.getNetworkInterfaces();
        while (networks.hasMoreElements()) {
            NetworkInterface network = networks.nextElement();
            System.out.println("接口名(短名）：" + network.getName());
            System.out.println("接口名(长名）：" + network.getDisplayName());
            var addresses = network.getInetAddresses();
            while (addresses.hasMoreElements()) {
                var address = addresses.nextElement();
                if (address instanceof Inet4Address) {
                    System.out.printf("%-6s %-15s\n", " IPv4:", address);
                } else {
                    System.out.printf("%-6s %-20s\n", " IPv6:", address);
                }
            }
            System.out.println();
        }
    }

    static void printNetworkInfo(NetworkInterface networkInterface) throws SocketException {
        System.out.println("NIC name: " + networkInterface.getName());
        System.out.println("NIC display name: " + networkInterface.getDisplayName());
        System.out.println("NIC get harware address (MAC) " + convertByteToString(networkInterface.getHardwareAddress()));
        System.out.println("MTU: " + networkInterface.getMTU());
        System.out.println("Index: " + networkInterface.getIndex());
        NetworkInterface parentInterface = networkInterface.getParent();
        if (parentInterface != null) {
            System.out.println("Parent interface: " + parentInterface.getDisplayName());
        } else {
            System.out.println("No parent interface!");
        }
        System.out.println("Is loopback?  " + networkInterface.isLoopback());
        System.out.println("Is up " + networkInterface.isUp());
        System.out.println("Is virtual " + networkInterface.isVirtual());
        System.out.println("Support multicast?  " + networkInterface.supportsMulticast());
        //Enumeration<InetAddress> nifAddresses = networkInterface.getInetAddresses();
        List<InterfaceAddress> list = networkInterface.getInterfaceAddresses();
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Ip address: " + (list.get(i)).getAddress().getHostAddress());
        }
        System.out.println("");
        System.out.println("__________________________________________________");
        System.out.println("");
    }

    private static String convertByteToString(byte[] mac) {
        if (mac == null)
            return null;

        StringBuilder sb = new StringBuilder(18);
        for (byte b : mac) {
            if (sb.length() > 0)
                sb.append(':');
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

}
