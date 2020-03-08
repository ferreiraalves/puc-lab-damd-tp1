import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ElectionServant extends java.rmi.server.UnicastRemoteObject implements Election{

    private Map<String, Integer> candidates = new HashMap<String, Integer>();
    private Map<String, Boolean> voters = new HashMap<String, Boolean>();



    public ElectionServant() throws java.rmi.RemoteException {
        super();
        candidates.put("JOAO DAS COXINHAS", 0);
        candidates.put("DORIVAL DA 51", 0);
        candidates.put("HUGO DAS COMPUTARIAS", 0);
    }

    @Override
    public ArrayList<String> candidates() throws RemoteException {
        ArrayList<String> candidate_names = new ArrayList<String>();
        for (String key : candidates.keySet()){
            candidate_names.add(key);
        }
        return candidate_names;
    }

    @Override
    public void vote(String name, String id) throws RemoteException {
        if (voters.containsKey(name)){
            System.err.println("Duplicate voter");
        }else if (!candidates.containsKey(name)){
            System.err.println("Missing candidate");
        }else{
            candidates.put(name, candidates.get(name) + 1);
        }
        voters.put(name,true);
    }

    @Override
    public Result result(String name) throws RemoteException {
        if (!candidates.containsKey(name)){
            System.err.println("Missing candidate");
            return null;
        }else{
            return new Result(name, candidates.get(name));
        }
    }


}
