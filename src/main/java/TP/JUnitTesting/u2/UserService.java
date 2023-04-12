package TP.JUnitTesting.u2;

/*
    Schreibe fuer die gegbene JUnit-Test-Klasse eine Klasse StringUtils
    welche die Bedingungen fuer die Tests erfuellt und ohne Fehler durchlaeuft.
*/


import java.util.ArrayList;

public class UserService {
  ArrayList<User> users = new ArrayList<>();
  long currentId = 0;

  public UserService () {
    users.add(new User(1l, "admin", "password123"));
  }

  boolean logout (long id){
    for(User i: users){
      if(i.id == id){
        users.remove(i);
        return true;
      }
    }
    return false;
  }

  boolean resetPassword(long id){
    for(User i: users){
      if(i.id == id){
        i.password = "";
        return true;
      }
    }
    return false;
  }

  boolean login (String name, String password){
    if(password == null || name == null || password.equals("") || name.equals("")){
      throw new IllegalArgumentException("Username and password must not be null or empty");
    }
    //users.add(new User(currentId, name, password));
    for(User i: users){
      if(i.name.equals(name)){
        return i.password.equals(password);
      }
    }
    return false;
  }

  boolean changePassword(long id, String oldPw, String newPw){
    if(oldPw.equals(newPw)){
      return false;
    }
    for(User i: users){
      if(i.id == id){
        if(i.password.equals(oldPw)){
          i.password = newPw;
          return true;
        }else{
          return false;
        }
      }
    }
    return false;
  }
}
