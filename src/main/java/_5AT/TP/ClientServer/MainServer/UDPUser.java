package _5AT.TP.ClientServer.MainServer;

public class UDPUser {
    private String userName;
    private String ipAddress;
    private int port;

    public UDPUser(String userName, String ipAddress, int port) {
        setUserName(userName);
        setIpAddress(ipAddress);
        setPort(port);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public boolean equals(Object o){
        UDPUser u = (UDPUser) o;
        if ((u.ipAddress.equals(getIpAddress()) && u.port == getPort()) || u.userName.equals(getUserName())){
            return true;
        }
        return false;
    }
}
