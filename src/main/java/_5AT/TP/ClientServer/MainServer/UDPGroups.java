package _5AT.TP.ClientServer.MainServer;

import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class UDPGroups {
    private ArrayList<UDPGroup> groups;
    private int[] nextFreeIP = new int[4];
    private boolean ipOverflow = false;
    public UDPGroups (){
        this.groups = new ArrayList<>();
        nextFreeIP[0] = 224;
        nextFreeIP[1] = 0;
        nextFreeIP[2] = 0;
        nextFreeIP[3] = 1;
    }
    public synchronized String getGroupList (){
        StringBuilder result = new StringBuilder();
        for (UDPGroup u:groups) {
            result.append(u.toString()).append("\n---\n");
        }
        return result.toString();
    }
    public synchronized void removeUser (UDPUser u){
        ArrayList<UDPGroup> toRemove = new ArrayList<>();
        for (UDPGroup g:groups){
            g.removeUser(u);
            if (g.userCount() <= 1){
                toRemove.add(g);
            }
        }
        for (UDPGroup g: toRemove){
            groups.remove(g);
        }
    }
    public synchronized void joinGroup(UDPUser u, String name){
        UDPGroup g = new UDPGroup(name, "", null);
        if (groups.contains(g)){
            g = groups.get(groups.indexOf(g));
            g.join(u);
        }else{
            g = new UDPGroup(name, nextFreeIP[0] + "." + nextFreeIP[1] + "." + nextFreeIP[2] + "." + nextFreeIP[3], u);
            groups.add(g);
            incNextFreeIP();
        }
    }
    private void incNextFreeIP(){
        if (nextFreeIP[3] == 254){
            if (nextFreeIP[2] == 254){
                if (nextFreeIP[1] == 254){
                    if (nextFreeIP[0] == 239){
                        ipOverflow = true;
                        nextFreeIP[0] = 224;
                        nextFreeIP[1] = 0;
                        nextFreeIP[2] = 0;
                        nextFreeIP[3] = 1;
                    }else{
                        ++nextFreeIP[0];
                        nextFreeIP[1] = 0;
                    }
                }else{
                    ++nextFreeIP[1];
                    nextFreeIP[2] = 0;
                }
            }else{
                ++nextFreeIP[2];
                nextFreeIP[3] = 1;
            }
        }else{
            ++nextFreeIP[3];
        }
        if (ipOverflow){
            if (groups.contains(new UDPGroup("", nextFreeIP[0] + "." + nextFreeIP[1] + "." + nextFreeIP[2] + "." + nextFreeIP[3], null))){
                incNextFreeIP();
            }
        }
    }
}
