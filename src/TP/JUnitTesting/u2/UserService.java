package TP.JUnitTesting.u2;

/*
    Schreibe fuer die gegbene JUnit-Test-Klasse eine Klasse StringUtils
    welche die Bedingungen fuer die Tests erfuellt und ohne Fehler durchlaeuft.
*/


import java.util.ArrayList;

public class UserService {
  ArrayList<User> users = new ArrayList<>();
  long currentId = 0;

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
    //users.add(new User(currentId, name, password));
    for(User i: users){
      if(i.name.equals(name)){
        return i.password.equals(password);
      }
    }
    return false;
  }

  boolean changePassword(long id, String oldPw, String newPw){
    return false;
  }

}
