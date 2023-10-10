package _5AT.TP.ClientServer.MainServer;

public class User {
    private String userName;
    private String password;
    private String privileges;
    private boolean valid;
    public User(String userName, String password, String privileges){
        this.valid = true;
        setUserName(userName);
        setPassword(password);
        setPrivileges(privileges);
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        if (password.length() < 3){
            valid = false;
            return;
        }
        this.password = password;
    }
    public String getPrivileges() {
        return privileges;
    }
    public void setPrivileges(String privileges) {
        if (!privileges.equals("admin") && !privileges.equals("user")){
            valid = false;
            return;
        }
        this.privileges = privileges;
    }
    public boolean isValid (){
        return valid;
    }
    @Override
    public boolean equals (Object o){
        return (o instanceof User) && ((User)o).userName.equals(this.userName);
    }

}
