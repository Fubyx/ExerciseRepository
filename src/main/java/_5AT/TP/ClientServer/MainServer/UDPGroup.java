package _5AT.TP.ClientServer.MainServer;

import java.util.ArrayList;

public class UDPGroup {
    private ArrayList<UDPUser> users;
    private String ipAddress;
    private String name;
    public UDPGroup(String name, String ipAddress, UDPUser firstUser){
        users = new ArrayList<>();
        users.add(firstUser);
        this.name = name;
        this.ipAddress = ipAddress;
    }

    @Override
    public boolean equals (Object o){
        UDPGroup u = (UDPGroup) o;
        return u.name.equals(name) || u.ipAddress.equals(ipAddress);
    }
    @Override
    public String toString (){
        StringBuilder result = new StringBuilder("Name: " + name + "\n" + "IP: " + ipAddress + "\n" + "Users:\n");
        for (UDPUser user : users) {
            result.append("\t").append(user.getUserName()).append("\n");
        }
        return result.toString();
    }
    public void removeUser (UDPUser u){
        users.remove(u);
    }
    public int userCount (){
        return users.size();
    }
    public void join (UDPUser user){
        users.add(user);
    }
}
