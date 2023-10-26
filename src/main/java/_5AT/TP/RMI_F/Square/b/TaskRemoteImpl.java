package _5AT.TP.RMI_F.Square.b;

public class TaskRemoteImpl implements TaskRemote{
    @Override
    public Object doTask(Object obj, Task task) {
        return task.doTask(obj);
    }
}
