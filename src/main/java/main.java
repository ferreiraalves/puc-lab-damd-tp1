import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class main {
    public static void main(String[] args) {
        try {
            ElectionServant e = new ElectionServant();
            UUID uuid = UUID.randomUUID();
            e.vote("DORIVAL DA 51", uuid.toString());
            e.result("DORIVAL DA 51");
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
}
