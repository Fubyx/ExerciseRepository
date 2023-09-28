package _5AT.TP.Executor.Client_Server;

import java.util.Objects;

public class Userinformation {
    private String userName;
    private String password;
    private String privileges;

    public Userinformation(String userName, String password, String privileges) {
        this.setUserName(userName);
        this.setPassword(password);
        this.setPrivileges(privileges);
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
        this.password = password;
    }

    public String getPrivileges() {
        return privileges;
    }

    public void setPrivileges(String privileges) {
        if(!privileges.equals("admin"))
            this.privileges = "user";
        else
            this.privileges = privileges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Userinformation that = (Userinformation) o;
        return Objects.equals(userName, that.userName);
    }

}
