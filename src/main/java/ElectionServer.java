import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ElectionServer {

    public ElectionServer() {
        try {
            Election e = new ElectionServant();
            Naming.rebind("rmi://localhost:8080/ElectionService", e);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

        // Dont forget to rmiregistry
    public static void main(String args[]) throws RemoteException {
        Registry a = LocateRegistry.createRegistry(8080);
        new ElectionServer();
        System.out.println("Election server running.");
    }


}
