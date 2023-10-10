package _5AT.TP.ClientServer.MainServer;

import java.util.ArrayList;

public class Users {
    private final ArrayList<User> users;
    public Users (){
        users = new ArrayList<>();
    }
    public synchronized User addUser (String userName, String password, String privileges){
        User u = new User(userName, password, privileges);
        if (u.isValid() && !users.contains(new User(userName, "", ""))){
            users.add(u);
            return u;
        }
        return null;
    }
    /**
     * Checks if a user with the given information exists.
     * @param userName The UserName of the user.
     * @param password The password of the user;
     * @return The privilege level of the user or "" if the given information is incorrect.
     */
    public synchronized String checkInformation (String userName, String password){
        User u = new User(userName, "", "");
        int i = users.indexOf(u);
        if (i != -1 && users.get(i).getPassword().equals(password)){
            return users.get(i).getPrivileges();
        }
        return "";
    }
    public synchronized void removeUser (User user){
        users.remove(user);
    }
}
