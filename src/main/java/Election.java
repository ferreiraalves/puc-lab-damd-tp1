import java.util.ArrayList;

public interface Election extends java.rmi.Remote {

    public ArrayList<String> candidates() throws java.rmi.RemoteException;
    public void vote(String name, String id) throws java.rmi.RemoteException;
    public Integer result(String name) throws java.rmi.RemoteException;

}
