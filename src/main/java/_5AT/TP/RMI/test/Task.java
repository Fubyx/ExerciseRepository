package _5AT.TP.RMI.test;

import java.io.Serializable;

public interface Task extends Serializable {
    public Object doTask(Object arg);
}
