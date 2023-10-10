package _5AT.TP.ClientServer.MainServer;

import java.util.ArrayList;

public class UDPUsers {
    private final ArrayList<UDPUser> users;
    public UDPUsers (){
        users = new ArrayList<>();
    }
    public synchronized UDPUser addUser (String userName, String ipAddress, int port){
        UDPUser u = new UDPUser(userName, ipAddress, port);
        if(!users.contains(u)){
            users.add(u);
            return u;
        }
        return null;
    }
    public synchronized String getUserList (String userName){
        String text = "";
        for (UDPUser u:users){
            if (!u.getUserName().equals(userName)){
                text = text + u.getUserName() + " " + u.getIpAddress() + " " + u.getPort() + "\n";
            }
        }
        return text;
    }
    public synchronized void removeUser (String userName){
        users.remove(new UDPUser(userName, "", 0));
    }

}

